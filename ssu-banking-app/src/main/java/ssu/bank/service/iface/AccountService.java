package ssu.bank.service.iface;

import org.springframework.stereotype.Service;
import ssu.bank.entity.Account;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface AccountService {
	void createAccount(String login, String accCode);
	BigDecimal increaseAmount(Long accountId, BigDecimal amount, String chosenCurrency);
	BigDecimal moneyTransfer(Long accountId, String phone, BigDecimal amount);
	BigDecimal moneyTransfer(Long accountFromId, Long accountToId, BigDecimal amount);
	List<Account> listOfUserAccounts(String username);
}
