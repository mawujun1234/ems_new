package com.mawujun.mobile.task;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * @author mawujun qq:16064988 e-mail:16064988@qq.com 
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface MobileTaskRepository {
	
	
	public List<Map<String,Object>> queryTasknum(@Param("user_id")String user_id);
}
