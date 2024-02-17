package com.example.a2340a_team10;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.a2340a_team10.model.Player;
import com.example.a2340a_team10.viewmodel.PlayerView;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class DecreaseScoreTests {
    @Test
    public void testScoreDecreasesToZero() {
        PlayerView playerView = new PlayerView();
        Player player = Player.getPlayer();
        player.setScore(3);  // setting the score to 20 using Player setter

        playerView.decreaseScore(player);
        int scoreAfterDecrease = player.getScore();

        assertEquals(0, player.getScore());
    }

    @Test
    public void testScoreDecreasesBy10EachTime() {
        PlayerView playerView = new PlayerView();
        Player player = Player.getPlayer();
        player.setScore(20);  // setting the score to 20 using Player setter

        playerView.decreaseScore(player);
        int scoreAfterDecrease = player.getScore();

        assertEquals(10, player.getScore());
    }

    @Test
    public void testInitialScore() {
        Player player = Player.getPlayer();
        player.getScore();
        assertEquals(300, player.getScore());
    }

    @Test
    public void testScoreReset() {
        Player player = Player.getPlayer();
        player.setScore(20);
        player.resetScore();
        assertEquals(300, player.getScore());
    }


}
