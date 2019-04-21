package src.services;

import org.springframework.stereotype.Service;
import src.calculations.SNCalc;
import src.dto.IpAddressInfo;
import src.dto.IpAddressResponse;

import java.util.Arrays;
import java.util.List;

@Service
public class SubnetCalculatorService {


    public SubnetCalculatorService() {
        //this.ipAddressResponse = new IpAddressResponse(new IpAddressInfo("192.100.110.11")); // this is for test.
    }
    public IpAddressResponse buildIpList(String ip, int suffix) {
        StringBuilder wholeBin = new StringBuilder();
        String[] split = ip.split("\\."); // parse the string for the following format.
        Arrays.stream(split).forEach((s) -> wholeBin.append(String.format("%08d", Integer.valueOf(Integer.toBinaryString(Integer.parseInt(s))))));

        SNCalc oSNC = new SNCalc(suffix, wholeBin.toString());

        List<IpAddressInfo> addressInfos = oSNC.loopSubnets();

        return new IpAddressResponse(ip, suffix, addressInfos);

    }
}
