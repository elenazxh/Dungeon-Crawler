package com.example.a2340a_team10.model;

import java.util.List;

public class ScreenSetup {
    private int screenWidth;
    private int screenHeight;
    private List<Obstacle> obstacles;

    public ScreenSetup(int screenWidth, int screenHeight, List<Obstacle> obstacles) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.obstacles = obstacles;
    }

    public ScreenSetup(List<Obstacle> obstacles) {
        this(0, 0, obstacles);
    }

    public ScreenSetup() {
        this(0, 0, null);
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public void setObstacles(List<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }
}
