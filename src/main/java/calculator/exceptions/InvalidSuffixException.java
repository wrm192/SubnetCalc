package calculator.exceptions;

public class InvalidSuffixException extends RuntimeException {

    public InvalidSuffixException(String s) {
        super(s);
        System.out.println(s);
    }
}
