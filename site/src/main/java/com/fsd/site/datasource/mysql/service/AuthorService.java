package com.fsd.site.datasource.mysql.service;

import java.util.List;

import com.fsd.site.datasource.mysql.bean.Author;

public interface AuthorService {
	int add(Author author);
	int update(Author author);
	int delete(Long id);
	Author findAuthor(Long id);
	List<Author> findAuthorList();
}
