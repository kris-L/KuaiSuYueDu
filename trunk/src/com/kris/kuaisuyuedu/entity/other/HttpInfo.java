package com.kris.kuaisuyuedu.entity.other;

/**
 * HTTP请求参数
 * 
 */
public class HttpInfo {
	/** 方法 */
	private String method;
	/** 数据类型 */
	private String type;
	/** 路径 */
	private String url;
	/** 参数 */
	private String request;

	public static final String GET = "GET";
	public static final String POST = "POST";

	public static final String CONTENT_TYPE_HTML = "text/html; charset=UTF-8";
	public static final String CONTENT_TYPE_XML = "text/xml; charset=UTF-8";
	public static final String CONTENT_TYPE_JSON = "application/json; charset=UTF-8";

	public HttpInfo() {
	}

	public HttpInfo(String url) {
		this.method = GET;
		this.type = CONTENT_TYPE_JSON;
		this.url = url;
	}

	public HttpInfo(String url, String request) {
		this.method = POST;
		this.type = CONTENT_TYPE_JSON;
		this.url = url;
		this.request = request;
	}

	public HttpInfo(String method, String type, String url) {
		this.method = method;
		this.type = type;
		this.url = url;
	}

	public HttpInfo(String method, String type, String url, String request) {
		this.method = method;
		this.type = type;
		this.url = url;
		this.request = request;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	@Override
	public String toString() {
		return "HttpInfo [method=" + method + ", type=" + type + ", url=" + url
				+ ", request=" + request + "]";
	}
}
