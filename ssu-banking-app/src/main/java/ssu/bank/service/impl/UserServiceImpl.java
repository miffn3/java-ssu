package ssu.bank.service.impl;

import ssu.bank.entity.User;
import ssu.bank.repo.iface.UserRepository;
import ssu.bank.service.iface.UserService;

public class UserServiceImpl implements UserService {
	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void createUser(String username, String password, String phone, String address) {
		User user = new User(username, password, phone, address);
		userRepository.save(user);
	}

	@Override
	public Long getUserIdByUsername(String username) {
		User user = userRepository.findByUsername(username);
		return user.getId();
	}

	@Override
	public User getUserByPhone(String phone) {
		return userRepository.findByPhone(phone);
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public boolean existsUserByPhone(String phone) {
		return userRepository.existsUserByPhone(phone);
	}

	@Override
	public boolean existsUserByUsername(String username) {
		return userRepository.existsUserByUsername(username);
	}
}
