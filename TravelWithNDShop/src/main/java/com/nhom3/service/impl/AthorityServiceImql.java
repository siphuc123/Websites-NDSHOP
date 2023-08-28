package com.nhom3.service.impl;

import java.util.List;

import com.nhom3.dao.*;
import com.nhom3.entity.*;
import com.nhom3.service.AthorityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AthorityServiceImql implements AthorityService {
	@Autowired
	AuthorityDAO adao;
	@Autowired
	AccountDAO acdao;

	@Override
	public List<Authority> findAuthoritiesOfAdministrators() {
		List<Account> accounts = acdao.getAdministrators();
		return adao.authritiesOf(accounts);
	}

	@Override
	public List<Authority> findAll() {
		return adao.findAll();
	}

	@Override
	public void delete(Integer id) {
		adao.deleteById(id);
	}

	@Override
	public Authority create(Authority auth) {
		return adao.save(auth);
	}

}
