package com.mawujun.baseinfo;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mawujun.org.Org;
import com.mawujun.utils.string.StringUtils;
/**
 * @author mawujun qq:16064988 e-mail:16064988@qq.com 
 * @version 1.0
 * @since 1.0
 */
@Controller
//@RequestMapping("/customer")
public class CustomerController {

	@Resource
	private CustomerService customerService;


//	/**
//	 * 请按自己的需求修改
//	 * @author mawujun email:16064988@163.com qq:16064988
//	 * @param id 是父节点的id
//	 * @return
//	 */
//	@RequestMapping("/customer/query.do")
//	@ResponseBody
//	public List<Customer> query(String id) {
//		Cnd cnd=Cnd.select().andEquals(M.Customer.parent.id, "root".equals(id)?null:id);
//		List<Customer> customeres=customerService.query(cnd);
//		//JsonConfigHolder.setFilterPropertys(Customer.class,M.Customer.parent.name());
//		return customeres;
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
//	@RequestMapping("/customer/query.do")
//	@ResponseBody
//	public Page query(Integer start,Integer limit,String sampleName){
//		Page page=Page.getInstance(start,limit);//.addParam(M.Customer.sampleName, "%"+sampleName+"%");
//		return customerService.queryPage(page);
//	}

	@RequestMapping("/customer/query.do")
	@ResponseBody
	public List<CustomerVO> query(String node,String includeInvalid) {	
		List<CustomerVO> customeres=null;
		if("root".equals(node)){
			customeres=customerService.queryChildren(null,null,includeInvalid);
		} else {
			customeres=customerService.queryChildren(node,null,includeInvalid);
		}
		
		//JsonConfigHolder.setAutoWrap(false);
		return customeres;
	}
	/**
	 * 用在地图上获取的
	 * @param parent_id
	 * @param name
	 * @return
	 */
	@RequestMapping("/customer/query4combo.do")
	@ResponseBody
	public List<CustomerVO> query4combo(String parent_id,String name) {	
		List<CustomerVO> customeres=null;
		if(!StringUtils.hasText(parent_id)){
			customeres=customerService.queryChildren(null,null,"false");
		} else {
			if(StringUtils.hasText(name)){
				name="%"+name+"%";
			}
			//改成按用户可以访问的作业单位的所蜀的顾客进行过滤
			customeres=customerService.queryChildren(parent_id,name,"false");
		}
		
		//JsonConfigHolder.setAutoWrap(false);
		return customeres;
	}
	@RequestMapping("/customer/queryCombo.do")
	@ResponseBody
	public List<Customer> queryCombo(String name,Boolean showBlank) {	
		List<Customer> list= customerService.queryCombo(name);
		if(showBlank!=null && showBlank==true){
			Customer pubCode=new Customer();
			pubCode.setId("");
			pubCode.setName("所有");
			list.add(0,pubCode);
		}
		return list;
		//return customerService.query(Cnd.select().andEquals(M.Customer.status, true).andLike(M.Customer.name, name));	
	}
	
	@RequestMapping("/customer/queryAreaCombo.do")
	@ResponseBody
	public List<Customer> queryAreaCombo(Boolean showBlank) {	
		List<Customer> list= customerService.queryAreaCombo();
		if(showBlank!=null && showBlank==true){
			Customer pubCode=new Customer();
			pubCode.setId("");
			pubCode.setName("所有");
			list.add(0,pubCode);
		}
		return list;
		//return customerService.query(Cnd.select().andEquals(M.Customer.status, true).andLike(M.Customer.name, name));	
	}
	@RequestMapping("/customer/transform.do")
	@ResponseBody
	public String transform(String parent_id,String[] customer_ids) {
		 customerService.transform(parent_id,customer_ids);
		 return "{success:true}";
	}

	@RequestMapping("/customer/load.do")
	public Customer load(String id) {
		return customerService.get(id);
	}
	
	@RequestMapping("/customer/create.do")
	@ResponseBody
	public Customer create(@RequestBody Customer customer) {
		if(customer.getType()==2){
			customer.setParent_id(null);
		}
		customerService.create(customer);
		return customer;
	}
	
	@RequestMapping("/customer/update.do")
	@ResponseBody
	public  Customer update(@RequestBody Customer customer) {
		customerService.update(customer);
		return customer;
	}
	
//	@RequestMapping("/customer/deleteById.do")
//	@ResponseBody
//	public String deleteById(String id) {
//		customerService.deleteById(id);
//		return id;
//	}
//	
	@RequestMapping("/customer/destroy.do")
	@ResponseBody
	public Customer destroy(Customer customer) {
		customerService.delete(customer);
		return customer;
	}
	

	
//	@RequestMapping("/customer/queryPole.do")
//	@ResponseBody
//	public List<Customer> query() {	
//		List<Customer> customeres=customerService.queryAll();
//		return customeres;
//	}
	
	
}
