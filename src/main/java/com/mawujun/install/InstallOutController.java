package com.mawujun.install;
import java.beans.IntrospectionException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;

import org.springframework.beans.BeansException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mawujun.cache.CacheMgr;
import com.mawujun.cache.EquipKey;
import com.mawujun.cache.EquipScanType;
import com.mawujun.controller.spring.mvc.ResultModel;
import com.mawujun.exception.BusinessException;
import com.mawujun.store.IEcodeCache;
import com.mawujun.utils.M;
import com.mawujun.utils.bean.BeanUtils;
import com.mawujun.utils.page.Pager;
import com.mawujun.utils.string.StringUtils;
/**
 * @author mawujun qq:16064988 e-mail:16064988@qq.com 
 * @version 1.0
 * @since 1.0
 */
@Controller
//@RequestMapping("/installOut")
public class InstallOutController {
	@Resource
	private InstallOutService installOutStoreService;
	//@Resource
	//private EquipmentService equipmentService;
	@Resource
	private CacheMgr cacheMgr;

	/**
	 * 主要用于新品入库的时候
	 * @author mawujun 16064988@qq.com 
	 * @param ecode
	 * @return
	 */
	@RequestMapping("/installOut/getEquipmentByEcode.do")
	@ResponseBody
	public ResultModel getEquipmentByEcode(String ecode,String store_id,Long checkDate,String installOutType_content,String installOutType_id,String installOutType_name) {	
		EquipKey key=EquipKey.getInstance(EquipScanType.installout, store_id,checkDate);
		IEcodeCache equipmentVO=cacheMgr.getQrcode(key, ecode);
		if(equipmentVO!=null){
			//JsonConfigHolder.setSuccessValue(false);
			//JsonConfigHolder.setMsg("设备已经扫过!");
			//return (InstallOutListVO)equipmentVO;
			throw new BusinessException("备已经扫过!");
		}
		
		InstallOutListVO equipment= installOutStoreService.getInstallOutListVOByEcode(ecode,store_id);
//		if(equipment==null){
//			//equipment=new Equipment();
//			//equipment.setStatus(0);
//			throw new BusinessException("对不起，该条码对应的设备不存在，或者该设备挂在其他仓库中,或者设备状态不对!");
//		}
		equipment.setInstallOutType_id(installOutType_id);
		equipment.setInstallOutType_name(installOutType_name);
		equipment.setInstallOutType_content(installOutType_content);

		cacheMgr.putQrcode(key, equipment);
		//System.out.println("===========================================================================");
		//System.out.println(equipment.getProd_spec());
		//System.out.println(installOutType_name);
		//System.out.println(installOutType_content);
		//equipment.setProd_spec(null);
		//equipment.setStyle(null);
		//System.out.println(JSON.toJSONString(equipment));
		
	
		return ResultModel.getInstance().setRoot(equipment);
	}
//	/**
//	 * 主要用于新品入库的时候
//	 * @author mawujun 16064988@qq.com 
//	 * @param ecode
//	 * @return
//	 */
//	@RequestMapping("/installOut/getEquipmentByEcode.do")
//	@ResponseBody
//	public EquipmentVO getEquipmentByEcode(String ecode,String store_id,Long checkDate) {	
//		EquipKey key=EquipKey.getInstance(EquipScanType.installout, store_id,checkDate);
//		EquipmentVO equipmentVO=cacheMgr.getQrcode(key, ecode);
//		if(equipmentVO!=null){
//			JsonConfigHolder.setSuccessValue(false);
//			JsonConfigHolder.setMsg("设备已经扫过!");
//			return equipmentVO;
//		}
//		
//		EquipmentVO equipment= equipmentService.getEquipmentByEcode_in_store(ecode,store_id);
//		if(equipment==null){
//			//equipment=new Equipment();
//			//equipment.setStatus(0);
//			throw new BusinessException("对不起，该条码对应的设备不存在，或者该设备挂在其他仓库中!");
//		}
//		//设备返库的时候，设备如果不是手持或损坏状态的话，就不能进行返库，说明任务没有扫描或者没有提交
//		if(equipment.getStatus()!=EquipmentStatus.in_storage){
//			throw new BusinessException("设备状态不是\"已入库\",不能领用该设备!");
//		}
//		cacheMgr.putQrcode(key, equipment);
//		return equipment;
//	}
	
	@RequestMapping("/installOut/removeEquipFromCache.do")
	@ResponseBody
	public String removeEquipFromCache(String ecode,String store_id,Long checkDate) {	
		cacheMgr.removeQrcode(EquipKey.getInstance(EquipScanType.installout, store_id,checkDate),ecode);
		return "{success:true}";
	}
	@RequestMapping("/installOut/clearEquipFromCache.do")
	@ResponseBody
	public String clearEquipFromCache(String store_id,Long checkDate) {	
		cacheMgr.clearQrcode(EquipKey.getInstance(EquipScanType.installout, store_id,checkDate));
		return "{success:true}";
	}
	
