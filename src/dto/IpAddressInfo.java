package src.dto;

import lombok.Data;

@Data
public class IpAddressInfo {

    private String wireAddress;
    private String firstHost;
    private String lastHost;
    private String networkAddress;

    public IpAddressInfo() {
    }

    public IpAddressInfo(String wireAddress, String firstHost, String lastHost, String networkAddress) {
        this.wireAddress = wireAddress;
        this.firstHost = firstHost;
        this.lastHost = lastHost;
        this.networkAddress = networkAddress;
    }

    public String getWireAddress() {
        return wireAddress;
    }

    public void setWireAddress(String wireAddress) {
        this.wireAddress = wireAddress;
    }

    public String getFirstHost() {
        return firstHost;
    }

    public void setFirstHost(String firstHost) {
        this.firstHost = firstHost;
    }

    public String getLastHost() {
        return lastHost;
    }

    public void setLastHost(String lastHost) {
        this.lastHost = lastHost;
    }

    public String getNetworkAddress() {
        return networkAddress;
    }

    public void setNetworkAddress(String networkAddress) {
        this.networkAddress = networkAddress;
    }
}

