package com.jk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jk.mapper.invoiceMapper;
import com.jk.pojo.Excamine;
import com.jk.pojo.Invoice;
import com.jk.service.invoiceService;
@Service
public class invoiceServiceImpl implements invoiceService {
	

	@Autowired
	private invoiceMapper invoiceDao;

	@Override
	public List<Excamine> getExcamine() {
		return invoiceDao.getExcamine();
	}

	@Override
	public void save(Invoice invoice) {
		invoiceDao.save(invoice);
	}

	@Override
	public List<Invoice> getInvoice(Invoice invoice) {
		return invoiceDao.getInvoice(invoice);
	}

	@Override
	public void update(Invoice invoice) {
		invoiceDao.update(invoice);
	}

	@Override
	public void saveExamine(Invoice invoice) {
		
		//保存发票审核信息
	    try {
	    	invoiceDao.saveExamine(invoice);
			
		} catch (Exception e) {
			System.err.println(e);
		}
		
		
		//修改发票状态
		invoiceDao.update(invoice);
		
	}
	
}
