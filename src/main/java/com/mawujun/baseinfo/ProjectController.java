package com.mawujun.baseinfo;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mawujun.repository.cnd.Cnd;
import com.mawujun.utils.M;
import com.mawujun.utils.page.Pager;
import com.mawujun.utils.string.StringUtils;
/**
 * @author mawujun qq:16064988 e-mail:16064988@qq.com 
 * @version 1.0
 * @since 1.0
 */
@Controller
//@RequestMapping("/project")
public class ProjectController {

	@Resource
	private ProjectService projectService;


//	/**
//	 * 请按自己的需求修改
//	 * @author mawujun email:16064988@163.com qq:16064988
//	 * @param id 是父节点的id
//	 * @return
//	 */
//	@RequestMapping("/project/query.do")
//	@ResponseBody
//	public List<Project> query(String id) {
//		Cnd cnd=Cnd.select().andEquals(M.Project.parent.id, "root".equals(id)?null:id);
//		List<Project> projectes=projectService.query(cnd);
//		//JsonConfigHolder.setFilterPropertys(Project.class,M.Project.parent.name());
//		return projectes;
//	}

	/**
	 * 这是基于分页的几种写法,的例子，请按自己的需求修改
	 * @author mawujun email:16064988@163.com qq:16064988
	 * @param start
	 * @param limit
	 * @param userName
	 * @return
	 */
	@RequestMapping("/project/query.do")
	@ResponseBody
	public Pager<Project> query(Integer start,Integer limit,String name){
		Pager<Project> page=new Pager<Project>();//Page.getInstance(start,limit);
		page.setStart(start);
		page.setLimit(limit);
		if(StringUtils.hasText(name)){
			page.addParam(M.Project.name, "%"+name+"%");
		}
		return projectService.queryPage(page);
	}
	@RequestMapping("/project/queryCombo.do")
	@ResponseBody
	public List<Project> queryCombo(String name,Boolean showBlank){
		List<Project> list=null;
		if(StringUtils.hasText(name)){
			list= projectService.query(Cnd.select().andEquals(M.Supplier.status, true).andLike(M.Supplier.name, name));	
		} else {
			list= projectService.query(Cnd.select().andEquals(M.Supplier.status, true));	
		}
		if(showBlank!=null && showBlank){
			Project all=new Project();
			all.setId("");
			all.setName("所有");
			list.add(0, all);
		}
		return list;
	}
//
//	@RequestMapping("/project/query.do")
//	@ResponseBody
//	public List<Project> query() {	
//		List<Project> projectes=projectService.queryAll();
//		return projectes;
//	}
	

	@RequestMapping("/project/load.do")
	public Project load(String id) {
		return projectService.get(id);
	}
	
	@RequestMapping("/project/create.do")
	@ResponseBody
	public Project create(@RequestBody Project project) {
		projectService.create(project);
		return project;
	}
	
	@RequestMapping("/project/update.do")
	@ResponseBody
	public  Project update(@RequestBody Project project) {
		projectService.update(project);
		return project;
	}
	
//	@RequestMapping("/project/deleteById.do")
//	@ResponseBody
//	public String deleteById(String id) {
//		projectService.deleteById(id);
//		return id;
//	}
//	
//	@RequestMapping("/project/disable.do")
//	@ResponseBody
//	public Project disable(@RequestBody Project project) {
//		project.setStatus(false);
//		projectService.update(project);
//		return project;
//	}
	
	@RequestMapping("/project/destroy.do")
	@ResponseBody
	public Project destroy(Project project) {
		projectService.delete(project);
		return project;
	}
	
	
}
