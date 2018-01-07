package com.fsd.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.bean.Event;
import com.fsd.service.EventService;

@RestController
@RequestMapping(value = "/events")
public class EventController {

	@Autowired
	private EventService eventService;

	@RequestMapping(method = RequestMethod.GET)
	public List<Event> list(HttpServletRequest request) {
		return eventService.getByMap(null);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Event detail(@PathVariable Integer id) {
		return eventService.getById(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Event create(@RequestBody Event event) {
		return eventService.create(event);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public Event update(@RequestBody Event event) {
		return eventService.update(event);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public int delete(@PathVariable Integer id) {
		return eventService.delete(id);
	}
}
