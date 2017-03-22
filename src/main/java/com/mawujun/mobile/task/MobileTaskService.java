package com.mawujun.mobile.task;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mawujun.baseinfo.EquipmentRepository;
import com.mawujun.baseinfo.EquipmentWorkunit;
import com.mawujun.baseinfo.EquipmentWorkunitPK;
import com.mawujun.baseinfo.EquipmentWorkunitRepository;
import com.mawujun.baseinfo.EquipmentWorkunitType;
import com.mawujun.baseinfo.PoleRepository;
import com.mawujun.controller.spring.mvc.ResultModel;
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
	@Autowired
	private PoleRepository poleRepository;
	



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
		
		//如果任务状态为 新任务，则把任务状态改成已阅
		if(mobileTaskVO.getStatus()==TaskStatus.newTask){
			taskRepository.update(Cnd.update().set(M.Task.status, TaskStatus.read.toString())
					.andEquals(M.Task.id, task_id));
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


		// 更新设备状态为处理中,只记录设备第一次扫描的时间，也就是设备还是处在新任务或者已阅状态的时候
		//同时更新startHandDate时间
		if(task.getStatus()==TaskStatus.newTask || task.getStatus()==TaskStatus.read){
			taskRepository.update_to_handling_status(task_id);
		}
		
		

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
		equiplist.setInstall_type(taskListTypeEnum);

		return equiplist;
	}
	
	public void update_install_type(String ecode,String task_id,String install_type){
		taskEquipmentListRepository.update(Cnd.update().set(M.TaskEquipmentList.type, TaskListTypeEnum.valueOf(install_type))
				.andEquals(M.TaskEquipmentList.task_id, task_id)
				.andEquals(M.TaskEquipmentList.ecode, ecode));
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
	
	public void submit(String task_id) {
		Task task=taskRepository.get(task_id);
		if(task.getStatus()==TaskStatus.submited || task.getStatus()==TaskStatus.complete){
			throw new BusinessException("任务已经提交,不能再提交!");
		}
		
		//判断是否扫描过设备，任何任务只要是没有烧苗过设备就不准提交
		int scan_count=taskRepository.query_count_tasklist_by_task(task_id);
		if(scan_count==0){
			throw new BusinessException("请先扫描设备，然后再提交！");
		}
		

		//如果是取消杆位，提交前进行判断，扫描了的设备数量和杆位上实际具有的数量是否一致
		//是否是该杆位上的设备，已经在扫描的时候就判断了
		if(TaskType.cancel==task.getType()){
			//查找杆位上的数量
			int pole_eqips=poleRepository.query_count_equipment_in_pole(task.getPole_id());
			
			//获取这个任务扫描的设备数量
			if(pole_eqips!=scan_count){
				throw new BusinessException("该杆位上实际的设备数量为:"+pole_eqips+",但现在只扫描了"+scan_count+"!");
			}
			//再添加一个比较，如果
		} else if(TaskType.repair==task.getType()){
			//故障类型，故障原因，故障处理方法等必须填写不然不能提交
			if(task.getHitchType_id()==null){
				throw new BusinessException("故障类型必须填写！");
			}
			if(task.getHitchReasonTpl_id()==null ||task.getHitchReason()==null){
				throw new BusinessException("故障原因必须填写！");
			}
			if(task.getHandleMethod_id()==null){
				throw new BusinessException("故障处理方法必须填写！");
			}
			
//			//如果没有设备扫描过，就提出报警，必须扫描一个才可以
//			int scan_count=taskRepository.query_count_tasklist_by_task(task_id);
//			if(scan_count==0){
//				throw new BusinessException("请扫描一个设备再提交！");
//			}
			
		}
		
		
		//更新任务状态
		task.setStatus( TaskStatus.submited);
		task.setSubmitDate(new Date());
		taskRepository.update(task);
	}
	
	
	
	
	
	public List<WkTypenum> queryType_num(){
		return mobileTaskRepository.queryType_num(ShiroUtils.getUserId());
	}
	public List<Typenum> querySubtype_num(String workunit_id,String type_id){
		return mobileTaskRepository.querySubtype_num( workunit_id, type_id,ShiroUtils.getUserId());
	}

}
