package cn.loach.client.exceptions;

public class LoachException extends RuntimeException{

    public LoachException(String message) {
        super(message);
    }

    public LoachException(Throwable cause) {
        super(cause);
    }
}
