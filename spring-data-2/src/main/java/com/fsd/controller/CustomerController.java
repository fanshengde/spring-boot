package com.fsd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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

	// http://localhost:8080/customer/getCustomerByEmail/fanshengde@hotmail.com
	@RequestMapping(value = "/customer/getCustomerByEmail/{emailAddress}", method = RequestMethod.GET)
	@ResponseBody
	public String getUserByid(@PathVariable String emailAddress) {
		System.out.println("hello user 111" + emailAddress);

		Customer entity = customerRepository.findByEmailAddress(new EmailAddress(emailAddress));
		if (entity == null) {
			return "没有任何数据";
		}
		return entity.toString();
	}

	//

	@RequestMapping(value = "/customer/save/{saveEntity}", method = RequestMethod.GET)
	@ResponseBody
	public void savetCustomer(@PathVariable String saveEntity) {
		System.out.println("hello user 222");
		String[] infos = saveEntity.split(":");
		Customer entity = new Customer(infos[0], infos[1]);
		EmailAddress emailAddress = new EmailAddress(infos[2]);
		customerRepository.findByEmailAddress(emailAddress);
		Assert.isNull(customerRepository.findByEmailAddress(emailAddress), "email must be unique");

		entity.setEmailAddress(emailAddress);
		customerRepository.save(entity);
	}

	@RequestMapping(value = "/customer/saveAll/{saveEntity}", method = RequestMethod.GET)
	@ResponseBody
	public void savetCustomerAll(@PathVariable String saveEntity) {
		System.out.println("hello user 222");
		String[] infos = saveEntity.split(":");
		Customer entity = new Customer(infos[0], infos[1]);
		EmailAddress emailAddress = new EmailAddress(infos[2]);
		customerRepository.findByEmailAddress(emailAddress);
		Assert.isNull(customerRepository.findByEmailAddress(emailAddress), "email must be unique");

		entity.setEmailAddress(emailAddress);
		entity.add(new Address("yongding lu", "beijing", "zh"));
		customerRepository.save(entity);
	}

	@RequestMapping(value = "/customer/likeCustomerByEmail/{emailAddress}", method = RequestMethod.GET)
	@ResponseBody
	public String likeUserByid(@PathVariable String emailAddress) {
		System.out.println("hello user 111" + emailAddress);

		List<Customer> entity = customerRepository.findByFirstnameLike(emailAddress);

		return entity.toString();
	}

	@RequestMapping(value = "/customer/findByFirstnameAndLastname/{names}", method = RequestMethod.GET)
	@ResponseBody
	public String findByFirstnameAndLastname(@PathVariable String names) {
		System.out.println("hello user 111" + names);
		String[] name = names.split(":");
		List<Customer> entity = customerRepository.findByFirstnameAndLastname(name[0], name[1]);

		return entity.toString();
	}

	@RequestMapping(value = "/customer/findByAddressCity/{city}", method = RequestMethod.GET)
	@ResponseBody
	public String findByAddressCity(@PathVariable String city) {
		System.out.println("hello user 111" + city);
		List<Customer> entity = customerRepository.findByAddressCity(city);

		return entity.toString();
	}

	@RequestMapping(value = "/customer/findByLastname/{name}", method = RequestMethod.GET)
	@ResponseBody
	public String findByLastname(@PathVariable String name) {
		System.out.println("hello user 111" + name);

		Pageable pageable = PageRequest.of(0, 2, Direction.ASC, "firstname");

		Page<Customer> entity = customerRepository.findByLastname(name, pageable);

		return entity.getContent().toString();
	}

	@RequestMapping(value = "/customer/findByLastname4Sort/{name}", method = RequestMethod.GET)
	@ResponseBody
	public String findByLastname4Sort(@PathVariable String name) {
		System.out.println("hello user 111" + name);

		Sort sort = new Sort(Direction.DESC, "lastname");

		Iterable<Customer> entity = customerRepository.findByLastname(name, sort);
		return entity.toString();
	}

	@RequestMapping(value = "/customer/findByAll/{nums}", method = RequestMethod.GET)
	@ResponseBody
	public String findByAll(@PathVariable String nums) {
		System.out.println("hello user 111: " + nums);
		String[] num = nums.split(":");
		Pageable pageable = PageRequest.of(Integer.valueOf(num[0]), Integer.valueOf(num[1]), Direction.ASC,
				"firstname");

		Page<Customer> entity = customerRepository.findAll(pageable);

		return entity.getContent().toString();
	}
	@RequestMapping(value = "/customer/findByAllSort/{nums}", method = RequestMethod.GET)
	@ResponseBody
	public String findByAllSort(@PathVariable String nums) {
		System.out.println("hello user 111: " + nums);
		String[] num = nums.split(":");
		Sort sort = new Sort(Direction.ASC, "lastname");
		Iterable entity = customerRepository.findAll(sort);
		
		return entity.toString();
	}
}
