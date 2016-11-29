package com.mawujun.baseinfo;

import org.springframework.stereotype.Repository;

import com.mawujun.repository.IRepository;

@Repository
public interface EquipmentStoreRepository extends IRepository<EquipmentStore,EquipmentStorePK> {
	//public void changeStore(Map<String,Object> params);
}
