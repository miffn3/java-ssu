package ssu.bank.repo.iface;

import org.springframework.data.repository.CrudRepository;
import ssu.bank.entity.Operation;

public interface OperationRepository extends CrudRepository<Operation, Long> {
}
