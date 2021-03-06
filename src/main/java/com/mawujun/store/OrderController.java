package com.mawujun.store;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mawujun.baseinfo.EquipmentProdService;
import com.mawujun.controller.spring.mvc.MapParams;
import com.mawujun.permission.ShiroUtils;
import com.mawujun.utils.M;
import com.mawujun.utils.page.Pager;
import com.mawujun.utils.page.Params;
import com.mawujun.utils.string.StringUtils;
/**
 * @author mawujun qq:16064988 e-mail:16064988@qq.com 
 * @version 1.0
 * @since 1.0
 */
@Controller
//@RequestMapping("/order")
public class OrderController {

	@Resource
	private OrderService orderService;
	@Resource
	private EquipmentProdService equipmentProdService;


//	/**
//	 * 请按自己的需求修改
//	 * @author mawujun email:16064988@163.com qq:16064988
//	 * @param id 是父节点的id
//	 * @return
//	 */
//	@RequestMapping("/order/query.do")
//	@ResponseBody
//	public List<Order> query(String id) {
//		Cnd cnd=Cnd.select().andEquals(M.Order.parent.id, "root".equals(id)?null:id);
//		List<Order> orderes=orderService.query(cnd);
//		//JsonConfigHolder.setFilterPropertys(Order.class,M.Order.parent.name());
//		return orderes;
//	}

//	/**
//	 * 这是基于分页的几种写法,的例子，请按自己的需求修改
//	 * @author mawujun email:16064988@163.com qq:16064988
//	 * @param start
//	 * @param limit
//	 * @param userName
//	 * @return
//	 */
//	@RequestMapping("/order/query.do")
//	@ResponseBody
//	public Page query(Integer start,Integer limit,String sampleName){
//		Page page=Page.getInstance(start,limit);//.addParam(M.Order.sampleName, "%"+sampleName+"%");
//		return orderService.queryPage(page);
//	}
	
	/**
	 * 根据订单号，查询所有的订单明细
	 * @author mawujun email:160649888@163.com qq:16064988
	 * @param orderNo
	 * @return
	 */
	@RequestMapping("/order/queryMain.do")
	@ResponseBody
	public Pager<Order> queryMain(Integer start,Integer limit,String store_id,String date_start,String date_end,String orderNo
			,String instore_date_start,String instore_date_end
			,String project_id,String supplier_id,String status,String orderType) {
		Pager<Order> page=new Pager<Order>();//Page.getInstance(start,limit);
		page.setStart(start);
		page.setLimit(limit);
		page.addParam(M.Order.store_id, store_id);
		if(orderNo!=null && !"".equals(orderNo.trim())){
			page.addParam(M.Order.orderNo, "%"+orderNo+"%");
		}
		page.addParam(M.Order.project_id, project_id);
		page.addParam(M.Order.supplier_id, supplier_id);
		page.addParam("date_start", date_start);
		page.addParam("date_end", date_end);
		page.addParam("instore_date_start", instore_date_start);
		page.addParam("instore_date_end", instore_date_end);
		page.addParam(M.Order.status, status);
		page.addParam("user_id", ShiroUtils.getAuthenticationInfo().getId());
		page.addParam("orderType", orderType);
		Pager<Order> orderes=orderService.queryMain(page);
		return orderes;
	}

	/**
	 * 根据订单号，查询所有的订单明细
	 * @author mawujun email:160649888@163.com qq:16064988
	 * @param orderNo
	 * @return
	 */
	@RequestMapping("/order/queryList.do")
	@ResponseBody
	public List<OrderListVO> queryList(Integer start,Integer limit,String order_id) {	
		//Page page=Page.getInstance(start,limit);
		//page.addParam(M.OrderList.order_id, order_id);
		
		List<OrderListVO> orderlists=orderService.queryList(order_id);
		return orderlists;
	}
	
