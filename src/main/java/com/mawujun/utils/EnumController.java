package com.mawujun.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mawujun.org.Dim;
import com.mawujun.org.OrgType;

@Controller
public class EnumController {
	/**
	 * 查询拥有的维度
	 * @return
	 */
	@RequestMapping("/enum/queryDimes.do")
	@ResponseBody
	public List<Map<String,String>> queryDimes() {
		Dim[] dimes=Dim.values();
		List<Map<String,String>> result=new ArrayList<Map<String,String>>();
		for(Dim dim:dimes){
			Map<String,String> map=new HashMap<String,String>();
			map.put("key", dim.toString());
			map.put("name", dim.getName());
			result.add(map);
			
		}
		return result;
	}
	
	@RequestMapping("/enum/queryOrgTypes.do")
	@ResponseBody
	public List<Map<String,String>> queryOrgTypes() {
		OrgType[] dimes=OrgType.values();
		List<Map<String,String>> result=new ArrayList<Map<String,String>>();
		for(OrgType dim:dimes){
			Map<String,String> map=new HashMap<String,String>();
			map.put("key", dim.toString());
			map.put("name", dim.getName());
			result.add(map);
		}
		return result;
	}
	
	@RequestMapping("/enum/queryStates.do")
	@ResponseBody
	public List<Map<String,String>> queryStates() {
		State[] dimes=State.values();
		List<Map<String,String>> result=new ArrayList<Map<String,String>>();
		for(State dim:dimes){
			Map<String,String> map=new HashMap<String,String>();
			map.put("key", dim.toString());
			map.put("name", dim.getName());
			result.add(map);
		}
		return result;
	}

}
