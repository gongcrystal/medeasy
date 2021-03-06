package com.medimpact.medeasy.common;

/**
 * @author Crystal E-mail:
 * @version 创建时间：2017年11月7日 类说明
 */
public class DataModel<T> {

	/**
	 * 异常堆栈
	 */
	private StackTraceElement[] errorTrace;
	/**
	 * 给前端的提示信息
	 */
	private String prompt;
	/**
	 * true:成功,false:失败
	 */
	private boolean success;

	private T rows; // for jqgrid
	private T data; // for datatable;
	private Long page;
	private Long records;
	private Long total;

	private Pagination pagination;
	/**
	 * 兼容 DataTable的分页
	 */
	private Long recordsTotal;
	private Long recordsFiltered;
	private int draw;

	public DataModel() {
	}

	public DataModel(StackTraceElement[] errorTrace, String prompt, boolean success, T data, Long page, Long records,
			Long total) {
		this.errorTrace = errorTrace;
		this.prompt = prompt;
		this.success = success;
		this.rows = data;
		this.page = page;
		this.records = records;
		this.total = total;
		this.data = data;

	}

	public DataModel(StackTraceElement[] errorTrace, String prompt, boolean success, T data, Long recordsTotal,
			Long recordsFiltered, int draw) {
		this.errorTrace = errorTrace;
		this.prompt = prompt;
		this.success = success;
		this.rows = data;
		this.recordsFiltered = recordsFiltered;
		this.recordsTotal = recordsTotal;
		this.draw = draw;
		this.data = data;

	}

	public DataModel(StackTraceElement[] errorTrace, String prompt, boolean success, Pagination pagination, T data) {
		this.errorTrace = errorTrace;
		this.prompt = prompt;
		this.success = success;
		this.pagination = pagination;
		this.rows = data;
		this.data = data;
	}

	public DataModel(StackTraceElement[] errorTrace, String prompt, boolean success, T data) {
		this.errorTrace = errorTrace;
		this.prompt = prompt;
		this.success = success;
		this.rows = data;
		this.data = data;
	}

	public static <T> DataModel<T> getSucDataModal(T data) {
		return new DataModel<T>(null, null, true, data);
	}

	public static <T> DataModel<T> getSucDataModal(T data, Long page, Long records, Long total) {

		return new DataModel<T>(null, null, true, data, page, records, total);
	}

	public static <T> DataModel<T> getSucDataModal4DT(T data, Long recordsTotal, Long recordsFiltered, int draw) {

		return new DataModel<T>(null, null, true, data, recordsTotal, recordsFiltered, draw);
	}

	/*
	 * 成功但没有结果集的DataModal
	 *
	 * @param <T>
	 * 
	 * @return
	 */
	public static <T> DataModel<T> getNotDataSucDataModal() {
		DataModel<T> d = new DataModel<T>(null, null, true, null);
		return d;
	}

	/**
	 * 失败的DataModal
	 *
	 * @param prompt
	 * @param errorTrace
	 * @param <T>
	 * @return
	 */
	public static <T> DataModel<T> getFailDataModal(String prompt, StackTraceElement[] errorTrace) {
		DataModel<T> d = new DataModel<T>(errorTrace, prompt, false, null);
		return d;
	}

	/**
	 * 失败的DataModal
	 *
	 * @param prompt
	 * @param <T>
	 * @return
	 */
	public static <T> DataModel<T> getFailDataModalNotException(String prompt) {
		DataModel<T> d = new DataModel<T>(null, prompt, false, null);
		return d;
	}

	public StackTraceElement[] getErrorTrace() {
		return errorTrace;
	}

	public void setErrorTrace(StackTraceElement[] errorTrace) {
		this.errorTrace = errorTrace;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getRows() {
		return rows;
	}

	public void setRows(T rows) {
		this.rows = rows;
	}

	public Long getPage() {
		return page;
	}

	public void setPage(Long page) {
		this.page = page;
	}

	public Long getRecords() {
		return records;
	}

	public void setRecords(Long records) {
		this.records = records;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Long getRecordsTotal() {
		return recordsTotal == null ? (pagination == null ? 0 : pagination.getTotalRows()) : recordsTotal;
	}

	public void setRecordsTotal(Long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public Long getRecordsFiltered() {
		return recordsFiltered == null ? (pagination == null ? 0 : pagination.getTotalRows()) : recordsFiltered;
	}

	public void setRecordsFiltered(Long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
