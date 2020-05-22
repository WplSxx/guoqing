package com.jk.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jk.pojo.ListMap;
import com.jk.pojo.Pay;
import com.jk.pojo.Student;
import com.jk.service.StudentService;
import com.jk.servlet.ExportExcel;

 
@Controller
@RequestMapping("/student") 
public class StudentAction{
	
	protected final static Logger log= Logger.getLogger(StudentAction.class);
	
	

	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private StudentService studentService; 
	
	/**
	 * 进入列表页面
	 * @param model 承接对象
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("toList") 
	public String list(Student model) throws Exception{
		return "student/studentList";
	}
	/**
	 * 欠费名单
	 * @param model
	 * @return
	 * @throws Exception
	 * @user wpl
	 */
	@RequestMapping("toPay") 
	public String toPay(Integer id,Model model) throws Exception{
		model.addAttribute("sid", id);
		return "student/payList";
	}
	/**
	 * 跳转到缴费界面
	 * @param model
	 * @return
	 * @throws Exception
	 * @user wpl
	 */
	@RequestMapping("toUpdatePay") 
	public String toUpdatePay(String id,Integer yinj,Model model) throws Exception{
		model.addAttribute("id",id);
		model.addAttribute("yinj",yinj);
		return "student/toUpdatePay";
	}
	
	
	/**
	 * 列表页面列表数据获取
	 * @param model 承接对象
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("dataList") 
	@ResponseBody
	public List<Student> datalist(Model model,HttpServletResponse response) throws Exception{
		List<Student> dataList = studentService.queryByList();
		//设置页面数据
		return dataList;
	}
	/**
	 * 欠费数据
	 * @param model 承接对象
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("dataPayList") 
	@ResponseBody
	public List<Student> dataPayList(Student model,HttpServletResponse response) throws Exception{
		List<Student> dataList = studentService.queryPayByList(model);
		//设置页面数据
		return dataList;
	}
	
	//添加角色的方法
	@RequestMapping("updatePay")
	@ResponseBody
	public Map<String,Object> updatePay(Integer pid,Integer pamount,Integer pamount_must,Student pform){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			Pay pay = new Pay();
			pay.setPamount(pamount);
			pay.setPid(pid);
			if(pamount_must>=pamount){
				pay.setIspay(0);
			}else{
				pay.setIspay(1);
			}
			pay.setPform(pform.getPform());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date nowDate = new Date();
			pay.setPtime(formatter.format(nowDate));
			studentService.updatePay(pay);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
	
		return map;
	}
	
	//导出的方法
			@RequestMapping("Daochul")
			public  void Daochul(HttpServletResponse response){ 
				String title = "用户表";
				String[] rowName = {"学生编号","学生姓名","费用类型","欠费金额","所在班级"};
				List<Object[]> dataList=new ArrayList<Object[]>();
				Student model = new Student();
				model.setSid(0);
				List<Student> list=studentService.queryPayByList(model);
				Object[] obj=null;
				for (Student p : list) {
					obj=new Object[rowName.length];
					obj[0]=Integer.valueOf(p.getSid());
					obj[1]=p.getSname();
					if(p.getPtype()==1){
						obj[2]= "学费";
		 			}else if(p.getPtype()==2){
		 				obj[2]= "住宿费";
		 			}else if(p.getPtype()==3){
		 				obj[2]= "保证金";
		 			}else if(p.getPtype()==4){
		 				obj[2]= "补交费";
		 			}
					obj[3]=p.getPamount_must()-p.getPamount();
					obj[4]=p.getClassName();
					dataList.add(obj);
				}
				ExportExcel excel = new ExportExcel(title, rowName, dataList, response);
				try {
					excel.export();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
	}
	/**
	 *统计图
	 * @param model 承接对象
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("toIchart") 
	public String toIchart(Student model) throws Exception{
		return "student/ichartjsDemo";
	}
	
	
	/**
	 * 
	 * @param pid
	 * @param pamount
	 * @param pamount_must
	 * @param pform
	 * @return
	 * @user wpl
	 */
	@RequestMapping("bingtu")
	@ResponseBody
	public Map<String,Object> bingtu(Integer pid,Integer pamount,Integer pamount_must,Student pform){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			//查询缴费信息按班级分布数据
			List<ListMap> list=studentService.queryPaybingtu();
		    for(ListMap l : list){
		    	if(null == l.getValue()){
		    		l.setValue(0.0);
		    	}
		    }
			map.put("data", list);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
		
		return map;
	}
}
