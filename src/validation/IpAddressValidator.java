package src.validation;

import src.exceptions.InvalidIpAddressException;
import src.exceptions.InvalidRequestException;
import src.exceptions.InvalidSuffixException;

import java.util.Arrays;

public class IpAddressValidator {
    public IpAddressValidator() {
    }

    public static void validateSuffix(String ipAddress, int suffix) {
        if(suffix > 31 || suffix < 8) {
            throw new InvalidSuffixException("Suffix must be between 8-31");
        }

        //TODO found out the rest of logic for suffix, totally forget this shit
    }

    public static void validateIP(String ipAddress) {
        if(ipAddress == null) {
            throw new InvalidRequestException("Ip address not set");
        }

        String[] split = ipAddress.split("\\.");

        if(Integer.parseInt(split[0]) == 127 || Integer.parseInt(split[0]) > 223) {
            throw new InvalidIpAddressException("Value 127 is reserved and values greater than 223(Class D) are ignored");
        }

        Arrays.stream(split).forEach(s ->{
            int value = Integer.parseInt(s);

            if(value > 255 || value < 0) {
                throw new InvalidIpAddressException("highest digit could ever be is 254, and smallest is 0");
            }
        });
    }

}

