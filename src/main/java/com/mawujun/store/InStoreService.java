package com.mawujun.store;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mawujun.baseinfo.Equipment;
import com.mawujun.baseinfo.EquipmentCycleService;
import com.mawujun.baseinfo.EquipmentPlace;
import com.mawujun.baseinfo.EquipmentProdRepository;
import com.mawujun.baseinfo.EquipmentRepository;
import com.mawujun.baseinfo.EquipmentStatus;
import com.mawujun.baseinfo.EquipmentStore;
import com.mawujun.baseinfo.EquipmentStoreRepository;
import com.mawujun.baseinfo.EquipmentStoreType;
import com.mawujun.baseinfo.OperateType;
import com.mawujun.baseinfo.TargetType;
import com.mawujun.permission.ShiroUtils;
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
public class InStoreService extends AbstractService<InStore, String>{

	@Autowired
	private InStoreRepository inStoreRepository;
	@Autowired
	private InStoreListRepository inStoreListRepository;
	@Autowired
	private EquipmentRepository equipmentRepository;
	@Autowired
	private EquipmentStoreRepository equipmentStoreRepository;
	@Autowired
	private BarcodeRepository barcodeRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderListRepository orderListRepository;
	@Autowired
	private EquipmentCycleService equipmentCycleService;
//	@Autowired
//	private StoreService storeService;
	@Autowired
	private EquipmentProdRepository equipmentProdRepository;
	
	SimpleDateFormat ymdHmsDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
	
	@Override
	public InStoreRepository getRepository() {
		return inStoreRepository;
	}

	/**
	 * 新设备入库
	 * @author mawujun email:160649888@163.com qq:16064988
	 * @param equipments
	 */
	public void newInStore(Equipment[] equipments,InStore inStore) {
		//插入入库单
		String instore_id=ymdHmsDateFormat.format(new Date());
		//InStore inStore=new InStore();
		inStore.setId(instore_id);
		inStore.setOperateDate(new Date());
		inStore.setOperater(ShiroUtils.getAuthenticationInfo().getId());
		//inStore.setType(1);
		inStoreRepository.create(inStore);
		
		//以订单明细id为key，入库数量为num为value的map
		Map<String,Integer> totalnumMap=new HashMap<String,Integer>();
		//插入设备表,同时设置仓库，入库时间，是否新设备
		for(Equipment equipment:equipments){
			equipment.setFisData(new Date());
			//equipment.setLastInDate(new Date());
			equipment.setStatus(EquipmentStatus.in_storage);
			//equipment.setIsnew(true);
			//equipment.setStore_id(inStore.getStore_id());
			equipment.setPlace(EquipmentPlace.store);
			equipment.setMemo("");
			equipmentRepository.create(equipment);
			////修改条码状态
			//barcodeRepository.update(Cnd.update().set(M.Barcode.isInStore, true).andEquals(M.Barcode.ecode, equipment.getEcode()));
			//更新订单明细中的累计入库数量
			//orderRepository.updateTotalNum(equipment.getOrder_id(), M.Order.totalNum+"+1");
			if(totalnumMap.containsKey(equipment.getOrderlist_id())){
				totalnumMap.put(equipment.getOrderlist_id(), totalnumMap.get(equipment.getOrderlist_id())+1);
			} else {
				totalnumMap.put(equipment.getOrderlist_id(), 1);
			}
			
			//插入到仓库中
			EquipmentStore equipmentStore=new EquipmentStore();
			equipmentStore.setEcode(equipment.getEcode());
			equipmentStore.setStore_id(inStore.getStore_id());
			equipmentStore.setNum(1);
			equipmentStore.setInDate(new Date());
			equipmentStore.setType(EquipmentStoreType.newinstore);
			equipmentStore.setType_id(inStore.getId());
			equipmentStore.setFrom_id(equipment.getOrderlist_id());
			equipmentStoreRepository.create(equipmentStore);
			
			//插入入库单明细
			InStoreList inStoreList=new InStoreList();
			inStoreList.setEcode(equipment.getEcode());
			inStoreList.setInStore_id(instore_id);
			inStoreList.setIsnew(equipment.getIsnew());
			inStoreList.setOrderlist_id(equipment.getOrderlist_id());
			inStoreListRepository.create(inStoreList);
			
			//更新条码的状态
			barcodeRepository.update(Cnd.update().set(M.Barcode.status, 1).andEquals(M.Barcode.ecode, equipment.getEcode()));
			
			//更新品名的型号不能修改
			equipmentProdRepository.update_lock_style(equipment.getProd_id(),true);
			
			//记录设备入库的生命周期
			equipmentCycleService.logEquipmentCycle(equipment.getEcode(), OperateType.newinstore, instore_id, TargetType.store,inStore.getStore_id(),inStore.getMemo());
		}
		
		for(Entry<String,Integer> entry:totalnumMap.entrySet()) {
			//更新某个订单明细的入库数量
			OrderList orderList=orderListRepository.get(entry.getKey());
			orderList.addTotalnum(entry.getValue());
			orderListRepository.update(orderList);
			//orderRepository.updateTotalNum(entry.getKey(), M.OrderList.totalNum+"+"+entry.getValue());
			//当某个订单明细中的数量已经全部入库后，就删除在barcode中所有还没有入库的条码，因为这些条码已经失效了
			//防止以前打印的条码又拿过来入库了，同时可以减少ems_barcode中无效的数量
			orderRepository.deleteBarcodeWhenAllin(entry.getKey());
		}
		
	}
	/**
	 * 获取某个入库单中的明细数据
	 * @author mawujun email:160649888@163.com qq:16064988
	 * @param inStore_id
	 * @return
	 */
	public List<InStoreListVO> queryList(String inStore_id) {
		return inStoreRepository.queryList(inStore_id);
	}
	/**
	 * 判断某个条码是否已经存在
	 * @author mawujun 16064988@qq.com 
	 * @param ecode
	 * @return true 已经存在该条码的设备，false，该设备还不存在
	 */
	public boolean checkEquipmentExist(String ecode){
		int count=inStoreRepository.checkEquipmentExist(ecode);
		if(count>0){
			return true;
		}
		return false;
	}
}
