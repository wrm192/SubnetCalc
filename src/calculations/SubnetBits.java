package src.calculations;

import lombok.Data;

@Data
public class SubnetBits {

	String classType;
	int subnetBits;
	int lockedBits;
	String startingSubnet;
	String rangeBinary;
	String binaryString;
	public int maxSubnets;
	int suffix;

	public SubnetBits(int suffix, String binary, String classType) {
		this.suffix = suffix;
		this.classType = classType;
		this.rangeBinary = "";

		if (classType.equals("A")) {
			this.lockedBits = 8;
			this.subnetBits = suffix - lockedBits;
		} else if (classType.equals("B")) {
			this.lockedBits = 16;
			this.subnetBits = suffix - lockedBits;
		} else {
			this.lockedBits = 24;
			this.subnetBits = suffix - lockedBits;
		}

		this.maxSubnets = (int) Math.pow(2, this.subnetBits);

		binaryString = binary.substring(lockedBits, (lockedBits + subnetBits));

	}

	public void addOne(String binaryToAdd) {
		int count = 0;
		int x = (1 + Integer.parseInt(binaryToAdd, 2));
		String newBinary = Integer.toString(x, 2);
		String paddedString = "";
		
		while (count < subnetBits - newBinary.length()) {
			paddedString += "0";
			count++;
		}
		paddedString += newBinary;

		rangeBinary = paddedString;
	}

	public void setSubnet(int subnetStart) {
		int count = 0;
		String newString = Integer.toString(subnetStart, 2);
		String paddedString = "";

		while (count < subnetBits - newString.length()) {
			paddedString += "0";
			count++;
		}

		paddedString += newString;
		rangeBinary = paddedString;

	}
}
