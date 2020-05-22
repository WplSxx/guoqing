package com.jk.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.jk.pojo.Role;
import com.jk.pojo.Tree;
import com.jk.pojo.User;

public interface RoleService {
    /**
     * 保存角色信息
     * @param role
     * @user wpl
     */
	void add(Role role);

    /**
     * 保存角色和权限关系
     * @param role
     * @user wpl
     */
	void addRoleRow(Role role);
    /**
     * 删除角色
     * @param id
     * @user wpl
     */
	void delete(Integer id);

}
