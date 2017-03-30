package com.mawujun.baseinfo;
import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mawujun.repository.cnd.Cnd;
import com.mawujun.utils.M;


import com.mawujun.baseinfo.PolePhoto;
import com.mawujun.baseinfo.PolePhotoService;
/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Controller
//@RequestMapping("/polePhoto")
public class PolePhotoController {

	@Resource
	private PolePhotoService polePhotoService;


//	/**
//	 * 这是基于分页的几种写法,的例子，请按自己的需求修改
//	 * @author mawujun email:16064988@163.com qq:16064988
//	 * @param start
//	 * @param limit
//	 * @param userName
//	 * @return
//	 */
//	@RequestMapping("/polePhoto/queryPager.do")
//	@ResponseBody
//	public Pager<PolePhoto> queryPager(Pager<PolePhoto> pager){
//		
//		return polePhotoService.queryPage(pager);
//	}
//
//	@RequestMapping("/polePhoto/queryAll.do")
//	@ResponseBody
//	public List<PolePhoto> queryAll() {	
//		List<PolePhoto> polePhotoes=polePhotoService.queryAll();
//		return polePhotoes;
//	}
//	
//
//	@RequestMapping("/polePhoto/load.do")
//	public PolePhoto load(String id) {
//		return polePhotoService.get(id);
//	}
//	
	@RequestMapping("/polePhoto/create.do")
	@ResponseBody
	public PolePhoto create(@RequestBody PolePhoto polePhoto) {
		polePhotoService.create(polePhoto);
		return polePhoto;
	}
	
	@RequestMapping("/polePhoto/update.do")
	@ResponseBody
	public  PolePhoto update(@RequestBody PolePhoto polePhoto) {
		polePhotoService.update(polePhoto);
		return polePhoto;
	}
	
//	@RequestMapping("/polePhoto/deleteById.do")
//	@ResponseBody
//	public String deleteById(String id) {
//		polePhotoService.deleteById(id);
//		return id;
//	}
	
	@RequestMapping("/polePhoto/destroy.do")
	@ResponseBody
	public PolePhoto destroy(@RequestBody PolePhoto polePhoto) {
		polePhotoService.delete(polePhoto);
		return polePhoto;
	}
	
	
}
