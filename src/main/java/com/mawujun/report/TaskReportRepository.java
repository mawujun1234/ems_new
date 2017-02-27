package com.mawujun.report;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.mawujun.task.TaskVO;
import com.mawujun.utils.page.Pager;
import com.mawujun.utils.page.Params;

@Repository
public interface TaskReportRepository {
	public List<TaskVO> exportUnrepairPoleReport(Params params);
	public Pager<TaskVO> queryUnrepairPoleReport(Pager<TaskVO> page);
	
	public Pager<TaskRepairReport> queryTaskRepairReport(Pager<TaskRepairReport> page);
	public List<TaskRepairReport> queryTaskRepairReport(@Param("date_start")String date_start,@Param("date_end")String date_end
			,@Param("pole_code")String pole_code,@Param("hitchType_id")String hitchType_id,@Param("user_id")String user_id);
}
