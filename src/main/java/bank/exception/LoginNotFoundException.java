package bank.exception;

public class LoginException extends BankException {
    private BankErrorCode bankErrorCode;

    public LoginException(BankErrorCode bankErrorCode, BankErrorCode bankErrorCode1) {
        super(bankErrorCode);
        this.bankErrorCode = bankErrorCode1;
    }

    public LoginException(BankErrorCode bankErrorCode, String param, BankErrorCode bankErrorCode1) {
        super(bankErrorCode, param);
        this.bankErrorCode = bankErrorCode1;
    }
}
