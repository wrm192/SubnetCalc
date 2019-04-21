package src.exceptions;

public class InvalidRequestException extends RuntimeException{
    public InvalidRequestException(String s) {
        super(s);
        System.out.println(s);
    }

}
