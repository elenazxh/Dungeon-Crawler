package com.example.a2340a_team10.model;

public class RedFlask extends PowerUp {
    public RedFlask() {
        this.function = "Recover 2 HP";
    }

    @Override
    public boolean collectPowerUp() {
        int x = hero.getPosX();
        int y = hero.getPosY();
        return 2190 <= x && x <= 2250 && 630 <= y && y <= 740;
    }
}