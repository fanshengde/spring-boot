package com.fsd.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.fsd.pojo.Customer;
import com.fsd.pojo.order.Order;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {
	List<Order> findByCustomer(Customer customer);

}
