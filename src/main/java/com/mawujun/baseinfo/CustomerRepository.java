package com.mawujun.baseinfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.mawujun.repository.IRepository;
/**
 * @author mawujun qq:16064988 e-mail:16064988@qq.com 
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface CustomerRepository extends IRepository<Customer, String>{

	public List<CustomerVO> queryChildren(@Param("parent_id")String parent_id,@Param("name")String name,@Param("includeInvalid")String includeInvalid,@Param("user_id")String user_id);
	
	public List<Customer> queryCombo(@Param("name")String name,@Param("user_id")String user_id);
	public List<Customer> queryAreaCombo(@Param("user_id")String user_id);

}
