package com.mawujun.baseinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mawujun.exception.BusinessException;
import com.mawujun.repository.cnd.Cnd;
import com.mawujun.service.AbstractService;
import com.mawujun.utils.M;


/**
 * @author mawujun qq:16064988 e-mail:16064988@qq.com 
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class EquipmentSubtypeService extends AbstractService<EquipmentSubtype, String>{

	@Autowired
	private EquipmentSubtypeRepository equipmentSubtypeRepository;
	@Autowired
	private EquipmentProdRepository equipmentProdRepository;
	
	@Override
	public EquipmentSubtypeRepository getRepository() {
		return equipmentSubtypeRepository;
	}

	public void delete(EquipmentSubtype entity) {
		int aa=equipmentProdRepository.queryCount(Cnd.select().andEquals(M.EquipmentProd.subtype_id, entity.getId()));
		if(aa>0){
			throw new BusinessException("该品名已经被使用,不能删除!");
		}else {
			this.getRepository().delete(entity);
		}
		//this
		int count=equipmentProdRepository.queryCount(Cnd.select().andEquals(M.EquipmentProd.subtype_id, entity.getId()).andEquals(M.EquipmentProd.status, true));
		if(count>0){
			throw new BusinessException("还存在可用的品名,不能取消小类!");
		}
		
		this.getRepository().update(Cnd.update().set(M.EquipmentSubtype.status, false).andEquals(M.EquipmentSubtype.id, entity.getId()));
	}
}
