
public class HostBits {
	int suffix;
	int hostBits;
	String startingBin;

	int maxHosts;
	
	//0 - Wire, 1- firstHost, 2- lastHost, 3- Broadcast, 4- Starting
	String [] binNeeded = new String[4];

	public HostBits(int suffix, String binaryString) {
		this.suffix = suffix;
		this.hostBits = 32 - suffix;
		this.maxHosts = (int) Math.pow(2, this.hostBits);
	
		for(int i = 0; i < binNeeded.length ; i++) {
			binNeeded[i] = ""; // clear all the strings
		}
		startingBin = binaryString.substring(suffix, 32);
		setBin();
	}

	public void setBin() {
		int count = 0;
		while (count < hostBits) {
			 binNeeded[0]+= "0";
			binNeeded[3] += "1";
			count++;
		}
		

		count = 0;
		while (count < (hostBits - 1)) {
			binNeeded[1] += 0;
			binNeeded[2]+= 1;
			count++;
		}

		binNeeded[1] += 1;
		binNeeded[2] += 0;
	}

}
