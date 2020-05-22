package com.jk.pojo;

import java.util.Date;

public class Invoice {
 
	//用户表用作登录 查询
	private Integer id;
	
	private Integer type;
	
	private Integer status;
	
	
	private Integer user_id;
	
	private String user_name;
	
	private Double money;
	
	private Integer department;

	private String content;
	
	private Integer invoice_id;
	
    private Date examine_time;
    
    private String examine_name;
    
    private Integer is_adopt;
    

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Integer getDepartment() {
		return department;
	}
	public void setDepartment(Integer department) {
		this.department = department;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(Integer invoice_id) {
		this.invoice_id = invoice_id;
	}
	public Date getExamine_time() {
		return examine_time;
	}
	public void setExamine_time(Date examine_time) {
		this.examine_time = examine_time;
	}
	public Integer getIs_adopt() {
		return is_adopt;
	}
	public void setIs_adopt(Integer is_adopt) {
		this.is_adopt = is_adopt;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getExamine_name() {
		return examine_name;
	}
	public void setExamine_name(String examine_name) {
		this.examine_name = examine_name;
	}
	
	
}
