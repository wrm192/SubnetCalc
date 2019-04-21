package src.dto;

import lombok.Data;

import java.util.List;

@Data
public class IpAddressResponse {

    private String startingIp;
    private int suffix;
    private List<IpAddressInfo> ipAddressInfo;

    public IpAddressResponse(String startingIp, int suffix, List<IpAddressInfo> ipAddressInfo) {
        this.startingIp = startingIp;
        this.suffix = suffix;
        this.ipAddressInfo = ipAddressInfo;
    }

    public String getStartingIp() {
        return startingIp;
    }

    public void setStartingIp(String startingIp) {
        this.startingIp = startingIp;
    }

    public int getSuffix() {
        return suffix;
    }

    public void setSuffix(int suffix) {
        this.suffix = suffix;
    }

    public List<IpAddressInfo> getIpAddressInfo() {
        return ipAddressInfo;
    }

    public void setIpAddressInfo(List<IpAddressInfo> ipAddressInfo) {
        this.ipAddressInfo = ipAddressInfo;
    }

}
