package com.mawujun.baseinfo;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mawujun.permission.ShiroUtils;
import com.mawujun.service.AbstractService;



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

	public List<CustomerVO> queryChildren(String parent_id,String name) {
		return customerRepository.queryChildren(parent_id,name,ShiroUtils.getUserId());
	}
	
	public List<Customer> queryCombo(String name) {
		return customerRepository.queryCombo(name,ShiroUtils.getUserId());
	}
}
