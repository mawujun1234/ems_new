package com.mawujun.task;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.mawujun.baseinfo.Pole;
import com.mawujun.baseinfo.PoleVO;
import com.mawujun.repository.IRepository;
import com.mawujun.utils.page.Pager;
/**
 * @author mawujun qq:16064988 e-mail:16064988@qq.com 
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface TaskRepository extends IRepository<Task, String>{
	public int query_count_tasklist_by_task(@Param("task_id")String task_id);
	
	public String queryMax_id(@Param("createDate")String createDate);
	public Pager<Pole> queryPoles(Pager<Pole> page);
	public Pager<Pole> queryPoles_no_send_task(Pager<Pole> page);
	public int count_task_quip_status(@Param("task_id")String task_id);
	
	public List<TaskEquipmentListVO> queryTaskEquipmentListVO(@Param("task_id")String task_id);
	/**
	 * 返回已经入库的条码
	 * @author mawujun 16064988@qq.com 
	 * @param task_id
	 * @param ecodes
	 * @return
	 */
	//public List<String> count_task_quip_status1(@Param("task_id")String task_id,@Param("ecodes")List<String> ecodes);
	//public List<String> query_task_equip_list(@Param("task_id")String task_id);
	/**
	 * 查询其他扫描过这个设备的任务列表
	 * @author mawujun email:160649888@163.com qq:16064988
	 * @param workunit_id
	 * @param ecode
	 * @return
	 */
	public List<String> query_other_task_have_scaned(@Param("workunit_id")String workunit_id,@Param("ecode")String ecode);
	
	//public Page queryRepairTaskesReport(Page page);
	
	/**
	 * 根据条码查询最新的任务信息
	 * @author mawujun email:160649888@163.com qq:16064988
	 * @param page
	 * @return
	 */
	public Task queryMaxId_ecode(@Param("ecode")String ecode);
	
	//public Pager<Task> mobile_queryPage(Pager<Task> page) ;
	
	//public Pager<Task> mobile_search(Pager<Task> page);
	
	//public List<TaskEquipmentListVO> mobile_queryTaskEquipmentInfos(@Param("task_id")String task_id);
	
	public List<Task> queryReadOvertimeTask(@Param("read")Integer read) ;
	public List<Task> queryHandlingOvertimeTask(@Param("handling")Integer handling) ;
	
	
	//public List<Pole> mobile_queryPoles(@Param("pole_name")String pole_name,@Param("workunit_id")String workunit_id);
	
	public Pager<Task> querySubmited(Pager<Task> page);
	
	public void update_to_handling_status(@Param("task_id")String task_id);
	
	public int check_handleMethod_used(@Param("handleMethod_id")String handleMethod_id);
	
	public int count_repair_task_by_pole_id(@Param("pole_id")String pole_id);
	
	public int count_patrolTaskType_id(@Param("patrolTaskType_id")String patrolTaskType_id);
	
	public void initTaskMember(@Param("task_id")String task_id);
}
