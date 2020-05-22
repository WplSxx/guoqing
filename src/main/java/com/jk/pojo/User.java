package com.jk.pojo;

public class User {
 
	//用户表用作登录 查询
	private Integer id;
	private String user_name;
	private String pwd;
	private String nickname;
	private String crtime;
	private String uptime;
	private String youxiang;
	private String isRegister;
	//======================\\
	
	public Integer getId() {
		return id;
	}
	public String getIsRegister() {
		return isRegister;
	}
	public void setIsRegister(String isRegister) {
		this.isRegister = isRegister;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getCrtime() {
		return crtime;
	}
	public void setCrtime(String crtime) {
		this.crtime = crtime;
	}
	public String getUptime() {
		return uptime;
	}
	public void setUptime(String uptime) {
		this.uptime = uptime;
	}
	public String getYouxiang() {
		return youxiang;
	}
	public void setYouxiang(String youxiang) {
		this.youxiang = youxiang;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", user_name=" + user_name + ", pwd=" + pwd
				+ ", nickname=" + nickname + ", crtime=" + crtime + ", uptime="
				+ uptime + ", youxiang=" + youxiang + ", isRegister="
				+ isRegister + "]";
	}
	
}
