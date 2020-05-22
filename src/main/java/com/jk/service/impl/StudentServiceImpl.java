package com.jk.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jk.mapper.StudentMapper;
import com.jk.pojo.ListMap;
import com.jk.pojo.Pay;
import com.jk.pojo.Student;
import com.jk.service.StudentService;
@Service
public class StudentServiceImpl implements StudentService {


	protected final static Map<String,String> mapStatic = new HashMap<String,String>();  
	static { 
		mapStatic.put("1", "学费");
		mapStatic.put("2", "住宿费");
		mapStatic.put("3", "保证金");
	}
	protected final static Map<String,String> mapStaticSlive = new HashMap<String,String>();  
	static { 
		mapStaticSlive.put("1", "学费");
		mapStaticSlive.put("2", "保证金");
	}
	
	private final static Integer tuition = 21800;
	private final static Integer hotel_expense = 500;
	private final static Integer bond = 100;



	
	@Autowired
	private StudentMapper studentMapper;


	@Override
	public List<Student> queryByList() {
		// TODO Auto-generated method stub
		return studentMapper.queryByList();
	}


	@Override
	public void queryStudentPay() {
		//查询所有学生
		List<Student> studentList = studentMapper.queryByList();
		for(Student student:studentList){
			//查询缴费记录  (此人有没有生成缴费数据)
			List<Pay> payList = studentMapper.queryByPay(student.getSid());
			if(payList!=null && payList.size()>0){
				//是否住宿
				if(student.getSlive()==1){
					//查询缴费记录  (此人本月有没有生成缴费“住宿费”数据)
					List<Pay> payListNow = studentMapper.queryByPayNow(student.getSid());
					if(payListNow.size()==0){
			            Pay pay = new Pay();    
			        	pay.setPamount_must(hotel_expense);
			        	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date nowDate = new Date();
						pay.setPtime(formatter.format(nowDate));
						pay.setPtype(2);
						pay.setPname(student.getSid());
						pay.setPamount(0);
						studentMapper.savePay(pay);
					}
				}
			}else{
				//是否住宿
				if(student.getSlive()==1){
					for(int i=1;i<=mapStatic.size();i++){
						Pay pay = new Pay();
						if(i==1){
							pay.setPamount_must(tuition);
						}else if(i==2){
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							Date nowDate = new Date();
							pay.setPtime(formatter.format(nowDate));
							pay.setPamount_must(hotel_expense);
						}else if(i==3){
							pay.setPamount_must(bond);
						}
						pay.setPtype(i);
						pay.setPname(student.getSid());
						pay.setPamount(0);
						studentMapper.savePay(pay);
					}
				}else{
					for(int i=1;i<=mapStaticSlive.size();i++){
						Pay pay = new Pay();
						pay.setPtype(i);
						if(i==1){
							pay.setPamount_must(tuition);
						}else if(i==2){
							pay.setPtype(3);
							pay.setPamount_must(bond);
						}
						pay.setPname(student.getSid());
						pay.setPamount(0);
						studentMapper.savePay(pay);
					}
				}
			}
		}
	}


	@Override
	public List<Student> queryPayByList(Student model) {
		// TODO Auto-generated method stub
		return studentMapper.queryPayByList(model);
	}


	@Override
	public void updatePay(Pay pay) {
		// TODO Auto-generated method stub
		studentMapper.updatePay(pay);
	}


	@Override
	public List<ListMap> queryPaybingtu() {
		// TODO Auto-generated method stub
		return studentMapper.queryPaybingtu();
	}
	
	
}
