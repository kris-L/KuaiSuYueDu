package com.kris.kuaisuyuedu.entity;

import com.google.gson.annotations.SerializedName;
public class NewVersionInfo {

	@SerializedName("uploadtime")
	private String uploadtime;

	@SerializedName("appversion")
	private String appVersion;

	@SerializedName("filepath")
	private String path;
	
	@SerializedName("size")
	private String size;
	
	@SerializedName("name")
	private String name;
	
	@SerializedName("appName")
	private String appName;
	
	@SerializedName("remark")
	private String remark;
	
	/**是否强制升级（0—否；1—是）*/
	@SerializedName("force")
	private int force;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUploadtime() {
		return uploadtime;
	}

	public void setUploadtime(String uploadtime) {
		this.uploadtime = uploadtime;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getForce() {
		return force;
	}

	public void setForce(int force) {
		this.force = force;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	@Override
	public String toString() {
		return "Version [uploadtime=" + uploadtime + ", appVersion="
				+ appVersion + ", path=" + path + ", size=" + size + ", name="
				+ name + ", remark=" + remark + "]";
	}
}
