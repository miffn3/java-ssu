package bank.exception;

public enum LoginErrorCode  {

    USER_WRONG_LOGIN("Wrong login %s"),
    SESSION_ACCESS_COOKIE("User doesn't have permission to do this"),
    SESSION_WRONG_COOKIE("Session with this cookie doesn't exist"),
    ;


    private String errorString;

    LoginErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}
