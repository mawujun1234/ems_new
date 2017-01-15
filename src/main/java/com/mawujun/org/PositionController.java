package com.mawujun.org;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mawujun.permission.User;
import com.mawujun.repository.cnd.Cnd;
import com.mawujun.utils.M;
/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Controller
//@RequestMapping("/position")
public class PositionController {

	@Resource
	private PositionService positionService;

	/**
	 * 请按自己的需求修改
	 * @author mawujun email:16064988@163.com qq:16064988
	 * @param id 是父节点的id
	 * @return
	 */
	@RequestMapping("/position/query.do")
	@ResponseBody
	public List<Position> query(String orgno) {
		Cnd cnd=Cnd.select().andEquals(M.Position.org_id, orgno);
		List<Position> positiones=positionService.query(cnd);
		return positiones;
	}


	@RequestMapping("/position/queryAll.do")
	@ResponseBody
	public List<Position> queryAll() {	
		List<Position> positiones=positionService.queryAll();
		return positiones;
	}
	

	@RequestMapping("/position/load.do")
	@ResponseBody
	public Position load(String id) {
		return positionService.get(id);
	}
	
	@RequestMapping("/position/create.do")
	//@ResponseBody
	public Position create(@RequestBody Position position) {
		
		positionService.create(position,Dim.base);
		return position;
	}
	
	@RequestMapping("/position/update.do")
	@ResponseBody
	public  Position update(@RequestBody Position position) {
		positionService.update(position,Dim.base);
		return position;
	}
	
	@RequestMapping("/position/deleteById.do")
	@ResponseBody
	public String deleteById(String id) {
		positionService.deleteById(id);
		return id;
	}
	
	@RequestMapping("/position/destroy.do")
	@ResponseBody
	public Position destroy(@RequestBody Position position) {
		positionService.delete(position);
		return position;
	}
	/**
	 * 查询这个角色可以看到的仓库或作业单位
	 * @param position_id
	 * @return
	 */
	@RequestMapping("/position/querySelectOrgs.do")
	@ResponseBody
	public List<PositionOrgAccessVO> querySelectOrgs(String position_id,String orgType) {
		
		return positionService.querySelectOrgs(position_id,orgType);
	}
	/**
	 * 
	 * @param vo
	 * @return
	 */
	@RequestMapping("/position/selectOrg.do")
	@ResponseBody
	public String selectOrg(PositionOrgAccessVO vo) {
		positionService.selectStore(vo);
		return "{success:true}";
	}
	
	@RequestMapping("/position/deselectOrg.do")
	@ResponseBody
	public String deselectOrg(PositionOrgAccessVO vo) {
		positionService.deselectStore(vo);
		return "{success:true}";
	}
	
	/**
	 * 获取指定维修中心下的操作员名单
	 * 反向获取可以访问当前组织节点的用户
	 * @author mawujun 16064988@qq.com 
	 * @param repairs
	 * @param str_in_id
	 * @return
	 */
	@RequestMapping("/position/queryUsersByOrg.do")
	@ResponseBody
	public List<User> queryUsersByOrg(String org_id,Boolean look,Boolean edit){
		
		return positionService.queryUsersByOrg(org_id,look,edit);
	}
}
