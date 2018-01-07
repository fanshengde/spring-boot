package com.fsd.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsd.bean.Event;
import com.fsd.dao.EventDao;

@Service
public class EventService {
	@Autowired
	private EventDao eventDao;

	public List<Event> getByMap(Map<String, Object> map) {
		return eventDao.getByMap();
	}

	public Event getById(Integer id) {
		return eventDao.getById(id);
	}

	public Event create(Event event) {
		eventDao.create(event);
		return event;
	}
	
	public Event update(Event event) {
		eventDao.update(event);
		return event;
	}
	
	public int delete(Integer id) {
		return eventDao.delete(id);
	}
}
