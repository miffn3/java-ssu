package ssu.bank.repo.iface;

import org.springframework.data.repository.CrudRepository;
import ssu.bank.entity.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {
}
