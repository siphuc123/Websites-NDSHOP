package com.nhom3.service;


import java.util.List;

import com.nhom3.entity.Authority;

public interface AthorityService {

	public List<Authority> findAuthoritiesOfAdministrators();

	public List<Authority> findAll();
	
	public void delete(Integer id);

	public Authority create(Authority auth);

}
