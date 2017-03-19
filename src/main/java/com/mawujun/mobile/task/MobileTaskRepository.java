package com.mawujun.mobile.task;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.mawujun.task.HitchTypeVO;
import com.mawujun.task.TaskVO;
import com.mawujun.utils.page.Pager;
/**
 * @author mawujun qq:16064988 e-mail:16064988@qq.com 
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface MobileTaskRepository {
	
	
	public List<TaskesNum> queryTasknum(@Param("user_id")String user_id);
	
	public Pager<TaskVO> queryTaskes(Pager<TaskVO> params);
	public Pager<TaskVO> searchTaskes(Pager<TaskVO> params);
	
	public MobileTaskVO getMobileTaskVO(@Param("task_id")String id);
	public List<Equiplist> getMobileTaskVO_equiplist(@Param("task_id")String task_id);
	public List<Members> getMobileTaskVO_members(@Param("task_id")String task_id);
	
	public List<HitchTypeVO> queryAllHitchtype();
	
	public Equiplist scanEquip_info(@Param("ecode")String ecode);
	
	/**
	 * 查询作业单位和拥有的人
	 * @author mawujun qq:16064988 mawujun1234@163.com
	 * @return
	 */
	public List<Workunit> queryMembers(@Param("user_id")String user_id,@Param("task_id")String task_id);
}
