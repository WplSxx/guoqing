package com.jk.mapper;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jk.pojo.Org;
import com.jk.pojo.Role;
import com.jk.pojo.Tree;
import com.jk.pojo.User;
import com.jk.pojo.UserRole;

public interface OrgMapper {

	void add(Org org);

	void delete(Integer id);

	List<Org> queryOrg(Org orgQuery);

	Org queryById(Integer id);

	void update(Org org);
	
}
