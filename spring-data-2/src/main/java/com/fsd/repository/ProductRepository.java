package com.fsd.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fsd.pojo.Product;

//QueryDslPredicateExecutor<Product> 因为querydsl在maven还没成功导入
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
	Page<Product> findByDescriptionContaining(String description, Pageable pageable);
	Page<Product> findByAttributesContaining(String attribute, Pageable pageable);
}
