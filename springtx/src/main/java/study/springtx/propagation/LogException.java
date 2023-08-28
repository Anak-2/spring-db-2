package study.springtx.propagation;

public enum LogException {
    Message("로그예외");

    private final String value;

    LogException(String s) {
        this.value = s;
    }

    public String getValue(){
        return value;
    }
}
