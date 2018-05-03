package com.fsd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.pojo.Address;
import com.fsd.pojo.Customer;
import com.fsd.pojo.EmailAddress;
import com.fsd.repository.CustomerRepository;

@RestController
public class CustomerController {
	@Autowired
	CustomerRepository customerRepository;
	
	//http://localhost:8080/customer/getCustomerByEmail/fanshengde@hotmail.com
	@RequestMapping(value="/customer/getCustomerByEmail/{emailAddress}", method = RequestMethod.GET)
	@ResponseBody
	public String getUserByid(@PathVariable String emailAddress) {
		System.out.println("hello user 111"+ emailAddress);
		
		Customer entity = customerRepository.findByEmailAddress(new EmailAddress(emailAddress));
		if(entity == null) {
			return "没有任何数据";
		}
		return entity.toString();
	}
	
	
	//
	
	@RequestMapping(value="/customer/save/{saveEntity}", method = RequestMethod.GET)
	@ResponseBody
	public void savetCustomer(@PathVariable String saveEntity) {
		System.out.println("hello user 222");
		String[] infos = saveEntity.split(":");
		Customer entity = new Customer(infos[0], infos[1]);
		EmailAddress emailAddress = new EmailAddress(infos[2]);
		customerRepository.findByEmailAddress(emailAddress);
		Assert.isNull(customerRepository.findByEmailAddress(emailAddress),"email must be unique");
		
		entity.setEmailAddress(emailAddress);
		customerRepository.save(entity);
	}
	@RequestMapping(value="/customer/saveAll/{saveEntity}", method = RequestMethod.GET)
	@ResponseBody
	public void savetCustomerAll(@PathVariable String saveEntity) {
		System.out.println("hello user 222");
		String[] infos = saveEntity.split(":");
		Customer entity = new Customer(infos[0], infos[1]);
		EmailAddress emailAddress = new EmailAddress(infos[2]);
		customerRepository.findByEmailAddress(emailAddress);
		Assert.isNull(customerRepository.findByEmailAddress(emailAddress),"email must be unique");
		
		entity.setEmailAddress(emailAddress);
		entity.add(new Address("yongding lu", "beijing", "zh"));
		customerRepository.save(entity);
	}
	
	@RequestMapping(value="/customer/likeCustomerByEmail/{emailAddress}", method = RequestMethod.GET)
	@ResponseBody
	public String likeUserByid(@PathVariable String emailAddress) {
		System.out.println("hello user 111"+ emailAddress);
		
		List<Customer> entity = customerRepository.findByFirstnameLike(emailAddress);
		
		return entity.toString();
	}
}
