package com.mawujun.store;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeansException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mawujun.baseinfo.Equipment;
import com.mawujun.baseinfo.EquipmentStatus;
import com.mawujun.baseinfo.EquipmentVO;
import com.mawujun.cache.CacheMgr;
import com.mawujun.cache.EquipKey;
import com.mawujun.cache.EquipScanType;
import com.mawujun.controller.spring.mvc.ResultModel;
import com.mawujun.exception.BusinessException;
import com.mawujun.utils.M;
import com.mawujun.utils.bean.BeanUtils;
import com.mawujun.utils.page.Pager;
/**
 * @author mawujun qq:16064988 e-mail:16064988@qq.com 
 * @version 1.0
 * @since 1.0
 */
@Controller
//@RequestMapping("/inStore")
public class InStoreController {

	@Resource
	private InStoreService inStoreService;
	@Resource
	private OrderService orderService;

	@Resource
	private CacheMgr cacheMgr;

	/**
	 * 主要用于新品入库的时候
	 * @author mawujun 16064988@qq.com 
	 * @param ecode
	 * @return
	 */
	@RequestMapping("/inStore/getEquipFromBarcode.do")
	@ResponseBody
	public ResultModel getEquipFromBarcode(String ecode,String store_id,Long checkDate) {	
		EquipKey key=EquipKey.getInstance(EquipScanType.newInStore, store_id,checkDate);
		EquipmentVO equipmentVO=(EquipmentVO)cacheMgr.getQrcode(key, ecode);
		if(equipmentVO!=null){
//			JsonConfigHolder.setSuccessValue(false);
//			JsonConfigHolder.setMsg("设备已经扫过!");
//			return equipmentVO;
			return ResultModel.getInstance().setRoot(equipmentVO).setSuccess(false).setMsg("设备已经扫过!");
		}
		
		if(inStoreService.checkEquipmentExist(ecode)){
			//下面的if(equipmentVO.getStatus()!=EquipmentStatus.no_storage)也是做同样的判断
			throw new BusinessException("该设备已经入过库了，不能重复入库!");
		}
		
		
		equipmentVO= orderService.getEquipFromBarcode(ecode);
		
		
		if(equipmentVO!=null){
			if(store_id==null || !equipmentVO.getStore_id().equals(store_id)){
				throw new BusinessException("该条码不能入库到所选择的仓库!");
			}
			if(equipmentVO.getStatus()!=EquipmentStatus.no_storage){//这是新设备入库的情况
				//Ext.Msg.alert("消息","该设备为非新增设备,不能添加到入库列表.");
				throw new BusinessException("该设备为非新增设备,不能添加到入库列表!");
			}
			cacheMgr.putQrcode(key, equipmentVO);
			
			//Integer total=cacheMgr.getQrcodesAll(key).length;
			//JsonConfigHolder.setTotal(total);
			
			return ResultModel.getInstance().setRoot(equipmentVO).setSuccess(true);
			//return equipmentVO;
		} else {
			//return new EquipmentVO();
			throw new BusinessException("该条码的设备不存在或者已经失效，请重新导出打印!");
		}
		
	}
	@RequestMapping("/inStore/removeEquipFromCache.do")
	@ResponseBody
	public String removeEquipFromCache(String ecode,String store_id,Long checkDate) {	
		cacheMgr.removeQrcode(EquipKey.getInstance(EquipScanType.newInStore, store_id,checkDate),ecode);
		return "{success:true}";
	}
	@RequestMapping("/inStore/clearEquipFromCache.do")
	@ResponseBody
	public String clearEquipFromCache(String store_id,Long checkDate) {	
		cacheMgr.clearQrcode(EquipKey.getInstance(EquipScanType.newInStore, store_id,checkDate));
		return "{success:true}";
	}
	
	/**
	 * 查询缓存中的数据
	 * @author mawujun email:160649888@163.com qq:16064988
	 * @param store_id
	 * @return
	 */
	@RequestMapping("/inStore/refreshEquipFromCache.do")
	@ResponseBody
	public IEcodeCache[] refreshEquipFromCache(String store_id,Long checkDate) {	
		if(store_id==null){
			return new EquipmentVO[0];
		}
		IEcodeCache[] result = cacheMgr.getQrcodesAll(EquipKey.getInstance(EquipScanType.newInStore, store_id,checkDate));
		if(result==null){
			//return new InstallOutListVO[0];
			return new InStoreListVO[0];
		}
		return result;
	}
//	/**
//	 * 查询缓存中的数据
//	 * @author mawujun email:160649888@163.com qq:16064988
//	 * @param store_id
//	 * @return
//	 */
//	@RequestMapping("/inStore/queryEquipFromCache.do")
//	@ResponseBody
//	public Page queryEquipFromCache(String store_id,Integer start,Integer limit,Long checkDate) {	
//		if(store_id==null){
//			return new Page();
//		}
//		Page list=cacheMgr.getQrcodes(EquipKey.getInstance(EquipScanType.newInStore, store_id,checkDate),start,limit);
//		if(list==null){
//			return new Page();
//		}
//		return list;
//	}
	
	
	@RequestMapping("/inStore/newInStore.do")
	@ResponseBody
	//public String newInStore(@RequestBody Equipment[] equipments,InStore inStore,Long checkDate) throws  IOException, IllegalAccessException, InvocationTargetException, BeansException, IntrospectionException{
	public String newInStore(InStore inStore,Long checkDate) throws  IOException, IllegalAccessException, InvocationTargetException, BeansException, IntrospectionException{
		//inStoreService.newInStore(equipments,inStore);
		EquipKey key=EquipKey.getInstance(EquipScanType.newInStore, inStore.getStore_id(),checkDate);
		IEcodeCache[] equipmentVOs=cacheMgr.getQrcodesAll(key);
		Equipment[] equipments=new Equipment[equipmentVOs.length];
		int i=0;
		for(IEcodeCache equipmentVO:equipmentVOs){
			//org.apache.commons.beanutils.BeanUtils.copyProperties(dest, orig);
			equipments[i]=new Equipment();
			BeanUtils.copyExcludeNull(equipmentVO,equipments[i]);
			i++;
		}
		inStoreService.newInStore(equipments, inStore);
		
		cacheMgr.clearQrcode(key);
		return "{success:true}";
	}
	
	/**
	 * 入库单报表查询
	 * @author mawujun email:160649888@163.com qq:16064988
	 * @return
	 */
	@RequestMapping("/inStore/query.do")
	@ResponseBody
	public Pager<InStore> query(Integer start,Integer limit,String operateDate_start,String operateDate_end,String store_id) {
		Pager<InStore> page=new Pager<InStore>();//Page.getInstance(start, limit);
		page.setStart(start);
		page.setLimit(limit);
		page.addParam("operateDate_start", operateDate_start);
		page.addParam("operateDate_end", operateDate_end);
		page.addParam(M.InStore.store_id, store_id);
		page=inStoreService.queryPage(page);
		return page;
	}
	/**
	 * 查询某个入库单的明细
	 * @author mawujun email:160649888@163.com qq:16064988
	 * @param inStore_id
	 * @return
	 */
	@RequestMapping("/inStore/queryList.do")
	@ResponseBody
	public List<InStoreListVO> queryList(String inStore_id) {
		List<InStoreListVO> inStoreListes=inStoreService.queryList(inStore_id);
		return inStoreListes;
	}
}
