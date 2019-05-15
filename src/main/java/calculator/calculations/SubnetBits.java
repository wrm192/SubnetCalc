package calculator.calculations;
import lombok.Data;

@Data
public class SubnetBits {

    private String classType;
    private int subnetBits;
    private String rangeBinary;
    private int maxSubnets;

    public String getClassType() {
        return classType;
    }

    String getRangeBinary() {
        return rangeBinary;
    }

    int getMaxSubnets() {
        return maxSubnets;
    }

    SubnetBits(int suffix, String classType) {
        int lockedBits;
        this.classType = classType;
        this.rangeBinary = "";

        if (classType.equals("A")) {
            lockedBits = 8;
            this.subnetBits = suffix - lockedBits;
        } else if (classType.equals("B")) {
            lockedBits = 16;
            this.subnetBits = suffix - lockedBits;
        } else {
            lockedBits = 24;
            this.subnetBits = suffix - lockedBits;
        }
        this.maxSubnets = (int) Math.pow(2, this.subnetBits);
    }

    void addOne(String binaryToAdd) {
        if (!binaryToAdd.equals("")) {
            int x = (1 + Integer.parseInt(binaryToAdd, 2));
            this.addAmount(x);
        }

    }

    void setSubnet(int subnetStart) {
        this.addAmount(subnetStart);
    }

    private void addAmount(int add) {
        if (this.subnetBits != 0) {
            int count = 0;
            String newString = Integer.toString(add, 2);
            StringBuilder temp = new StringBuilder();

            while (count < this.subnetBits - newString.length()) {
                temp.append("0");
                count++;
            }
            temp.append(newString);
            rangeBinary = temp.toString();
        }
    }

    @Override
    public String toString() {
        return "SubnetBits{" +
                "classType='" + classType + '\'' +
                ", subnetBits=" + subnetBits +
                ", rangeBinary='" + rangeBinary + '\'' +
                ", maxSubnets=" + maxSubnets +
                '}';
    }
}
