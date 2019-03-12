package calculations;

public class LockedBits {

	int suffix;
	int lockedBits;
	int decValue;
	String binaryString;

	public LockedBits(int suffix, String binary, String classType) {
		this.suffix = suffix;

		if (classType.equals("A")) {
			lockedBits = 8;
		} else if (classType.equals("B")) {
			lockedBits = 16;
		} else {
			lockedBits = 24;
		}
		binaryString = binary.substring(0, lockedBits);
		decValue = Integer.parseInt(binaryString, 2);

	}

}
