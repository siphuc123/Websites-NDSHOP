package com.nhom3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhom3.dao.CartDAO;
import com.nhom3.entity.*;
import com.nhom3.service.CartService;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	CartDAO cdao;
	

	@Override
	public Cart create(JsonNode data) {
		ObjectMapper mapper = new ObjectMapper();
		Cart item = mapper.convertValue(data,  Cart.class);
		cdao.save(item);
		return item;
	}

	@Override
	public Cart findById(Long id) {
		return cdao.findById(id).get();
	}

	@Override
	public List<Cart> findByUsername(String username) {
		return cdao.findByUsername(username);
	}

	@Override
	public List<Cart> findByIdAndUsername(String id, String username) {
		return cdao.findByIdAndUsername(id, username);
	}

	@Override
	public Cart save(Cart cart) {
		return cdao.save(cart);
	}

	@Override
	public List<Cart> findAll() {
		return cdao.findAll();
	}

	@Override
	public void delete(Long id) {
		cdao.deleteById(id);
		
	}

	@Override
	public void deleteAll() {
		cdao.deleteAll();		
	}

}
