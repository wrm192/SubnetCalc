package main.java.dto;

public class IpAddressInfo {

    private String wireAddress;
    private String firstHost;
    private String lastHost;
    private String broadcastAddress;
    private int seq;

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

    public String getBroadcastAddress() {
        return broadcastAddress;
    }

    public void setBroadcastAddress(String broadcastAddress) {
        this.broadcastAddress = broadcastAddress;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    @Override
    public String toString() {
        return "IpAddressInfo{" +
                "wireAddress='" + wireAddress + '\'' +
                ", firstHost='" + firstHost + '\'' +
                ", lastHost='" + lastHost + '\'' +
                ", broadcastAddress='" + broadcastAddress + '\'' +
                ", seq=" + seq +
                '}';
    }
}

