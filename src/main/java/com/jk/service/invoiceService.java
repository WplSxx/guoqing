package com.jk.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.jk.pojo.Excamine;
import com.jk.pojo.Invoice;
import com.jk.pojo.Role;
import com.jk.pojo.Tree;
import com.jk.pojo.User;

public interface invoiceService {

	/**
	 * 查询部门数据
	 * @return
	 */
	List<Excamine> getExcamine();
    /**
     * 保存发票信息
     * @param invoice
     */
	void save(Invoice invoice);
	/**
	 * 查询发票数据
	 * @param invoice
	 */
	List<Invoice> getInvoice(Invoice invoice);
	/**
	 * 发票状态修改
	 * @param invoice
	 */
	void update(Invoice invoice);
	/**
	 * 发票状态修改，并添加审核信息
	 * @param invoice
	 */
	void saveExamine(Invoice invoice);
}
