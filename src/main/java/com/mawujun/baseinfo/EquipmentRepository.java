package com.mawujun.baseinfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.mawujun.repository.IRepository;
import com.mawujun.utils.page.Pager;
/**
 * @author mawujun qq:16064988 e-mail:16064988@qq.com 
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface EquipmentRepository extends IRepository<Equipment, String>{

	public EquipmentVO getEquipmentByEcode_in_store(@Param("ecode")String ecode,@Param("store_id")String store_id);
	
	public EquipmentWorkunitVO getEquipmentWorkunitVO(@Param("ecode")String ecode);
	public EquipmentStoreVO getEquipmentStoreVO(@Param("ecode")String ecode);
	public EquipmentRepairVO getEquipmentRepairVO(@Param("ecode")String ecode);
	public EquipmentPoleVO getEquipmentPoleVO(@Param("ecode")String ecode);
	
	public EquipmentVO getEquipmentInfo(@Param("ecode")String ecode);
	
	
	public int check_in_workunit_by_ecode(@Param("ecode")String ecode,@Param("workunit_id")String workunit_id);
	public int check_in_store_by_ecode(@Param("ecode")String ecode,@Param("store_id")String store_id);
	public int check_in_repair_by_ecode(@Param("ecode")String ecode,@Param("repair_id")String repair_id);
	public int check_in_pole_by_ecode(@Param("ecode")String ecode,@Param("pole_id")String pole_id);
	public EquipmentWorkunit getBorrowEquipmentWorkunit(@Param("ecode")String ecode);
	//public void changeStore(Map<String,Object> params);
	
	public List<EquipmentVO> queryByStore_total(EquipmentVO equipmentVO) ;
	public Pager<EquipmentVO> queryByStore(Pager<EquipmentVO> page) ;
	
	public List<EquipmentVO> queryByWorkunit_total(EquipmentVO equipmentVO) ;
	public Pager<EquipmentVO> queryByWorkunit(Pager<EquipmentVO> page) ;

}
