package com.nhom3.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhom3.entity.Category;

public interface CategoryDAO extends JpaRepository<Category, String> {

}
