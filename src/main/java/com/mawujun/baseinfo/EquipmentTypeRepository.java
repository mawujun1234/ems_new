package com.mawujun.baseinfo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mawujun.repository.IRepository;
/**
 * @author mawujun qq:16064988 e-mail:16064988@qq.com 
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface EquipmentTypeRepository extends IRepository<EquipmentType, String>{
	public List<EquipmentType> queryTypeAndSubtype();

}
