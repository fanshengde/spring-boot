package com.fsd.dao;

import java.util.List;

import com.fsd.bean.Event;

public interface EventDao {
	public List<Event> getByMap();
	public Event getById(Integer id);
	public Integer create(Event event);
	public int update(Event event);
	public int delete(Integer id);
}
