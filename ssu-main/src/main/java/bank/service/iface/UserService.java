package bank.service.iface;


public interface UserService {
    String createUser(String login, String password, String phone, String address);
    String authorization(String login, String password);
    String getUserIdByLogin(String login);
    boolean existUserByLogin(String login);
}
