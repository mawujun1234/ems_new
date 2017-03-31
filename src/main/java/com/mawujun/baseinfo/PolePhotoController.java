package com.mawujun.baseinfo;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mawujun.controller.spring.mvc.ResultModel;
import com.mawujun.repository.cnd.Cnd;
import com.mawujun.utils.M;
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
	@RequestMapping("/polePhoto/queryByPole.do")
	@ResponseBody
	public List<PolePhoto> queryByPole(String pole_id) {	
		List<PolePhoto> polePhotoes=polePhotoService.queryByPole(pole_id);
		return polePhotoes;
	}
	@RequestMapping("/polePhoto/upload.do")
	@ResponseBody
	public ResultModel upload(HttpServletRequest request,@RequestParam("file") CommonsMultipartFile file,String pole_id,String id) {
		String realpath=request.getServletContext().getRealPath("/");
		try {
			polePhotoService.upload(realpath, file, pole_id,id);
		} catch(Exception e){
			return ResultModel.getInstance().setSuccess(false).setMsg(e.getMessage());
		}
		
		return ResultModel.getInstance();
	}
	
//	@RequestMapping("/polePhoto/update.do")
//	@ResponseBody
//	public  PolePhoto update(@RequestBody PolePhoto polePhoto) {
//		polePhotoService.update(polePhoto);
//		return polePhoto;
//	}
	
	@RequestMapping("/polePhoto/deleteById.do")
	@ResponseBody
	public String deleteById(String id) {
		polePhotoService.deleteBatch(Cnd.delete().andEquals(M.PolePhoto.id, id));
		return id;
	}
	
//	@RequestMapping("/polePhoto/destroy.do")
//	@ResponseBody
//	public PolePhoto destroy(@RequestBody PolePhoto polePhoto) {
//		polePhotoService.delete(polePhoto);
//		return polePhoto;
//	}
	
	
}
