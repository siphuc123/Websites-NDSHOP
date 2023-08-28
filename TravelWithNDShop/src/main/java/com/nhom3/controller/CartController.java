package com.nhom3.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nhom3.entity.Cart;
import com.nhom3.service.CartService;

@Controller
public class CartController {
	@Autowired
	CartService cartService;
	@RequestMapping("/cart/view")
	public String view() {
		return "cart/view";
	}
}
