package com.fsd.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.pojo.Product;
import com.fsd.repository.ProductRepository;

@RestController
public class ProductController {
	@Autowired
	ProductRepository productRepository;

	@RequestMapping(value = "/product/getProduct/{description}", method = RequestMethod.GET)
	@ResponseBody
	public String getProductByName(@PathVariable String description) {
		Pageable pageable = PageRequest.of(0, 2, Direction.ASC, "description");
		
		Page<Product> entity = productRepository.findByDescriptionContaining(description, pageable);

		return entity.getContent().toString();
	}
	
//	@RequestMapping(value = "/product/product/{emailAddress}", method = RequestMethod.GET)
	@RequestMapping(value = "/product/saveEntity/{entity}", method = RequestMethod.GET)
	@ResponseBody
	public String saveEntity(@PathVariable String entity) {
		if(entity != null) {
			String[] entitys = entity.split(":");
			
			if(entitys.length == 3) {
				Product myEntity = new Product(entitys[0],new BigDecimal(entitys[1]),entitys[2]);
				myEntity.setAttributes(entitys[0], entitys[2]);
				productRepository.save(myEntity);
			}
		}
		
		return entity;
	}
	
	
	@RequestMapping(value = "/product/getProductByAttribute/{attribute}", method = RequestMethod.GET)
	@ResponseBody
	public String getProductByAttribute(@PathVariable String attribute) {
		Pageable pageable = PageRequest.of(0, 2, Direction.ASC, "description");
		
		Page<Product> entity = productRepository.findByAttributesContaining(attribute, pageable);

		return entity.getContent().toString();
	}
}
