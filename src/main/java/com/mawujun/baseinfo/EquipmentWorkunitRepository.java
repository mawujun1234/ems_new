package com.mawujun.baseinfo;

import org.springframework.stereotype.Repository;

import com.mawujun.repository.IRepository;

@Repository
public interface EquipmentWorkunitRepository extends IRepository<EquipmentWorkunit,EquipmentWorkunitPK> {
	//public EquipmentWorkunit getBorrowEquipment(@Param("ecode")String ecode);
}
