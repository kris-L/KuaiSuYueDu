package com.kris.kuaisuyuedu.entity.other;

import com.google.gson.annotations.SerializedName;

/**
 * 调用后台接口返回的结果
 * 
 */
public class Result<T> {
	// 结果码
	private int code;

	// 结果描述
	@SerializedName("detail")
	private String desc;

	@SerializedName("question_tag")
	private int question_tag;

	// 总记录数
	@SerializedName("pagecount")
	private int count;

	private int pageNo;

	private int pageSize;

	private int pageTotal;

	// 返回结果对象
	@SerializedName("result")
	private T t;


	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getQuestion_tag() {
		return question_tag;
	}

	public void setQuestion_tag(int question_tag) {
		this.question_tag = question_tag;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	@Override
	public String toString() {
		return "Result [code=" + code + ", desc=" + desc + ", count=" + count
				+ ", pageNo=" + pageNo + ", pageSize=" + pageSize
				+ ", pageTotal=" + pageTotal + ", question_tag=" + question_tag
				+ ", t=" + t + "]";
	}



}
