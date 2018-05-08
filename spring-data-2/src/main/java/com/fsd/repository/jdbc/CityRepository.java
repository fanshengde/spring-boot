package com.fsd.repository.jdbc;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fsd.pojo.jdbc.City;

public interface CityRepository extends QuerydslPredicateExecutor<City>, PagingAndSortingRepository<City, Long> {

}
