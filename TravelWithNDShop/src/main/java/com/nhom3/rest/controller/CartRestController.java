package com.nhom3.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.nhom3.entity.*;
import com.nhom3.service.CartService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/carts")
public class CartRestController {
	@Autowired
	CartService cartService;

	@PostMapping()
	public Cart create(@RequestBody JsonNode data) {
		return cartService.create(data);
	}

	@GetMapping()
	public List<Cart> getAll() {
		return cartService.findAll();
	}

	@GetMapping("{username}")
	public List<Cart> get(@PathVariable("username") String username) {
		return cartService.findByUsername(username);
	}

//	@GetMapping("{id}")
//	public Cart getOne(@PathVariable("id") Long id) {
//		return cartService.findById(id);
//	}

	@PutMapping("{id}")
	public Cart put(@PathVariable("id") Long id, @RequestBody Cart cart) {
		return cartService.save(cart);
	}


	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Long id) {
		cartService.delete(id);
	}
	@DeleteMapping()
	public void deleteAll() {
		cartService.deleteAll();
	}
}
