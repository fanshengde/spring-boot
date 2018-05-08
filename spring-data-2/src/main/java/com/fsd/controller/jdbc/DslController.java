package com.fsd.controller.jdbc;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.pojo.jdbc.City;
import com.fsd.pojo.jdbc.QCity;
import com.fsd.repository.jdbc.CityRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;

@RestController
@Transactional
public class DslController {
	@Autowired
	CityRepository cityRepository;

	@Autowired
	@PersistenceContext
	private EntityManager entityManager;

	private JPAQueryFactory queryFactory;

	@PostConstruct
	public void init() {
		queryFactory = new JPAQueryFactory(entityManager);
	}

	// @Autowired
	// HotelRepository hotelRepository;

	// 单表单条件查询
	@RequestMapping(value = "/dsl/city/{name}", method = RequestMethod.GET)
	@ResponseBody
	public City getCity(@PathVariable String name) {
		QCity qcity = QCity.city;
		Predicate predicate = qcity.name.eq(name);
		Optional<City> cityOptional = cityRepository.findOne(predicate);
		City city = cityOptional.get();
		return city;
	}

	@RequestMapping(value = "/dsl/citys", method = RequestMethod.GET)
	@ResponseBody
	public Iterable<City> getCitys() {
		QCity qcity = QCity.city;
		Predicate predicate = qcity.sid.gt(0);
		Iterable<City> entitys = cityRepository.findAll(predicate);
		return entitys;
	}

	// 单表多条件 - 1
	@RequestMapping(value = "/dsl/city4nameAndstate/{vars}", method = RequestMethod.GET)
	@ResponseBody
	public City getCityByNameAndState(@PathVariable String vars) {
		City city = null;
		if (vars != null) {
			String[] var = vars.split(":");
			if (var.length == 2) {
				QCity qCity = QCity.city;
				Predicate predicate = qCity.name.eq(var[0]).and(qCity.state.eq(var[1]));
				city = cityRepository.findOne(predicate).get();
			}
		}
		return city;

	}

	@RequestMapping(value = "/dsl/city4nameAndstate2/{vars}", method = RequestMethod.GET)
	@ResponseBody
	public City getCityByNameAndState2(@PathVariable String vars) {
		City city = null;
		if (vars != null) {
			String[] var = vars.split(":");
			if (var.length == 2) {
				QCity qCity = QCity.city;
				city = queryFactory.select(qCity).from(qCity).where(qCity.name.eq(var[0]).and(qCity.state.eq(var[1])))
						.fetchOne();
			}
		}
		return city;

	}

	@RequestMapping(value = "/dsl/join", method = RequestMethod.GET)
	@ResponseBody
	public String join() {
		QCity qCity = QCity.city;
		QCity cityName = new QCity("name");
		List<City> citys = queryFactory.selectFrom(qCity).innerJoin(qCity).on(qCity.name.eq(cityName.name)).fetch();
		return citys.toString();
	}

	// 排序
	@RequestMapping(value = "/dsl/sort", method = RequestMethod.GET)
	@ResponseBody
	public List<City> findCityByOrder() {
		QCity qCity = QCity.city;

		return queryFactory.selectFrom(qCity).orderBy(qCity.sid.desc()).fetch();
	}

	// Group By使用
	@RequestMapping(value = "/dsl/group", method = RequestMethod.GET)
	@ResponseBody
	public List<String> findCityByGroup() {
		QCity qcity = QCity.city;
		return queryFactory.select(qcity.name).from(qcity).groupBy(qcity.name).fetch();
	}

	// 删除用户
	@RequestMapping(value = "/dsl/delEntity/{name}", method = RequestMethod.GET)
	@ResponseBody
	public long deleteCity(@PathVariable String name) {
		QCity qcity = QCity.city;
		return queryFactory.delete(qcity).where(qcity.name.eq(name)).execute();
	}

	// 更新记录
	@RequestMapping(value = "/dsl/updateEntity/{name}", method = RequestMethod.GET)
	@ResponseBody
	public long updateCity(@PathVariable String name) {
		QCity qcity = QCity.city;

		return queryFactory.update(qcity).where(qcity.name.eq(name)).set(qcity.name, qcity.name + "test").execute();
	}

	// 使用原生Query
	@RequestMapping(value = "/dsl/originalSql/{name}", method = RequestMethod.GET)
	@ResponseBody
	public City findOneUserByOriginalSql(@PathVariable final String name) {
		QCity qcity = QCity.city;

		Query query = queryFactory.selectFrom(qcity).where(qcity.name.eq(name)).createQuery();
		return (City) query.getSingleResult();
	}

	// 分页查询单表
	@RequestMapping(value = "/dsl/page/{offset}/{pageSize}")
	@ResponseBody
	public Page<City> findAllAndPager(@PathVariable final int offset, @PathVariable  final int pageSize) {
		Predicate predicate = QCity.city.sid.gt(0);
		Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "sid"));
		PageRequest pr = PageRequest.of(offset, pageSize, sort);
		return cityRepository.findAll(predicate, pr);
	}

	// 使用jpa方式
	@RequestMapping(value = "/dsl/saveCity/{vars}", method = RequestMethod.GET)
	@ResponseBody
	public String saveCity(@PathVariable String vars) {
		if (vars != null) {
			String[] var = vars.split(":");
			if (var.length == 4) {
				City city = new City(var[0], var[1], var[2], var[3]);
				cityRepository.save(city);
			}
		}
		return vars;
	}
}
