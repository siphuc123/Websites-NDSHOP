package com.nhom3.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nhom3.entity.Favorite;

public interface FavoriteDAO extends JpaRepository<Favorite, Integer> {

	@Query("SELECT o FROM Favorite o WHERE o.account.username = ?1")
	List<Favorite> findByUser(String username);

	@Query("SELECT o FROM Favorite o WHERE o.product.id = ?1 AND o.account.username = ?2")
	Favorite findByProduct(int id, String username);
}
