package bank.service.iface;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    String createAccount(String userId, String accCode);
    String increaseAmount(BigDecimal amount);
    String moneyTransfer(String phone, BigDecimal amount);
    List<String> showHistory(String userId);
}