	@RequestMapping("/order/download.do")
	@ResponseBody
	public void download(String store_id,String date_start,String date_end,String orderNo
			,String instore_date_start,String instore_date_end
			,String project_id,String supplier_id,String status,String orderType,HttpServletResponse response) throws  IOException{
		//orderService.addList(orderList);
		MapParams params=new MapParams();
		params.addParam(M.Order.store_id, store_id);
		if(orderNo!=null && !"".equals(orderNo.trim())){
			params.addParam(M.Order.orderNo, "%"+orderNo+"%");
		}
		params.addParam(M.Order.project_id, project_id);
		params.addParam(M.Order.supplier_id, supplier_id);
		params.addParam("date_start", date_start);
		params.addParam("date_end", date_end);
		params.addParam("instore_date_start", instore_date_start);
		params.addParam("instore_date_end", instore_date_end);
		params.addParam(M.Order.status, status);
		params.addParam("user_id", ShiroUtils.getAuthenticationInfo().getId());
		params.addParam("orderType", orderType);
		
		
		XSSFWorkbook wb = new XSSFWorkbook();    
		Sheet sheet1 = wb.createSheet("资料");
		
		LinkedHashMap<String,String> titles=new LinkedHashMap<String,String>();
		titles.put("ORDERNO", "订单号");
		titles.put("STORE_NAME", "仓库");
		titles.put("STATUS", "状态");
		titles.put("PROJECT_NAME", "项目");
		titles.put("OPERATER", "操作者");
		titles.put("SUPPLIER_NAME", "供应商");
		titles.put("ORDERTYPE", "订单类型");
		titles.put("ORDERDATE", "订购日期");
		titles.put("SUBTYPE_NAME", "设备类型");
		titles.put("PROD_NAME", "品名");
		titles.put("BRAND_NAME", "品牌");
		titles.put("PROD_STYLE", "型号");
		titles.put("QUALITY_MONTH", "质保");
		titles.put("PROD_SPEC", "描述");
		titles.put("PROD_UNIT", "单位");
		titles.put("ORDERNUM", "订购数量");
		titles.put("UNITPRICE", "单价");
		titles.put("CREATEDATE", "订单创建日期");
//		titles.put("", "");
//		titles.put("", "");
//		titles.put("", "");
//		titles.put("", "");
//		titles.put("", "");
//		titles.put("", "");
//		titles.put("", "");
//		titles.put("", "");
//		titles.put("", "");
//		titles.put("", "");



		List<Map<String, Object>> list = orderService.download(params);
		crreateTitle_export(wb,sheet1,titles);
		
		crreateData_exportSample(wb,sheet1,titles,list);

		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");    
        response.setHeader("Content-disposition", "attachment;filename="+new String("订单导出记录".getBytes(),"ISO8859-1")+".xlsx");    
        OutputStream ouputStream = response.getOutputStream();    
        wb.write(ouputStream);    
        ouputStream.flush();    
        ouputStream.close();   

	}
	
	private void crreateTitle_export(XSSFWorkbook wb, Sheet sheet1, LinkedHashMap<String, String> titles) {
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cellStyle.setFillForegroundColor(HSSFColor.YELLOW.index);

		Font font = wb.createFont();
		// font.setFontHeightInPoints(18);
		font.setFontName("Courier New");
		cellStyle.setFont(font);

		Row title = sheet1.createRow(0);

		int i = 0;
		for (Entry<String, String> entry : titles.entrySet()) {
			Cell cell = title.createCell(i);
			cell.setCellValue(entry.getValue());
			cell.setCellStyle(cellStyle);
			i++;
		}

	}

