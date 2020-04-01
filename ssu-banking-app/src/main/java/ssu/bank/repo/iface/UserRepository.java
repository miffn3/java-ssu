package ssu.bank.repo.iface;

import org.springframework.data.repository.CrudRepository;
import ssu.bank.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByLogin(String login);
}
