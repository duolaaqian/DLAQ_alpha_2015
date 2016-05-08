package com.dlaq.test.excel.demo01;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * 值产生器接口
 */
public interface CellValueProcessor {
	
	Object processCellValue(Object value,HSSFRow row,HSSFCell cell);
	
	CellStyle processCellStyle(Object value,HSSFRow row,HSSFCell cell);
}
