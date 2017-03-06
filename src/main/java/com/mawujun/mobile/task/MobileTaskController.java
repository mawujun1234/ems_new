package com.mawujun.mobile.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mawujun.baseinfo.EquipmentVO;
import com.mawujun.controller.spring.mvc.ResultModel;
import com.mawujun.report.EquipmentStatusController;
import com.mawujun.task.HandleMethodService;
import com.mawujun.task.TaskStatus;
import com.mawujun.task.TaskType;
import com.mawujun.task.TaskVO;
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
	
	@RequestMapping("/mobile/task/queryTaskes.do")
	@ResponseBody
	public Pager<TaskVO> queryTaskes(TaskType type,TaskStatus status){
		Pager<TaskVO> pager=mobileTaskService.queryTaskes(type, status);
		return pager;
	}
	
	@RequestMapping("/mobile/task/getMobileTaskVO.do")
	@ResponseBody
	public ResultModel getMobileTaskVO(String id){
		MobileTaskVO vo=mobileTaskService.getMobileTaskVO(id);
		
		return ResultModel.getInstance().setRoot(vo);
	}
	/**
	 * 获取设备信息
	 * @param ecode
	 * @return
	 */
	@RequestMapping("/mobile/task/getEquip_info.do")
	@ResponseBody
	public ResultModel getEquipInfo(String ecode){
		EquipmentVO vo= equipmentStatusController.query(ecode);
		return ResultModel.getInstance().setRoot(vo);
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
}
