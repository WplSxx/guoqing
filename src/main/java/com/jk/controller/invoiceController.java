package com.jk.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jk.pojo.Excamine;
import com.jk.pojo.Invoice;
import com.jk.pojo.User;
import com.jk.service.invoiceService;
@Controller
@RequestMapping("/invoice")
public class invoiceController {

	@Autowired
	private invoiceService invoiceService;
	
	//发票保存
	@RequestMapping("save")
	@ResponseBody				
	public Map<String,Object> save(Invoice invoice,HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("usermsg");
		invoice.setUser_id(user.getId());
		invoice.setStatus(0);
		invoiceService.save(invoice);
		Map<String, Object> returnMap = new HashMap<String,Object>();
		returnMap.put("success", true);
		return returnMap;
	}
	
	//跳转到发票填写
	@RequestMapping("jumpInvoiceSumbmit")
	public String jumpInvoiceSumbmit(Invoice invoice,HttpServletRequest request){
		return "jumpInvoice";
	}
	
	//跳转到发票列表
	@RequestMapping("jumpInvoice")
	public String jumpInvoice(Invoice invoice,HttpServletRequest request){
		return "invoice";
	}
	//跳转到添加发票列表
	@RequestMapping("jumpAddInvoice")
	public String jumpAddInvoice(Invoice invoice,HttpServletRequest request){
		return "addInvoice";
	}
	//跳转到发票审核
	@RequestMapping("jumpExamine")
	public String jumpExamine(Invoice invoice,HttpServletRequest request){
		return "examine";
	}
	
	//发票数据查询
	@RequestMapping("getInvoice")
	@ResponseBody				
	public Map<String,Object> getInvoice(Invoice invoice,HttpServletRequest request){
		List<Invoice> list = invoiceService.getInvoice(invoice);
		Map<String, Object> returnMap = new HashMap<String,Object>();
		returnMap.put("rows", list);
		return returnMap;
	}
	
	
	//修改发票状态
	@RequestMapping("update")
	@ResponseBody
	public Map<String, Object> update(Invoice invoice){
		Map<String, Object> returnMap = new HashMap<String,Object>();
		try {
			invoiceService.update(invoice);
			returnMap.put("success","true");
		} catch (Exception e) {
			// TODO: handle exception
			returnMap.put("msg", "保存失败");
		}
		return returnMap;
	}
	/**
	 * 修改发票状态，并添加审核信息
	 * @param invoice
	 * @return
	 */
	@RequestMapping("saveExamine")
	@ResponseBody
	public Map<String, Object> saveExamine(Invoice invoice,HttpServletRequest request){
		Map<String, Object> returnMap = new HashMap<String,Object>();
		try {
			User user = (User) request.getSession().getAttribute("usermsg");
			invoice.setUser_id(user.getId());
			invoice.setExamine_time(new Date());
			invoice.setInvoice_id(invoice.getId());
			invoiceService.saveExamine(invoice);
			returnMap.put("success","true");
		} catch (Exception e) {
			// TODO: handle exception
			returnMap.put("msg", "保存失败");
		}
		return returnMap;
	}
	
	
	//查询审核数据的方法
	@RequestMapping("getExcamine")
	@ResponseBody
	public Map<String, Object> getExcamine(HttpServletRequest request){
		
		List<Excamine> list = invoiceService.getExcamine();
		Map<String, Object> returnMap = new HashMap<String,Object>();
		returnMap.put("data", list);
		return returnMap;
	}
		
}
