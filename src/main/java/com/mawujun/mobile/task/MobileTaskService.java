package com.mawujun.mobile.task;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mawujun.baseinfo.EquipmentRepository;
import com.mawujun.baseinfo.EquipmentWorkunit;
import com.mawujun.baseinfo.EquipmentWorkunitPK;
import com.mawujun.baseinfo.EquipmentWorkunitRepository;
import com.mawujun.baseinfo.EquipmentWorkunitType;
import com.mawujun.exception.BusinessException;
import com.mawujun.permission.ShiroUtils;
import com.mawujun.repository.cnd.Cnd;
import com.mawujun.task.EquipmentInstalloutType;
import com.mawujun.task.HitchTypeVO;
import com.mawujun.task.LockEquipmentService;
import com.mawujun.task.Task;
import com.mawujun.task.TaskEquipmentList;
import com.mawujun.task.TaskEquipmentListRepository;
import com.mawujun.task.TaskListTypeEnum;
import com.mawujun.task.TaskMember;
import com.mawujun.task.TaskMemberRepository;
import com.mawujun.task.TaskRepository;
import com.mawujun.task.TaskService;
import com.mawujun.task.TaskStatus;
import com.mawujun.task.TaskType;
import com.mawujun.task.TaskVO;
import com.mawujun.utils.M;
import com.mawujun.utils.page.Pager;

@Service
@Transactional(propagation=Propagation.REQUIRED)
public class MobileTaskService {
	static Logger logger=LogManager.getLogger(TaskService.class);
	
	@Autowired
	private MobileTaskRepository mobileTaskRepository;
	@Autowired
	private TaskService taskService;
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private TaskEquipmentListRepository taskEquipmentListRepository;
	
	@Autowired
	private LockEquipmentService lockEquipmentService;
	@Autowired
	private EquipmentRepository equipmentRepository;
	@Autowired
	private EquipmentWorkunitRepository equipmentWorkunitRepository;
	@Autowired
	private TaskMemberRepository taskMemberRepository;
//	@Autowired
//	private OrgRepository orgRepository;


	//@Override
	public MobileTaskRepository getRepository() {
		// TODO Auto-generated method stub
		return mobileTaskRepository;
	}

	/**
	 * 查询作业单位身上挂着的作业数
	 * @author mawujun qq:16064988 mawujun1234@163.com
	 * @param user_id
	 * @return
	 */
	public List<TaskesNum> queryTasknum(String user_id){
		return mobileTaskRepository.queryTasknum(user_id);
	}
	
	public Pager<TaskVO> queryTaskes( Pager<TaskVO> params){
		
		return mobileTaskRepository.queryTaskes(params);
	}
	
	public Pager<TaskVO> searchTaskes( Pager<TaskVO> params) {
		
		return mobileTaskRepository.searchTaskes(params);
	}
	
	public MobileTaskVO getMobileTaskVO(String task_id) {
		MobileTaskVO mobileTaskVO= mobileTaskRepository.getMobileTaskVO(task_id);
		
		List<Equiplist> equiplist=mobileTaskRepository.getMobileTaskVO_equiplist(task_id);
		if(equiplist!=null){
			mobileTaskVO.setEquiplist(equiplist);
		}
		
		
		List<Members> members=mobileTaskRepository.getMobileTaskVO_members(task_id);
		if(members!=null){
			String temp="";
			for(Members member:members){
				//当有一个人属于两个作业单位的时候，就只取其中一个
				if(!temp.equals(member.getId())){
					mobileTaskVO.addMember(member);
					temp=member.getId();
				}
			}
		}
		
		
		return mobileTaskVO;
	}
	

	public List<HitchTypeVO> queryAllHitchtype(){
		return mobileTaskRepository.queryAllHitchtype();
	}
	
