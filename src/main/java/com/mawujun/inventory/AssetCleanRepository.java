package com.mawujun.inventory;

import org.springframework.stereotype.Repository;

import com.mawujun.repository.IRepository;

@Repository
public interface AssetCleanRepository extends IRepository<AssetClean, AssetClean.PK>{
	public void proc_report_assetclean();
}
