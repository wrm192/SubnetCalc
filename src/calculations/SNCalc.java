package src.calculations;


import lombok.Data;
import src.dto.IpAddressInfo;
import src.exceptions.InvalidIpAddressException;

import java.util.ArrayList;
import java.util.List;


@Data
public class SNCalc {

	private int m_startingRange;
	private int m_endRange;
	private HostBits oHB;
	private SubnetBits subnetBits;
	private LockedBits lockedBits;
	private int numOfSubnets;


	public SNCalc(int suffix, String binary) {
		m_startingRange = 0;

		int firstOctValue = Integer.parseInt(binary.substring(0, 8), 2);
		if (firstOctValue == 127 || firstOctValue > 223) {
			throw new InvalidIpAddressException("Invalid Ip range");
		} else if (firstOctValue < 128) {
			subnetBits = new SubnetBits(suffix, "A");
			lockedBits = new LockedBits(binary, "A");
		} else if (firstOctValue < 192) {
			subnetBits = new SubnetBits(suffix, "B");
			lockedBits = new LockedBits(binary, "B");
		} else {
			subnetBits = new SubnetBits(suffix,"C");
			lockedBits = new LockedBits(binary,"C");
		}
		oHB = new HostBits(suffix);

		m_endRange = this.subnetBits.getMaxSubnets();
		this.numOfSubnets = m_endRange - m_startingRange;
	}

	public HostBits getoHB() {
		return oHB;
	}

	public int getNumOfSubnets() {
		return numOfSubnets;
	}

	public SubnetBits getSubnetBits() {
		return subnetBits;
	}

	private String wholeString(String locked, String sub, String host) {
		String totalBin = "";
		totalBin += locked + sub + host;
		return totalBin;
	}

	private int[] convertToIP(String wholeBin) {
		int[] octets = new int[4];

		octets[0] = Integer.parseInt(wholeBin.substring(0, 8), 2);
		octets[1] = Integer.parseInt(wholeBin.substring(8, 16), 2);
		octets[2] = Integer.parseInt(wholeBin.substring(16, 24), 2);
		octets[3] = Integer.parseInt(wholeBin.substring(24, 32), 2);

		return octets;
	}

	private String printIP(int[] octets) {

		return octets[0] + "." + octets[1] + "." + octets[2] + "." + octets[3];
	}

	public List<IpAddressInfo> buildListOfSubnets() {
		List<IpAddressInfo> ips= new ArrayList<>();
		subnetBits.setSubnet(m_startingRange);

		for (int i = m_startingRange; i < m_endRange; i++) {
			IpAddressInfo current = new IpAddressInfo();
			for (int j = 0; j < oHB.binNeeded.length; j++) {
				String currentIp = printIP(convertToIP(wholeString(this.lockedBits.getBinaryString(), this.subnetBits.getRangeBinary(), oHB.binNeeded[j])));
				switch (j) {
					case 0:
						current.setWireAddress(currentIp);
						break;
					case 1:
						current.setFirstHost(currentIp);
						break;
					case 2:
						current.setLastHost(currentIp);
						break;
					case 3:
						current.setBroadcastAddress(currentIp);
						break;
				}
			}
			ips.add(current);
			subnetBits.addOne(this.subnetBits.getRangeBinary());
		}
		return  ips;
	}

}
