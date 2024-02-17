package com.example.a2340a_team10.model;

import java.util.ArrayList;

// This class stores a list of Records that will be present on the leaderboard
public class LeaderboardModel {
    private static LeaderboardModel leaderboard;
    private static ArrayList<Attempt> attemptHistory;
    private Attempt latestAttempt;

    private LeaderboardModel() {
        attemptHistory = new ArrayList<>();
    }

    public static LeaderboardModel getInstance() {
        if (leaderboard == null) {
            leaderboard = new LeaderboardModel();
        }
        return leaderboard;
    }

    public  Attempt getLatestAttempt() {
        return latestAttempt;
    }

    public void setLatestAttempt(Attempt latestAttempt) {
        this.latestAttempt = latestAttempt;
    }

    public ArrayList<Attempt> getAttemptHistory() {
        return attemptHistory;
    }

    public Attempt getlatestAttempt() {
        return latestAttempt;
    }

    public void setlatestAttempt(Attempt lastestAttempt) {
        this.latestAttempt = lastestAttempt;
    }
}
