package com.jk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jk.mapper.OrgMapper;
import com.jk.pojo.Org;
import com.jk.service.OrgService;
@Service
public class OrgServiceImpl implements OrgService {


	
	@Autowired
	private OrgMapper orgMapper;

	@Override
	public void add(Org org) {
		// TODO Auto-generated method stub
		orgMapper.add(org);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		orgMapper.delete(id);
	}

	@Override
	public List<Org> queryOrg(Org orgQuery) {
		// TODO Auto-generated method stub
		return orgMapper.queryOrg(orgQuery);
	}

	@Override
	public Org queryById(Integer id) {
		// TODO Auto-generated method stub
		return orgMapper.queryById(id);
	}

	@Override
	public void update(Org org) {
		// TODO Auto-generated method stub
		orgMapper.update(org);
	}
		
}
