package com.example.a2340a_team10.viewmodel;

import android.view.KeyEvent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.a2340a_team10.model.*;

import java.util.List;
import java.util.Timer;

public class PlayerView extends ViewModel {
    private final MutableLiveData<Integer> scoreLiveData = new MutableLiveData<>();
    private final Timer scoreTimer = new Timer();
    private final Object scoreLock = new Object();
    private int[] pos;
    private int scoreDecreaseAmount = 10;
    private Player hero = Player.getPlayer();

    public PlayerView() {
        // Initialize the score LiveData with the starting score
        scoreLiveData.postValue(hero.getScore());
    }

    public PlayerView(boolean forTest) {
        pos = new int[2];
    }

    public void decreaseScore(Player player) {
        synchronized (scoreLock) {
            int playerScore = player.getScore();
            int liveDataScore = scoreLiveData.getValue() != null ? scoreLiveData.getValue() : 0;

            // Update Player's score
            int updatedPlayerScore = Math.max(0, playerScore - scoreDecreaseAmount);
            player.setScore(updatedPlayerScore);

            // Update LiveData's score
            int updatedLiveDataScore = Math.max(0, liveDataScore - scoreDecreaseAmount);
            scoreLiveData.postValue(updatedLiveDataScore);
        }
    }

    public void increaseScore(int increment) {
        synchronized (scoreLock) {
            int currentScore = hero.getScore();
            hero.setScore(currentScore + increment);
            scoreLiveData.postValue(hero.getScore());
        }
    }

    public LiveData<Integer> getScoreLiveData() {
        return scoreLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        scoreTimer.cancel();
    }

    public boolean inBoundary(int[] positions) {
        int posX = positions[0];
        int posY = positions[1];
        return posX >= 0 && posY >= 0 && posY <= 1150 && posX <= 3000;
    }
    public boolean jump(int playerY, int playerX, int screen) {
        boolean ans = false;
        if (screen == 1) {
            if ((playerY >= 2800) && (playerX >= 600) && (playerX <= 700)) {
                ans = true;
            }
        }
        return ans;
    }

    public boolean onObstacle(int[] positions, List<Obstacle> obstacles) {
        for (Obstacle obstacle : obstacles) {
            boolean above = positions[1] + 66 < obstacle.getY();
            boolean below = positions[1] > obstacle.getY() + obstacle.getHeight();
            boolean onLeft = positions[0] + 60 < obstacle.getX();
            boolean onRight = positions[0] > obstacle.getX() + obstacle.getWidth();
            if ((!above) && (!below) && (!onLeft) && (!onRight)) {
                return true;
            }
        }
        return false;
    }

    public void movePlayer(ScreenSetup screenSetup, KeyAction keyAction) {
        int[] positions;
        boolean noCollision;
        boolean inBoundary;
        if (keyAction != null && screenSetup != null) {
            positions = keyAction.performAction(this);
            if (screenSetup.getObstacles() == null) {
                noCollision = true;
            } else {
                noCollision = !onObstacle(positions, screenSetup.getObstacles());
            }
            inBoundary = inBoundary(positions);
            if (noCollision && inBoundary) {
                pos = positions;
            }
        }
    }

    public void bounceBack(ScreenSetup screenSetup) {
        MoveKeyActionFactory moveKeyActionFactory = new MoveKeyActionFactory();
        KeyAction keyAction = moveKeyActionFactory.createKeyAction(KeyEvent.KEYCODE_DPAD_LEFT);
        movePlayer(screenSetup, keyAction);
    }

    public int[] getPos() {
        return pos;
    }

    public void setPos(int[] pos) {
        this.pos = pos;
    }

    public boolean isTouchingCoin(int playerX, int playerY, int coinX, int coinY) {
        int playerSize = 35;
        int coinSize = 25;

        return playerX < coinX + coinSize && playerX + playerSize > coinX
                && playerY < coinY + coinSize && playerY + playerSize > coinY;
    }

}

