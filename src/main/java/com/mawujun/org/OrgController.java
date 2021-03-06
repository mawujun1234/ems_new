package com.mawujun.org;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mawujun.permission.ShiroUtils;
import com.mawujun.repository.cnd.Cnd;
import com.mawujun.utils.M;
/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Controller
//@RequestMapping("/org")
public class OrgController {

	@Resource
	private OrgService orgService;


	/**
	 * 读取组织节点树
	 * @author mawujun email:16064988@163.com qq:16064988
	 * @param id 是父节点的id
	 * @return
	 */
	@RequestMapping("/org/queryNodeVO.do")
	@ResponseBody
	public List<NodeVO> queryNodeVO(String parent_id,Dim dim) {
		if(dim==null){
			dim=Dim.base;
		}

		//Cnd cnd=Cnd.select().andEquals(M.OrgOrg, "root".equals(parent_id)?null:parent_id);
		List<NodeVO> orges=orgService.queryNodeVO(parent_id,dim);
		return orges;
	}
	/**
	 * 
	 * @param parent_id
	 * @param dim
	 * @param exclude_id 这个id的组织及其自节点不会被查询，因为父节点都不显示了，子节点就显示不出来了
	 * @return
	 */
	@RequestMapping("/org/queryOnlyOrg.do")
	@ResponseBody
	public List<NodeVO> queryOnlyOrg(String parent_id,Dim dim,String exclude_id) {
		if(dim==null){
			dim=Dim.base;
		}

		//Cnd cnd=Cnd.select().andEquals(M.OrgOrg, "root".equals(parent_id)?null:parent_id);
		List<NodeVO> orges=orgService.queryOnlyOrg(parent_id,dim,exclude_id);
		return orges;
	}
	/**
	 * 添加了权限的组织节点过滤
	 * @author mawujun qq:16064988 mawujun1234@163.com
	 * @param parent_id
	 * @param dim
	 * @return
	 */
	@RequestMapping("/org/query4Combo.do")
	@ResponseBody
	public List<Org> query4Combo(String parent_no,String channo,Dim dim,Boolean showBlank) {
		if(dim==null){
			dim=Dim.base;
		}

		List<Org> orges=new ArrayList<Org>();//orgService.query4Combo(parent_no, channo,dim,ShiroUtils.getUserId());
		if(parent_no==null || "".equals(parent_no)){
			//orges=orgService.query4Combo(parent_no, channo,dim,ShiroUtils.getUserId());
		} else {
			orges=orgService.query4Combo(parent_no, channo,dim,ShiroUtils.getUserId());
		}
		if(showBlank!=null && showBlank==true){
			Org blank=new Org();
			blank.setId("");
			blank.setName("所有");
			orges.add(0, blank);
		}
		return orges;
	}
	/*
	 * 查询所有的作业单位
	 * @return
	 */
	@RequestMapping("/org/queryWorkunits.do")
	@ResponseBody
	public List<Org> queryWorkunit() {
		Cnd cnd=Cnd.select().andEquals(M.Org.orgType, OrgType.workunit);
		List<Org> orges=orgService.query(cnd);
		return orges;
	}
	
