package bank.service.impl;

import bank.entity.User;
import bank.repository.iface.UserRepository;
import bank.service.iface.UserService;

import java.util.UUID;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String createUser(String login, String password, String phone, String address) {
        User user = new User(UUID.randomUUID(), login, password, phone, address);
        try {
            userRepository.insertUser(user);
        } catch (Exception ex) {
            return null;
        }
        return login;
    }

    @Override
    public String authorization(String login, String password) {
        User user;
        try {
            user = userRepository.getUserByLogin(login);
        } catch (Exception ex) {
            return null;
        }
        if (user != null)
            if(user.getPassword().equals(password))
                return login;
        return null;
    }

    @Override
    public String getUserIdByLogin(String login) {
        User user;
        try {
            user = userRepository.getUserByLogin(login);
        } catch (Exception ex) {
            return null;
        }
        if (user != null)
            return user.getId().toString();
        return null;
    }

    @Override
    public User getUserByPhone(String phone) {
        User user;
        try {
            user = userRepository.getUserByPhone(phone);
        } catch (Exception ex) {
            return null;
        }
        return user;
    }

    @Override
    public boolean existUserByLogin(String login) {
        User user = null;
        try {
           user = userRepository.getUserByLogin(login);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (user == null) {
            return false;
        } else {
            return true;
        }
    }
}
