package com.fsd.dao.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fsd.pojo.Address;
import com.fsd.pojo.Customer;
import com.fsd.pojo.EmailAddress;


@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerRepositoryTest {
	@Autowired
	CustomerRepository repository;
	
	@Test
	public void savesCustomerCorrectly() {
		EmailAddress email = new EmailAddress("fanshengde@hotmail.com");
		
		Customer fsd = new Customer("fan", "shengde");
		fsd.setEmailAddress(email);
		fsd.add(new Address("yongding_lu", "beijing", "chnia"));
	
		Customer result = repository.save(fsd);
		assertThat(result.getSid(),is(notNullValue()));
	}
	
	@Test
	public void readsCustomerByEmail() {
		EmailAddress email = new EmailAddress("fanshengde@hotmail.com");
		Customer zs = new Customer("z", "s");
		zs.setEmailAddress(email);
		
		Customer result = repository.findByEmailAddress(email);
		assertThat(result, is(zs));
	}
	
	@Test
	public void preventDuplicateEmail() {
		Customer fsd = repository.findByEmailAddress(new EmailAddress("fanshengde@hotmail.com"));
		
		Customer anotherFsd = new Customer("fan", "shengde1");
		anotherFsd.setEmailAddress(fsd.getEmailAddress());
		
		repository.save(anotherFsd);
	}
}
