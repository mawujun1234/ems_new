package com.mawujun.baseinfo;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mawujun.repository.cnd.Cnd;
import com.mawujun.utils.M;
import com.mawujun.utils.string.StringUtils;
/**
 * @author mawujun qq:16064988 e-mail:16064988@qq.com 
 * @version 1.0
 * @since 1.0
 */
@Controller
//@RequestMapping("/supplier")
public class SupplierController {

	@Resource
	private SupplierService supplierService;


//	/**
//	 * 请按自己的需求修改
//	 * @author mawujun email:16064988@163.com qq:16064988
//	 * @param id 是父节点的id
//	 * @return
//	 */
//	@RequestMapping("/supplier/query.do")
//	@ResponseBody
//	public List<Supplier> query(String id) {
//		Cnd cnd=Cnd.select().andEquals(M.Supplier.parent.id, "root".equals(id)?null:id);
//		List<Supplier> supplieres=supplierService.query(cnd);
//		//JsonConfigHolder.setFilterPropertys(Supplier.class,M.Supplier.parent.name());
//		return supplieres;
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
//	@RequestMapping("/supplier/query.do")
//	@ResponseBody
//	public Page query(Integer start,Integer limit,String sampleName){
//		Page page=Page.getInstance(start,limit);//.addParam(M.Supplier.sampleName, "%"+sampleName+"%");
//		return supplierService.queryPage(page);
//	}

	@RequestMapping("/supplier/query.do")
	@ResponseBody
	public List<Supplier> query(String name) {	
		if(StringUtils.hasText(name)){
			List<Supplier> supplieres=supplierService.query(Cnd.select().andLike(M.Supplier.name, name, true));
			return supplieres;
		} else {
			List<Supplier> supplieres=supplierService.queryAll();
			return supplieres;
		}
	}
	

	@RequestMapping("/supplier/load.do")
	public Supplier load(String id) {
		return supplierService.get(id);
	}
	
	@RequestMapping("/supplier/create.do")
	@ResponseBody
	public Supplier create(@RequestBody Supplier supplier) {
		supplierService.create(supplier);
		return supplier;
	}
	
	@RequestMapping("/supplier/update.do")
	@ResponseBody
	public  Supplier update(@RequestBody Supplier supplier) {
		supplierService.createOrUpdate(supplier);
		return supplier;
	}
	
//	@RequestMapping("/supplier/deleteById.do")
//	@ResponseBody
//	public String deleteById(String id) {
//		supplierService.deleteById(id);
//		return id;
//	}
	
	@RequestMapping("/supplier/destroy.do")
	@ResponseBody
	public Supplier destroy(@RequestBody Supplier supplier) {
		//supplierService.delete(supplier);
		supplier.setStatus(false);
		supplierService.update(supplier);
		return supplier;
	}
	
	@RequestMapping("/supplier/querySupplierCombo.do")
	@ResponseBody
	public List<Supplier> querySupplierComboS(String name,Boolean showBlank) {
		//System.out.println("==========================================================="+name);
		List<Supplier> list=null;
		if(StringUtils.hasText(name)){
			list= supplierService.query(Cnd.select().andEquals(M.Supplier.status, true).andLike(M.Supplier.name, name));	
		} else {
			list= supplierService.query(Cnd.select().andEquals(M.Supplier.status, true));	
		}
		if(showBlank!=null && showBlank){
			Supplier all=new Supplier();
			all.setId("");
			all.setName("所有");
			list.add(0, all);
		}
		return list;
	}
}
