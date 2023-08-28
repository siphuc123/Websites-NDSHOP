package com.nhom3.service;

import java.util.List;

import com.nhom3.entity.Account;

public interface AccountService {
	
	public Account findById(String username);

	public List<Account> getAdministrators();

	public List<Account> findAll();
	
	Account create(Account acc);

	Account update(Account acc);

	void delete(String username);
}