	@RequestMapping("/installOut/updateMemoFromCache.do")
	@ResponseBody
	public String updateMemoFromCache(String ecode,String store_id,Long checkDate,String memo) {	
		InstallOutListVO equipment=(InstallOutListVO)cacheMgr.getQrcode(EquipKey.getInstance(EquipScanType.installout, store_id,checkDate),ecode);
		equipment.setMemo(memo);
		return "{success:true}";
	}
	
	/**
	 * 查询缓存中的数据
	 * @author mawujun email:160649888@163.com qq:16064988
	 * @param store_id
	 * @return
	 */
	@RequestMapping("/installOut/refreshEquipFromCache.do")
	@ResponseBody
	public IEcodeCache[] refreshEquipFromCache(String store_id,Long checkDate) {	
		if(store_id==null){
			return new InstallOutListVO[0];
		}
		IEcodeCache[] result =cacheMgr.getQrcodesAll(EquipKey.getInstance(EquipScanType.installout, store_id,checkDate));
		if(result==null){
			return new InstallOutListVO[0];
		}
		return result;
	}
	
	/**
	 * 设备出库，设备领用
	 * @author mawujun 16064988@qq.com 
	 * @param equipments
	 * @return
	 * @throws IntrospectionException 
	 * @throws BeansException 
	 * @throws IOException
	 */
	@RequestMapping("/installOut/equipmentOutStoreSaveAndPrint.do")
	@ResponseBody
	//public String equipmentOutStoreSaveAndPrint(@RequestBody InstallOutList[] installOutListes, InstallOut outStore, String installOut_id) { 
	public String equipmentOutStoreSaveAndPrint(InstallOut installOut, String installOut_id,Long checkDate) throws BeansException, IntrospectionException { 
		
		EquipKey key=EquipKey.getInstance(EquipScanType.installout, installOut.getStore_id(),checkDate);
		IEcodeCache[] equipmentVOs=cacheMgr.getQrcodesAll(key);
		InstallOutList[] installOutListes=new InstallOutList[equipmentVOs.length];
		int i=0;
		for(IEcodeCache equipmentVO:equipmentVOs){
			//org.apache.commons.beanutils.BeanUtils.copyProperties(dest, orig);
			installOutListes[i]=new InstallOutList();
			BeanUtils.copyExcludeNull(equipmentVO,installOutListes[i]);
			i++;
		}
		String installOut_id_re=installOutStoreService.equipOutStoreSaveAndPrint(installOutListes, installOut,installOut_id);
		
		
		return installOut_id_re;
	}
	/**
	 * 设备出库，设备领用
	 * @author mawujun 16064988@qq.com 
	 * @param equipments
	 * @return
	 * @throws IntrospectionException 
	 * @throws BeansException 
	 * @throws IOException
	 */
	@RequestMapping("/installOut/equipmentOutStore.do")
	@ResponseBody
	//public String equipOutStore(@RequestBody InstallOutList[] installOutListes, InstallOut outStore , String installOut_id) { 
	public String equipOutStore( InstallOut installOut , String installOut_id,Long checkDate) throws BeansException, IntrospectionException { 
		EquipKey key=EquipKey.getInstance(EquipScanType.installout, installOut.getStore_id(),checkDate);
		IEcodeCache[] equipmentVOs=cacheMgr.getQrcodesAll(key);
		InstallOutList[] installOutListes=new InstallOutList[equipmentVOs.length];
		int i=0;
		for(IEcodeCache equipmentVO:equipmentVOs){
			//org.apache.commons.beanutils.BeanUtils.copyProperties(dest, orig);
			installOutListes[i]=new InstallOutList();
			BeanUtils.copyExcludeNull(equipmentVO,installOutListes[i]);
			i++;
		}
		installOut_id=installOutStoreService.equipOutStore(installOutListes, installOut,installOut_id);
		cacheMgr.clearQrcode(key);
		return installOut_id;
	}
	@RequestMapping("/installOut/qeryEditInstallOutListVO.do")
	@ResponseBody
	public List<InstallOutListVO> qeryEditInstallOutListVO(String installOut_id,String store_id,Long checkDate) { 
		if(!StringUtils.hasText(installOut_id)){
			throw new BusinessException("请先选择一条单据!");
		}
		List<InstallOutListVO> page=installOutStoreService.queryList(installOut_id);
		
		EquipKey key=EquipKey.getInstance(EquipScanType.installout, store_id,checkDate);
		cacheMgr.clearQrcode(key);
		for(InstallOutListVO equipment:page){
			cacheMgr.putQrcode(key, equipment);
		}
		
		
		return page;
	}
//	
//	/**
//	 * 设备出库，设备领用
//	 * @author mawujun 16064988@qq.com 
//	 * @param equipments
//	 * @return
//	 * @throws IOException
//	 */
//	@RequestMapping("/installOut/equipmentOutStoreSaveAndPrint.do")
//	@ResponseBody
//	public String equipmentOutStoreSaveAndPrint(@RequestBody InstallOutList[] installOutListes, InstallOut outStore, String installOut_id) { 
//		
//			
//		String installOut_id_re=installOutStoreService.equipOutStoreSaveAndPrint(installOutListes, outStore,installOut_id);
//		
//		
//		return installOut_id_re;
//	}
//	/**
//	 * 设备出库，设备领用
//	 * @author mawujun 16064988@qq.com 
//	 * @param equipments
//	 * @return
//	 * @throws IOException
//	 */
//	@RequestMapping("/installOut/equipmentOutStore.do")
//	@ResponseBody
//	public String equipOutStore(@RequestBody InstallOutList[] installOutListes, InstallOut outStore , String installOut_id) { 
//		
//		installOut_id=installOutStoreService.equipOutStore(installOutListes, outStore,installOut_id);
//		return installOut_id;
//	}
	SimpleDateFormat yyyyMMdd=new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 设备出库，设备领用
	 * @author mawujun 16064988@qq.com 
	 * @param equipments
	 * @return
	 * @throws JRException 
	 * @throws IOException
	 */
	@RequestMapping("/installOut/equipmentOutStorePrint.do")
	@ResponseBody
	public void equipmentOutStorePrint(HttpServletRequest request,HttpServletResponse response,String installOut_id) throws  IOException, JRException { 
		
		InstallOutVO installOutVO=installOutStoreService.getInstallOutVO(installOut_id);
		List<InstallOutListVO> installOutListes=installOutStoreService.queryList(installOut_id);
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("project_name", installOutVO.getProject_name()); 
		params.put("installout_id", installOutVO.getId());  
		params.put("workunit_name", installOutVO.getWorkUnit_name()); 
		params.put("operater_name", installOutVO.getOperater_name());//仓管姓名
		params.put("operateDate", yyyyMMdd.format(installOutVO.getOperateDate()));
		params.put("totalnum", installOutListes.size());  
		
		
//		List<InstallOutListVO> installOutListes=new ArrayList<InstallOutListVO>();
//		for(int i=0;i<20;i++){
//			InstallOutListVO a=new InstallOutListVO();
//			a.setBrand_name(i+"品牌");
//			a.setEcode("020202-**-1506260006");
//			a.setInstallOut_id(i+"");
//			a.setProd_name(i+"品名品名品名品名品名品名品名品名品名品名sdfetlkjsdfnejkksdjfljl");
//			a.setSubtype_name(i+"小类小类小类小类小类小类小类小类");
//			a.setStyle(i+"DS-2CD4012F(A/W/P/SDI/FC)detgtdsfsdfe4ddferereertgthgh");
//			a.setProd_unit("台");
//			installOutListes.add(a);
//		}
//		Map<String,Object> params=new HashMap<String,Object>();
//		params.put("project_name", "111111"); 
//		params.put("installout_id", "20150630124512001");  
//		params.put("workunit_name", "哈哈哈哈"); 
//		params.put("operater_name", "哈哈哈哈");//仓管姓名
//		params.put("operateDate", "2015-07-01");
		
		
		
		JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(installOutListes);
		

		
		
		String JASPER_FILE_NAME=request.getSession().getServletContext().getRealPath("/install/report/installout.jasper");
		//JasperReport jasperReport = (JasperReport)JRLoader.loadObject(JASPER_FILE_NAME);
		File reportFile=new File(JASPER_FILE_NAME);
		InputStream in=new FileInputStream(reportFile);
		JasperPrint print = JasperFillManager.fillReport(in, params, dataSource);
		
		response.setContentType("application/pdf;charset=UTF-8");
		OutputStream outputStream=response.getOutputStream();
		JRPdfExporter exporter = new JRPdfExporter(); 
		SimpleOutputStreamExporterOutput simpleOutputStreamExporterOutput=new SimpleOutputStreamExporterOutput(outputStream);
		exporter.setExporterOutput(simpleOutputStreamExporterOutput);
		
		SimplePdfReportConfiguration simplePdfReportConfiguration=new SimplePdfReportConfiguration();
		exporter.setConfiguration(simplePdfReportConfiguration);
		
		SimpleExporterInput simpleExporterInput=new SimpleExporterInput(print);
		exporter.setExporterInput(simpleExporterInput);
		// 导出
		exporter.exportReport();
		outputStream.close();
		
		

		
//		// 使用JRHtmlExproter导出Html格式
//		response.setContentType("text/html;charset=UTF-8");
//		OutputStream outputStream=response.getOutputStream();
//		HtmlExporter exporter = new HtmlExporter();
//		//exporter.getCurrentJasperPrint()
//		SimpleHtmlExporterOutput simpleHtmlExporterOutput=new SimpleHtmlExporterOutput(outputStream,"UTF-8");
//		exporter.setExporterOutput(simpleHtmlExporterOutput);
//		
//		SimpleHtmlReportConfiguration simpleHtmlReportConfiguration=new SimpleHtmlReportConfiguration();
//		exporter.setConfiguration(simpleHtmlReportConfiguration);
//		
//		//SimpleExporterInputItem simpleExporterInputItem=new SimpleExporterInputItem(print);
//		SimpleExporterInput simpleExporterInput=new SimpleExporterInput(print);
//		exporter.setExporterInput(simpleExporterInput);
//		// 导出
//		exporter.exportReport();
//		outputStream.close();

	}
	
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
	@RequestMapping("/installOut/queryMain.do")
	@ResponseBody
	public Pager<InstallOut> queryMain(Integer start,Integer limit,String operateDate_start,String operateDate_end,String store_id,String workUnit_id,String project_id) { 
		Pager<InstallOut> page=new Pager<InstallOut>();//Page.getInstance(start, limit);
		page.setStart(start);
		page.setLimit(limit);
		page.addParam("operateDate_start", operateDate_start);
		page.addParam("operateDate_end", operateDate_end);
		page.addParam(M.InstallOut.store_id, store_id);
		page.addParam(M.InstallOut.workUnit_id, workUnit_id);
		//page.addParam(M.InstallOut.installOutType_id, installOutType_id);
		page.addParam(M.InstallOut.project_id, project_id);
		page=installOutStoreService.queryMain(page);
		return page;
	}
	
