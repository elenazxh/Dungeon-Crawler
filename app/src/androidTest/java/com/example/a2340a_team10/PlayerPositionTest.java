package com.example.a2340a_team10;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import com.example.a2340a_team10.model.Player;

public class PlayerPositionTest {

    private Player player;

    @Before
    public void setUp() {
        player = Player.getPlayer();
    }

    @Test
    public void testUpdatePosition() {

        int expectedPosX = 4;
        int expectedPosY = 4;

        player.updatePosition(expectedPosX, expectedPosY, false);

        assertEquals(expectedPosX, player.getPosX(), 0.00);
        assertEquals(expectedPosY, player.getPosY(), 0.00);
    }
}
