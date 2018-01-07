package com.fsd.site.datasource.mysql.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.fsd.site.datasource.mysql.bean.Author;
import com.fsd.site.datasource.mysql.dao.AuthorDao;

@Service("authorDao")
public class AuthorDaooImpl implements AuthorDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int add(Author author) {
		System.out.println("-------------: " + author.getRealName() + " : " + author.getNickName());
		return jdbcTemplate.update("insert into t_author(real_name, nick_name) values(?, ?)", author.getRealName(),
				author.getNickName());
	}

	@Override
	public int update(Author author) {
		return jdbcTemplate.update("update t_author set real_name = ?, nick_name= ? where id = ?",
				new Object[] { author.getRealName(), author.getNickName(), author.getId() });
	}

	@Override
	public int delete(Long id) {
		return jdbcTemplate.update("delete from t_author where id = ?", id);
	}

	@Override
	public Author findAuthor(long id) {
		List<Author> list = jdbcTemplate.query("select * from t_author where id = ?", new Object[] {id}, new BeanPropertyRowMapper(Author.class));
		
		if(null != list && list.size() > 0 ) {
			Author author = list.get(0);
			return author;
		}else {
			return null;
		}
	}

	@Override
	public List<Author> findAuthorList() {
		List<Author> list = jdbcTemplate.query("select * from t_author", new Object[] {}, new BeanPropertyRowMapper(Author.class));
		return list;
	}

}
