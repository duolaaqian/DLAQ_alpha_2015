package com.dlaq.test.excel.demo01;

/**
 * 导出配置，方便传递各种参数
 */
public class ExportConfig {
	
	/**
	 * 填充数据的开始行坐标
	 */
	private int rowStartIndex=0;
	
	/**
	 * 填充数据的开始列坐标
	 */
	private int colStartIndex=0;
	
	/**
	 * 填充单元格时的值处理器
	 */
	CellValueProcessor cellValueProcessor;

	public CellValueProcessor getCellValueProcessor(){
		return cellValueProcessor;
	}
	public void setCellValueProcessor(CellValueProcessor cellValueProcessor){
		this.cellValueProcessor = cellValueProcessor;
	}

	public int getRowStartIndex() {
		return rowStartIndex;
	}
	public void setRowStartIndex(int rowStartIndex) {
		this.rowStartIndex = rowStartIndex;
	}

	public int getColStartIndex() {
		return colStartIndex;
	}
	public void setColStartIndex(int colStartIndex) {
		this.colStartIndex = colStartIndex;
	}
}