	private void crreateData_exportSample(XSSFWorkbook wb, Sheet sheet1, LinkedHashMap<String, String> titles,
			List<Map<String,Object>> list) {
		//List<Map<String, Object>> list = sampleDesignService.query_exportSample(params);
		if (list == null || list.size() == 0) {
			return;
		}
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			Row row = sheet1.createRow(i + 1);
			int j = 0;
			for (Entry<String, String> entry : titles.entrySet()) {
				Cell cell = row.createCell(j);
				j++;
				if (map.get(entry.getKey()) != null) {
					cell.setCellValue(map.get(entry.getKey()).toString());
				}
			}
		}
	}
	
	/**
	 * 根据订单号，查询所有的订单明细，用在条码打印的时候
	 * @author mawujun email:160649888@163.com qq:16064988
	 * @param parent_id 品名的父id
	 * @return
	 */
	@RequestMapping("/order/queryList4Barcode.do")
	@ResponseBody
	public List<OrderListVO> queryList4Barcode(String order_id) {	
		//Page page=Page.getInstance(start,limit);
		//page.addParam(M.OrderList.order_id, order_id);
		if(!StringUtils.hasText(order_id)){
			return new ArrayList<OrderListVO>();
		}
		List<OrderListVO> orderlists=null;
		Params params=Params.init().add(M.OrderList.order_id, order_id);
//		if(StringUtils.hasText(parent_id)){
//			params.addIf(M.EquipmentProd.parent_id, parent_id);
//			//当点击查看套件内容的时候
//			orderlists=orderService.queryList4Barcode_tj_children(params);
//		} else {
			orderlists=orderService.queryList4Barcode(params);
//		}
		
		
		return orderlists;
	}


	
	/**
	 * 查询该用户可以编辑的仓库的所有订单,而且订单还咩有全部入库
	 * @author mawujun email:160649888@163.com qq:16064988
	 * @return
	 */
	@RequestMapping("/order/queryUncomplete.do")
	@ResponseBody
	public List<Order> queryUncomplete(String orderNo,String project_id) {
		if(StringUtils.hasText(orderNo)){
			orderNo="%"+orderNo+"%";
		} 
		return orderService.queryUncompleteOrderno(orderNo,project_id);
	}
	
	@RequestMapping("/order/create.do")
	@ResponseBody
	public String create(Order order,@RequestBody OrderList[] orderLists) throws  IOException{
		orderService.create(order,orderLists);
		return "{success:true}";
	}
	
	@RequestMapping("/order/update.do")
	@ResponseBody
	public String update(Order order) throws  IOException{
		orderService.update(order);
		return "{success:true}";
	}
	
	@RequestMapping("/order/editover.do")
	@ResponseBody
	public String editover(String id) throws  IOException{
		orderService.editover(id);
		return "{success:true}";
	}
	
	@RequestMapping("/order/delete.do")
	@ResponseBody
	public String delete(String id) throws  IOException{
		orderService.delete(id);
		return "{success:true}";
	}
	@RequestMapping("/order/forceBack.do")
	@ResponseBody
	public String forceBack(String id) throws  IOException{
		orderService.forceBack(id);
		return "{success:true}";
	}
	
	
	@RequestMapping("/order/exportBarcode.do")
	@ResponseBody
	public String exportBarcode(HttpServletRequest request,HttpServletResponse response,@RequestBody OrderList[] orderLists,String orderno) throws  IOException{

		
		List<BarcodeVO> results=new ArrayList<BarcodeVO>();
		results=orderService.getBarCodeList(orderLists);

		String contextPath=request.getSession().getServletContext().getRealPath("/");
		
		orderno=orderno.replaceAll("\\\\", "_");
		String fileName="qrcode("+orderno+").xlsx";
		String filePath="temp"+File.separatorChar+fileName;
		String path=contextPath+filePath;
		File file=new File(path);
		if(!file.exists()){
			//File temp=new File(contextPath+"temp");
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdir();
			}
			file.createNewFile();
		}
		

        //HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        //HSSFSheet hssfSheet = hssfWorkbook.createSheet("二维码数据源");
		XSSFWorkbook wb =new XSSFWorkbook();
		Sheet hssfSheet = wb.createSheet("二维码数据源");
        
        Row hssfRow0 = hssfSheet.createRow(0);
    	Cell cell00 = hssfRow0.createCell(0);
    	cell00.setCellValue("条码");
    	Cell cell011 = hssfRow0.createCell(1);
    	cell011.setCellValue("型号");
    	Cell cell012= hssfRow0.createCell(2);
    	cell012.setCellValue("品牌");
    	Cell cell013 = hssfRow0.createCell(3);
    	cell013.setCellValue("供应商");
    	Cell cell014 = hssfRow0.createCell(4);
    	cell014.setCellValue("小类");
    	Cell cell015 = hssfRow0.createCell(5);
    	cell015.setCellValue("品名");
    	
        for(int i=1;i<=results.size();i++){
        	BarcodeVO barcodeVO=results.get(i-1);
        	
        	Row hssfRow = hssfSheet.createRow(i);
        	Cell cell0 = hssfRow.createCell(0);
        	cell0.setCellValue(barcodeVO.getEcode());
        	Cell cell1 = hssfRow.createCell(1);
        	cell1.setCellValue(barcodeVO.getProd_style());
        	
        	Cell cell2 = hssfRow.createCell(2);
        	cell2.setCellValue(barcodeVO.getBrand_name());
        	Cell cell3 = hssfRow.createCell(3);
        	cell3.setCellValue(barcodeVO.getSupplier_name());
        	Cell cell4 = hssfRow.createCell(4);
        	cell4.setCellValue(barcodeVO.getSubtype_name());
        	Cell cell5 = hssfRow.createCell(5);
        	cell5.setCellValue(barcodeVO.getProd_name());
        	 
        }
        OutputStream out = new FileOutputStream(file);
        wb.write(out);
        out.close();


	    //return "/"+filePath.replace(File.separatorChar, '/');
	    return "{success:true,fileName:'"+fileName+"'}";
	}
	
	@RequestMapping("/order/downloadBarcode.do")
	//@ResponseBody
	public void downloadBarcode(HttpServletRequest request,HttpServletResponse response,String orderno) throws  IOException{
		orderno=orderno.replaceAll("\\\\", "_");
		
		String contextPath=request.getSession().getServletContext().getRealPath("/");
		String fileName="qrcode("+orderno+").xlsx";
		String filePath="temp"+File.separatorChar+fileName;
		String path=contextPath+filePath;
		File file=new File(path);
		//FileReader reader=new FileReader(file);
		FileInputStream in=new FileInputStream(file);
		
		response.setHeader("content-disposition","attachment; filename="+fileName);
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=uft-8");
		//response.setContentType("application/vnd.ms-excel;charset=uft-8");
		//response.setContentType("text/plain; charset=gb2312");
		
		OutputStream  out = response.getOutputStream();
		int n;
		byte b[]=new byte[1024];
		while((n=in.read(b))!=-1){
			out.write(b,0,n);
		}
		in.close();
		
		out.flush();
		out.close();
		

	}
	
