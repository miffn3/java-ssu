package ssu.bank.repo.iface;

import org.springframework.data.repository.CrudRepository;
import ssu.bank.entity.Account;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {
	List<Account> findByClientId(Long clientId);
}
