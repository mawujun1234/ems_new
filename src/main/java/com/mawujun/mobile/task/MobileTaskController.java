package com.mawujun.mobile.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mawujun.task.TaskStatus;
import com.mawujun.task.TaskType;
import com.mawujun.task.TaskVO;
import com.mawujun.utils.page.Pager;

@Controller
public class MobileTaskController {
	@Autowired
	private MobileTaskService mobileTaskService;
	
	@RequestMapping("/mobile/task/queryTaskes.do")
	@ResponseBody
	public Pager<TaskVO> queryTaskes(TaskType type,TaskStatus status){
		Pager<TaskVO> pager=mobileTaskService.queryTaskes(type, status);
		return pager;
	}

}