package bank.exception;

public class UserAlreadyExistException extends BankException {

    private BankErrorCode bankErrorCode;

    public UserAlreadyExistException(String login) {
        super(BankErrorCode.USER_ALREADY_EXIST, login);
    }
}