	@RequestMapping("/installOut/queryList.do")
	@ResponseBody
	public List<InstallOutListVO> queryList(String installOut_id) { 
		if(!StringUtils.hasText(installOut_id)){
			throw new BusinessException("请先选择一条单据!");
		}
		List<InstallOutListVO> page=installOutStoreService.queryList(installOut_id);
		return page;
	}
	
	/**
	 * 查询正在编辑状态的所有领用单
	 * @author mawujun email:160649888@163.com qq:16064988
	 * @param start
	 * @param limit
	 * @param operateDate_start
	 * @param operateDate_end
	 * @param store_id
	 * @param workUnit_id
	 * @param project_id
	 * @return
	 */
	@RequestMapping("/installOut/queryEditInstallOut.do")
	@ResponseBody
	public ResultModel queryEditInstallOut() { 
		
		return ResultModel.getInstance().setRoot(installOutStoreService.queryEditInstallOut());
	}
	
	@RequestMapping("/installOut/deleteEditInstallOut.do")
	@ResponseBody
	public String deleteEditInstallOut(String id) { 
		installOutStoreService.deleteEditInstallOut(id);
		return "{success:true}";
	}
	
	/**
	 * 查询差异化的领用设备，因为一种情况就是一个设备领出去后，过了几个月才安装的
	 * @author mawujun email:160649888@163.com qq:16064988
	 * @param installOut_id
	 * @return
	 */
	@RequestMapping("/installOut/queryDifference.do")
	@ResponseBody
	public Pager<InstallOutList> queryDifference(Integer start,Integer limit
			,String operateDate_start,String operateDate_end
			,String returnDate_start,String returnDate_end,String store_id,String workUnit_id
			,String project_id
			,String type_id
			,String subtype_id) { 
		//Page page=Page.getInstance(start, limit);
		Pager<InstallOutList> page=new Pager<InstallOutList>();
		page.setStart(start);
		page.setLimit(limit);
		page.addParam("operateDate_start", operateDate_start);
		page.addParam("operateDate_end", operateDate_end);
		page.addParam("returnDate_start", returnDate_start);
		page.addParam("returnDate_end", returnDate_end);
		page.addParam(M.InstallOut.store_id, store_id);
		page.addParam(M.InstallOut.workUnit_id, workUnit_id);
		//page.addParam(M.InstallOut.installOutType_id, installOutType_id);
		//page.addParam(M.InstallOut.project_id, project_id);
		page.addParam("type_id", type_id);
		page.addParam(M.Equipment.subtype_id, subtype_id);
		page=installOutStoreService.queryDifference(page);
		return page;
	}
}
