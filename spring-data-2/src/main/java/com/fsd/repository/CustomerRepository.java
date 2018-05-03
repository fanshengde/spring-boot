package com.fsd.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fsd.pojo.Customer;
import com.fsd.pojo.EmailAddress;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
	Customer save(Customer customer);

	Customer findByEmailAddress(EmailAddress emailAddress);
	
	List<Customer> findByFirstnameLike(String name);
}