	public void updateTaskHitchtype(String id,String hitchType_id,String hitchReasonTpl_id,String hitchReason) {
		taskService.update(Cnd.update().set(M.Task.hitchType_id, hitchType_id)
				.set(M.Task.hitchReasonTpl_id, hitchReasonTpl_id)
				.set(M.Task.hitchReason, hitchReason)
				.andEquals(M.Task.id, id));
		return;
	}
	
	public void updateHandleMethod(String id,String handleMethod_id) {
		taskService.update(Cnd.update().set(M.Task.handleMethod_id, handleMethod_id).andEquals(M.Task.id, id));
	}
	
	public void updateHitchReason(String id,String hitchReason){
		taskService.update(Cnd.update().set(M.Task.hitchReason, hitchReason).andEquals(M.Task.id, id));
	}
	
	public void updateHandleContact(String id,String handle_contact){
		taskService.update(Cnd.update().set(M.Task.handle_contact, handle_contact).andEquals(M.Task.id, id));
	}
	/**
	 * 扫描设备
	 * @author mawujun qq:16064988 mawujun1234@163.com
	 * @param ecode
	 * @param task_id
	 * @return
	 */
	public Equiplist scanEquip_info(String ecode,String task_id){
		Task task = taskService.get(task_id);
		TaskType task_type = task.getType();
		String pole_id = task.getPole_id();
		// 判断设备是否被不是这个任务的设备锁定了
		lockEquipmentService.check_locked(ecode, task_id);

		// 判断设备是否在该作业单位手上
		TaskListTypeEnum taskListTypeEnum = TaskListTypeEnum.install;
		EquipmentInstalloutType installoutType = EquipmentInstalloutType.other;
		if (task_type == TaskType.cancel) {
			int count = equipmentRepository.check_in_pole_by_ecode(ecode, pole_id);
			// 判断是不是作业单位上的设备
			if (count == 0) {
				throw new BusinessException(ecode + "不在这个点位上!");
			}
			taskListTypeEnum = TaskListTypeEnum.uninstall;
		} else if (task_type == TaskType.patrol) {
			int count = equipmentRepository.check_in_pole_by_ecode(ecode, pole_id);
			// 判断是不是作业单位上的设备
			if (count == 0) {
				throw new BusinessException(ecode + "不在这个点位上!");
			}
			taskListTypeEnum = TaskListTypeEnum.patrol;
		} else if (task_type == TaskType.newInstall) {

			//EquipmentWorkunitPK id = new EquipmentWorkunitPK(ecode, ShiroUtils.getUserId());
			EquipmentWorkunitPK id = new EquipmentWorkunitPK(ecode, task.getWorkunit_id());
			EquipmentWorkunit equipmentWorkunit = equipmentWorkunitRepository.get(id);
			// 判断是不是作业单位上的设备
			if (equipmentWorkunit == null) {
				throw new BusinessException(ecode + "不在这个作业单位上!");
			}
			taskListTypeEnum = TaskListTypeEnum.install;
			if (equipmentWorkunit.getType() == EquipmentWorkunitType.borrow) {
				installoutType = EquipmentInstalloutType.borrow;
			}
			if (equipmentWorkunit.getType() == EquipmentWorkunitType.installout) {
				installoutType = EquipmentInstalloutType.installout;
			}
		} else if (task_type == TaskType.repair) {
			//EquipmentWorkunitPK id = new EquipmentWorkunitPK(ecode, ShiroUtils.getUserId());
			EquipmentWorkunitPK id = new EquipmentWorkunitPK(ecode, task.getWorkunit_id());
			EquipmentWorkunit equipmentWorkunit = equipmentWorkunitRepository.get(id);

			if (equipmentWorkunit != null) {
				taskListTypeEnum = TaskListTypeEnum.install;
			}
			// 如果是维修任务,并且不在作业单位上，那要判断是不是在点位上
			if (equipmentWorkunit == null) {
				logger.info("这里可能出现为null的情况:ecode={},,pole_id={},task_id={}", ecode, pole_id, task_id);
				int count = equipmentRepository.check_in_pole_by_ecode(ecode, pole_id);
				if (count == 0) {
					throw new BusinessException(ecode + "不在该作业单位或点位上!");
				} else {
					// 如果是维修任务，并且这个设备在点位上，那就是卸载这个设备
					taskListTypeEnum = TaskListTypeEnum.uninstall;
				}
			} else {
				if (equipmentWorkunit.getType() == EquipmentWorkunitType.borrow) {
					installoutType = EquipmentInstalloutType.borrow;
				}
				if (equipmentWorkunit.getType() == EquipmentWorkunitType.installout) {
					installoutType = EquipmentInstalloutType.installout;
				}
			}

		}


		// 更新设备状态为处理中
		taskRepository.update_to_handling_status(task_id);

		TaskEquipmentList list = new TaskEquipmentList();
		list.setEcode(ecode);
		// list.setEquipment_status(equipmentVO.getStatus());
		list.setTask_id(task_id);
		list.setType(taskListTypeEnum);
		list.setInstalloutType(installoutType);

		list.setScanDate(new Date());
		taskEquipmentListRepository.create(list);

		// 对设备进行锁定
		lockEquipmentService.lockByTask(ecode, task_id);
		// 更新equipment的curr_task_id
		equipmentRepository.update(Cnd.update().set(M.Equipment.currt_task_id, task_id).andEquals(M.Equipment.ecode, ecode));

		// //获取某个品名的相关信息
		// EquipmentProdVO
		// equipmentProd=equipmentProdService.getEquipmentProdVO(equipmentProdService.splitEcode(ecode));
		//
		// TaskEquipmentListVO vo=new TaskEquipmentListVO();
		// BeanUtils.copyProperties(list, vo);
		// vo.setSubtype_name(equipmentProd.getSubtype_name());
		// vo.setProd_style(equipmentProd.getStyle());
		// //vo.setSupplier_name(equipmentVO.getSupplier_name());
		// vo.setProd_name(equipmentProd.getName());
		// vo.setBrand_name(equipmentProd.getBrand_name());

		Equiplist equiplist = mobileTaskRepository.scanEquip_info(ecode);

		return equiplist;
	}

