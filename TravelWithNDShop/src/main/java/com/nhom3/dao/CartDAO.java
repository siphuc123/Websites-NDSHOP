package com.nhom3.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nhom3.entity.*;

public interface CartDAO extends JpaRepository<Cart, Long> {
	
	@Query("SELECT o FROM Cart o WHERE o.username LIKE ?1")
	List<Cart> findByUsername(String username);
	
	@Query("SELECT o FROM Cart o WHERE o.product.id LIKE ?1 AND o.username LIKE ?2")
	List<Cart> findByIdAndUsername(String id, String username);
}
