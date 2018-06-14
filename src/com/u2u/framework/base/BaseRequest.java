package com.u2u.framework.base;

/**
 * @ClassName: BaseRequest <br>
 * @Description: Mapper 基础类. <br>
 * @date 2015-3-2 下午01:38:30 <br>
 * 
 * @author Dean
 * @param <T>
 * @param <PK>
 */
public class BaseRequest {

	private String title;

	private String page;
	//每頁顯示多少條記錄
	private String pageSize;
	//一共多少條記錄
	private String rows;

	private String[] sort;

	private String[] order;

	private int export;
	
	private String rowNames;
	/**
	 * 返回 bare_field_comment
	 * 
	 * @return bare_field_comment
	*/
	public String getTitle() {
		return title;
	}
	

	/**
	 * 设置 bare_field_comment
	 * 
	 * @param title
	 *            bare_field_comment
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	

	/**
	 * 返回 bare_field_comment
	 * 
	 * @return bare_field_comment
	*/
	public String getPage() {
		return page;
	}
	

	/**
	 * 设置 bare_field_comment
	 * 
	 * @param page
	 *            bare_field_comment
	 */
	public void setPage(String page) {
		this.page = page;
	}
	

	/**
	 * 返回 每頁顯示多少條記錄
	 * 
	 * @return 每頁顯示多少條記錄
	*/
	public String getPageSize() {
		return pageSize;
	}
	

	/**
	 * 设置 每頁顯示多少條記錄
	 * 
	 * @param pageSize
	 *            每頁顯示多少條記錄
	 */
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	

	/**
	 * 返回 一共多少條記錄
	 * 
	 * @return 一共多少條記錄
	*/
	public String getRows() {
		return rows;
	}
	

	/**
	 * 设置 一共多少條記錄
	 * 
	 * @param rows
	 *            一共多少條記錄
	 */
	public void setRows(String rows) {
		this.rows = rows;
	}
	

	/**
	 * 返回 bare_field_comment
	 * 
	 * @return bare_field_comment
	*/
	public String[] getSort() {
		return sort;
	}
	

	/**
	 * 设置 bare_field_comment
	 * 
	 * @param sort
	 *            bare_field_comment
	 */
	public void setSort(String[] sort) {
		this.sort = sort;
	}
	

	/**
	 * 返回 bare_field_comment
	 * 
	 * @return bare_field_comment
	*/
	public String[] getOrder() {
		return order;
	}
	

	/**
	 * 设置 bare_field_comment
	 * 
	 * @param order
	 *            bare_field_comment
	 */
	public void setOrder(String[] order) {
		this.order = order;
	}
	

	/**
	 * 返回 bare_field_comment
	 * 
	 * @return bare_field_comment
	*/
	public int getExport() {
		return export;
	}
	

	/**
	 * 设置 bare_field_comment
	 * 
	 * @param export
	 *            bare_field_comment
	 */
	public void setExport(int export) {
		this.export = export;
	}


	/**
	 * 返回 bare_field_comment
	 * 
	 * @return bare_field_comment
	*/
	public String getRowNames() {
		return rowNames;
	}
	


	/**
	 * 设置 bare_field_comment
	 * 
	 * @param rowNames
	 *            bare_field_comment
	 */
	public void setRowNames(String rowNames) {
		this.rowNames = rowNames;
	}
	
}
