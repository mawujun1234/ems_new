package com.mawujun.mobile.login;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MobileLoginRepository {
	
	public List<Myinfo> queryMyinfo(@Param("user_id")String user_id);

}
