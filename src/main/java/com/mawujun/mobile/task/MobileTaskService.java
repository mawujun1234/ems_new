package com.mawujun.mobile.task;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mawujun.permission.ShiroUtils;
import com.mawujun.repository.cnd.Cnd;
import com.mawujun.task.HitchTypeVO;
import com.mawujun.task.TaskService;
import com.mawujun.task.TaskStatus;
import com.mawujun.task.TaskType;
import com.mawujun.task.TaskVO;
import com.mawujun.utils.M;
import com.mawujun.utils.page.Pager;

@Service
@Transactional(propagation=Propagation.REQUIRED)
public class MobileTaskService {
	@Autowired
	private MobileTaskRepository mobileTaskRepository;
	@Autowired
	private TaskService taskService;


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
	public List<Map<String,Object>> queryTasknum(String user_id){
		return mobileTaskRepository.queryTasknum(user_id);
	}
	
	public Pager<TaskVO> queryTaskes(TaskType type,TaskStatus status){
		Pager<TaskVO> params=new Pager<TaskVO>();
		params.addParam(M.Task.type, type.toString());
		params.addParam(M.Task.status, status.toString());
		params.addParam("user_id", ShiroUtils.getUserId());
		return mobileTaskRepository.queryTaskes(params);
	}
	
	public MobileTaskVO getMobileTaskVO(String task_id) {
		MobileTaskVO mobileTaskVO= mobileTaskRepository.getMobileTaskVO(task_id);
		
		List<Equiplist> equiplist=mobileTaskRepository.getMobileTaskVO_equiplist(task_id);
		mobileTaskVO.setEquiplist(equiplist);
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

}
