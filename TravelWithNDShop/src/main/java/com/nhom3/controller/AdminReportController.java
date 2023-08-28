package com.nhom3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nhom3.dao.*;
import com.nhom3.entity.*;



@Controller
public class AdminReportController {
	// 1. nhớ tiêm ProductDAO vào để gọi hàm
		@Autowired
		ProductDAO dao;
		@Autowired
		OrderDAO ordDao;
		@Autowired
		OrderDetailDAO ODDAO;

		@RequestMapping("/product")
		public String product() {
			return "redirect:/admin/index.html#!/product";
		}

		@RequestMapping("/authorize")
		public String authorize() {
			return "redirect:/admin/index.html#!/authorize";
		}

		@RequestMapping("/account")
		public String account() {
			return "redirect:/admin/index.html#!/account";
		}

		@RequestMapping("/admin/inventory-by-category")
		public String inventory(Model model) {
			List<Report> items = dao.getInventoryByCategory();
			model.addAttribute("items", items);
			return "report/inventory-by-category";
		}

		@RequestMapping("/admin/inventory-by-order")
		public String inventory1(Model model) {
			List<Report> items = ODDAO.getInventoryByOrder();
			model.addAttribute("items", items);
			return "report/inventory-by-order";
		}

		@RequestMapping("/admin/inventory-by-order-month")
		public String inventory2(Model model) { 
			List<Report> items = ODDAO.getInventoryByOrder1();
			// List<Report> items = ODDAO.getInventoryByProduct();
			model.addAttribute("items", items);
			return "report/inventory-by-order-month";
		}

		@RequestMapping("/admin/report/submit-order")
		public String inventory3(Model model) {
			List<Order> items = ordDao.findByStatus("Đang xử lý");
			model.addAttribute("items", items);
			return "report/orders-manage";
		}

		// đơn hàng trạng thái
		@RequestMapping("/admin/report/ok/edit/{id}")
		public String accept(Model model, @PathVariable("id") Long id) {
			Order item = ordDao.findById(id).get();
			item.setStatus("Xác nhận");
			ordDao.save(item);
			/*
			 * List<Order> items = ordDao.findAll(); model.addAttribute("items", items);
			 */
			return "redirect:/admin/report/submit-order";
		}

		@RequestMapping("/admin/report/not/edit/{id}")
		public String notOK(Model model, @PathVariable("id") Long id) {
			Order item = ordDao.findById(id).get();
			item.setStatus("Đã hủy");
			ordDao.save(item);
			return "redirect:/admin/report/submit-order";
		}
}
