package com.example.a2340a_team10.viewmodel;

import com.example.a2340a_team10.model.Attempt;
import com.example.a2340a_team10.model.LeaderboardModel;

import java.util.ArrayList;
import java.util.Collections;

public class LeaderboardViewModel {
    public static void addAttempt() {
        ArrayList<Attempt> attemptHistory = LeaderboardModel.getInstance().getAttemptHistory();
        Attempt newAttempt = new Attempt();
        LeaderboardModel.getInstance().setLatestAttempt(newAttempt);
        attemptHistory.add(newAttempt);
        Collections.sort(attemptHistory);
    }
}
