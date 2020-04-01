package ssu.bank.service.impl;

import ssu.bank.repo.iface.UserRepository;
import ssu.bank.service.iface.UserService;

public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
}
