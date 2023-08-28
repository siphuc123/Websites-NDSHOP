package com.nhom3.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nhom3.entity.OrderDetail;
import com.nhom3.entity.Report;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long> {

	// Thống kê số sản phẩm bán nhiều nhất

	@Query("SELECT new Report(o.product, sum(o.price), count(o.quantity)) FROM OrderDetail o "
		+ " GROUP BY o.product " + " ORDER BY sum(o.quantity) DESC")
	List<Report> getInventoryByOrder();

	// Thống kê đơn hàng theo tháng

	@Query("SELECT new Report(o.order, sum(o.price), count(o.quantity)) FROM OrderDetail o "
		+ " GROUP BY o.order " + " ORDER BY sum(o.quantity) DESC")
	List<Report> getInventoryByOrder1();
//	@Query("SELECT new Report(o.product.category, sum(o.quantity), sum(o.price*o.quantity)) " + " FROM OrderDetail o "
//		+ " GROUP BY o.product.category" + " ORDER BY sum(o.price*o.quantity) DESC")
//	List<Report> getInventoryByCategory();
//
//	@Query("SELECT new Report(o.product, sum(o.quantity), sum(o.price*o.quantity)) " + " FROM OrderDetail o "
//		+ " GROUP BY o.product" + " ORDER BY sum(o.price*o.quantity) DESC")
//	List<Report> getInventoryByProduct();
//
//	@Query("SELECT new Report(o.order.account, count(o.order.id), sum(o.price*o.quantity)) " + " FROM OrderDetail o "
//		+ " GROUP BY o.order.account" + " ORDER BY sum(o.price*o.quantity) DESC")
//	List<Report> getInventoryByUser();
}
