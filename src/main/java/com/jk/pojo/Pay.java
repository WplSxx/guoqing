package com.jk.pojo;



public class Pay {
	
	
	private Integer pid;//学生id
	
	private java.lang.String ptime;//缴费时间
	
	private Integer pname;//学生id
	
	private Integer pamount;//金额
	
	private Integer ptype;//缴费类型
	
	private java.lang.String pform;//缴费方式
	
	private Integer pamount_must;//应缴费金额
	
	private Integer ispay;//是否欠费（0:否，1:是）

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public java.lang.String getPtime() {
		return ptime;
	}

	public void setPtime(java.lang.String ptime) {
		this.ptime = ptime;
	}

	public Integer getPname() {
		return pname;
	}

	public void setPname(Integer pname) {
		this.pname = pname;
	}

	public Integer getPamount() {
		return pamount;
	}

	public void setPamount(Integer pamount) {
		this.pamount = pamount;
	}

	
	public Integer getPtype() {
		return ptype;
	}

	public void setPtype(Integer ptype) {
		this.ptype = ptype;
	}

	public java.lang.String getPform() {
		return pform;
	}

	public void setPform(java.lang.String pform) {
		this.pform = pform;
	}

	public Integer getIspay() {
		return ispay;
	}

	public void setIspay(Integer ispay) {
		this.ispay = ispay;
	}

	public Integer getPamount_must() {
		return pamount_must;
	}

	public void setPamount_must(Integer pamount_must) {
		this.pamount_must = pamount_must;
	}
	
	
}