	/**
	 * 
	 * @author mawujun email:160649888@163.com qq:16064988
	 * @param type 
	 * @return
	 */
	@RequestMapping("/org/queryStores4Combo.do")
	@ResponseBody
	public List<Org> queryStores4Combo(Boolean look,Boolean edit,OrgType orgtype,Boolean showBlank) {
		List<Org> list=null;
		if(orgtype==null){
			list=  orgService.queryStores4Combo(look, edit);
		} else {
			list=  orgService.queryStores4Combo(look, edit,orgtype);
		}
		if(showBlank!=null && showBlank==true){
			Org pubCode=new Org();
			pubCode.setId("");
			pubCode.setName("所有");
			list.add(0,pubCode);
		}
		return list;
		
	}
	/**
	 * 
	 * @author mawujun email:160649888@163.com qq:16064988
	 * @param type 
	 * @return
	 */
	@RequestMapping("/org/queryRepaircenter4Combo.do")
	@ResponseBody
	public List<Org> queryRepaircenter4Combo(Boolean look,Boolean edit,Boolean showBlank) {	
		List<Org> list= orgService.queryRepaircenter4Combo(look, edit);
		
		if(showBlank!=null && showBlank==true){
			Org pubCode=new Org();
			pubCode.setId("");
			pubCode.setName("所有");
			list.add(0,pubCode);
		}
		return list;
	}
	/**
	 * 
	 * @author mawujun email:160649888@163.com qq:16064988
	 * @param type 
	 * @return
	 */
	@RequestMapping("/org/queryWorkunits4Combo.do")
	@ResponseBody
	public List<Org> queryWorkunits4Combo(Boolean look,Boolean edit,Boolean showBlank) {	
		
		List<Org> list= orgService.queryWorkunits4Combo(look, edit);
		if(showBlank!=null && showBlank==true){
			Org pubCode=new Org();
			pubCode.setId("");
			pubCode.setName("所有");
			list.add(0,pubCode);
		}
		return list;
	}

//	/**
//	 * 查询某个职位可以访问的组织单元
//	 * @author mawujun qq:16064988 mawujun1234@163.com
//	 * @param parent_id
//	 * @param dim
//	 * @return
//	 */
//	@RequestMapping("/org/queryOrgAccess.do")
//	@ResponseBody
//	public List<NodeVO> queryOrgAccess(String parent_id,Dim dim,String position_id) {
//		if(position_id==null){
//			return new ArrayList<NodeVO>();
//		}
//		if(dim==null){
//			dim=Dim.base;
//		}
//
//		//Cnd cnd=Cnd.select().andEquals(M.OrgOrg, "root".equals(parent_id)?null:parent_id);
//		List<NodeVO> orges=orgService.queryOrgAccess(parent_id,dim,position_id);
//		return orges;
//	}
//	
//	@RequestMapping("/org/checkOrgNodes.do")
//	@ResponseBody
//	public void checkNodes(Dim dim,String position_id,String orgno,String orgty,Boolean checked,String parent_orgnos[]) {
////		//System.out.println(MenuType.menu);
////		//Cnd cnd=Cnd.select().andEquals(M.Menu.parent_id, "root".equals(parent_id)?null:parent_id));
////		if("root".equals(parent_id)){
////			parent_id=null;
////		}
////		List<MenuVO> menues=menuService.query_checkbox(null);
////		return menues;
//		if(dim==null){
//			dim=Dim.base;
//		}
//		
//		orgService.checkNodes(dim, position_id, orgno, orgty, checked, parent_orgnos);
//		return;
//	}

//	@RequestMapping("/org/queryAll.do")
//	@ResponseBody
//	public List<Org> queryAll() {	
//		List<Org> orges=orgService.queryAll();
//		return orges;
//	}
	

	@RequestMapping("/org/load.do")
	@ResponseBody
	public Org load(String id) {
		return orgService.get(id);
	}
	
	@RequestMapping("/org/create.do")
	@ResponseBody
	public Org create(@RequestBody OrgVO org) {
//		if("root".equals(parent_no)){
//			org.setParent_id(null);
//		}
		orgService.create(org);
		return org;
	}
	
	@RequestMapping("/org/update.do")
	@ResponseBody
	public  Org update(@RequestBody Org org) {
		orgService.update(org);
		return org;
	}
	@RequestMapping("/org/updateParentOrg.do")
	@ResponseBody
	public  String updateParentOrg(String org_id,String old_parent_id,String new_parent_id,Dim dim) {
		orgService.updateParentOrg(org_id, old_parent_id, new_parent_id, dim);
		return "{success:true}";
	}
	
	@RequestMapping("/org/destroy.do")
	@ResponseBody
	public Org destroy(@RequestBody Org org,Dim dim) {
		orgService.delete(org,dim);
		return org;
	}
	@RequestMapping("/org/initReportCode.do")
	@ResponseBody
	public String initReportCode() {
		orgService.initReportCode();
		return "{success:true}";
	}
}
