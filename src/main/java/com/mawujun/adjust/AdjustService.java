package com.mawujun.adjust;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mawujun.baseinfo.EquipmentCycleService;
import com.mawujun.baseinfo.EquipmentPlace;
import com.mawujun.baseinfo.EquipmentRepository;
import com.mawujun.baseinfo.EquipmentStatus;
import com.mawujun.baseinfo.EquipmentStore;
import com.mawujun.baseinfo.EquipmentStorePK;
import com.mawujun.baseinfo.EquipmentStoreRepository;
import com.mawujun.baseinfo.EquipmentStoreType;
import com.mawujun.baseinfo.OperateType;
import com.mawujun.baseinfo.TargetType;
import com.mawujun.exception.BusinessException;
import com.mawujun.org.OrgService;
import com.mawujun.permission.ShiroUtils;
import com.mawujun.permission.UserRepository;
import com.mawujun.repository.cnd.Cnd;
import com.mawujun.service.AbstractService;
import com.mawujun.utils.M;
import com.mawujun.utils.page.Pager;


/**
 * @author mawujun qq:16064988 e-mail:16064988@qq.com 
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class AdjustService extends AbstractService<Adjust, String>{

	@Autowired
	private AdjustRepository adjustRepository;
	@Autowired
	private AdjustListRepository adjustListRepository;
	@Autowired
	private EquipmentRepository equipmentRepository;
	@Autowired
	private EquipmentStoreRepository equipmentStoreRepository;

//	@Autowired
//	private StoreService storeService;
	@Autowired
	private OrgService orgService;
	
	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private EquipmentCycleService equipmentCycleService;
	SimpleDateFormat ymdHmsDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
	
	@Override
	public AdjustRepository getRepository() {
		return adjustRepository;
	}
	
	public AdjustListVO getAdjustListVOByEcode(String ecode,String store_id) {
		return adjustRepository.getAdjustListVOByEcode(ecode, store_id);
	}
	/***
	 * 这里包括创建普通的调拨单和归还的调拨单
	 * @param adjust
	 * @param adjuestLists
	 */
	public void newAdjuest(Adjust adjust,AdjustList[] adjuestLists) {
		//创建调拨单
		//Adjust adjust=BeanUtils.copyOrCast(adjuestVOs[0], Adjust.class);//adjuestVOs[0];//每条记录的主单内容都是一样的
		adjust.setId(ymdHmsDateFormat.format(new Date()));
		adjust.setStr_out_date(new Date());
		
		adjust.setStatus(AdjustStatus.carry);
		if(adjust.getAdjustType()==AdjustType.borrow){
			adjust.setReturnStatus(AdjustReturnStatus.nonereturn);
		} else {
			adjust.setReturnStatus(AdjustReturnStatus.neednot);
		}
		
		adjust.setStr_out_oper_id(ShiroUtils.getAuthenticationInfo().getId());

		adjustRepository.create(adjust);
		
		
		for(AdjustList adjustList:adjuestLists){
			//创建调拨明细
			//AdjustList adjustList=new AdjustList();
			adjustList.setAdjust_id(adjust.getId());
			adjustList.setAdjustListStatus(AdjustListStatus.noin);
			//adjustList.setEcode(adjustVO.getEcode());
			//adjustList.setOut_num(1);
			adjustListRepository.create(adjustList);
			
			//修改设备状态为"在途"
			equipmentRepository.update(Cnd.update().set(M.Equipment.status, EquipmentStatus.in_transit).andEquals(M.Equipment.ecode, adjustList.getEcode()));
			
			equipmentCycleService.logEquipmentCycle(adjustList.getEcode(), OperateType.adjust_out, adjust.getId(), TargetType.store, adjust.getStr_out_id(), adjust.getMemo());
		}
		//
	}
	/**
	 * 建立设备归还的领用单
	 * @author mawujun 16064988@qq.com 
	 * @param adjust
	 * @param adjuestLists
	 * @param adjust_id_borrow
	 */
	public void adjuestReturn(Adjust adjust,AdjustList[] adjuestLists,String adjust_id_borrow) {
		adjust.setAdjustType(AdjustType.returnback);
		adjust.setAdjust_id_borrow(adjust_id_borrow);
		adjust.setStatus(AdjustStatus.carry);
		adjust.setReturnStatus(AdjustReturnStatus.neednot);
		//被归还的调拨单
		Adjust adjust_borrow=adjustRepository.get(adjust_id_borrow);
		if(adjust_borrow==null){
			throw new BusinessException("借用调拨单不存在，请检查是否输错了!");
		}
		//先判断调拨单是不是借用单
		if(adjust_borrow.getAdjustType()!=AdjustType.borrow){
			throw new BusinessException("你选择的调拨单不是借用类型!");
		}
		
		if(adjust_borrow.getReturnStatus()==AdjustReturnStatus.over){
			throw new BusinessException("该调拨单已经全部归还，不能再归还了!");
		}
		//这条很重要，不然数据很容易出现混乱
		if(adjust_borrow.getStatus()==AdjustStatus.partin || adjust_borrow.getStatus()==AdjustStatus.carry){
			throw new BusinessException("该调拨单还没有完全接收，不能进行归还操作!");
		}

		//查处所有未归还的明细数据,就是已入库状态的 明细数据,lost的设备不会在这查询结果里出现
		List<AdjustList> adjustlist_borrowes=adjustRepository.query_borrow_in_adjustList(adjust_id_borrow);
		//如果扫描的数量超过了未归还借用设备的数量，就给出提示，设备过多了
		//注意当对某个借用单进行多次归还的时候，注意判断
		if(adjuestLists.length>adjustlist_borrowes.size()){
			throw new BusinessException(adjust_id_borrow+"还有"+adjustlist_borrowes.size()+"件没归还,但现在扫描了"+adjuestLists.length+"件");
		}
		
		//新建归还调拨单
		newAdjuest( adjust, adjuestLists);
		
		
		//进行设备品名的判断，如果不是同个品名，是不能归还的
		boolean exists_prod=false;
		AdjustList exists_adjustList_borrow=null;
		for(AdjustList adjuestList:adjuestLists){
			exists_prod=false;
			for(AdjustList  aa:adjustlist_borrowes){
				//表示有相同的品名
				if(aa.getProd_id().equals(adjuestList.getProd_id())){
					exists_prod=true;
					exists_adjustList_borrow=aa;
					break;
				}
			}
			
			if(exists_prod){
				adjustlist_borrowes.remove(exists_adjustList_borrow);
				//更新出借方明细单上的数据
				//修改调拨单明细状态为 returnback 已归还
				//先在出借单上记录归还单的id和ecode
				adjustListRepository.update(Cnd.update().set(M.AdjustList.adjustlist_id_returnback, adjuestList.getId())
						.set(M.AdjustList.ecode_returnback, adjuestList.getEcode())
						.set(M.AdjustList.isReturn, true)
						.andEquals(M.AdjustList.id,exists_adjustList_borrow.getId()));
				//这里不需要了，因为在上面的newAdjuest（）方法中进行记录过了
				//equipmentCycleService.logEquipmentCycle(adjuestList.getEcode(), OperateType.adjust_in, adjust.getId(), TargetType.store, adjust.getStr_in_id(), null);

			} else {
				throw new BusinessException(adjuestList.getEcode()+"该条码设备的品名不对,在借用单中已经不存在该品名的设备需要归还!");
			}
		}
		
		//借用单的状态修改要在入库的时候进行判断
		
	}
	public Pager<AdjustVO> query4InStore(Pager<AdjustVO> page){
		//List<Store> stores=storeRepository.queryAll();
		
		Pager<AdjustVO> results=adjustRepository.query4InStore(page);
		List<AdjustVO> list=results.getRoot();
		for(AdjustVO adjustVO:list){
			if(orgService.get(adjustVO.getStr_out_id())!=null){
				adjustVO.setStr_out_name(orgService.get(adjustVO.getStr_out_id()).getName());
			}
			if(orgService.get(adjustVO.getStr_in_id())!=null){
				adjustVO.setStr_in_name(orgService.get(adjustVO.getStr_in_id()).getName());
			}
//			for(Store store:stores){
//				if(store.getId().equals(adjustVO.getStr_out_id())){
//					adjustVO.setStr_out_name(store.getName());
//				} else if(store.getId().equals(adjustVO.getStr_in_id())){
//					adjustVO.setStr_in_name(store.getName());
//				}
//			}
		}
		return results;
	}
	
	
	public List<AdjustListVO> query4InStoreList(String adjust_id) {
		return adjustRepository.query4InStoreList(adjust_id);
	}
	/**
	 * 当按部分入库按钮的时候，并且判断当入库数和实际要入库数一样的时候，就当做事全部入库，走的是就是全部入库的路线了
	 * @author mawujun 16064988@qq.com 
	 * @return
	 */
	public void adjustInStore(AdjustList[] adjustLists,String adjust_id,String project_id) {
		if(project_id==null || "".equals(project_id)){
			throw new BusinessException("请先选择项目!");
		}
		Adjust adjust=adjustRepository.get(adjust_id);
		
		if(adjust.getProject_id()!=null && !"".equals(adjust.getProject_id())){
			if(!adjust.getProject_id().equals(project_id)){
				throw new BusinessException("两次入库选择的项目不一致!");
			}
		} else {
			adjust.setProject_id(project_id);
		}
		Date inDate=new Date();
		
		//获取当前调拨下的设备总数,本来应该是sum的，但现在是sum和count一样的
		int out_num_total=adjustListRepository.queryCount(Cnd.count(M.AdjustList.id).andEquals(M.AdjustList.adjust_id, adjust_id));
		//Long out_num_total=(Long)adjustListRepository.querySum(Cnd.sum(M.AdjustList.out_num).andEquals(M.AdjustList.adjust_id, adjust_id));
		
		//int total=0;
		for(AdjustList adjustList:adjustLists) {
			//更新调拨单明细的状态为已经入库
			adjustListRepository.update(Cnd.update().set(M.AdjustList.adjustListStatus, AdjustListStatus.in)
					.set(M.AdjustList.indate, inDate)
					.andEquals(M.AdjustList.id, adjustList.getId()));
			//记录该设备的调整记录,设备调拨入库
			equipmentCycleService.logEquipmentCycle(adjustList.getEcode(), OperateType.adjust_in,adjust.getId() , TargetType.store, adjust.getStr_in_id(), adjust.getMemo());
			
			
			//更新出借方明细单上的数据表示已经归还
			adjustListRepository.update(Cnd.update()
					.set(M.AdjustList.isReturn, true)
					.andEquals(M.AdjustList.adjustlist_id_returnback,adjustList.getId())
					.andEquals(M.AdjustList.ecode_returnback, adjustList.getEcode()));
			
			//同时更改设备状态，从A仓库到B仓库
			//同时修改设备状态
			//修改设备状态为"在库"
//			equipmentRepository.update(Cnd.update().set(M.Equipment.status, EquipmentStatus.in_storage)
//					.set(M.Equipment.store_id, str_in_id).andEquals(M.Equipment.ecode, adjustList.getEcode()));
			equipmentRepository.update(Cnd.update().set(M.Equipment.status, EquipmentStatus.in_storage)
					.set(M.Equipment.place, EquipmentPlace.store)
					.andEquals(M.Equipment.ecode, adjustList.getEcode()));
			
			//从仓库中删除
			EquipmentStorePK equipmentStorePK=new EquipmentStorePK();
			equipmentStorePK.setEcode( adjustList.getEcode());
			equipmentStorePK.setStore_id(adjust.getStr_out_id());
			equipmentStoreRepository.deleteById(equipmentStorePK);
			
			//插入到仓库
			EquipmentStore equipmentStore=new EquipmentStore();
			equipmentStore.setEcode( adjustList.getEcode());
			equipmentStore.setStore_id(adjust.getStr_in_id());
			equipmentStore.setNum(1);
			equipmentStore.setInDate(new Date());
			equipmentStore.setType(EquipmentStoreType.adjust);
			equipmentStore.setType_id(adjustList.getAdjust_id());
			equipmentStore.setFrom_id(adjust.getStr_out_id());
			equipmentStoreRepository.create(equipmentStore);
		
		}
		//这个时候就表示是都选择了，修改整个调拨单的状态
		//Long in_num_total=(Long)adjustListRepository.querySum(Cnd.sum(M.AdjustList.in_num).andEquals(M.AdjustList.adjust_id, adjust_id));
		//还有仓库迁移要做,如果明确是丢失的设备，那这个借用单也算是
		int in_num_total=adjustListRepository.queryCount(Cnd.sum(M.AdjustList.id)
				.andEquals(M.AdjustList.adjust_id, adjust_id).andIn(M.AdjustList.adjustListStatus, AdjustListStatus.in.toString(),AdjustListStatus.lost.toString()));
		
		//if(adjustRepository.sumInnumByadjust_id(adjust_id)==out_num_total){
		//入的数量和出的数量
		if(in_num_total==out_num_total){
			adjust.setStatus(AdjustStatus.over);
		} else {
			//这里表示未完全入库
			//adjustRepository.update(Cnd.update().set(M.Adjust.status, AdjustStatus.noallin).andEquals(M.Adjust.id, adjust_id));
			adjust.setStatus(AdjustStatus.partin);
		}
		//如果是借用单，设置归还状态为未归还，其实这个不执行也可以，因为在建立调拨单的时候，就已经指定了
		if(adjust.getAdjustType()==AdjustType.borrow){
			adjust.setReturnStatus(AdjustReturnStatus.nonereturn);
		} 
		adjust.setStr_in_date(inDate);
		adjust.setStr_in_oper_id(ShiroUtils.getAuthenticationInfo().getId());
		adjustRepository.update(adjust);
		
		if(adjust.getAdjustType()==AdjustType.returnback){
			//如果该调拨单已经全部归还，变更借用单的状态为over
			//如果该调拨单还没有全部归还，则修改调拨单状态为partreturn
			adjustRepository.updateAdjustIsAllReturn(adjust.getAdjust_id_borrow());
		}
	}
