package ssu.bank.service.iface;

import org.springframework.stereotype.Service;
import ssu.bank.entity.Account;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface AccountService {
	void createAccount(String login, String accCode);
	BigDecimal increaseAmount(Account account, BigDecimal amount, String chosenCurrency);
	boolean moneyTransfer(Account account, String phone, BigDecimal amount);
	BigDecimal moneyTransfer(Account accountFrom, Account accountTo, BigDecimal amount);
	List<Account> listOfUserAccounts(String username);
}
