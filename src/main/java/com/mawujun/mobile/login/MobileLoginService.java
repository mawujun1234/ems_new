package com.mawujun.mobile.login;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mawujun.permission.ShiroUtils;

@Service
@Transactional(propagation=Propagation.REQUIRED)
public class MobileLoginService {
	@Autowired
	private MobileLoginRepository mobileLoginRepository;
	
	public List<Myinfo> queryMyinfo(String user_id) {
		return mobileLoginRepository.queryMyinfo(user_id);
	}

}
