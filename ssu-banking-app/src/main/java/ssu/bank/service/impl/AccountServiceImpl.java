package ssu.bank.service.impl;

import ssu.bank.entity.Account;
import ssu.bank.entity.User;
import ssu.bank.repo.iface.AccountRepository;
import ssu.bank.service.iface.AccountService;
import ssu.bank.service.iface.OperationService;
import ssu.bank.service.iface.UserService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static ssu.bank.utils.Exchange.getRate;

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
	public void createAccount(String username, String accCode) {
		Long clientId = userService.getUserIdByUsername(username);
		Account account = new Account(clientId, accCode);
		accountRepository.save(account);
	}

	@Override
	public BigDecimal increaseAmount(Long accountId, BigDecimal amount, String chosenCurrency) {
		Account account = accountRepository.findById(accountId).get();
		String accCurrency = account.getAccCode();
		if (!chosenCurrency.toUpperCase().equals(accCurrency))
			amount = exchange(amount, accCurrency, chosenCurrency);

		BigDecimal oldAmount = account.getAmount();
		BigDecimal newAmount = oldAmount.add(amount);
		Account tmp = new Account(account.getId(), account.getClientId(),  newAmount, accCurrency);
		accountRepository.save(tmp);
		return newAmount;
	}

	@Override
	public BigDecimal moneyTransfer(Long accountId, String phone, BigDecimal amount) {
		Account account = accountRepository.findById(accountId).get();
		User userTo = userService.getUserByPhone(phone);
		Account accountTo;
		accountTo = accountRepository.findByClientId(userTo.getId()).get(0);
		if (account.equals(accountTo))
			return null;
		String accToCurrency = accountTo.getAccCode();
		String accFromCurrency = account.getAccCode();
		BigDecimal oldAmountFrom = account.getAmount();
		if (oldAmountFrom.compareTo(amount) < 0)
			return null;
		BigDecimal newAmountFrom = account.getAmount().subtract(amount);
		BigDecimal newAmountTo;
		if (!accToCurrency.toUpperCase().equals(accFromCurrency)) {
			BigDecimal exchangedAmount = exchange(amount, accToCurrency, accFromCurrency);
			newAmountTo = accountTo.getAmount().add(exchangedAmount);
		} else {
			newAmountTo = accountTo.getAmount().add(amount);
		}

		accountTo.setAmount(newAmountTo);
		accountRepository.save(accountTo);

		account.setAmount(newAmountFrom);
		accountRepository.save(account);

		operationService.addOperation(dtf.format(LocalDateTime.now()), accFromCurrency, account.getId(),
				accountTo.getId(), amount, oldAmountFrom, newAmountFrom);
		return newAmountFrom;
	}

	@Override
	public BigDecimal moneyTransfer(Long accountFromId, Long accountToId, BigDecimal amount) {
		if(accountFromId.equals(accountToId))
			return null;
		Account accountFrom = accountRepository.findById(accountFromId).get();
		Account accountTo = accountRepository.findById(accountToId).get();
		String accToCurrency = accountTo.getAccCode();
		String accFromCurrency = accountFrom.getAccCode();
		BigDecimal oldAmountFrom = accountFrom.getAmount();
		if (oldAmountFrom.compareTo(amount) < 0)
			return null;
		BigDecimal newAmountFrom = accountFrom.getAmount().subtract(amount);
		BigDecimal newAmountTo;
		if (!accToCurrency.toUpperCase().equals(accFromCurrency)) {
			BigDecimal exchangedAmount = exchange(amount, accToCurrency, accFromCurrency);
			newAmountTo = accountTo.getAmount().add(exchangedAmount);
		} else {
			newAmountTo = accountTo.getAmount().add(amount);
		}

		accountTo.setAmount(newAmountTo);
		accountRepository.save(accountTo);
		accountFrom.setAmount(newAmountFrom);
		accountRepository.save(accountFrom);
		operationService.addOperation(dtf.format(LocalDateTime.now()), accFromCurrency, accountFrom.getId(),
				accountTo.getId(), amount, oldAmountFrom, newAmountFrom);
		return newAmountFrom;
	}

	@Override
	public List<Account> listOfUserAccounts(String login) {
		Long clientId = userService.getUserIdByUsername(login);
		List<Account> accounts;
		accounts = accountRepository.findByClientId(clientId);
		return accounts;
	}

	private BigDecimal exchange(BigDecimal amount, String accCurrency, String chosenCurrency) {
		amount = amount.multiply(BigDecimal.valueOf(getRate(chosenCurrency, accCurrency)));
		double res = amount.doubleValue();
		return BigDecimal.valueOf(res);
	}
}

