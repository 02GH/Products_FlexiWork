/*QueryVo.java 是一个 Java 类，用于封装查询条件，通常用于分页查询的场景。该类的主要作用是将请求中的分页信息（如当前页、每页条数等）封装在一个对象中，方便在数据访问层或服务层进行处理。*/
package com.work.pojo; // 定义该类所在的包

public class QueryVo {
	// 当前页码，表示用户请求的页数
	private Integer page;

	// 每页显示的数量，默认为8条
	private Integer size = 8;

	// 开始行数，通常用于计算从哪一行开始查询数据，默认为0
	private Integer startRow = 0;

	// 获取当前页码
	public Integer getPage() {
		return page; // 返回当前页码
	}

	// 设置当前页码
	public void setPage(Integer page) {
		this.page = page; // 设置当前页码
	}

	// 获取每页显示的数量
	public Integer getSize() {
		return size; // 返回每页显示的数量
	}

	// 设置每页显示的数量
	public void setSize(Integer size) {
		this.size = size; // 设置每页显示的数量
	}

	// 获取开始行数
	public Integer getStartRow() {
		return startRow; // 返回开始行数
	}

	// 设置开始行数
	public void setStartRow(Integer startRow) {
		this.startRow = startRow; // 设置开始行数
	}
}
