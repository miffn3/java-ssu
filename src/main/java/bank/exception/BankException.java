package bank.exception;

public class BankException extends RuntimeException {
    BankErrorCode bankErrorCode;

    public BankException(BankErrorCode bankErrorCode) {
        super(bankErrorCode.getErrorString());
        this.bankErrorCode = bankErrorCode;
    }

    public BankException(BankErrorCode bankErrorCode, String param) {
        super(String.format(bankErrorCode.getErrorString(), param));
        this.bankErrorCode = bankErrorCode;
    }

    public BankErrorCode getBankErrorCode() {
        return bankErrorCode;
    }
}

