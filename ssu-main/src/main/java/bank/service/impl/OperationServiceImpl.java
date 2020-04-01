package bank.service.impl;

import bank.entity.Operation;
import bank.repository.iface.OperationRepository;
import bank.service.iface.OperationService;

import java.util.List;

public class OperationServiceImpl implements OperationService {
	private OperationRepository operationRepository;

	public OperationServiceImpl(OperationRepository operationRepository) {
		this.operationRepository = operationRepository;
	}

	@Override
	public String addOperation(Operation operation)
	{
		return null;
	}

	@Override
	public List<String> getHistory(String login)
	{
		return null;
	}
}
