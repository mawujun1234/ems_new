package com.mawujun.mobile.task;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mawujun.baseinfo.EquipmentVO;
import com.mawujun.permission.ShiroUtils;
import com.mawujun.task.HitchTypeVO;
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

}
