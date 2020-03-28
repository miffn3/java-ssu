package bank.exception;

public enum BankErrorCode {

    USER_WRONG_LOGIN("Wrong login %s"),
    USER_ALREADY_EXIST("Incorrect count of product or client doesn't have enough money")
    ;


    private String errorString;

    BankErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}
