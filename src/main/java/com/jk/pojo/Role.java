package com.jk.pojo;

public class Role {

	private Integer id;
	private String root_name;
	private String crea_user;
	private String crea_time;
	
	private String roleId;
	
	private String rowId;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRoot_name() {
		return root_name;
	}
	public void setRoot_name(String root_name) {
		this.root_name = root_name;
	}
	public String getCrea_user() {
		return crea_user;
	}
	public void setCrea_user(String crea_user) {
		this.crea_user = crea_user;
	}
	public String getCrea_time() {
		return crea_time;
	}
	public void setCrea_time(String crea_time) {
		this.crea_time = crea_time;
	}
	public Role(Integer id, String root_name, String crea_user, String crea_time) {
		super();
		this.id = id;
		this.root_name = root_name;
		this.crea_user = crea_user;
		this.crea_time = crea_time;
	}
	public Role() {
		super();
	}
	
	public String getRowId() {
		return rowId;
	}
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	
}