//	/**
//	 * 当按全部入库按钮的时候，当要入库的数量和实际要入库的数量不一致的时候，要给出提醒，如果还是要强制入库，就表示某个设备丢失了
//	 * @author mawujun 16064988@qq.com 
//	 * @return
//	 */
//	public void allInStr(AdjustList[] adjustLists,String str_in_id) {
//		Long out_num_total=adjustListRepository.queryCount(Cnd.count(M.AdjustList.id).andEquals(M.AdjustList.adjust_id, adjustLists[0].getAdjust_id()));
//		if(adjustLists.length!=out_num_total){
//			throw new BusinessException("设备没有全部到货，确定要结束这个调拨单吗?");
//		}
//		
//	}
	
	public Pager<AdjustVO> queryPageVO(Pager<AdjustVO> page) {
		//List<Store> stores=storeRepository.queryAll();
		
		Pager<AdjustVO> results=adjustRepository.queryPageVO(page);
		List<AdjustVO> list=results.getRoot();
		for(AdjustVO adjustVO:list){
//			for(Store store:stores){
//				if(store.getId().equals(adjustVO.getStr_out_id())){
//					adjustVO.setStr_out_name(store.getName());
//				} else if(store.getId().equals(adjustVO.getStr_in_id())){
//					adjustVO.setStr_in_name(store.getName());
//				}
//			}
			if(orgService.get(adjustVO.getStr_out_id())!=null){
				adjustVO.setStr_out_name(orgService.get(adjustVO.getStr_out_id()).getName());
			}
			if(orgService.get(adjustVO.getStr_in_id())!=null){
				adjustVO.setStr_in_name(orgService.get(adjustVO.getStr_in_id()).getName());
			}
		}
		return results;
	}
	public List<AdjustListVO> queryList(String adjust_id) {
		return adjustRepository.query4InStoreList(adjust_id);
	}

	/**
	 * 只有在途和noreturn状态的调拨单才可以转
	 * @author mawujun 16064988@qq.com 
	 * @param adjust_id
	 */
	public void change2installout(String adjust_id) {
		Adjust adjust=adjustRepository.get(adjust_id);
		//入过本来就是领用单，就不进行转换
		if(adjust.getAdjustType()==AdjustType.installout){
			throw new BusinessException("该调拨单已经是领用单，不需要转!");
		}
		if(adjust.getAdjustType()!=AdjustType.borrow){
			throw new BusinessException("该调拨单不是借用单，不能转!");
		}
		
		//如果该借用单已经有过入库了，但还有一部分设备没有入库的时候，那是不能转的，先把那部分设备处理掉
		if(adjust.getStatus()==AdjustStatus.partin){
			throw new BusinessException("设备还没有完全入库，请先完全入库后，再转为领用!");
		}
		//已经有部分归还的，也不能转
		if(adjust.getReturnStatus()==AdjustReturnStatus.partreturn){
			throw new BusinessException("设备已经部分归还，不能转为领用!");
		}
		
		//如果已经结束了，也不能转
		if(adjust.getStatus()==AdjustStatus.over){
			throw new BusinessException("该调拨单已经结束，不能转为领用!");
		}
		
		//入过当前用户没有权限操作这个仓库，那也不能转换,只有发货仓库才有这个权限
		if(userRepository.check_edit_store_permission(ShiroUtils.getUserId(), adjust.getStr_out_id())<=0){
			throw new BusinessException("你不是发货仓库，没有权限把该调拨单转换为领用单!");
		}
		
		adjust.setAdjustType(AdjustType.installout);
		//adjust.setStatus(AdjustStatus.over);
		adjustRepository.update(adjust);
	}
	
	public List<AdjustVO> queryBorrowAdjuest() {
		List<AdjustVO> list=adjustRepository.queryBorrowAdjuest(ShiroUtils.getUserId());
		for(AdjustVO adjustVO:list){
			if(orgService.get(adjustVO.getStr_out_id())!=null){
				adjustVO.setStr_out_name(orgService.get(adjustVO.getStr_out_id()).getName());
			}
			if(orgService.get(adjustVO.getStr_in_id())!=null){
				adjustVO.setStr_in_name(orgService.get(adjustVO.getStr_in_id()).getName());
			}

		}
		return list;
	}
}
