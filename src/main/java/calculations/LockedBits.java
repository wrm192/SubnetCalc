package main.java.calculations;

class LockedBits {

    private String binaryString;

    LockedBits(String binary, String classType) {
        int lockedBits;
        if (classType.equals("A")) {
            lockedBits = 8;
        } else if (classType.equals("B")) {
            lockedBits = 16;
        } else {
            lockedBits = 24;
        }
        binaryString = binary.substring(0, lockedBits);
    }

    String getBinaryString() {
        return binaryString;
    }

}
