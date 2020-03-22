package bank.service.impl;

import bank.service.iface.AccountService;

import java.math.BigDecimal;
import java.util.List;

public class AccountServiceImpl implements AccountService {
    @Override
    public String createAccount(String userId, String accCode) {
        return null;
    }

    @Override
    public String increaseAmount(BigDecimal amount) {
        return null;
    }

    @Override
    public String moneyTransfer(String phone, BigDecimal amount) {
        return null;
    }

    @Override
    public List<String> showHistory(String userId) {
        return null;
    }
}
