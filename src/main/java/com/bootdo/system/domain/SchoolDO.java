package com.bootdo.system.domain;

import java.io.Serializable;

/* 
 * 学校管理
 * 
 *@Description todo
 *@author xuwenxiang
 *@date 2021年9月28日上午10:27:54
 *@type_name
 */
public class SchoolDO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long schId;
	
	private Long parentId;
	
	private String schName;
	
	public SchoolDO() {}

	public Long getSchId() {
		return schId;
	}
	public void setSchId(Long schId) {
		this.schId = schId;
	}
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getSchName() {
		return schName;
	}

	public void setSchName(String schName) {
		this.schName = schName;
	}

	
	

	
	
	

}
