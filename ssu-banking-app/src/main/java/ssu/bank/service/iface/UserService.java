package ssu.bank.service.iface;

import org.springframework.stereotype.Service;
import ssu.bank.entity.User;

@Service
public interface UserService {
	void createUser(String username, String password, String phone, String address);
	Long getUserIdByUsername(String username);
	User getUserByPhone(String phone);
	User getUserByUsername(String username);
	boolean existsUserByUsername(String username);
	boolean existsUserByPhone(String phone);
}
