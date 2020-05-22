package com.jk.mapper;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jk.pojo.ListMap;
import com.jk.pojo.Org;
import com.jk.pojo.Pay;
import com.jk.pojo.Role;
import com.jk.pojo.Student;
import com.jk.pojo.Tree;
import com.jk.pojo.User;
import com.jk.pojo.UserRole;

public interface StudentMapper {


	List<Student> queryByList();

	List<Pay> queryByPay(Integer sid);

	void savePay(Pay pay);

	List<Pay> queryByPayNow(Integer sid);

	List<Student> queryPayByList(Student model);

	void updatePay(Pay pay);

	List<ListMap> queryPaybingtu();
	
}
