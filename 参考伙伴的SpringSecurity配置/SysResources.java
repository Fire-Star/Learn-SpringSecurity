package com.libo.DAO;

import java.io.Serializable;

public class SysResources implements Serializable{

	private static final long serialVersionUID = -6862289646521845699L;
	private String resourcePath;
	private String authName;
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getResourcePath() {
		return resourcePath;
	}
	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}
	public String getAuthName() {
		return authName;
	}
	public void setAuthName(String authName) {
		this.authName = authName;
	}
	
}
