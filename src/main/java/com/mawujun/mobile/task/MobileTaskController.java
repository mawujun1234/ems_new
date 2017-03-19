package com.mawujun.mobile.task;

import java.util.List;

import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mawujun.baseinfo.EquipmentVO;
import com.mawujun.controller.spring.mvc.ResultModel;
import com.mawujun.exception.BusinessException;
import com.mawujun.permission.ShiroUtils;
import com.mawujun.report.EquipmentStatusController;
import com.mawujun.task.HandleMethodService;
import com.mawujun.task.TaskStatus;
import com.mawujun.task.TaskType;
import com.mawujun.task.TaskVO;
import com.mawujun.utils.M;
import com.mawujun.utils.page.Pager;

@Controller
public class MobileTaskController {
	@Autowired
	private MobileTaskService mobileTaskService;
	@Autowired
	private EquipmentStatusController equipmentStatusController;
	@Autowired
	private HandleMethodService handleMethodService;
//	@Autowired
//	private TaskService taskService;
	/**
	 * 初始化个任务的数量
	 * @return
	 */
	@RequestMapping("/mobile/task/init_taskes_num.do")
	@ResponseBody
	public ResultModel init_taskes_num(){
//		Map<String,Object> root=new HashMap<String,Object>();
		List<TaskesNum> tasknum=mobileTaskService.queryTasknum(ShiroUtils.getUserId());
//		for(Map<String,Object> map:tasknum){
//			Map<String,Object> num=new HashMap<String,Object>();
//			//num.put("total_num",map.get("TOTAL_NUM") );
//			num.put("newTask_num", map.get("NEWTASK_NUM"));
//			num.put("read_num", map.get("READ_NUM"));
//			num.put("handling_num", map.get("HANDLING_NUM"));
//			num.put("submited_num", map.get("SUBMITED_NUM"));
//			root.put("task_"+map.get("TYPE"), tasknum);
//		}
		
		return ResultModel.getInstance().setRoot(tasknum);
	}
	/**
	 * 从具体的类型的任务进去的时候
	 * @author mawujun qq:16064988 mawujun1234@163.com
	 * @param type
	 * @param status
	 * @return
	 */
	@RequestMapping("/mobile/task/queryTaskes.do")
	@ResponseBody
	public Pager<TaskVO> queryTaskes(TaskType type,TaskStatus status,Integer start,Integer limit){
		Pager<TaskVO> params=new Pager<TaskVO>();
		params.addParam(M.Task.type, type.toString());
		params.addParam(M.Task.status, status.toString());
		params.addParam("user_id", ShiroUtils.getUserId());
		params.setStart(start);
		params.setLimit(limit);
		Pager<TaskVO> pager=mobileTaskService.queryTaskes(params);
		return pager;
	}
	
	@RequestMapping("/mobile/task/searchTaskes.do")
	@ResponseBody
	public Pager<TaskVO> searchTaskes(String name,String create_date,TaskType type,Integer start,Integer limit){
		if(!StringUtils.hasText(name) && !StringUtils.hasText(create_date)){
			throw new BusinessException("名称和日期必须有一个有值!");
		}
		Pager<TaskVO> params=new Pager<TaskVO>();
		params.addParam("name", name);
		params.addParam(M.Task.createDate, create_date);
		if(type!=null){
			params.addParam(M.Task.type, type.toString());
		}
		
		params.addParam("user_id", ShiroUtils.getUserId());
		params.setStart(start);
		params.setLimit(limit);
		Pager<TaskVO> pager=mobileTaskService.searchTaskes(params);
		return pager;
	}
	
	@RequestMapping("/mobile/task/getMobileTaskVO.do")
	@ResponseBody
	public ResultModel getMobileTaskVO(String id){
		MobileTaskVO vo=mobileTaskService.getMobileTaskVO(id);
		
		return ResultModel.getInstance().setRoot(vo);
	}
	/**
	 * 获取设备信息,当点开一个设备编号，查看明细的时候
	 * @param ecode
	 * @return
	 */
	@RequestMapping("/mobile/task/getEquip_info.do")
	@ResponseBody
	public ResultModel getEquipInfo(String ecode){
		EquipmentVO vo= equipmentStatusController.query(ecode);
		return ResultModel.getInstance().setRoot(vo);
	}
	/**
	 * 扫描的时候
	 * @author mawujun qq:16064988 mawujun1234@163.com
	 * @param ecode 
	 * @return
	 */
	@RequestMapping("/mobile/task/scanEquip_info.do")
	@ResponseBody
	public ResultModel scanEquip_info(String ecode,String task_id){
		Equiplist equiplist=mobileTaskService.scanEquip_info(ecode,task_id);
		return ResultModel.getInstance().setRoot(equiplist);
	}
	
	@RequestMapping("/mobile/task/delete_equip_info.do")
	@ResponseBody
	public String delete_equip_info(String ecode,String task_id){
		mobileTaskService.delete_equip_info(ecode,task_id);
		return "{\"success\":true}";
	}
	 
	
	@RequestMapping("/mobile/task/queryAllHitchtype.do")
	@ResponseBody
	public ResultModel queryAllHitchtype(){
		//EquipmentVO vo= equipmentStatusController.query(ecode);
		return ResultModel.getInstance().setRoot(mobileTaskService.queryAllHitchtype());
	}

	@RequestMapping("/mobile/task/updateTaskHitchtype.do")
	@ResponseBody
	public String updateTaskHitchtype(String id,String hitchType_id,String hitchReasonTpl_id,String hitchReason){
		mobileTaskService.updateTaskHitchtype(id, hitchType_id, hitchReasonTpl_id, hitchReason);
		return "{\"success\":true}";
	}
	
	@RequestMapping("/mobile/task/queryHandleMethodes.do")
	@ResponseBody
	public ResultModel queryHandleMethodes(){
		
		return ResultModel.getInstance().setRoot(handleMethodService.queryAll());
	}
	
	@RequestMapping("/mobile/task/updateHandleMethod.do")
	@ResponseBody
	public String updateHandleMethod(String id,String handleMethod_id){
		mobileTaskService.updateHandleMethod(id, handleMethod_id);
		return "{\"success\":true}";
	}
	
	@RequestMapping("/mobile/task/updateHitchReason.do")
	@ResponseBody
	public String updateHitchReason(String id,String hitchReason){
		mobileTaskService.updateHitchReason(id, hitchReason);
		return "{\"success\":true}";
	}
	
	@RequestMapping("/mobile/task/updateHandleContact.do")
	@ResponseBody
	public String updateHandleContact(String id,String handle_contact){
		mobileTaskService.updateHandleContact(id, handle_contact);
		return "{\"success\":true}";
	}
	
	
	@RequestMapping("/mobile/task/queryMembers.do")
	@ResponseBody
	public List<Workunit> queryMembers(String task_id) {
		return mobileTaskService.queryMembers(task_id);
		
	}
	
	@RequestMapping("/mobile/task/selectMember.do")
	@ResponseBody
	public ResultModel selectMember(String task_id,String user_id) {
		mobileTaskService.selectMember(task_id, user_id);
		return ResultModel.getInstance();
		
	}
	@RequestMapping("/mobile/task/deleteMember.do")
	@ResponseBody
	public ResultModel deleteMember(String task_id,String user_id) {
		mobileTaskService.deleteMember(task_id, user_id);
		return ResultModel.getInstance();
		
	}
	

}
