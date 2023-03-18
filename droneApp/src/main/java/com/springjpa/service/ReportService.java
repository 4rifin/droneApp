package com.springjpa.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springjpa.dao.CustomerDao;
import com.springjpa.model.Customer;

@Service
@Transactional
public class ReportService {
	
	@Autowired 
	CustomerDao customerDao;
	
	public List<Map<String, ?>> findAllReportCustomer(){
		List<Map<String,?>> listAllReportCustomer = new ArrayList<Map<String,?>>();
		List<Customer> listCustomer = (List<Customer>) customerDao.findAll();
		int number = 0;
		for (Customer customer : listCustomer) {
			Map<String, Object> m = new HashMap<String,Object>();
			m.put("firstName", customer.getFirstName());
			m.put("lastName", customer.getLastName());
			listAllReportCustomer.add(m);
			number ++;
		}
		return listAllReportCustomer;
	}
	
}
