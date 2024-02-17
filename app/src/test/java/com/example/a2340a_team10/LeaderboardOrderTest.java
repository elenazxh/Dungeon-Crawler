package com.example.a2340a_team10;

import org.junit.BeforeClass;
import org.junit.Test;

import com.example.a2340a_team10.model.Attempt;
import com.example.a2340a_team10.model.LeaderboardModel;
import com.example.a2340a_team10.model.Player;
import com.example.a2340a_team10.viewmodel.LeaderboardViewModel;


import static org.junit.Assert.*;

import java.util.ArrayList;

public class LeaderboardOrderTest {


    @BeforeClass
    public static void setupBeforeClass() {
        setPlayer("Player1", 120, R.drawable.female_elf);
        LeaderboardViewModel.addAttempt();
        setPlayer("Player2", 0, R.drawable.witch);
        LeaderboardViewModel.addAttempt();
        setPlayer("Player3", 1000, R.drawable.male_elf);
        LeaderboardViewModel.addAttempt();
        setPlayer("Player4", 1, R.drawable.witch);
        LeaderboardViewModel.addAttempt();
        setPlayer("Player5", 1001, R.drawable.wizard);
        LeaderboardViewModel.addAttempt();
    }
    @Test
    public void testDescendingOrderLeaderboard() {
            ArrayList<Attempt> listOnDisplay = LeaderboardModel.getInstance().getAttemptHistory();
            assertEquals("Player5" , listOnDisplay.get(0).getName());
            assertEquals("Player3" , listOnDisplay.get(1).getName());
            assertEquals("Player1" , listOnDisplay.get(2).getName());
            assertEquals("Player4" , listOnDisplay.get(3).getName());
            assertEquals("Player2" , listOnDisplay.get(4).getName());
    }

    @Test
    public void testDisplayLastAttempt() {
        Attempt latestAttempt = LeaderboardModel.getInstance().getLatestAttempt();
        assertEquals("Player5" , latestAttempt.getName());
    }

    private static void setPlayer(String name, int score, int characterChoice) {
        Player.getPlayer().setName(name);
        Player.getPlayer().setScore(score);
        Player.getPlayer().setCharacterChoice(characterChoice);
    }
}
