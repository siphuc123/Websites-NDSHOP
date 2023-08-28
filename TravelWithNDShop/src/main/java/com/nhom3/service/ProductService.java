package com.nhom3.service;

import java.util.List;

import com.nhom3.entity.Product;

public interface ProductService {

	List<Product> findAll();

	Product findById(Integer id);

	List<Product> findByCategoryId(String cid);

	Product create(Product product);

	Product update(Product product);

	void delete(Integer id);

	List<Product> findByOutstanding();
	
	List<Product> findByAvailable();
	
	List<Product> findAllByCategory(String string);
	
	List<Product> findByKey(String key);

}
