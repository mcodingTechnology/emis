package com.mcoding.emis.goods.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.mcoding.emis.goods.order.bean.Order;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExportExcel<T extends Serializable> implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 把数据导出到excel表
	 * @param os excel表的导出 流
	 * @param titleAndDataMap 表头与数据的关联,不能为空。例如：{ {"序号", "id"}}， “序号”是导出的excel表的表头，“id”是导入data数据的key
	 * @param data 导出的数据
	 * @param sheetTitle sheet名
	 * @param sheetIndex sheet的索引
	 * @return
	 * @throws IOException 
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 */
	public static WritableWorkbook exportDataToExcel(OutputStream os, String[][] titleAndDataMap, List<Map<String, Object>> data, String sheetTitle, int sheetIndex) throws IOException, RowsExceededException, WriteException{
		// 准备设置excel工作表的标题
        WritableWorkbook wwb = null;
		wwb = Workbook.createWorkbook(os);
		// 添加第一个工作表并设置第一个Sheet的名字
		WritableSheet sheet = wwb.createSheet(sheetTitle, sheetIndex);
		// 设置字体
		WritableFont titleFont = new WritableFont(
				WritableFont.createFont("微软雅黑"), 20);
		WritableCellFormat titleFormat = new WritableCellFormat(titleFont);

		/* Label label1 = new Label(17, 0, "商家订单总额统计", font); */
		Label label = new Label(3, 0, "" + DateHelper.getCurrentDateStr()
				+ "_订单统计表" + "--总数(条)", titleFormat);
		sheet.addCell(label);
		for (int i = 0; i < titleAndDataMap.length; i++) {
			// Label(x,y,z) 代表单元格的第x+1列，第y+1行, 内容z
			// 在Label对象的子对象中指明单元格的位置和内容
			String title = titleAndDataMap[i][0];
			label = new Label(i, 1, title);
			// 将定义好的 单元格添加到工作表中
			sheet.addCell(label);
		}

		WritableFont cellFont = new WritableFont(
				WritableFont.createFont("微软雅黑"), 10);
		WritableCellFormat cellFormat = new WritableCellFormat(cellFont);

		for (int i = 0; data != null && i < data.size(); i++) {

			for (int j = 0; j < titleAndDataMap.length; j++) {
				Label tmpLabel = new Label(j, i + 2, String.valueOf(data.get(i)
						.get(titleAndDataMap[j][1])), cellFormat);
				sheet.addCell(tmpLabel);
			}
		}
			
		return wwb;
	}

	public static WritableWorkbook exportCardRecordToExcel(OutputStream os, String[][] titleAndDataMap, List<Map<String, Object>> data, String sheetTitle, int sheetIndex) throws IOException, RowsExceededException, WriteException{
		// 准备设置excel工作表的标题
		WritableWorkbook wwb = null;
		wwb = Workbook.createWorkbook(os);
		// 添加第一个工作表并设置第一个Sheet的名字
		WritableSheet sheet = wwb.createSheet(sheetTitle, sheetIndex);
		// 设置字体
		WritableFont titleFont = new WritableFont(
				WritableFont.createFont("微软雅黑"), 16);
		WritableCellFormat titleFormat = new WritableCellFormat(titleFont);

		/* Label label1 = new Label(17, 0, "商家订单总额统计", font); */
		Label label = new Label(3, 0, "" + DateHelper.getCurrentDateStr()
				+ "_特权卡核销记录明细", titleFormat);
		sheet.addCell(label);

		for (int i = 0; i < titleAndDataMap.length; i++) {
			// Label(x,y,z) 代表单元格的第x+1列，第y+1行, 内容z
			// 在Label对象的子对象中指明单元格的位置和内容
			String title = titleAndDataMap[i][0];
			label = new Label(i, 1, title);
			// 将定义好的 单元格添加到工作表中
			sheet.addCell(label);
		}

		WritableFont cellFont = new WritableFont(
				WritableFont.createFont("微软雅黑"), 10);
		WritableCellFormat cellFormat = new WritableCellFormat(cellFont);

		for (int i = 0; data != null && i < data.size(); i++) {

			for (int j = 0; j < titleAndDataMap.length; j++) {
				Label tmpLabel = new Label(j, i + 2, String.valueOf(data.get(i)
						.get(titleAndDataMap[j][1])), cellFormat);
				sheet.addCell(tmpLabel);
			}
		}

		return wwb;
	}
	
	/**
	 * 
	 * @param in 导入的excel表
	 * @param sheetIndex 导入的excel的sheet的索引
	 * @param startRowIndex 导入excel的首行数据。从首行数据一直向下查找数据，直至找不到。
	 * @param headRowIndex 导入的excel表的表头的索引
	 * @param titleAndDataMap 例如：{ {"序号", "id"}}， “序号”是导入的excel表的表头，“id”是导出的data数据的key
	 * @return
	 * @throws BiffException
	 * @throws IOException
	 */
	public static List<Map<String, Object>> importExcelDataToMap(InputStream in, int sheetIndex, int startRowIndex, int headRowIndex, String[][] titleAndDataMap) throws BiffException, IOException{
		Workbook workbook = Workbook.getWorkbook(in);
		Sheet sheet = workbook.getSheet(sheetIndex);
		
		//1、设置每一列的数据，存入map时候，对应的key
		Map<Integer, String> indexAndTitleKeyMap = new Hashtable<Integer, String>();
		Cell[] headRow = sheet.getRow(headRowIndex);
		for(int i=0; i<headRow.length; i++){
			String title = headRow[i].getContents();
			
			for(int j=0;  j<titleAndDataMap.length; j++){
				if(title.equals(titleAndDataMap[j][0])){
					indexAndTitleKeyMap.put(i, titleAndDataMap[j][1]);
				}
			}
		}
		
		List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
		
		//2、查出excel的数据，并导入到map里面
		int rowCount = sheet.getRows();
		for(; sheet!=null && startRowIndex < rowCount; startRowIndex ++){
			Cell[] rows = sheet.getRow(startRowIndex);
			Map<String, Object> tmpMap = new Hashtable<String, Object>();
			
			Iterator<Integer> iterator = indexAndTitleKeyMap.keySet().iterator();
			while (iterator.hasNext()) {
				int index = iterator.next();
				String key = indexAndTitleKeyMap.get(index);
				String content = rows[index].getContents();
				tmpMap.put(key, content);
			}
			
			dataList.add(tmpMap);
		}
		
		return dataList;
	}

	public static WritableWorkbook exportDataToOrderSendExcel(OutputStream os, String[][] titleAndDataMap,
			List<Map<String, Object>> data, String sheetTitle, int sheetIndex, Map<String, Object> order) throws IOException, RowsExceededException, WriteException{
		// 准备设置excel工作表的标题
        WritableWorkbook wwb = null;
		wwb = Workbook.createWorkbook(os);
		// 添加第一个工作表并设置第一个Sheet的名字
		WritableSheet sheet = wwb.createSheet(sheetTitle, sheetIndex);
		// 设置字体
		WritableFont titleFont = new WritableFont(
				WritableFont.createFont("微软雅黑"), 10);
		WritableCellFormat titleFormat = new WritableCellFormat(titleFont);

		/* Label label1 = new Label(17, 0, "商家订单总额统计", font); */
		/*Label label = new Label(3, 0, "" + DateHelper.getCurrentDateStr()
				+ "_发货明细表", titleFormat);
		sheet.addCell(label);*/
		// 组装发货单明细
		Label label1 = new Label(0,0,"买家: "+order.get("fullName"),titleFormat);
		sheet.addCell(label1);
		Label label2 = new Label(0,1,"收件人: "+order.get("receiver")+"          "+"电话: "+order.get("receiverPhone"),titleFormat);
		sheet.addCell(label2);
		Label label3 = new Label(0,2,"收货地址: "+order.get("address"),titleFormat);
		sheet.addCell(label3);
		Label label4 = new Label(0,3,"订单号: "+order.get("outNo")+"          "+"商品数量: "+order.get("nums"),titleFormat);
		sheet.addCell(label4);
		Label label5 = new Label(0,4,"订单总价: "+order.get("fee")+"          "+"已优惠: "+order.get("preferentialprice"),titleFormat);
		sheet.addCell(label5);
		   SimpleDateFormat formatter = new SimpleDateFormat ("yyyy年MM月dd日 HH时mm分"); 
		    String ctime = formatter.format(order.get("payTime")); 
		Label label6 = new Label(0,5,"付款时间: "+ctime,titleFormat);
		sheet.addCell(label6);
		WritableFont cellFont = new WritableFont(
				WritableFont.createFont("微软雅黑"), 10);
		WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
		for (int i = 0; i < titleAndDataMap.length; i++) {
			// Label(x,y,z) 代表单元格的第x+1列，第y+1行, 内容z
			// 在Label对象的子对象中指明单元格的位置和内容
			String title = titleAndDataMap[i][0];
			Label label = new Label(i, 6, title,cellFormat);
			// 将定义好的 单元格添加到工作表中
			sheet.addCell(label);
		}
		for (int i = 0; data != null && i < data.size(); i++) {
			for (int j = 0; j < titleAndDataMap.length; j++) {
				Label tmpLabel = new Label(j, i + 7, String.valueOf(data.get(i)
						.get(titleAndDataMap[j][1])), cellFormat);
				sheet.addCell(tmpLabel);
			}
		}
			
		return wwb;
	}
	public static WritableWorkbook exportReturnDataToExcel(OutputStream os, String[][] titleAndDataMap, List<Map<String, Object>> data, String sheetTitle, int sheetIndex) throws IOException, RowsExceededException, WriteException{
		// 准备设置excel工作表的标题
        WritableWorkbook wwb = null;
		wwb = Workbook.createWorkbook(os);
		// 添加第一个工作表并设置第一个Sheet的名字
		WritableSheet sheet = wwb.createSheet(sheetTitle, sheetIndex);
		// 设置字体
		WritableFont titleFont = new WritableFont(
				WritableFont.createFont("微软雅黑"), 20);
		WritableCellFormat titleFormat = new WritableCellFormat(titleFont);

		/* Label label1 = new Label(17, 0, "商家订单总额统计", font); */
		Label label = new Label(3, 0, "" + DateHelper.getCurrentDateStr()
				+ "_退货单统计表" + "--总数(条)", titleFormat);
		sheet.addCell(label);
		for (int i = 0; i < titleAndDataMap.length; i++) {
			// Label(x,y,z) 代表单元格的第x+1列，第y+1行, 内容z
			// 在Label对象的子对象中指明单元格的位置和内容
			String title = titleAndDataMap[i][0];
			label = new Label(i, 1, title);
			// 将定义好的 单元格添加到工作表中
			sheet.addCell(label);
		}

		WritableFont cellFont = new WritableFont(
				WritableFont.createFont("微软雅黑"), 10);
		WritableCellFormat cellFormat = new WritableCellFormat(cellFont);

		for (int i = 0; data != null && i < data.size(); i++) {

			for (int j = 0; j < titleAndDataMap.length; j++) {
				Label tmpLabel = new Label(j, i + 2, String.valueOf(data.get(i)
						.get(titleAndDataMap[j][1])), cellFormat);
				sheet.addCell(tmpLabel);
			}
		}
			
		return wwb;
	}
}