//	@RequestMapping("/order/exportBarcode.do")
//	@ResponseBody
//	public String exportBarcode(HttpServletRequest request,HttpServletResponse response,@RequestBody OrderVO[] orderVOs) throws  IOException{
//
//		
//		List<String> results=new ArrayList<String>();
//		results=orderService.getBarCodeList(orderVOs);
//
//		String contextPath=request.getSession().getServletContext().getRealPath("/");
//		
//		String fileName="barcode("+orderVOs[0].getOrderNo()+").txt";
//		String filePath="temp"+File.separatorChar+fileName;
//		String path=contextPath+filePath;
//		File file=new File(path);
//		if(!file.exists()){
//			//File temp=new File(contextPath+"temp");
//			if (!file.getParentFile().exists()) {
//				file.getParentFile().mkdir();
//			}
//			file.createNewFile();
//		}
//		FileWriter writer = new FileWriter(file, false);
//		for(String ecode:results){
//			writer.append(ecode+"\r\n");
//		} 
//	    writer.close();
//
//	    //return "/"+filePath.replace(File.separatorChar, '/');
//	    return fileName;
//	}
	
	
	
	
	
	/**
	 * 添加明细数据
	 * @author mawujun email:160649888@163.com qq:16064988
	 * @param orderNo
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/order/addList.do")
	@ResponseBody
	public String addList(OrderList orderList) throws  IOException{
		orderService.addList(orderList);
		return "{success:true}";
	}
	
	/**
	 * 更新明细数据
	 * @author mawujun email:160649888@163.com qq:16064988
	 * @param orderNo
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/order/updateList.do")
	@ResponseBody
	public String updateList(OrderList orderList) throws  IOException{
		orderService.updateList(orderList);
		return "{success:true}";
	}
	
	/**
	 * 删除明细数据
	 * @author mawujun email:160649888@163.com qq:16064988
	 * @param orderNo
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/order/deleteList.do")
	@ResponseBody
	public String deleteList(String id) throws  IOException{
		orderService.deleteList(id);
		return "{success:true}";
	}
	


}
