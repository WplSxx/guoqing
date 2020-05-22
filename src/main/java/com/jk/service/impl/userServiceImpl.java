package com.jk.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jk.mapper.userMapper;
import com.jk.pojo.Role;
import com.jk.pojo.Tree;
import com.jk.pojo.User;
import com.jk.pojo.UserRole;
import com.jk.service.userService;
@Service
public class userServiceImpl implements userService {
	

	@Autowired
	private userMapper usermapper;
	
	
	
private static final String file = "stmp";
	
	/**
	 * 初始化资源文件服务
	 */
	private static Properties props = new Properties();
	static {
		ResourceBundle bundle = getProperties(file);
		props.put("mail.smtp.auth", bundle.getString("mail.smtp.auth"));//否需要身份验证
		props.put("mail.smtp.ssl.enable", bundle.getString("mail.smtp.ssl.enable"));//安全性认证
		props.put("mail.smtp.host", bundle.getString("mail.smtp.host"));//邮件主机
		props.put("mail.smtp.timeout", bundle.getString("mail.smtp.timeout"));//超时
		props.put("mail.smtp.port", Integer.parseInt(bundle.getString("mail.smtp.port")));//端口
		props.put("mail.smtp.pass", bundle.getString("mail.smtp.pass"));//认证密码
		props.put("mail.smtp.from", bundle.getString("mail.smtp.from"));//发送方
	}
	
	public static ResourceBundle getProperties(String properties) {
		return ResourceBundle.getBundle(properties, Locale.getDefault());
	}

	@Override
	public Map<String, Object> queryUserByName(User user,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(user !=null){
			User u = usermapper.queryUserByName(user.getUser_name());
			if(u !=null){
				if(u.getPwd().equals(user.getPwd())){
					request.getSession().setAttribute("usermsg",u);
					map.put("msg","登陆成功!!!");
					map.put("success",true);
					List<Role> role = usermapper.queryRoleByUserId(u.getId());
					request.getSession().setAttribute("userrole",role);
				}else{
					map.put("msg","密码错误");
					map.put("success",false);
				}
			}else{
				map.put("msg","用户不存在");
				map.put("success",false);
			}
		}
		
		return map;
	}

	@Override
	public List<Tree> getTree(Integer id) {
		return usermapper.getTree(id);
	}

	
	public static void sendMainStatic(String email,String title,String context){
		boolean flag = true;
		Session session = Session.getDefaultInstance(props, new Authenticator() {
			
			
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication(props.getProperty("mail.smtp.from"), props.getProperty("mail.smtp.pass"));
			}
		});
		session.setDebug(false);
		
		System.out.println("邮件地址：" + email + ",邮件内容：" + context);
		try {
			// 设置群发邮件
			Address[] tos = new InternetAddress[1];
			tos[0] = new InternetAddress(email);//设置邮件
			javax.mail.Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(props.getProperty("mail.smtp.from")));
			message.addRecipients(javax.mail.Message.RecipientType.TO, tos);// 群发邮件
			message.setSubject(title);//设置主题
			message.setText(context);//设置内容
			Transport.send(message);
			
		}
		catch (AddressException e) {
			System.out.println("收件人不存在：" + email + "信息错误：" + e.getMessage());
			flag = false;
		}
		catch (MessagingException e) {
			System.out.println("发送邮件出错：" + email + "信息错误：" + e.getMessage());
			flag = false;
		}
		catch (Exception e) {
			System.out.println("发送邮件出错：" + email + "信息错误：" + e.getMessage());
			flag = false;
		}
	}

	public static void main(String[] str){
		sendMainStatic("mistersuo@sina.com","邮箱验证","http://127.0.0.1:8080/adfdf/id=''");
	}

	@Override
	public void zhuce(User user) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date nowDate = new Date();
		user.setCrtime(formatter.format(nowDate));
		user.setUptime(formatter.format(nowDate));
		usermapper.zhuce(user);
		//sendMainStatic(user.getYouxiang(),"注册验证","http://192.168.3.46:8080/ssm-xingqi/yanzheng.jk?user_name="+user.getUser_name());
	}

	@Override
	public void updateZhuce(User user) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date nowDate = new Date();
		user.setUptime(formatter.format(nowDate));
		usermapper.updateZhuce(user);
		
	}

	//查询所有的用户
	@Override
	public 	List<User> getUsers(User user) {
		return usermapper.getUsers(user);
	}
	
	//查询角色的方法
	@Override
	public List<Role> getRoot() {
		return usermapper.getRoot();
	}

	@Override
	public void addUserRole(Integer id, String ids) {
		List<UserRole> list = new ArrayList<UserRole>();
		String[] idd=ids.split(",");
		for (int i = 0; i < idd.length; i++) {
				list.add(new UserRole(id,Integer.valueOf(idd[i])));
		}
		System.out.println(list);
		usermapper.delUserRole(id);
		usermapper.addUserRole(list);
	}

	
	@Override
	public List<Role> getRootById(Integer id) {
		return usermapper.getRootById(id);
	}

	@Override
	public List<Tree> queryPower() {
		return usermapper.queryPower();
	}

	//查询树的回显权限方法
	@Override
	public List<Tree> getPowerByRid(Integer id) {
		return usermapper.getPowerByRid(id);
	}

	@Override
	public List<User> queryuserdaochu() {
		return usermapper.queryuserdaochu();
	}
    /**
     * 删除用户
     */
	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		usermapper.delete(id);
	}

	@Override
	public User queryByUser(String id) {
		// TODO Auto-generated method stub
		return usermapper.queryByUser(id);
	}
	
}
