package com.jk.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jk.pojo.Role;
import com.jk.pojo.User;
import com.jk.service.RoleService;
import com.jk.service.userService;
@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private userService userservice;
	
	@Autowired
	private RoleService roleservice;
	
	
	
	//登陆后跳转页面的方法
	@RequestMapping("/toAddRole")
	public String getUser(){
		return "role/addRole";
	}
	
	
	//登陆后跳转页面的方法
	@RequestMapping("/addRole")
	@ResponseBody
	public  Map<String,Object>  addRole(Role role,HttpServletRequest request){
		 Map<String,Object> returnMap = new HashMap<String,Object>();
		 try {
			User user = (User) request.getSession().getAttribute("usermsg");
			role.setCrea_user(user.getUser_name().toString());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date nowDate = new Date();
			role.setCrea_time(formatter.format(nowDate));
			roleservice.add(role);
			returnMap.put("success", true);
		} catch (Exception e) {
			// TODO: handle exception
			returnMap.put("success", false);
			returnMap.put("msg", e);
		}
		 return returnMap;
	}
	/**
	 * 新增角色和权限之间关系
	 * @param role
	 * @return
	 * @user wpl
	 */
	@RequestMapping("/addUserRole")
	@ResponseBody
	public  Map<String,Object>  addUserRole(Role role){
		Map<String,Object> returnMap = new HashMap<String,Object>();
		try {
			roleservice.addRoleRow(role);
			returnMap.put("success", true);
		} catch (Exception e) {
			// TODO: handle exception
			returnMap.put("success", false);
			returnMap.put("msg", e);
		}
		return returnMap;
	}
	/**
     * 根据id删除角色
     * @param id
     * @param ids
     * @return
     * @user wpl
     */
	@RequestMapping("/deleteRole")
	@ResponseBody
	public Map<String,Object> deleteRole(Integer id){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			roleservice.delete(id);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
	
		return map;
	}
	
	
}
