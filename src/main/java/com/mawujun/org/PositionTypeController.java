package com.mawujun.org;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mawujun.exception.BusinessException;
import com.mawujun.repository.cnd.Cnd;
import com.mawujun.utils.M;
/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Controller
//@RequestMapping("/positionType")
public class PositionTypeController {

	@Resource
	private PositionTypeService positionTypeService;
	@Resource
	private PositionService positionService;


//	/**
//	 * 这是基于分页的几种写法,的例子，请按自己的需求修改
//	 * @author mawujun email:16064988@163.com qq:16064988
//	 * @param start
//	 * @param limit
//	 * @param userName
//	 * @return
//	 */
//	@RequestMapping("/positionType/query.do")
//	@ResponseBody
//	public Pager<PositionType> query(Pager<PositionType> pager){
//		return positionTypeService.queryPage(pager);
//	}

	@RequestMapping("/positionType/query.do")
	@ResponseBody
	public List<PositionType> queryAll() {	
		List<PositionType> positionTypees=positionTypeService.queryAll();
		return positionTypees;
	}
	

	@RequestMapping("/positionType/load.do")
	public PositionType load(String id) {
		return positionTypeService.get(id);
	}
	
	@RequestMapping("/positionType/create.do")
	//@ResponseBody
	public PositionType create(@RequestBody PositionType positionType) {
		positionTypeService.create(positionType);
		return positionType;
	}
	
	@RequestMapping("/positionType/update.do")
	//@ResponseBody
	public  PositionType update(@RequestBody PositionType positionType) {
		positionTypeService.update(positionType);
		return positionType;
	}
	
//	@RequestMapping("/positionType/deleteById.do")
//	//@ResponseBody
//	public String deleteById(String id) {
//		positionTypeService.deleteById(id);
//		return id;
//	}
	
	@RequestMapping("/positionType/destroy.do")
	//@ResponseBody
	public PositionType destroy(@RequestBody PositionType positionType) {
		//判断有没有被职位引用
		int count=positionService.queryCount(Cnd.count().andEquals(M.Position.positionType_id, positionType.getId()));
		if(count>0){
			throw new BusinessException("该职位类型已经被引用，不能删除!");
		}
		positionTypeService.delete(positionType);
		return positionType;
	}
	
	
}
