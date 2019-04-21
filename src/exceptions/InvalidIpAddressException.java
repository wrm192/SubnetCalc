package src.exceptions;

public class InvalidIpAddressException extends RuntimeException{
    public InvalidIpAddressException(String err) {
        super(err);
        System.err.println(err);
    }
}
