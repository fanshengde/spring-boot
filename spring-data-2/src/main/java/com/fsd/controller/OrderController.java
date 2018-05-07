package com.fsd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.pojo.Address;
import com.fsd.pojo.Customer;
import com.fsd.pojo.order.Order;
import com.fsd.repository.CustomerRepository;
import com.fsd.repository.OrderRepository;

@RestController
public class OrderController {
	@Autowired
	OrderRepository orderRepository;

	@Autowired
	CustomerRepository customerRepository;
	
	@RequestMapping(value = "/order/saveEntity/{saveEntity}")
	@ResponseBody
	public void saveEntity(@PathVariable String saveEntity) {
		if (saveEntity != null) {
			String[] saveEntitys = saveEntity.split(":");
			if (saveEntitys.length == 5) {
				Customer customer = new Customer(saveEntitys[0], saveEntitys[1]);
				Address shippingAddress = new Address(saveEntitys[2], saveEntitys[3], saveEntitys[4]);
				customer.add(shippingAddress);
				Address billingAddress = shippingAddress;
				customerRepository.save(customer);
				
				
				Order order = new Order(customer, shippingAddress);
				orderRepository.save(order);
			}
		}
	}

	@RequestMapping(value = "/order/getEntity/{customer}")
	@ResponseBody
	public List<Order> findByCustomer(@PathVariable String customerInfo) {
		String[] customerInfos = customerInfo.split(":");
		Customer customer = new Customer(customerInfos[0], customerInfos[1]);
		List<Order> result = orderRepository.findByCustomer(customer);
		return result;
	}

}
