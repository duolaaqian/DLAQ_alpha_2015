package com.dlaq.test.excel.demo01;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;

public class Client {
	public static void main(String[] args) throws IOException {
		test01();
		test02();
		test03();
	}
	
	/**
	 * 只导出数据Excel
	 */
	public static void test01() throws IOException{
		List<Map> l = getList();
		ExportGenerator generator = new ExportGenerator(l);
		HSSFWorkbook workBook = generator.getWorkBookInstance();
		FileOutputStream out = new FileOutputStream("D:\\excel1.xls");
		workBook.write(out);
	}
	
	/**
	 * 导出带表头、带边框的Excel
	 */
	public static void test02() throws IOException{
		List<Map> l = getList();
		ExportGenerator generator = new YearReportExportGenerator(l);
		HSSFWorkbook workBook = generator.getWorkBookInstance();
		FileOutputStream out = new FileOutputStream("D:\\excel2.xls");
		workBook.write(out);
	}
	
	/**
	 * 导出带表头、带边框和行样式的Excel
	 */
	public static void test03() throws IOException{
		List<Map> l = getList();
		CellValueProcessor cvp = new CellValueProcessor(){
			@Override
			public Object processCellValue(Object value, HSSFRow row,HSSFCell cell) {
				return value==null?"-":value;
			}
			@Override
			public CellStyle processCellStyle(Object value, HSSFRow row,HSSFCell cell) {
				HSSFCellStyle style = null;
				if(value instanceof Number){
					int v = (int)value;
					if(v<0){
						style = row.getSheet().getWorkbook().createCellStyle();
						style.cloneStyleFrom(cell.getCellStyle());
						style.setFillForegroundColor(HSSFColor.RED.index);
						style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					}else if(v>100){
						style = row.getSheet().getWorkbook().createCellStyle();
						style.cloneStyleFrom(cell.getCellStyle());
						style.setFillForegroundColor(HSSFColor.BRIGHT_GREEN.index);
						style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					}
				}else{
					style = row.getSheet().getWorkbook().createCellStyle();
					style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
				}
				return style;
			}
		};
		ExportConfig cfg = new ExportConfig();
		cfg.setCellValueProcessor(cvp);
		ExportGenerator generator = new YearReportExportGenerator(l,cfg);
		HSSFWorkbook workBook = generator.getWorkBookInstance();
		FileOutputStream out = new FileOutputStream("D:\\excel3.xls");
		workBook.write(out);
	}
	
	/**
	 * 获取假数据
	 */
	public static List<Map> getList(){
		List<Map> l = new ArrayList<Map>();
		Random r = new Random();
		Map m;
		for(int i=0;i<10;i++){
			m = new LinkedMap();
			m.put("name", "指标"+i);
			for(int j=0;j<12;j++){
				if(r.nextInt(20)<1){
					m.put(j, null);
				}else{
					m.put(j, r.nextInt(120)-10);	
				}
			}
			l.add(m);
		}
		return l;
	}
}
