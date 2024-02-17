package com.example.a2340a_team10.sprint5;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class BlueFlaskTest {

    public boolean collectPowerUp(int x, int y) {
        return 1490 <= x && x <= 1580 && 440 <= y && y <= 560;
    }

    @Test
    public void blueF1() {
        int x = 1490;
        int y = 1600;
        assertEquals(collectPowerUp(x, y),false);
    }

    @Test
    public void blueF2() {
        int x = 1530;
        int y = 510;
        assertEquals(collectPowerUp(x, y),true);
    }
}
