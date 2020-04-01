package ssu.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ssu.bank.repo.iface.AccountRepository;
import ssu.bank.repo.iface.OperationRepository;
import ssu.bank.repo.iface.UserRepository;
import ssu.bank.service.iface.AccountService;
import ssu.bank.service.iface.OperationService;
import ssu.bank.service.iface.UserService;
import ssu.bank.service.impl.AccountServiceImpl;
import ssu.bank.service.impl.OperationServiceImpl;
import ssu.bank.service.impl.UserServiceImpl;

@Configuration
public class ImplConfig {
	@Bean
	public UserService userService(UserRepository userRepository) {
		return new UserServiceImpl(userRepository);
	}

	@Bean
	public AccountService accountService(AccountRepository accountRepository) {
		return new AccountServiceImpl(accountRepository);
	}

	@Bean
	public OperationService operationService(OperationRepository operationRepository) {
		return new OperationServiceImpl(operationRepository);
	}
}
