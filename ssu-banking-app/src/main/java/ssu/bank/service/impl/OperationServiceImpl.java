package ssu.bank.service.impl;

import ssu.bank.entity.Operation;
import ssu.bank.repo.iface.OperationRepository;
import ssu.bank.service.iface.OperationService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OperationServiceImpl implements OperationService {
	private OperationRepository operationRepository;

	public OperationServiceImpl(OperationRepository operationRepository) {
		this.operationRepository = operationRepository;
	}

	@Override
	public void addOperation(String date, String currency, Long accountFrom, Long accountTo, BigDecimal amount,
	                         BigDecimal moneyBefore, BigDecimal moneyAfter) {
		Operation operation = new Operation(date, currency, accountFrom, accountTo, amount,
				moneyBefore, moneyAfter);
			operationRepository.save(operation);
	}

	@Override
	public List<String> getHistory(Long accountId) {
		List<Operation> operations;
		operations = operationRepository.findByAccountFrom(accountId);

		System.out.println(operations.size());
		List<String> result = new ArrayList<>();

		for (Operation operation : operations) {
			result.add(operation.toString());
		}
		return result;
	}
}
