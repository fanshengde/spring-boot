package com.fsd.site.datasource.mysql.dao;

import java.util.List;

import com.fsd.site.datasource.mysql.bean.Author;

public interface AuthorDao {
	int add(Author author);
	int update(Author author);
	int delete(Long id);
	Author findAuthor(long id);
	List<Author> findAuthorList();
}
