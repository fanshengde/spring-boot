package com.fsd.dao.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fsd.pojo.Customer;
import com.fsd.pojo.EmailAddress;

@Repository
@Qualifier("customerRepository")
public interface CustomerRepository extends CrudRepository<Customer, Long> {
	Customer findOne(Long id);

	Customer save(Customer customer);

	Customer findByEmailAddress(EmailAddress emailAddress);

}
