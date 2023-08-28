package com.nhom3.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.nhom3.entity.*;

public interface CartService {
	Cart create(JsonNode data);

	Cart findById(Long id);

	List<Cart> findByUsername(String username);

	List<Cart> findByIdAndUsername(String id, String username);

	Cart save(Cart cart);

	List<Cart> findAll();

	void delete(Long id);

	void deleteAll();
}
