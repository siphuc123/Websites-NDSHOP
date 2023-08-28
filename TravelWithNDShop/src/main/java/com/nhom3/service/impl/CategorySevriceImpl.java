package com.nhom3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom3.dao.CategoryDAO;
import com.nhom3.entity.Category;
import com.nhom3.service.CategoryService;

@Service
public class CategorySevriceImpl implements CategoryService {
	@Autowired
	CategoryDAO cdao;

	@Override
	public List<Category> findAll() {
		
		return cdao.findAll();
	}
}
