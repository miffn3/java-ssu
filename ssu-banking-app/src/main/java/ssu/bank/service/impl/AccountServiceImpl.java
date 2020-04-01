package ssu.bank.service.impl;

import ssu.bank.repo.iface.AccountRepository;
import ssu.bank.service.iface.AccountService;

public class AccountServiceImpl implements AccountService {
	private final AccountRepository accountRepository;

	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
}
