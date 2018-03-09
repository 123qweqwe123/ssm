package com.lmbx.ssm.vo;

import java.util.List;


public class Page<T> {
	
	private int totalRowCount;//总行数
	private int currentPage;//当前页
	
	private int pageRowSize = 10;//每页显示行数
	private int startRow;//起始行
	private int endRow;//结束行
	private int pageCount;//总页数
	
	private String orderBy;
	
	private List<T> resultList;
	
	
	public int getTotalRowCount() {
		return totalRowCount;
	}
	public void setTotalRowCount(int totalRowCount) {
		this.totalRowCount = totalRowCount;
		this.pageCount = (this.totalRowCount+this.pageRowSize-1)/this.pageRowSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		if(currentPage > this.pageCount)currentPage = this.pageCount;
		if(currentPage <= 0)currentPage = 1;
		this.startRow = (this.currentPage-1)*pageRowSize + 1;
		this.endRow = this.currentPage*this.pageRowSize;
	}
	public int getPageRowSize() {
		return pageRowSize;
	}
	public void setPageRowSize(int pageRowSize) {
		this.pageRowSize = pageRowSize;
	}
	public int getStartRow() {
		return startRow;
	}
	
	public int getStartRowForH2() {
		/*if(MultipleDbManage.instance().isMysql()){
			return this.startRow;
		}*/
		return this.getTotalRowCount()-this.getEndRow();
	}
	public int getEndRowForH2() {
		/*if(MultipleDbManage.instance().isMysql()){
			return this.endRow;
		}*/
		return this.getTotalRowCount()-this.getStartRow()+1;
	}
	
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getEndRow() {
		return endRow;
	}
	
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public List<T> getResultList() {
		return resultList;
	}
	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
}
