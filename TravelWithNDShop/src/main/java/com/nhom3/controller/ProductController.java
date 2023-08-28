package com.nhom3.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nhom3.entity.*;
import com.nhom3.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;

	@RequestMapping("/product/list")
	public String list(Model model) {
		List<Product> list = productService.findByOutstanding();
		model.addAttribute("items", list);
		return "product/list";
	}

	@RequestMapping("/non-bao-hiem")
	public String nbh(Model model) {
		List<Product> list = productService.findAllByCategory("NO");
		model.addAttribute("items", list);
		return "product/list";
	}

	@RequestMapping("/balo")
	public String balo(Model model) {
		List<Product> list = productService.findAllByCategory("BA");
		model.addAttribute("items", list);
		return "product/list";
	}

	@RequestMapping("/quan-ao")
	public String qa(Model model) {
		List<Product> list = productService.findAllByCategory("QA");
		model.addAttribute("items", list);
		return "product/list";
	}

	@RequestMapping("/gang-tay")
	public String gt(Model model) {
		List<Product> list = productService.findAllByCategory("GA");
		model.addAttribute("items", list);
		return "product/list";
	}

	@RequestMapping("/giay")
	public String giay(Model model) {
		List<Product> list = productService.findAllByCategory("GI");
		model.addAttribute("items", list);
		return "product/list";
	}

	@RequestMapping("/phu-kien-khac")
	public String khac(Model model) {
		List<Product> list = productService.findAllByCategory("KH");
		model.addAttribute("items", list);
		return "product/list";
	}

	@RequestMapping("/product/search")
	public String search(Model model, @RequestParam("key") Optional<String> key) {
		List<Product> list = productService.findByKey(key.orElse(""));
		model.addAttribute("items", list);
		return "product/list";
	}

	@RequestMapping("/product/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Product item = productService.findById(id);
		model.addAttribute("item", item);
		return "product/detail";
	}
}
