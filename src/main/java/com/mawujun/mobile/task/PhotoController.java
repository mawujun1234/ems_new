package com.mawujun.mobile.task;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mawujun.baseinfo.IPhoto;
import com.mawujun.baseinfo.PolePhoto;
import com.mawujun.baseinfo.PolePhotoService;
import com.mawujun.controller.spring.mvc.ResultModel;
import com.mawujun.repository.cnd.Cnd;
import com.mawujun.task.TaskPhoto;
import com.mawujun.task.TaskPhotoService;
import com.mawujun.utils.M;
/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Controller
//@RequestMapping("/polePhoto")
public class PhotoController {

	@Resource
	private PolePhotoService polePhotoService;
	@Resource
	private TaskPhotoService taskPhotoService;


	@RequestMapping("/mobile/photo/query.do")
	@ResponseBody
	public List<? extends IPhoto> query(String pole_id,String task_id,String type) {	
		
		if("pole".equals(type)){
			List<PolePhoto> polePhotoes=polePhotoService.queryByPole(pole_id);
			return polePhotoes;
		} else if("task".equals(type)){
			List<TaskPhoto> polePhotoes=taskPhotoService.queryByTask(task_id);
			return polePhotoes;
		}
		return new ArrayList<PolePhoto>();
	}
	@RequestMapping("/mobile/photo/upload.do")
	@ResponseBody
	public ResultModel upload(HttpServletRequest request,@RequestParam("file") CommonsMultipartFile file,String pole_id,String id,String task_id,String type) {
		String realpath=request.getServletContext().getRealPath("/");
		try {
			if("pole".equals(type)){
				polePhotoService.upload(realpath, file, pole_id,id);
			} else if("task".equals(type)){
				taskPhotoService.upload(realpath, file, task_id,id);
			}
			
		} catch(Exception e){
			return ResultModel.getInstance().setSuccess(false).setMsg(e.getMessage());
		}
		
		return ResultModel.getInstance();
	}
	
	
	@RequestMapping("/mobile/photo/deleteById.do")
	@ResponseBody
	public String deleteById(HttpServletRequest request,String id,String type) {
		String realpath=request.getServletContext().getRealPath("/");
		if("pole".equals(type)){
			//polePhotoService.deleteBatch(Cnd.delete().andEquals(M.PolePhoto.id, id));
			polePhotoService.deleteById(id, realpath);
		} else if("task".equals(type)){
			taskPhotoService.deleteById(id, realpath);
		}
		
		return id;
	}

	
	
}
