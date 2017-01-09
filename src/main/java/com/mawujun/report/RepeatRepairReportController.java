package com.mawujun.report;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mawujun.org.OrgService;
import com.mawujun.permission.UserService;

@Controller
public class RepeatRepairReportController {
	@Resource
	private RepairReportRepository repairReportRepository;
//	@Resource
//	private StoreService storeService;
	@Resource
	private UserService userService;
	@Resource
	private OrgService orgService;
	
	@RequestMapping("/report/repair/queryRepeatRepairReport.do")
	@ResponseBody
	public List<RepeatRepairReport> queryRepeatRepairReport(String date_start,String date_end,Integer repeatnum) {
		List<RepeatRepairReport> list= repairReportRepository.queryRepeatRepairReport(date_start,date_end,repeatnum);
		for(RepeatRepairReport repeat:list){
			repeat.setStr_out_name(orgService.get(repeat.getStr_out_id()).getName());
			if(repeat.getRpa_user_id()!=null){
				repeat.setRpa_user_name(userService.get(repeat.getRpa_user_id()).getName());
			}
			
		}
		return list;
	}
	
	@RequestMapping("/report/repair/exportRepeatRepairReport.do")
	@ResponseBody
	public void exportRepeatRepairReport(String date_start,String date_end,Integer repeatnum) {
		List<RepeatRepairReport> list=queryRepeatRepairReport(date_start,date_end,repeatnum);
	}
	
	
}
