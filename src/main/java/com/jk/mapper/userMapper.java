package com.jk.mapper;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jk.pojo.Role;
import com.jk.pojo.Tree;
import com.jk.pojo.User;
import com.jk.pojo.UserRole;

public interface userMapper {

	User queryUserByName(String user_name);

	List<Tree> getTree(Integer i);

	void zhuce(User user);

	void updateZhuce(User user);

	List<User> getUsers(User user);

	//查询所有的角色信息
	List<Role> getRoot();

	//通过用户id查询用户所拥有的角色
	List<Role> queryRoleByUserId(Integer id);

	//根据用户id删除所有关联的角色
	void delUserRole(Integer id);
	//新增用户角色
	void addUserRole(List<UserRole> list);
	//获取用户当前的角色
	List<Role> getRootById(Integer id);
	//查询所有的权限菜单
	List<Tree> queryPower();
	//查询树的回显权限方法
	List<Tree> getPowerByRid(Integer id);
	//导出的方法
	List<User> queryuserdaochu();
    /**
     * 删除用户
     * @param id
     * @user wpl
     */
	void delete(Integer id);

	User queryByUser(String id);
	
}
