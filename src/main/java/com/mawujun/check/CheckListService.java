package com.mawujun.check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mawujun.service.AbstractService;


/**
 * @author mawujun qq:16064988 e-mail:16064988@qq.com 
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class CheckListService extends AbstractService<CheckList, com.mawujun.check.CheckList.PK>{

	@Autowired
	private CheckListRepository checkListRepository;
	
	@Override
	public CheckListRepository getRepository() {
		return checkListRepository;
	}

}
