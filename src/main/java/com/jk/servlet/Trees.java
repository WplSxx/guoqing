package com.jk.servlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jk.pojo.Tree;

public class Trees {

	public static List<Map<String,Object>> getTreeStr(List<Tree> list,Integer fid){
		List<Map<String,Object>> newlist = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < list.size(); i++) {
			Tree t = list.get(i);
			Map<String,Object> map = null;
			if(t.getFid().equals(fid)){
				map = new HashMap<String, Object>();
				map.put("id", t.getId());
				map.put("text", t.getOrg_name());
				map.put("url", t.getUrl());
				map.put("state", t.getState());
				map.put("children", getTreeStr(list,t.getId()));
			}
			if (map != null) {
				newlist.add(map);
			}
		}
		
		return newlist;
	}
}
