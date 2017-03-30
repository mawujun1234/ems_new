package com.mawujun.baseinfo;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.mawujun.service.AbstractService;


import com.mawujun.baseinfo.PolePhoto;
import com.mawujun.baseinfo.PolePhotoRepository;


/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class PolePhotoService extends AbstractService<PolePhoto, String>{

	@Autowired
	private PolePhotoRepository polePhotoRepository;
	
	@Override
	public PolePhotoRepository getRepository() {
		return polePhotoRepository;
	}

}
