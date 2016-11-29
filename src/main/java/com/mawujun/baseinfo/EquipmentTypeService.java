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
public class EquipmentTypeService extends AbstractService<EquipmentType, String>{

	@Autowired
	private EquipmentTypeRepository equipmentTypeRepository;
	@Autowired
	private EquipmentSubtypeRepository equipmentSubtypeRepository;
	@Autowired
	private EquipmentProdRepository equipmentProdRepository;
	
	@Override
	public EquipmentTypeRepository getRepository() {
		return equipmentTypeRepository;
	}
	
//	public List<EquipmentType> queryType(String parentId) {
//		return this.getRepository().queryType(parentId);
//	}
	
	public void delete(EquipmentType entity) {
		int aa=equipmentSubtypeRepository.queryCount(Cnd.select().andEquals(M.EquipmentSubtype.parent_id, entity.getId()));
		if(aa>0){
			throw new BusinessException("该大类已经被使用,不能删除!");
		}else {
			this.getRepository().delete(entity);
		}
		//this
		int count=equipmentSubtypeRepository.queryCount(Cnd.select().andEquals(M.EquipmentSubtype.parent_id, entity.getId()).andEquals(M.EquipmentSubtype.status, true));
		if(count>0){
			throw new BusinessException("存在使用中的小类,不能取消!");
		}
		
		this.getRepository().update(Cnd.update().set(M.EquipmentType.status, false).andEquals(M.EquipmentType.id, entity.getId()));
	}

}
