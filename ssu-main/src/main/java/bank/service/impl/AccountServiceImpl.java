package bank.service.impl;

import bank.entity.Account;
import bank.entity.User;
import bank.repository.iface.AccountRepository;
import bank.service.iface.AccountService;
import bank.service.iface.OperationService;
import bank.service.iface.UserService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static bank.utils.Exchange.getRate;

public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;
    private UserService userService;
    private OperationService operationService;
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public AccountServiceImpl(AccountRepository accountRepository, UserService userService, OperationService operationService) {
        this.accountRepository = accountRepository;
        this.userService = userService;
        this.operationService = operationService;
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
        if (!chosenCurrency.toUpperCase().equals(accCurrency))
            amount = exchange(amount, accCurrency, chosenCurrency);

        BigDecimal oldAmount = account.getAmount();
        BigDecimal newAmount = oldAmount.add(amount);
        Account tmp = new Account(account.getId(), account.getClientId(),  newAmount, accCurrency);
        try {
            accountRepository.updateAccount(tmp);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return newAmount;
    }

    @Override
    public String moneyTransfer(Account account, String phone, BigDecimal amount) {
        User userTo = userService.getUserByPhone(phone);
        Account accountTo;
        try {
             accountTo = accountRepository.getAccountsByClientId(userTo.getId().toString()).get(0);
        } catch (Exception ex) {
            return null;
        }

        String accToCurrency = accountTo.getAccCode();
        String accFromCurrency = account.getAccCode();
        BigDecimal oldAmountFrom = account.getAmount();
        BigDecimal newAmountFrom = account.getAmount().subtract(amount);
        BigDecimal newAmountTo;
        if (!accToCurrency.toUpperCase().equals(accFromCurrency)) {
            BigDecimal exchangedAmount = exchange(amount, accToCurrency, accFromCurrency);
            newAmountTo = accountTo.getAmount().add(exchangedAmount);
        } else {
            newAmountTo = accountTo.getAmount().add(amount);
        }

        accountTo.setAmount(newAmountTo);
        try {
            accountRepository.updateAccount(accountTo);
        } catch (Exception ex) {
            return null;
        }
        account.setAmount(newAmountFrom);
        try {
            accountRepository.updateAccount(account);
        } catch (Exception ex) {
            return null;
        }
        operationService.addOperation(dtf.format(LocalDateTime.now()), accFromCurrency, account.getId().toString(),
                accountTo.getId().toString(), amount, oldAmountFrom, newAmountFrom);
        return amount + " " + accFromCurrency;
    }

    @Override
    public BigDecimal moneyTransfer(Account accountFrom, Account accountTo, BigDecimal amount) {
        String accToCurrency = accountTo.getAccCode();
        String accFromCurrency = accountFrom.getAccCode();
        BigDecimal oldAmountFrom = accountFrom.getAmount();
        BigDecimal newAmountFrom = accountFrom.getAmount().subtract(amount);
        BigDecimal newAmountTo;
        if (!accToCurrency.toUpperCase().equals(accFromCurrency)) {
            BigDecimal exchangedAmount = exchange(amount, accToCurrency, accFromCurrency);
            newAmountTo = accountTo.getAmount().add(exchangedAmount);
        } else {
            newAmountTo = accountTo.getAmount().add(amount);
        }

        accountTo.setAmount(newAmountTo);
        try {
            accountRepository.updateAccount(accountTo);
        } catch (Exception ex) {
            return null;
        }
        accountFrom.setAmount(newAmountFrom);
        try {
            accountRepository.updateAccount(accountFrom);
        } catch (Exception ex) {
            return null;
        }
        operationService.addOperation(dtf.format(LocalDateTime.now()), accFromCurrency, accountFrom.getId().toString(),
                accountTo.getId().toString(), amount, oldAmountFrom, newAmountFrom);
        return newAmountFrom;
    }

    @Override
    public List<Account> listOfUserAccounts(String login) {
        String clientId = userService.getUserIdByLogin(login);
        List<Account> accounts;
        try
        {
            accounts = accountRepository.getAccountsByClientId(clientId);
        } catch (Exception ex) {
            return null;
        }
        return accounts;
    }

    private BigDecimal exchange(BigDecimal amount, String accCurrency, String chosenCurrency) {
        amount = amount.multiply(BigDecimal.valueOf(getRate(chosenCurrency, accCurrency)));
        double res = amount.doubleValue();
        return BigDecimal.valueOf(res);
    }
}
