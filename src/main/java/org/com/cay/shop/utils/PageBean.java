package org.com.cay.shop.utils;

import java.io.Serializable;
import java.util.List;

public class PageBean<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int pageNo;// 当前页
	private int totalPage;// 总页数
	private int totalCount;// 总记录数
	private int pageSize;// 每页记录数

	private List<T> data;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}
