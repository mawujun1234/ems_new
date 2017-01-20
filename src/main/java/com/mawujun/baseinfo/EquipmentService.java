package com.mawujun.baseinfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mawujun.exception.BusinessException;
import com.mawujun.permission.ShiroUtils;
import com.mawujun.service.AbstractService;
import com.mawujun.utils.page.Pager;



/**
 * @author mawujun qq:16064988 e-mail:16064988@qq.com 
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class EquipmentService extends AbstractService<Equipment, String>{

	@Autowired
	private EquipmentRepository equipmentRepository;
	
	@Autowired
	private EquipmentCycleService equipmentCycleService;
	
	@Override
	public EquipmentRepository getRepository() {
		return equipmentRepository;
	}

	
	public EquipmentVO getEquipmentByEcode_in_store(String ecode,String store_id) {
		EquipmentVO equipment =equipmentRepository.getEquipmentByEcode_in_store(ecode,store_id);
		
		return equipment;
	}
	
	public EquipmentVO getEquipmentInfo(String ecode) {
		return equipmentRepository.getEquipmentInfo(ecode);
	}
	
	
	public EquipmentWorkunitVO getEquipmentWorkunitVO(String ecode){
		return equipmentRepository.getEquipmentWorkunitVO(ecode);
	}
	public EquipmentStoreVO getEquipmentStoreVO(String ecode){
		return equipmentRepository.getEquipmentStoreVO(ecode);
	}
	public EquipmentRepairVO getEquipmentRepairVO(String ecode){
		return equipmentRepository.getEquipmentRepairVO(ecode);
	}
	public EquipmentPoleVO getEquipmentPoleVO(String ecode){
		return equipmentRepository.getEquipmentPoleVO(ecode);
	}
	
	
	public String wait_for_repair(String ecode,String reason) {
		Equipment equip=this.get(ecode);
		if(equip.getStatus()== EquipmentStatus.wait_for_repair){
			//return "已经是损坏设备，不需要重复设置为损坏设备!";
			throw new BusinessException("已经是损坏设备，不需要重复设置为损坏设备!");
		} else if(equip.getStatus() != EquipmentStatus.in_storage){
			throw new BusinessException("设备不在仓库，不能修改为待维修状态!");
		} else {

			
			EquipmentStoreVO equipmentStoreVO=this.getEquipmentStoreVO(ecode);
			if(equipmentStoreVO==null){
				throw new BusinessException("设备不在仓库中，请注意!");
			}
			equip.setStatus(EquipmentStatus.wait_for_repair);
			this.update(equip);
			EquipmentCycle equipmentCycle=equipmentCycleService.logEquipmentCycle(ecode, OperateType.manual_wait_for_repair, ShiroUtils.getLoginName()
					, TargetType.store,equipmentStoreVO.getStore_id()
					,reason);
			//equipmentCycle.setMemo(reason);
			//equipmentCycleService.update(equipmentCycle);
		}
		//equipmentService.update(Cnd.update().set(M.Equipment.status, EquipmentStatus.wait_for_repair).andEquals(M.Equipment.ecode, ecode));
		return "{success:true}";
	}
	
	public String to_old(String ecode,String reason) {
		Equipment equip=this.get(ecode);
		if(equip.getIsnew()==false){
			throw new BusinessException("已经是旧设备，不需要重复设置为旧设备!");
		} else {
			equip.setIsnew(false);
			this.update(equip);
			
			EquipmentWorkunitVO aa=this.getEquipmentWorkunitVO(ecode);
			EquipmentStoreVO bb=null;
			EquipmentPoleVO cc=null;
			EquipmentRepairVO dd=null;
			
			TargetType targetType=TargetType.workunit;
			String targetType_id=null;
			if(aa==null){
				bb=this.getEquipmentStoreVO(ecode);

				if(bb!=null){
					targetType=TargetType.store;
					targetType_id=bb.getStore_id();
					
				} else {
					cc=this.getEquipmentPoleVO(ecode);
					
					if(cc!=null){
						targetType=TargetType.pole;	
						targetType_id=cc.getPole_id();
						
					} else {
						dd=this.getEquipmentRepairVO(ecode);
						
						if(dd!=null){
							targetType=TargetType.repair;
							targetType_id=dd.getRepair_id();		
						} else {
							throw new BusinessException("该设备不在仓库，点位，作业单位和维修中心,不能进行手工设旧处理!");
						}
					}
				}
			} else {
				targetType_id=aa.getWorkunit_id();
				targetType=TargetType.workunit;
			}
			
			
			
			
			EquipmentCycle equipmentCycle=equipmentCycleService.logEquipmentCycle(ecode, OperateType.manual_to_old, ShiroUtils.getLoginName(), targetType,targetType_id,reason);
			//equipmentCycle.setMemo(reason);
			//equipmentCycleService.update(equipmentCycle);
		}
		//equipmentService.update(Cnd.update().set(M.Equipment.isnew, false).andEquals(M.Equipment.ecode, ecode));
		return "{success:true}";
	}
	
	public List<EquipmentVO> queryByStore(EquipmentVO equipmentVO,Integer level,Integer start,Integer limit) {
		if(level==1){
			return equipmentRepository.queryByStore_total(equipmentVO);
		} else if(level==2 || level==3){
			Pager<EquipmentVO> page=new Pager<EquipmentVO>();
			page.setStart(start);
			page.setLimit(limit);
			page.setParams(equipmentVO);
			Pager<EquipmentVO> result=equipmentRepository.queryByStore(page);
			List<EquipmentVO> list= result.getRoot();
			
			EquipmentVO total=new EquipmentVO();
			total.setSubtype_id("total");
			total.setSubtype_name("<b>合计:</b>");
			int total_num=0;
			for(EquipmentVO equi_temp:list){
				total_num+=equi_temp.getNum();
			}
			total.setNum(total_num);

			list.add(total);
			return list;
		} else {
			return null;
		}
		
	}
	
	public List<EquipmentVO> queryByWorkunit(EquipmentVO equipmentVO,Integer level,Integer start,Integer limit) {
		//return workUnitRepository.queryEquipments(workUnit_id);
		if(level==1){
			//返回作业单位身上所有的设备，顶级
			return equipmentRepository.queryByWorkunit_total(equipmentVO);
		} else if(level==2 || level==3){
			Pager<EquipmentVO> page=new Pager<EquipmentVO>();
			page.setStart(start);
			page.setLimit(limit);
			page.setParams(equipmentVO);
			Pager<EquipmentVO> result=equipmentRepository.queryByWorkunit(page);
			List<EquipmentVO> list= result.getRoot();
			
			EquipmentVO total=new EquipmentVO();
			total.setSubtype_id("total");
			total.setSubtype_name("<b>合计:</b>");
			int total_num=0;
			for(EquipmentVO equi_temp:list){
				total_num+=equi_temp.getNum();
			}
			total.setNum(total_num);

			list.add(total);
			return list;
		} else {
			return null;
		}
	}
}
