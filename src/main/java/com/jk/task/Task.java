package com.jk.task;

import org.springframework.beans.factory.annotation.Autowired;

import com.jk.service.StudentService;



public class Task {
	
	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private StudentService studentService; 
	
	 public void show(){  
	        System.out.println("XMl:is show run");  
	        studentService.queryStudentPay();
	 }  
}