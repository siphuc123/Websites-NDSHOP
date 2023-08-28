package com.nhom3.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nhom3.dao.ProductDAO;
import com.nhom3.entity.Product;
import com.nhom3.service.ProductService;

@Service
public class ProductSevriceImpl implements ProductService {
	@Autowired
	ProductDAO pdao;

	@Override
	public List<Product> findAll() {
		return pdao.findAll();
	}

	@Override
	public Product findById(Integer id) {
		return pdao.findById(id).get();
	}

	@Override
	public List<Product> findByCategoryId(String cid) {
		return pdao.findByCategoryId(cid);
	}

	@Override
	public Product create(Product product) {
		return pdao.save(product);
	}

	@Override
	public Product update(Product product) {
		return pdao.save(product);
	}

	@Override
	public void delete(Integer id) {
		pdao.deleteById(id);;
	}

	@Override
	public List<Product> findByOutstanding() {
		return pdao.findByOutstanding();
	}

	@Override
	public List<Product> findByAvailable() {
		return pdao.findByAvailable();
	}

	@Override
	public List<Product> findAllByCategory(String string) {
		return pdao.findAllByCategory(string);
	}

	@Override
	public List<Product> findByKey(String key) {
		return pdao.findByKey(key);
	}

}
