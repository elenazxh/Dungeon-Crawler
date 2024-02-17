package com.example.a2340a_team10;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import com.example.a2340a_team10.model.Player;

public class StartingHealthTest {

    private Player player;

    @Before
    public void setUp() {
        player = Player.getPlayer();
    }

    @Test
    public void easyLevelTest() {
        player.setDifficulty("Easy");
        player.setHealth(5);
        assertEquals("Easy", player.getDifficulty());
    }

    @Test
    public void mediumLevelTest() {
        player.setDifficulty("Medium");
        player.setHealth(4);
        assertEquals("Medium", player.getDifficulty());
    }

    @Test
    public void hardLevelTest() {
        player.setDifficulty("Hard");
        player.setHealth(3);
        assertEquals("Hard", player.getDifficulty());
    }
}
