package ssu.bank.repo.iface;

import org.springframework.data.repository.CrudRepository;
import ssu.bank.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
	User findByPhone(String phone);
	boolean existsUserByUsername(String username);
	boolean existsUserByPhone(String phone);
}
