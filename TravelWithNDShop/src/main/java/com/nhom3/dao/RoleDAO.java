package com.nhom3.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.nhom3.entity.Role;

public interface RoleDAO extends JpaRepository<Role, Integer> {

}
