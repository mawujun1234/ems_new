package com.mawujun.baseinfo;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mawujun.exception.BusinessException;
import com.mawujun.permission.ShiroUtils;
import com.mawujun.repository.cnd.Cnd;
import com.mawujun.service.AbstractService;
import com.mawujun.task.TaskRepository;
import com.mawujun.utils.M;



/**
 * @author mawujun qq:16064988 e-mail:16064988@qq.com 
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class CustomerService extends AbstractService<Customer, String>{

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private PoleService poleService;
	
	private static HashMap<String,Customer> customers_cache=new HashMap<String,Customer>();
	@Override
	public Customer get(String id) {
		if(id==null){
			return null;
		}
		Customer customer=customers_cache.get(id);
		if(customer==null){
			return customerRepository.get(id);
		} else {
			return customer;
		}
	}
	@Override
	public CustomerRepository getRepository() {
		return customerRepository;
	}

	public List<CustomerVO> queryChildren(String parent_id,String name,String includeInvalid) {
		return customerRepository.queryChildren(parent_id,name,includeInvalid,ShiroUtils.getUserId());
	}
	
	public List<Customer> queryCombo(String name) {
		return customerRepository.queryCombo(name,ShiroUtils.getUserId());
	}
	public List<Customer> queryAreaCombo() {
		return customerRepository.queryAreaCombo(ShiroUtils.getUserId());
	}
	public void transform(String parent_id,String[] customer_ids) {
		if(parent_id==null || customer_ids==null || customer_ids.length==0){
			return;
		}
		for(String customer_id:customer_ids){
			customerRepository.update(Cnd.update().set(M.Customer.parent_id, parent_id).andEquals(M.Customer.id, customer_id));
		}
	}
	@Override
	public void delete(Customer customer) {
		int count=taskRepository.queryCount(Cnd.count(M.Task.id).andEquals(M.Task.customer_id, customer.getId()));
		if(count>0){
			//throw new BusinessException("不能删除,有任务关联，请发送点位取消任务!");
			customer.setStatus(false);
			this.update(customer);
		} else {
			count=poleService.queryCount(Cnd.count(M.Pole.id).andEquals(M.Pole.customer_id, customer.getId()));
			if(count>0){
				customer.setStatus(false);
				this.update(customer);
			} else {
				this.getRepository().delete(customer);
			}
		}
		
	}
}
