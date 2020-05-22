package com.jk.service;

import java.util.List;

import com.jk.pojo.Org;
import com.jk.pojo.Role;

public interface OrgService {
    /**
     * 保存权限信息
     * @param role
     * @user wpl
     */
	void add(Org role);


    /**
     * 删除角色
     * @param id
     * @user wpl
     */
	void delete(Integer id);


	List<Org> queryOrg(Org orgQuery);


	Org queryById(Integer id);


	void update(Org org);

}
