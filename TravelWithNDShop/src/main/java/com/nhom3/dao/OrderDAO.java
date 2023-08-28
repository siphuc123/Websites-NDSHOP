package com.nhom3.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nhom3.entity.Order;

public interface OrderDAO extends JpaRepository<Order, Long> {

	@Query("select o from Order o where o.account.username = ?1")
	List<Order> findByUsername(String username);

	// truy van theo loai
	@Query("SELECT o FROM Order o WHERE o.status LIKE ?1")
	List<Order> findByStatus(String id);
}
