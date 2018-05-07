package com.fsd.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsd.pojo.Customer;
import com.fsd.pojo.EmailAddress;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
	Customer save(Customer customer);

	Customer findByEmailAddress(EmailAddress emailAddress);
	
	List<Customer> findByFirstnameLike(String name);
	
	List<Customer> findByFirstnameAndLastname(String firstname, String lastname);
	
	//级联查询   查询address中city属性
	List<Customer> findByAddressCity(String city);
	
	//分页
	Page<Customer> findByLastname(@Param("firstname") String lastname, Pageable pageable);
	
	
	Iterable<Customer> findByLastname(String lastname, Sort sort);
	
	
}
