package com.jk.mapper;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jk.pojo.Excamine;
import com.jk.pojo.Invoice;
import com.jk.pojo.Role;
import com.jk.pojo.Tree;
import com.jk.pojo.User;
import com.jk.pojo.UserRole;

public interface invoiceMapper {

	List<Excamine> getExcamine();

	void save(Invoice invoice);

	List<Invoice> getInvoice(Invoice invoice);

	void update(Invoice invoice);

	void saveExamine(Invoice invoice);
	
}
