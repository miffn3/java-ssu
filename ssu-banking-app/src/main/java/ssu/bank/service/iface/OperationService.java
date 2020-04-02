package ssu.bank.service.iface;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface OperationService {
	void addOperation(String date, String currency, Long accountFrom, Long accountTo, BigDecimal amount,
	                  BigDecimal moneyBefore, BigDecimal moneyAfter);
	List<String> getHistory(Long accountId);
}