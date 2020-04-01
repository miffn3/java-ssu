package bank.service.iface;

import java.math.BigDecimal;
import java.util.List;

public interface OperationService {
	void addOperation(String date, String currency, String accountFrom, String accountTo, BigDecimal amount,
	                    BigDecimal moneyBefore, BigDecimal moneyAfter);
	List<String> getHistory(String accountId);
}
