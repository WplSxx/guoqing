package com.jk.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyInterceptor implements HandlerInterceptor{

	@Override //请求执行完毕之后
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("整个请求结束");
	}

	@Override //请求未到达前台前对参数进行改变
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		System.out.println("准备向前台响应");
	}

	@Override //在请求执行前
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		String url = request.getRequestURI();
		
		System.out.println(url+"请求前拦截");
		
		if(url.indexOf("login.jk")>0){
			return true;
		}else{
			Object object = request.getSession().getAttribute("usermsg");
			if(object != null){
				return true;
			}
		}
		if(url.indexOf("toregister.jk")>0){
			return true;
		}
		if(url.indexOf("zhuce.jk")>0){
			return true;
		}
		if(url.indexOf("yanzheng.jk")>0){
			return true;
		}
		if(url.indexOf("updateUser.jk")>0){
			return true;
		}
		request.getRequestDispatcher("index.jsp").forward(request,response);
		//quest.getRequestDispatcher("register.jsp").forward(request,response);
		return false;
	}

	
}