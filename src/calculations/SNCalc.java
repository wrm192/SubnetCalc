package src.calculations;


import lombok.Data;
import src.dto.IpAddressInfo;
import src.exceptions.InvalidIpAddressException;

import java.util.ArrayList;
import java.util.List;


@Data
public class SNCalc {

	public int m_suffix;
	public String m_startingBin;
	public int m_startingRange;
	public int m_endRange;
	public HostBits oHB;
	public SubnetBits oSB;
	public LockedBits oLB;
	public String[][] data;

	public SNCalc(int suffix, String binary) {
		this.m_suffix = suffix;
		this.m_startingBin = binary;
		m_startingRange = 0;

		int firstOctValue = Integer.parseInt(binary.substring(0, 8), 2);
		if (firstOctValue == 127 || firstOctValue > 223) {
			throw new InvalidIpAddressException("Invalid Ip range");
		} else if (firstOctValue < 128) {
			oSB = new SubnetBits(suffix, binary, "A");
			oLB = new LockedBits(suffix, binary, "A");
		} else if (firstOctValue >= 128 && firstOctValue < 192) {
			oSB = new SubnetBits(suffix, binary, "B");
			oLB = new LockedBits(suffix, binary, "B");
		} else {
			oSB = new SubnetBits(suffix, binary, "C");
			oLB = new LockedBits(suffix, binary, "C");
		}
		oHB = new HostBits(suffix, binary);

		m_endRange = oSB.maxSubnets;
		data = new String[oSB.maxSubnets][4];
	}


	public void setRanges(int start, int end) {

		if (start > oSB.maxSubnets) {
			System.out.println("Sorry that starting point isn't going to work. Will set to 0.");
			m_startingRange = 0;

		} else
			this.m_startingRange = start;

		if (end + 1 > oSB.maxSubnets) {
			System.out.println("Sorry that end point isn't going to work. Will set to max number");
			m_endRange = oSB.maxSubnets;
		}

		else
			this.m_endRange = end + 1;

		loopSubnets();

		this.m_endRange = oSB.maxSubnets;
		this.m_startingRange = 0;

	}

	public String wholeString(String locked, String sub, String host) {

		String totalBin = "";
		totalBin += locked + sub + host;
		return totalBin;
	}

	public int[] convertToIP(String wholeBin) {
		int[] octets = new int[4];

		octets[0] = Integer.parseInt(wholeBin.substring(0, 8), 2);
		octets[1] = Integer.parseInt(wholeBin.substring(8, 16), 2);
		octets[2] = Integer.parseInt(wholeBin.substring(16, 24), 2);
		octets[3] = Integer.parseInt(wholeBin.substring(24, 32), 2);

		return octets;
	}

	public String printIP(int[] octets) {

		return octets[0] + "." + octets[1] + "." + octets[2] + "." + octets[3];
	}

	public List<IpAddressInfo> loopSubnets() {
		List<IpAddressInfo> ips= new ArrayList<>();
		oSB.setSubnet(m_startingRange);

		for (int i = m_startingRange; i < m_endRange; i++) {
			IpAddressInfo current = new IpAddressInfo();
			for (int j = 1; j <= oHB.binNeeded.length; j++) {
				String currentIp = printIP(convertToIP(wholeString(oLB.binaryString, oSB.rangeBinary, oHB.binNeeded[j - 1])));
				switch (j) {
					case 1:
						current.setWireAddress(currentIp);
						break;
					case 2:
						current.setFirstHost(currentIp);
						break;
					case 3:
						current.setLastHost(currentIp);
						break;
					case 4:
						current.setNetworkAddress(currentIp);
						break;
				}
			}
			ips.add(current);
			oSB.addOne(oSB.rangeBinary);
		}
		return  ips;
	}

	public String getSubnetMask() {
		String binary = "";
		int count = 0;

		while (count < m_suffix) {
			binary += "1";
			count++;
		}
		while (binary.length() < 32) {
			binary += "0";
		}

		return binary;
	}

}