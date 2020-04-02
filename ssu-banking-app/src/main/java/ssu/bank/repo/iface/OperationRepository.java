package ssu.bank.repo.iface;

import org.springframework.data.repository.CrudRepository;
import ssu.bank.entity.Operation;

import java.util.List;

public interface OperationRepository extends CrudRepository<Operation, Long> {
	List<Operation> findByAccountFrom(Long accountFrom);
}
