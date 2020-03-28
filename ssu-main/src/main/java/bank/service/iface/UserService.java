package bank.service.iface;

import bank.exception.LoginNotFoundException;
import bank.exception.UserAlreadyExistException;

public interface UserService {
    String createUser(String login, String password) throws UserAlreadyExistException;
    String authorization(String login, String password) throws LoginNotFoundException;
}
