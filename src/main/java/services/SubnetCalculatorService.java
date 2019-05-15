package main.java.services;

import main.java.validation.IpAddressValidator;
import org.springframework.stereotype.Service;
import main.java.calculations.SNCalc;
import main.java.dto.IpAddressInfo;
import main.java.dto.IpAddressResponse;

import java.util.Arrays;
import java.util.List;

@Service
public class SubnetCalculatorService {

    public IpAddressResponse buildIpList(String ip, int suffix) {
        System.out.println(ip);
        IpAddressValidator.validateIP(ip);
        IpAddressValidator.validateSuffix(ip, suffix);
        StringBuilder wholeBin = new StringBuilder();
        String[] split = ip.split("\\."); // parse the string for the following format.
        Arrays.stream(split).forEach(s -> wholeBin.append(String.format("%08d", Integer.valueOf(Integer.toBinaryString(Integer.parseInt(s))))));

        SNCalc oSNC = new SNCalc(suffix, wholeBin.toString());
        List<IpAddressInfo> addressInfos = oSNC.buildListOfSubnets();
        IpAddressResponse ipAddressResponse = new IpAddressResponse(ip, suffix, addressInfos);
        ipAddressResponse.setNumOfSubnets(oSNC.getNumOfSubnets());
        ipAddressResponse.setClassType(oSNC.getSubnetBits().getClassType());
        ipAddressResponse.setMaxHosts(oSNC.getoHB().getMaxHosts());
        return ipAddressResponse;
    }
}
