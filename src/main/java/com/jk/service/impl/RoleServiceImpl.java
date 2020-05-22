package com.jk.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jk.mapper.RoleMapper;
import com.jk.pojo.Role;
import com.jk.pojo.UserRole;
import com.jk.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService {


	
	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public void add(Role role) {
		// TODO Auto-generated method stub
		//保存角色
		roleMapper.save(role);
	    //保存角色权限关系
		role.setRoleId(role.getId().toString());
		addRoleRow(role);
	}
	

	@Override
	public void addRoleRow(Role role) {
		//删除角色权限关系
		roleMapper.delRoleRow(role.getRoleId());
		
		if(role.getRowId()!=null && !role.getRowId().equals("")){
			List<Role> list = new ArrayList<Role>();
			String[] idd=role.getRowId().split(",");
			for (int i = 0; i < idd.length; i++) {
				Role roleNew = new Role();
				roleNew.setRoleId(role.getRoleId());
			    if(!idd[i].equals("")){
			    	roleNew.setRowId(idd[i]);
			    	list.add(roleNew);
	        	}
			}
			//从新创建关系
			roleMapper.addRoleRole(list);
		}
	}


	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		roleMapper.delete(id);
	}
	
}
