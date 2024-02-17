package com.example.a2340a_team10.model;

public class BlueFlask extends PowerUp {
    public BlueFlask() {
        this.function = "Teleport";
    }

    @Override
    public boolean collectPowerUp() {
        int x = hero.getPosX();
        int y = hero.getPosY();
        return 1490 <= x && x <= 1580 && 440 <= y && y <= 560;
    }
}
