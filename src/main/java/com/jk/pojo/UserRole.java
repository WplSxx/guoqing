package com.jk.pojo;

public class UserRole {

	private Integer userid;
	private Integer rootid;
	
	
	public UserRole() {
		super();
	}
	public UserRole(Integer userid, Integer rootid) {
		super();
		this.userid = userid;
		this.rootid = rootid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getRootid() {
		return rootid;
	}
	public void setRootid(Integer rootid) {
		this.rootid = rootid;
	}
	@Override
	public String toString() {
		return "UserRole [userid=" + userid + ", rootid=" + rootid + "]";
	}
	
	
}
