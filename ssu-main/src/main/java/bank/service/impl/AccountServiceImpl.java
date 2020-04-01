package bank.service.impl;

import bank.entity.Account;
import bank.repository.iface.AccountRepository;
import bank.service.iface.AccountService;
import bank.service.iface.UserService;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static bank.utils.Exchange.getRate;

public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;
    private UserService userService;

    public AccountServiceImpl(AccountRepository accountRepository, UserService userService) {
        this.accountRepository = accountRepository;
        this.userService = userService;
    }

    @Override
    public String createAccount(String login, String accCode) {
        String clientId = userService.getUserIdByLogin(login);
        UUID id = UUID.randomUUID();
        Account account = new Account(id, clientId, accCode);
        try {
            accountRepository.insertAccount(account);
        } catch (Exception ex) {
            return null;
        }
        return id.toString();
    }

    @Override
    public BigDecimal increaseAmount(Account account, BigDecimal amount, String chosenCurrency) {
        String accCurrency = account.getAccCode();
        if (!chosenCurrency.toUpperCase().equals(accCurrency)) {
            BigDecimal tmp = amount;
            tmp = tmp.multiply(BigDecimal.valueOf(getRate(chosenCurrency, accCurrency)));
            double res = tmp.doubleValue();
            amount = BigDecimal.valueOf(res);
        }
        BigDecimal newAmount = amount.add(account.getAmount());
        account.setAmount(newAmount);
        try {
            accountRepository.updateAccount(account);
        } catch (Exception ex) {
            return null;
        }
        return newAmount;
    }

    @Override
    public String moneyTransfer(Account account, String phone, BigDecimal amount) {
        return null;
    }

    @Override
    public List<Account> listOfUserAccounts(String login) {
        String clientId = userService.getUserIdByLogin(login);
        List<Account> accounts = null;
        try
        {
            accounts = accountRepository.getAccountsByClientId(clientId);
        } catch (Exception ex) {
            return null;
        }
        return accounts;
    }
}
