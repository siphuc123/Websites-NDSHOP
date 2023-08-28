package com.nhom3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom3.dao.RoleDAO;
import com.nhom3.entity.Role;
import com.nhom3.service.RoleService;


@Service
public class RoleServiceImql implements RoleService {
	@Autowired
	RoleDAO rdao;
	@Override
	public List<Role> findAll() {
		return rdao.findAll();
	}

}
