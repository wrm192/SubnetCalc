package main.java.exceptions;

public class InvalidIpAddressException extends RuntimeException {
    public InvalidIpAddressException(String ipAddress) {
        super("Invalid Ipaddress: " + ipAddress);
        String err = "Invalid Ipaddress: " + ipAddress;
        System.err.println(err);
    }
}
