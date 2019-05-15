package junit;


import calculator.calculations.HostBits;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;




class HostBitsTest {

    private HostBits hostBits;
    private static final int SUFFIX = 24;

    @BeforeEach
    void setup() {
        hostBits = new HostBits(SUFFIX);
    }

    @Test
    void hostBitsShouldBeCorrect() {

    }


}
