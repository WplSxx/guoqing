package com.jk.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jk.pojo.Role;
import com.jk.pojo.Tree;
import com.jk.pojo.User;
import com.jk.service.userService;
import com.jk.servlet.ExportExcel;
@Controller
@RequestMapping("/")
public class userController {

	@Autowired
	private userService userservice;
	
	//登录的方法
	@RequestMapping("login")
	@ResponseBody				
	public Map<String,Object> loginUser(User user,HttpServletRequest request){
	 	String yzm = (String) request.getSession().getAttribute("imageCode");
   		String a = request.getParameter("yzm");
		Map<String,Object> map = userservice.queryUserByName(user,request);
		if(a.equals(yzm)){
			return map;
		}
		return null;
	}
	
	//登陆后跳转页面的方法
	@RequestMapping("getUser")
	public String getUser(){
		return "show";
	}
	
	
	//查询tree的方法
	@RequestMapping("getTree")
	@ResponseBody
	public List<Map<String, Object>> getTree(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("usermsg");
		//Tree m = new Tree();
		//m.setId(user.getId());
		List<Tree> list =userservice.getTree(user.getId());
		/*List<Tree> list = userservice.getTree(m);*/
		List<Map<String, Object>> treeList =getTreeStr(list, 0);
		return treeList;
	}
	
	
	//递归树的调用方法
	public List<Map<String,Object>> getTreeStr(List<Tree> list,Integer fid){
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
	
	
		//注册时要跳转页面的方法
		@RequestMapping("toregister")
		public String getTegis(){
			return "register";
		}
	
		//注册时验证激活跳转页面的方法
		@RequestMapping("yanzheng")
		public String getYanzheng(){
			return "yanzheng";
		}
		
		//注册的方法
		@RequestMapping("zhuce")
		@ResponseBody
		public void zhuce(User user){
			userservice.zhuce(user);
		}
		
		//用户点击激活修改的方法
		@RequestMapping("updateUser")
		@ResponseBody
		public void updateUser(User user){
			System.out.println(user.getUser_name());
			userservice.updateZhuce(user);
		}
		
		//跳转到查询的展示页面中（准备展示数据）
		@RequestMapping("tozhanshi")
		public String tozhanshi(){
			return "zhanshi";
		}

		//查询所有的'用户表'的方法
		@RequestMapping("getUserBy")
		@ResponseBody	
		public List<User> getUsers(User user){
			
			return userservice.getUsers(user);
		}
		
		
		//分配用户是跳转到弹框内容的页面
		@RequestMapping("toAddJs")
		public String toAddJs(Model model){
			List<Role> list = userservice.getRoot();
			model.addAttribute("list",list);
			return "jiaose";
		}
		
		
		//获取角色的方法
		@RequestMapping("getRoot")
		public String getRoot(Model model,Integer id){
			List<Role> list = userservice.getRoot();
			List<Role> rlist = userservice.getRootById(id);
			model.addAttribute("list",list);
			model.addAttribute("id",id);
			model.addAttribute("urole",rlist);
			
			return "jiaose";
		}
		
		
		//添加角色的方法
		@RequestMapping("giveRole")
		@ResponseBody
		public Map<String,Object> giveRole(Integer id,String ids){
			Map<String,Object> map = new HashMap<String, Object>();
			try {
				userservice.addUserRole(id,ids);
				map.put("success", true);
			} catch (Exception e) {
				e.printStackTrace();
				map.put("success", false);
			}
		
			return map;
		}
		
		
		//获取角色信息
		@RequestMapping("queryRole")
		@ResponseBody
		public List<Role> queryRole(){
			return userservice.getRoot();
		}
		
		//点击角色信息是要跳转的页面（展示信息）
		@RequestMapping("toRoleMSG")
		public String toRole(){
			return "rolemsg";
		}
		
		
		//跳转页面的方法
		@RequestMapping("toPow")
		public String toPow(){
			return "rolemsg";
		}
		
		//跳转到查询树的页面（页面内是获取树的方法）
		@RequestMapping("toPowPage")
		public String toPowPage(Integer id,Model model){
			
			model.addAttribute("id",id);
			return "givePower";
		}
		
		//查询树的方法在弹框中展示
		@RequestMapping("getPower")
		@ResponseBody
		public List<Map<String, Object>> queryPower(){
			List<Tree> list = userservice.queryPower();
			List<Map<String, Object>> list2 = getTreeStr(list, 0);
			return list2;
		}
		
		
		//查询树的回显
		@RequestMapping("getPowerByRid")
		@ResponseBody
		public List<Tree> getPowerByRid(Integer id){
			List<Tree> list = userservice.getPowerByRid(id);
			return list;
		}
		
		
		//导出的方法
		@RequestMapping("Daochul")
		public  void Daochul(HttpServletResponse response){ 
			String title = "用户表";
			String[] rowName = {"用户ID","用户名称","用户密码","用户昵称","创建时间","修改时间"};
			List<Object[]> dataList=new ArrayList<Object[]>();
			List<User> list=userservice.queryuserdaochu();
			Object[] obj=null;
			for (User p : list) {
				obj=new Object[rowName.length];
				obj[0]=Integer.valueOf(p.getId());
				obj[1]=p.getUser_name();
				obj[2]=p.getPwd();
				obj[3]=p.getNickname();
				obj[4]=p.getCrtime();
				obj[5]=p.getUptime();
				dataList.add(obj);
			}
			ExportExcel excel = new ExportExcel(title, rowName, dataList, response);
			try {
				excel.export();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		//导入的方法
	    @RequestMapping("Daoru")  
	    public String importExcel( @RequestParam(value = "uploadExcel") MultipartFile excelFile,HttpServletRequest request) throws IOException{  
	    	String result = "";
	        if (null == excelFile) {  
	            result = "模板文件为空,请选择文件";  
	            return result;  
	        }  
	        String path = "E:\\demo";  
	        //容错处理  
	        File dir = new File(path);  
	        if(!dir.exists()) {  
	            dir.mkdirs();  
	        }  
	        
	        InputStream fis = excelFile.getInputStream();  
	        
	        List<Map<String, String>>  data = parseExcel(fis);  
	       //保存数据
	        for(Map<String,String> map : data){
	        	User user = new User();
	        	user.setUser_name(map.get("用户名称").toString());
	        	user.setNickname(map.get("用户昵称").toString());
	        	user.setPwd(map.get("用户密码").toString());
	        	user.setCrtime(map.get("创建时间").toString());
	        	user.setUptime(map.get("修改时间").toString());
	        	userservice.zhuce(user); 
	        }
	       
	       return "success";  
	    }  
	    
	    
	    //导入封装的一个方法
        public static List<Map<String, String>> parseExcel(InputStream fis) {  
            List<Map<String, String>> data = new ArrayList<Map<String, String>>();;  
            try {  
                HSSFWorkbook book = new HSSFWorkbook(fis);  
                HSSFSheet sheet = book.getSheetAt(0);  
                int firstRow = sheet.getFirstRowNum();  
                int lastRow = sheet.getLastRowNum();
                
                //除去表头和第一行  
                for(int i = firstRow + 1; i<lastRow + 1; i++) {  
                    Map<String,String> map = new HashMap<String,String>();  
                    HSSFRow row = sheet.getRow(i);  
                    int firstCell = row.getFirstCellNum();  
                    int lastCell = row.getLastCellNum();  
                    for(int j=firstCell; j<lastCell; j++) { 
                    	//获取列行
                	    HSSFCell cell2 = sheet.getRow(0).getCell(j); 
                	    //获取字符串的单元格
                        String key = cell2.getStringCellValue();  
                        HSSFCell cell = row.getCell(j);  
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {  
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);  
                        }  
                        String val = cell.getStringCellValue();  
                        if(i == firstRow) {  
                            break;  
                        }else{  
                            map.put(key, val);  
                        }  
                        System.out.println(map);  
                    }  
                    if(i != firstRow ) {  
                        data.add(map);  
                        System.out.println(map);  
                    }  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
            return data;  
        }  
        
        //添加用户的跳转页面
        @RequestMapping("toaddUser")
        public String toaddUser(String id,Model model){
        	if(null != id && !id.equals("")){
        		model.addAttribute("id",id);
        		User user = userservice.queryByUser(id);
        		model.addAttribute("user",user);
        	}else{
        		model.addAttribute("user",null);
        	}
        	return "user/addUser";
        }
        //重置密码
        @RequestMapping("toUpdateUser")
        public String toUpdateUser(String id,Model model){
        	if(null != id && !id.equals("")){
        		model.addAttribute("id",id);
        		User user = userservice.queryByUser(id);
        		model.addAttribute("user",user);
        	}else{
        		model.addAttribute("user",null);
        	}
        	return "user/updatePwdUser";
        }
        
        //添加用户信息的方法
        @RequestMapping("addUser")	
        @ResponseBody
        public Map<String,Object> addUser(User user){
        	Map<String,Object> map = new HashMap<String, Object>();
        	try {
        		if(null != user && null != user.getId() && !user.getId().equals("")){
        			userservice.updateZhuce(user);
        		}else{
        			userservice.zhuce(user);
        		}
        		map.put("success", true);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
				map.put("success", true);
			}
        	return map;
        }
        
        
        
        
    /**
     * 根据id删除用户
     * @param id
     * @param ids
     * @return
     * @user wpl
     */
	@RequestMapping("deleteUser")
	@ResponseBody
	public Map<String,Object> deleteUser(Integer id){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			userservice.delete(id);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
	
		return map;
	}
	
}
