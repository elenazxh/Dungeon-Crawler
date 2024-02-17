package com.example.a2340a_team10.sprint5;
import com.example.a2340a_team10.model.Player;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ScoreTest{

    private Player player;
    @Before
    public void setUp() {
        player = Player.getPlayer();
    }

    @Test
    public void ScoreInitializeAndUpdateTest() {
        assertEquals("initial score should be 0", 0, player.getScore());

        int newScore = 100;
        player.setScore(newScore);

        assertEquals("updated score should be 100", newScore, player.getScore());
    }
}
