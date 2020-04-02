package ssu.bank.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ssu.bank.dto.IncreaseDto;
import ssu.bank.dto.TransferDto;
import ssu.bank.entity.Account;
import ssu.bank.service.iface.AccountService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	private AccountService accountService;

	@Autowired
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@PostMapping("/create/{currency}")
	public Boolean create(@PathVariable("currency") String currency) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getPrincipal();
		String username = userDetails.getUsername();
		if (!currency.equals("RUB")  && !currency.equals("EUR") && !currency.equals("USD")){
			return null;
		}
		accountService.createAccount(username, currency);
		return true;
	}

	@PostMapping("/money/add/{id}")
	@ApiOperation("increase amount of money on account")
	public BigDecimal addMoney(@RequestBody IncreaseDto body,
	                           @PathVariable("id") String id) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getPrincipal();
		String username = userDetails.getUsername();
		String currency = body.getCurrency();
		if (!currency.equals("RUB")  && !currency.equals("EUR") && !currency.equals("USD")){
			return null;
		}
		return accountService.increaseAmount(Long.valueOf(id), body.getAmount(), body.getCurrency());
	}

	@PostMapping("/transfer/by/phone/{phone}")
	@ApiOperation("transfer to user by phone")
	public BigDecimal transferToUser(@RequestBody TransferDto body,
	                                 @PathVariable("phone") String phone) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getPrincipal();
		String username = userDetails.getUsername();
		return accountService.moneyTransfer(body.getAccountFrom(), phone,  body.getAmount());
	}

	@PostMapping("/transfer/by/account_id/{id}")
	@ApiOperation("transfer to account by account_id")
	public BigDecimal transferToAccount(@RequestBody TransferDto body,
	                                 @PathVariable("id") String accountId) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getPrincipal();
		String username = userDetails.getUsername();
		return accountService.moneyTransfer(body.getAccountFrom(), Long.valueOf(accountId),  body.getAmount());
	}

	@GetMapping("/info/all")
	@ApiOperation("get all user's accounts")
	public List<Account> getAccounts() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getPrincipal();
		String username = userDetails.getUsername();
		return accountService.listOfUserAccounts(username);
	}

}

