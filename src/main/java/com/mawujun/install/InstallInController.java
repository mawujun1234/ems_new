package com.mawujun.install;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mawujun.baseinfo.EquipmentService;
import com.mawujun.controller.spring.mvc.ResultModel;
import com.mawujun.exception.BusinessException;
import com.mawujun.utils.M;
import com.mawujun.utils.page.Pager;
import com.mawujun.utils.string.StringUtils;
/**
 * @author mawujun qq:16064988 e-mail:16064988@qq.com 
 * @version 1.0
 * @since 1.0
 */
@Controller
//@RequestMapping("/installIn")
public class InstallInController {
	private static Logger logger=LogManager.getLogger(InstallInController.class);

	@Resource
	private InstallInService installInService;
	@Resource
	private EquipmentService equipmentService;


//	/**
//	 * 请按自己的需求修改
//	 * @author mawujun email:16064988@163.com qq:16064988
//	 * @param id 是父节点的id
//	 * @return
//	 */
//	@RequestMapping("/installIn/query.do")
//	@ResponseBody
//	public List<InstallIn> query(String id) {
//		Cnd cnd=Cnd.select().andEquals(M.InstallIn.parent.id, "root".equals(id)?null:id);
//		List<InstallIn> installInes=installInService.query(cnd);
//		//JsonConfigHolder.setFilterPropertys(InstallIn.class,M.InstallIn.parent.name());
//		return installInes;
//	}
//
//	/**
//	 * 这是基于分页的几种写法,的例子，请按自己的需求修改
//	 * @author mawujun email:16064988@163.com qq:16064988
//	 * @param start
//	 * @param limit
//	 * @param userName
//	 * @return
//	 */
//	@RequestMapping("/installIn/query.do")
//	@ResponseBody
//	public Page query(Integer start,Integer limit,String sampleName){
//		Page page=Page.getInstance(start,limit);//.addParam(M.InstallIn.sampleName, "%"+sampleName+"%");
//		return installInService.queryPage(page);
//	}

//	@RequestMapping("/installIn/query.do")
//	@ResponseBody
//	public List<InstallIn> query() {	
//		List<InstallIn> installInes=installInService.queryAll();
//		return installInes;
//	}
//	
//
//	@RequestMapping("/installIn/load.do")
//	public InstallIn load(String id) {
//		return installInService.get(id);
//	}
//	
//	@RequestMapping("/installIn/create.do")
//	@ResponseBody
//	public InstallIn create(@RequestBody InstallIn installIn) {
//		installInService.create(installIn);
//		return installIn;
//	}
//	
//	@RequestMapping("/installIn/update.do")
//	@ResponseBody
//	public  InstallIn update(@RequestBody InstallIn installIn) {
//		installInService.update(installIn);
//		return installIn;
//	}
//	
//	@RequestMapping("/installIn/deleteById.do")
//	@ResponseBody
//	public String deleteById(String id) {
//		installInService.deleteById(id);
//		return id;
//	}
//	
//	@RequestMapping("/installIn/destroy.do")
//	@ResponseBody
//	public InstallIn destroy(@RequestBody InstallIn installIn) {
//		installInService.delete(installIn);
//		return installIn;
//	}
	
	/**
	 * 
	 * @author mawujun 16064988@qq.com 
	 * @param start
	 * @param limit
	 * @param operateDate_start
	 * @param operateDate_end
	 * @param store_id
	 * @param type 是出库单还是入库单
	 * @return
	 */
	@RequestMapping("/installIn/queryMain.do")
	@ResponseBody
	public Pager<InstallIn> queryMain(Integer start,Integer limit,String operateDate_start,String operateDate_end,String store_id,String workUnit_id,String type) { 
		//Page page=Page.getInstance(start, limit);
		Pager<InstallIn> page=new Pager<InstallIn>();
		page.setStart(start);
		page.setLimit(limit);
		page.addParam("operateDate_start", operateDate_start);
		page.addParam("operateDate_end", operateDate_end);
		page.addParam(M.InstallIn.store_id, store_id);
		page.addParam(M.InstallIn.workUnit_id, workUnit_id);
		page.addParam(M.InstallIn.type, type);
		page=installInService.queryMain(page);
		return page;
	}
	
	@RequestMapping("/installIn/queryList.do")
	@ResponseBody
	public List<InstallInListVO> queryList(String installIn_id) { 
		if(!StringUtils.hasText(installIn_id)){
			throw new BusinessException("请先选择一条单据!");
		}
		List<InstallInListVO> page=installInService.queryList(installIn_id);
		return page;
	}
	
	/**
	 * 在领用返回的时候，或旧设备返库的时候，获取设备信息的
	 * @author mawujun email:160649888@163.com qq:16064988
	 * @param ecode
	 * @param workunit_id
	 * @return
	 */
	@RequestMapping("/installIn/getEquipmentByEcode.do")
	@ResponseBody
	public ResultModel getEquipmentByEcode(String ecode,String workunit_id) {
		InstallInListVO equipment= installInService.getEquipmentByEcode(ecode,workunit_id);

		//return equipment;
		return ResultModel.getInstance().setRoot(equipment);
	}
	
	@RequestMapping("/installIn/equipmentInStore.do")
	@ResponseBody
	public String equipmentInStore(@RequestBody InstallInList[] installInLists,InstallIn installin) { 
		//乱码，解决方案，浏览器有没有进行转换，2使用一个主体进行封装在一起，然后再传送过来
		//3：还有其他有备注的地方都这样修改
		logger.info("========================{}",installin.getMemo());
		installInService.equipmentInStore(installInLists, installin);
		return "{success:true}";
	}
	
	
}
