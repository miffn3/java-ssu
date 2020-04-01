package bank.service.iface;


import bank.entity.User;

public interface UserService {
    String createUser(String login, String password, String phone, String address);
    String authorization(String login, String password);
    String getUserIdByLogin(String login);
    User getUserByPhone(String phone);
    boolean existUserByLogin(String login);
}
