package bank.service.iface;

import bank.entity.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    String createAccount(String login, String accCode);
    BigDecimal increaseAmount(Account account, BigDecimal amount, String chosenCurrency);
    String moneyTransfer(Account account, String phone, BigDecimal amount);
    BigDecimal moneyTransfer(Account accountFrom, Account accountTo, BigDecimal amount);
    List<Account> listOfUserAccounts(String login);
}
