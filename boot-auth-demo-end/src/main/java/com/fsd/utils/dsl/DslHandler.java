package com.fsd.utils.dsl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class DslHandler {
	@Autowired
	@PersistenceContext
	private static  EntityManager entityManager;

	private static JPAQueryFactory jpaQueryFactory;

	public static JPAQueryFactory instance() {
		if (jpaQueryFactory == null) {
			jpaQueryFactory = new JPAQueryFactory(entityManager);
		}
		return jpaQueryFactory;
	}
}
