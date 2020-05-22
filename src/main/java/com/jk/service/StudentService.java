package com.jk.service;

import java.util.List;

import com.jk.pojo.ListMap;
import com.jk.pojo.Org;
import com.jk.pojo.Pay;
import com.jk.pojo.Role;
import com.jk.pojo.Student;

public interface StudentService {
   
	List<Student> queryByList();
    /**
     * 计算缴费记录
     * 
     * @user wpl
     */
	void queryStudentPay();
	/**
	 * 欠费记录
	 * @param model
	 * @return
	 * @user wpl
	 */
	List<Student> queryPayByList(Student model);
	/**
	 * 缴费功能
	 * @param pay
	 * @user wpl
	 */
	void updatePay(Pay pay);
	/**
	 * 查询饼状图数据
	 * @return
	 * @user wpl
	 */
	List<ListMap> queryPaybingtu();

}
