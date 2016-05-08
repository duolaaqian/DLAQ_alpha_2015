package com.dlaq.test.excel.demo01;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;

public class YearReportExportGenerator extends ExportGenerator{
	public YearReportExportGenerator(List<Map> dataList) {
		super(dataList);
	}
	public YearReportExportGenerator(List<Map> dataList, ExportConfig exportConfig) {
		super(dataList, exportConfig);
	}
	
	/**
	 * 添加表头数据
	 */
	@Override
	public void postProcessBeforeGenerate(List<Map> dataList,HSSFWorkbook workBook) {
		createHead();
	}
	@Override
	public void postProcessAfterGenerate(List<Map> dataList,HSSFWorkbook workBook) {
		createBottom();
		createBorder();
	}
	
	/**
	 * 生成一个简易的表头
	 */
	public void createHead(){
		HSSFSheet sheet = getWorkBook().createSheet();
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = row.createCell(0);
		row.setHeight((short)500);
		cell.setCellValue("2015年XXX全年报表");
		for(int i=1;i<13;i++){
			row.createCell(i);
		}
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,12));
		HSSFCellStyle style = getWorkBook().createCellStyle();
		HSSFFont font = getWorkBook().createFont();
		font.setBold(true);
		font.setFontHeightInPoints((short)16);
		style.setFont(font);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cell.setCellStyle(style);
		
		HSSFRow row2 = sheet.createRow(1);
		HSSFCellStyle centerStyle = getWorkBook().createCellStyle();
		centerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		for(int i=0;i<13;i++){
			HSSFCell c = row2.createCell(i);
			if(i>0){
				c.setCellValue(i+"月");
				c.setCellStyle(centerStyle);
			}
		}
		ExportConfig cfg = getExportConfig();
		cfg.setRowStartIndex(cfg.getRowStartIndex()+2);
	}
	
	/**
	 * 添加边框
	 */
	public void createBorder(){
		HSSFSheet sheet = getWorkBook().getSheetAt(0);
		int rows = sheet.getLastRowNum();
		HSSFCellStyle style;
		for(int i=0;i<rows+1;i++){
			HSSFRow row = sheet.getRow(i);
			Iterator<Cell> iter = row.cellIterator();
			while(iter.hasNext()){
				Cell c = iter.next();
				style = row.getSheet().getWorkbook().createCellStyle();
				style.cloneStyleFrom(c.getCellStyle());
				style.setBorderTop((short)1);
				style.setBorderBottom((short)1);
				style.setBorderLeft((short)1);
				style.setBorderRight((short)1);
				c.setCellStyle(style);
			}
		}
	}
	
	/**
	 * 创建底部信息
	 */
	public void createBottom(){
		HSSFSheet sheet = getWorkBook().getSheetAt(0);
		int rows = sheet.getLastRowNum();
		HSSFRow row = sheet.createRow(rows+1);
		for(int i=1;i<13;i++){
			row.createCell(i);
		}
		Cell c = row.createCell(0);
		c.setCellValue("报告人：多啦a芊  报告日期：2015-12-17");
		HSSFCellStyle style = row.getSheet().getWorkbook().createCellStyle();
		style.cloneStyleFrom(c.getCellStyle());
		style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		c.setCellStyle(style);
		sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,12));
	}
}
