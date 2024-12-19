package com.work.common.utils; // 定义该类所在的包

import java.util.List; // 导入 List 类，用于存储数据

/**
 * Page 类用于封装分页信息
 *
 * @param <T> 泛型，表示分页中存储的数据类型
 */
public class Page<T> {
	private int total; // 总条数
	private int page; // 当前页
	private int size; // 当前页条数
	private List<T> rows; // 当前页的数据列表
	private List<T> rows_1; // 备用数据列表1
	private List<T> rows_2; // 备用数据列表2
	private List<T> rows_3; // 备用数据列表3
	private List<T> rows_4; // 备用数据列表4
	private List<T> rows_5; // 备用数据列表5
	private List<T> rows_6; // 备用数据列表6
	private List<T> rows_7; // 备用数据列表7
	private List<T> rows_8; // 备用数据列表8

	/**
	 * 获取总条数
	 *
	 * @return 总条数
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * 设置总条数
	 *
	 * @param total 总条数
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * 获取当前页
	 *
	 * @return 当前页
	 */
	public int getPage() {
		return page;
	}

	/**
	 * 设置当前页
	 *
	 * @param page 当前页
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * 获取当前页条数
	 *
	 * @return 当前页条数
	 */
	public int getSize() {
		return size;
	}

	/**
	 * 设置当前页条数
	 *
	 * @param size 当前页条数
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * 获取当前页数据列表
	 *
	 * @return 当前页数据列表
	 */
	public List<T> getRows() {
		return rows;
	}

	/**
	 * 设置当前页数据列表
	 *
	 * @param rows 当前页数据列表
	 */
	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	// 备用数据列表的 getter 和 setter 方法
	public List<T> getRows_1() {
		return rows_1;
	}

	public void setRows_1(List<T> rows_1) {
		this.rows_1 = rows_1;
	}

	public List<T> getRows_2() {
		return rows_2;
	}

	public void setRows_2(List<T> rows_2) {
		this.rows_2 = rows_2;
	}

	public List<T> getRows_3() {
		return rows_3;
	}

	public void setRows_3(List<T> rows_3) {
		this.rows_3 = rows_3;
	}

	public List<T> getRows_4() {
		return rows_4;
	}

	public void setRows_4(List<T> rows_4) {
		this.rows_4 = rows_4;
	}

	public List<T> getRows_5() {
		return rows_5;
	}

	public void setRows_5(List<T> rows_5) {
		this.rows_5 = rows_5;
	}

	public List<T> getRows_6() {
		return rows_6;
	}

	public void setRows_6(List<T> rows_6) {
		this.rows_6 = rows_6;
	}

	public List<T> getRows_7() {
		return rows_7;
	}

	public void setRows_7(List<T> rows_7) {
		this.rows_7 = rows_7;
	}

	public List<T> getRows_8() {
		return rows_8;
	}

	public void setRows_8(List<T> rows_8) {
		this.rows_8 = rows_8;
	}
}
