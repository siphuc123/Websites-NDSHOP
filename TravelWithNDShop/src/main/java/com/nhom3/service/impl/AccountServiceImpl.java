package com.nhom3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom3.dao.AccountDAO;
import com.nhom3.entity.Account;
import com.nhom3.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountDAO adao;

	@Override
	public Account findById(String username) {
		return adao.findById(username).get();
	}

	@Override
	public List<Account> getAdministrators() {
		return adao.getAdministrators();
	}

	@Override
	public List<Account> findAll() {
		return adao.findAll();
	}

	@Override
	public Account create(Account acc) {
		return adao.save(acc);
	}

	@Override
	public Account update(Account a) {
		return adao.save(a);
	}

	@Override
	public void delete(String acc) {
		adao.deleteById(acc);
	}

}
