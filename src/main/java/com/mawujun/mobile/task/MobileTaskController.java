package com.mawujun.mobile.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mawujun.baseinfo.EquipmentVO;
import com.mawujun.controller.spring.mvc.ResultModel;
import com.mawujun.report.EquipmentStatusController;
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

}
