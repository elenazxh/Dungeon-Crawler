package com.example.a2340a_team10.model;

import java.util.ArrayList;

public class Player implements Observable, CommonPowers {
    private static Player hero;
    private String playerName;
    private String difficulty;
    private int health;
    private int maxHealth;
    private int speed;
    private int score;
    private int posX;
    private int posY;
    private int characterChoice;
    private ArrayList<Observer> observers = new ArrayList<>();

    private Player() {
        this.score = 0;
        this.posX = 0;
        this.posY = 0;
    }
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    @Override
    public void removeAllObservers() {
        observers.clear();
    }
    @Override
    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public static Player getPlayer() {
        if (hero == null) {
            hero = new Player();
        }
        return hero;
    }

    public static void clear() {
        hero = null;
    }

    public void updatePosition(int newX, int newY, boolean notify) {
        this.posX = newX;
        this.posY = newY;
        if (notify) {
            notifyAllObservers();
        }
    }

    public boolean collectPowerUp() {
        return false;
    }

    public void setPosX(int newX) {
        this.posX = newX;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosY(int newY) {
        this.posY = newY;
    }

    public int getPosY() {
        return posY;
    }

    public String getName() {
        return playerName;
    }

    public void setName(String name) {
        playerName = name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health <= 5) {
            this.health = health;
        } else {
            this.health = 5;
        }
    }

    /* public void setMaxHealth(int health) {
        this.maxHealth = health;
    }
    */

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCharacterChoice() {
        return characterChoice;
    }

    public void setCharacterChoice(int choice) {
        this.characterChoice = choice;
    }

    public void resetScore() {
        this.score = 300;
    }

    public int getDifficultyMultiplier() {
        switch (this.difficulty) {
        case "Easy":
            return 1;
        case "Medium":
            return 2;
        case "Hard":
            return 3;
        default:
            return 0;
        }
    }
}
