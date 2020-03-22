package bank.exception;

public class LoginNotFoundException extends BankException {
    private BankErrorCode bankErrorCode;

    public LoginNotFoundException(String login) {
        super(BankErrorCode.USER_WRONG_LOGIN, login);
    }
}
