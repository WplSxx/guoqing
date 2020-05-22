package com.jk.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jk.pojo.Org;
import com.jk.pojo.Role;
import com.jk.pojo.User;
import com.jk.service.OrgService;
import com.jk.service.RoleService;
import com.jk.service.userService;
@Controller
@RequestMapping("/org")
public class OrgController {

	@Autowired
	private userService userservice;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private OrgService orgService;
	
	
	
	//登陆后跳转页面的方法
	@RequestMapping("/toOrg")
	public String getUser(){
		return "org/org";
	}
	
	
	
	
	/**
	 * 添加权限
	 * @param role
	 * @param request
	 * @return
	 * @user wpl
	 */
	@RequestMapping("/addOrg")
	@ResponseBody
	public  Map<String,Object>  addOrg(Org org,HttpServletRequest request){
		 Map<String,Object> returnMap = new HashMap<String,Object>();
		 try {
			 if(org.getId()!=null && org.getId()!=0){
				 orgService.update(org);
			 }else{
				 orgService.add(org);
			 }
			returnMap.put("success", true);
		} catch (Exception e) {
			// TODO: handle exception
			returnMap.put("success", false);
			returnMap.put("msg", e);
		}
		 return returnMap;
	}
	
	/**
	 * 删除权限
	 * @param role
	 * @param request
	 * @return
	 * @user wpl
	 */
	@RequestMapping("/deleteOrg")
	@ResponseBody
	public  Map<String,Object>  deleteOrg(Org org,HttpServletRequest request){
		Map<String,Object> returnMap = new HashMap<String,Object>();
		try {
			orgService.delete(org.getId());
			returnMap.put("success", true);
		} catch (Exception e) {
			// TODO: handle exception
			returnMap.put("success", false);
			returnMap.put("msg", e);
		}
		return returnMap;
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
			roleService.add(role);
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
			roleService.addRoleRow(role);
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
	@RequestMapping("deleteRole")
	@ResponseBody
	public Map<String,Object> deleteRole(Integer id){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			roleService.delete(id);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
	
		return map;
	}
	
	//获取权限信息
	@RequestMapping("queryOrg")
	@ResponseBody
	public List<Org> queryOrg(){
		Org orgQuery = new Org();
		return orgService.queryOrg(orgQuery);
	}
	
	//跳转到新增权限
	@RequestMapping("toAddOrg")
	public String toAddOrg(Model model,Integer id){
		Org orgQuery = new Org();
		orgQuery.setFid("0");
		List<Org> list = orgService.queryOrg(orgQuery);
		if(id!=null && id!=0){
			Org org = orgService.queryById(id);
			model.addAttribute("org",org);
		}
		model.addAttribute("fidList",list);
		return "org/addOrg";
	}
	
	
}
