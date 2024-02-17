package com.example.a2340a_team10.sprint5;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class YellowFlaskTest {
    public boolean collectPowerUp(int x, int y) {
        return 2190 <= x && x <= 2250 && 630 <= y && y <= 740;
    }

    @Test
    public void redF1() {
        int x = 1900;
        int y = 700;
        assertEquals(collectPowerUp(x, y),false);
    }

    @Test
    public void redF2(){
        int x = 2250;
        int y = 740;
        assertEquals(collectPowerUp(x, y),true);
    }
}
