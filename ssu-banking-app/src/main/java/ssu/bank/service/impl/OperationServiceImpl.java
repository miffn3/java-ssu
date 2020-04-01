package ssu.bank.service.impl;

import ssu.bank.repo.iface.OperationRepository;
import ssu.bank.service.iface.OperationService;

public class OperationServiceImpl implements OperationService {
	private final OperationRepository operationRepository;

	public OperationServiceImpl(OperationRepository operationRepository) {
		this.operationRepository = operationRepository;
	}
}
