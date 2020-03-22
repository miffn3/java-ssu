package bank.service.impl;

import bank.exception.LoginNotFoundException;
import bank.exception.UserAlreadyExistException;
import bank.service.iface.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public String createUser(String login, String password) throws UserAlreadyExistException {
        return null;
    }

    @Override
    public String authorization(String login, String password) throws LoginNotFoundException {
        return null;
    }
}
