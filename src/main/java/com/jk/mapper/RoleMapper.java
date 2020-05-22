package com.jk.mapper;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jk.pojo.Role;
import com.jk.pojo.Tree;
import com.jk.pojo.User;
import com.jk.pojo.UserRole;

public interface RoleMapper {

	void delRoleRow(String roleId);

	void addRoleRole(List<Role> role);

	void save(Role role);

	void delete(Integer id);

	
}
