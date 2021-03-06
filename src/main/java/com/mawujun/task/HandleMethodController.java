package com.mawujun.task;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mawujun.meta.MetaVersionService;
/**
 * @author mawujun qq:16064988 e-mail:16064988@qq.com 
 * @version 1.0
 * @since 1.0
 */
@Controller
//@RequestMapping("/handleMethod")
public class HandleMethodController {

	@Resource
	private HandleMethodService handleMethodService;
	@Resource
	private MetaVersionService metaVersionService;




	@RequestMapping("/handleMethod/query.do")
	@ResponseBody
	public List<HandleMethod> query() {	
		List<HandleMethod> handleMethodes=handleMethodService.queryAll();
		return handleMethodes;
	}
	
	@RequestMapping("/handleMethod/mobile/query.do")
	@ResponseBody
	public List<HandleMethod> mobile_query(Integer version,String hitchType_id) {	
		List<HandleMethod> handleMethodes=handleMethodService.queryAll();
		return handleMethodes;
	}
	

	@RequestMapping("/handleMethod/load.do")
	public HandleMethod load(String id) {
		return handleMethodService.get(id);
	}
	
	@RequestMapping("/handleMethod/create.do")
	@ResponseBody
	public HandleMethod create(@RequestBody HandleMethod handleMethod) {
		handleMethodService.create(handleMethod);
		metaVersionService.update(HandleMethod.class);
		return handleMethod;
	}
	
	@RequestMapping("/handleMethod/update.do")
	@ResponseBody
	public  HandleMethod update(@RequestBody HandleMethod handleMethod) {
		handleMethodService.update(handleMethod);
		metaVersionService.update(HandleMethod.class);
		return handleMethod;
	}
//	
//	@RequestMapping("/handleMethod/deleteById.do")
//	@ResponseBody
//	public String deleteById(String id) {
//		handleMethodService.deleteById(id);
//		return id;
//	}
	
	@RequestMapping("/handleMethod/destroy.do")
	@ResponseBody
	public HandleMethod destroy(@RequestBody HandleMethod handleMethod) {
		
		handleMethodService.delete(handleMethod);
		metaVersionService.update(HandleMethod.class);
		return handleMethod;
	}
	
	
}