	public void delete_equip_info(String ecode,String task_id) {
//		// 先判断，如果该设备已经入库了，那该设备就不能删除
//		Equipment equipment = equipmentRepository.getEquipmentInfo(ecode);
//
//		// 无论是安装，维修，取消，还是巡检，只要是设备不在杆位上或者不在作业单位手上，就不能从任务中删除了
//		if (equipment.getStatus() != EquipmentStatus.using && equipment.getStatus() != EquipmentStatus.out_storage) {
//			throw new BusinessException("该设备已经入库,不能删除!");
//		}
		
		// 无论是安装，维修，取消，还是巡检，只要是设备不在杆位上或者不在作业单位手上，就不能从任务中删除了
		
		//从人物上删除这个设备
		taskEquipmentListRepository.deleteBatch(Cnd.delete().andEquals(M.TaskEquipmentList.task_id, task_id).andEquals(M.TaskEquipmentList.ecode, ecode));
		// 更新equipment的curr_task_id
		equipmentRepository.update(Cnd.update().set(M.Equipment.currt_task_id, null).andEquals(M.Equipment.ecode, ecode));
		//解除度这个设备的锁定
		lockEquipmentService.unlock(ecode);
	}
	
	public List<Workunit> queryMembers(String task_id) {
		return mobileTaskRepository.queryMembers(ShiroUtils.getUserId(),task_id);
	}
	
	public void selectMember(String task_id,String user_id) {
		TaskMember member=new TaskMember();
		member.setTask_id(task_id);
		member.setUser_id(user_id);
		taskMemberRepository.create(member);
	}
	public void deleteMember(String task_id,String user_id) {
		TaskMember.PK pk=new TaskMember.PK();
		pk.setTask_id(task_id);
		pk.setUser_id(user_id);
		taskMemberRepository.deleteById(pk);
	}
	

}
