package com.mawujun.task;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mawujun.meta.MetaVersion;
import com.mawujun.meta.MetaVersionService;
/**
 * @author mawujun qq:16064988 e-mail:16064988@qq.com 
 * @version 1.0
 * @since 1.0
 */
@Controller
//@RequestMapping("/hitchType")
public class HitchTypeController {

	@Resource
	private HitchTypeService hitchTypeService;
	@Resource
	private MetaVersionService metaVersionService;

	@Resource
	private HandleMethodService handleMethodService;

//	/**
//	 * 请按自己的需求修改
//	 * @author mawujun email:16064988@163.com qq:16064988
//	 * @param id 是父节点的id
//	 * @return
//	 */
//	@RequestMapping("/hitchType/query.do")
//	@ResponseBody
//	public List<HitchType> query(String id) {
//		Cnd cnd=Cnd.select().andEquals(M.HitchType.parent.id, "root".equals(id)?null:id);
//		List<HitchType> hitchTypees=hitchTypeService.query(cnd);
//		//JsonConfigHolder.setFilterPropertys(HitchType.class,M.HitchType.parent.name());
//		return hitchTypees;
//	}
//
//	/**
//	 * 这是基于分页的几种写法,的例子，请按自己的需求修改
//	 * @author mawujun email:16064988@163.com qq:16064988
//	 * @param start
//	 * @param limit
//	 * @param userName
//	 * @return
//	 */
//	@RequestMapping("/hitchType/query.do")
//	@ResponseBody
//	public Page query(Integer start,Integer limit,String sampleName){
//		Page page=Page.getInstance(start,limit);//.addParam(M.HitchType.sampleName, "%"+sampleName+"%");
//		return hitchTypeService.queryPage(page);
//	}

	@RequestMapping("/hitchType/query.do")
	@ResponseBody
	public List<HitchType> query() {	
		List<HitchType> hitchTypees=hitchTypeService.queryAll();
		return hitchTypees;
	}
	
	@RequestMapping("/hitchType/mobile/query.do")
	@ResponseBody
	public List<HitchType> mobile_query() {	
		List<HitchType> hitchTypees=hitchTypeService.queryAll();
		
		MetaVersion metaVersion=metaVersionService.get(HitchType.class.getSimpleName());
		//2017.1.8   注视掉的，这个还是要加进去的
		//JsonConfigHolder.addProperty("version", metaVersion==null?0:metaVersion.getVersion());
		return hitchTypees;
	}
	

	@RequestMapping("/hitchType/load.do")
	public HitchType load(Integer id) {
		return hitchTypeService.get(id);
	}
	
	@RequestMapping("/hitchType/create.do")
	@ResponseBody
	public HitchType create(@RequestBody HitchType hitchType) {
		hitchTypeService.create(hitchType);
		metaVersionService.update(HitchType.class);
		return hitchType;
	}
	
	@RequestMapping("/hitchType/update.do")
	@ResponseBody
	public  HitchType update(@RequestBody HitchType hitchType) {
		hitchTypeService.update(hitchType);
		metaVersionService.update(HitchType.class);
		return hitchType;
	}
	
	@RequestMapping("/hitchType/deleteById.do")
	@ResponseBody
	public Integer deleteById(Integer id) {
		hitchTypeService.deleteById(id);
		metaVersionService.update(HitchType.class);
		return id;
	}
	
	/**
	 * 同时更新故障类型和原因模板
	 * @author mawujun 16064988@qq.com 
	 * @param version
	 * @return
	 */
	@RequestMapping("/hitchType/mobile/queryAll.do")
	@ResponseBody
	public Map<String,Object> mobile_queryAll(Integer hitchType_version,Integer handleMethod_version,Integer hitchReasonTpl_version) {
		Map<String,Object> map=new HashMap<String,Object>();
		
		MetaVersion metaVersion=metaVersionService.get(HitchType.class.getSimpleName());
		if(metaVersion!=null && metaVersion.getVersion()!=hitchType_version){
			List<HitchType> hitchTypes=hitchTypeService.queryAll();
			map.put("hitchTypes", hitchTypes);
			map.put("hitchType_version", metaVersion.getVersion());
		}
		
		metaVersion=metaVersionService.get( HandleMethod.class.getSimpleName());
		if(metaVersion!=null && metaVersion.getVersion()!=hitchType_version){
			List<HandleMethod> handleMethodes=handleMethodService.queryAll();
			map.put("HandleMethod", handleMethodes);
			map.put("HandleMethod_version", metaVersion.getVersion());
		}
		
//		metaVersion=metaVersionService.get(HitchReasonTpl.class.getSimpleName());
//		if(metaVersion!=null && metaVersion.getVersion()!=hitchReasonTpl_version){
//			List<HitchReasonTpl> hitchReasonTples=hitchReasonTplService.queryAll();
//			map.put("hitchReasonTpls", hitchReasonTples);
//			map.put("hitchReasonTpl_version", metaVersion.getVersion());
//		}	
		
		return map;
	}
	
//	@RequestMapping("/hitchType/destroy.do")
//	@ResponseBody
//	public HitchType destroy(@RequestBody HitchType hitchType) {
//		hitchTypeService.delete(hitchType);
//		return hitchType;
//	}
	
	
}
