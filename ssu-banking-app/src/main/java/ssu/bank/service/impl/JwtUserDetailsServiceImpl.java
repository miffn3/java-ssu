package ssu.bank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ssu.bank.entity.User;
import ssu.bank.repo.iface.AccountRepository;
import ssu.bank.repo.iface.UserRepository;
import ssu.bank.service.iface.JwtUserDetailsService;

import java.util.ArrayList;

@Component
public class JwtUserDetailsServiceImpl implements JwtUserDetailsService
{
	private UserRepository userRepository;

	@Autowired
	public JwtUserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException
	{
		User user = userRepository.findByLogin(login);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with login: " + login);
		}
		return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
				new ArrayList<>());
	}
}