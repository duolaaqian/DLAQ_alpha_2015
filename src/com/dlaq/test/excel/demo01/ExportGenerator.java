package com.dlaq.test.excel.demo01;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * 该类只提供生成workbook、填充数据到workbook 
 * 子类可对支持的数据格式进行拓展 子类可在生成前后加逻辑进行拓展
 * 
 * 支持的数据格式： List<Map>、List<DynaBean>、List<List> 
 * 其他数据格式可自己扩展 Collention<?> + ?[]
 * (Collention各种+数组各种) SimpleExportGenerator --> 简单导出，支持常见的格式
 * ComplexExportGenerator(implements simple) --> 复杂导出，支持Collention<?>
 * 
 * 生成workbook执行顺序(子类可重写) 
 * 1、postProcessBeforeGenerate() 
 * 2、generate()
 * 3、postProcessAfterGenerate()
 */
@SuppressWarnings("rawtypes")
public class ExportGenerator {
	private List<Map> dataList; //所要导出的数据

	private HSSFWorkbook workBook; //workBook实体类

	private ExportConfig exportConfig; //导出配置信息

	public ExportGenerator() {
		this(new ArrayList<Map>());
	};
	public ExportGenerator(List<Map> dataList) {
		this(dataList==null?new ArrayList<Map>():dataList, new ExportConfig());
	};
	public ExportGenerator(List<Map> dataList, ExportConfig exportConfig) {
		this.dataList = dataList==null?new ArrayList<Map>():dataList;
		this.exportConfig = exportConfig==null?new ExportConfig():exportConfig;
	};

	public ExportConfig getExportConfig() {
		return this.exportConfig;
	}
	public void setExportConfig(ExportConfig exportConfig) {
		this.exportConfig = exportConfig;
	}

	public List<Map> getDataList() {
		return this.dataList;
	};
	public void setDataList(List<Map> dataList) {
		this.dataList = dataList;
	};
	
	public HSSFWorkbook getWorkBook() {
		return workBook;
	}
	public void setWorkBook(HSSFWorkbook workBook) {
		this.workBook = workBook;
	}

	/**
	 * 获取 HSSFWorkbook 的源头 子类可以进行拓展
	 */
	protected HSSFWorkbook initWorkbook() {
		return new HSSFWorkbook();
	}

	/**
	 * 生成excel模板方法 主要确定方法执行顺序
	 */
	public void generateWorkBook() {
		workBook = initWorkbook();
		postProcessBeforeGenerate(dataList, workBook);
		generate(dataList, workBook);
		postProcessAfterGenerate(dataList, workBook);
	}

	/**
	 * 填充数据前调用该方法
	 */
	public void postProcessBeforeGenerate(List<Map> dataList,HSSFWorkbook workBook) {};

	/**
	 * 填充完数据后调用该方法
	 */
	public void postProcessAfterGenerate(List<Map> dataList,HSSFWorkbook workBook) {};

	/**
	 * 获取sheet
	 */
	protected HSSFSheet getOrCreateSheet(int index,HSSFWorkbook workBook){
		if(workBook.getNumberOfSheets()>0){
			return workBook.getSheetAt(0);
		}
		return workBook.createSheet();
	}
	
	/**
	 * 具体生成workBook方法
	 */
	protected void generate(List<Map> dataList, HSSFWorkbook workBook) {
		HSSFSheet sheet = getOrCreateSheet(0,workBook);
		int rowIndex = exportConfig.getRowStartIndex(), colIndex = exportConfig.getColStartIndex();
		for (Map m : dataList) {
			HSSFRow row = sheet.createRow(rowIndex);
			for (Object k : m.keySet()) {
				HSSFCell cell = row.createCell(colIndex);
				Object value = processCellValue(m.get(k), row, cell);
				setValue(cell, value);
				cell.setCellStyle(processCellStyle(value, row, cell));
				colIndex++;
			}
			colIndex = 0;
			rowIndex++;
		}
	}

	/**
	 * 对poi中的cell进行赋值
	 */
	protected void setValue(HSSFCell cell, Object value) {
		if (null == value) {
			cell.setCellValue((java.lang.String) value);//如果值为null的话，poi自己会处理
		} else if (value instanceof java.lang.String) {
			cell.setCellValue((java.lang.String) value);
		} else if (value instanceof java.lang.Double) {
			cell.setCellValue((double) value);
		} else if (value instanceof java.lang.Float) {
			cell.setCellValue((float) value);
		} else if (value instanceof java.lang.Long) {
			cell.setCellValue((long) value);
		} else if (value instanceof java.lang.Integer) {
			cell.setCellValue((int) value);
		} else if (value instanceof java.lang.Short) {
			cell.setCellValue((short) value);
		} else if (value instanceof java.lang.Boolean) {
			cell.setCellValue((boolean) value);
		} else if (value instanceof java.util.Date) {
			cell.setCellValue((java.util.Date) value);
		} else if (value instanceof java.util.Calendar) {
			cell.setCellValue((java.util.Calendar) value);
		} else if (value instanceof org.apache.poi.ss.usermodel.RichTextString) {
			cell.setCellValue((org.apache.poi.ss.usermodel.RichTextString) value);
		} else {
			cell.setCellValue(value.toString());//无匹配类型按String处理
		}
	}

	/**
	 * 根据Processor获取值
	 */
	protected Object processCellValue(Object obj, HSSFRow row, HSSFCell cell) {
		CellValueProcessor cvp = exportConfig.getCellValueProcessor();
		if (null == cvp) {
			return obj;
		}
		return cvp.processCellValue(obj, row, cell);
	}

	/**
	 * 根据Processor获取值 返回空不影响已有样式
	 */
	protected CellStyle processCellStyle(Object value, HSSFRow row,HSSFCell cell) {
		CellValueProcessor cvp = exportConfig.getCellValueProcessor();
		if (null == cvp) {
			return null;
		}
		return cvp.processCellStyle(value, row, cell);
	}

	public HSSFWorkbook getWorkBookInstance() {
		return getWorkBookInstance(false);
	}

	/**
	 * 向外部提供获取 HSSFWorkbook 的方法 refresh 是否重新生成HSSFWorkbook
	 */
	public HSSFWorkbook getWorkBookInstance(boolean refresh) {
		if (refresh || null == workBook) {
			generateWorkBook();
		}
		return workBook;
	}
}
