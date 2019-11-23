package com.smokeroom.entity;

/*封装分页对象，实体类继承。用于前端使用分页参数*/
public class pageObject {
	//当前页面
	private Integer pageNum = 1;
	//显示页面条数
	private Integer pageSize = 10;
	
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	@Override
	public String toString() {
		return "pageObject [pageNum=" + pageNum + ", pageSize=" + pageSize + "]";
	}
	
	
}
