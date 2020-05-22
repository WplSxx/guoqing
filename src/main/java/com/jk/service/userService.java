package com.jk.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.jk.pojo.Role;
import com.jk.pojo.Tree;
import com.jk.pojo.User;

public interface userService {

	Map<String, Object> queryUserByName(User user, HttpServletRequest request);

	List<Tree> getTree(Integer id);

	void zhuce(User user);

	void updateZhuce(User user);

	List<User> getUsers(User user);

	List<Role> getRoot();

	void addUserRole(Integer id, String ids);

	List<Role> getRootById(Integer id);

	List<Tree> queryPower();
	//查询树的回显方法
	List<Tree> getPowerByRid(Integer id);

	//导出的方法
	List<User> queryuserdaochu();
    /**
     * 删除用户
     * @param id
     * @user wpl
     */
	void delete(Integer id);
    /**
     * 根据用户id查询用户
     * @param id
     * @return
     * @user wpl
     */
	User queryByUser(String id);


}
