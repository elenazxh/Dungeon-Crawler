package com.example.a2340a_team10.model;

public abstract class PowerUp implements CommonPowers {

    protected String function;
    protected int posX;
    protected int posY;
    protected Player hero = Player.getPlayer();

    public abstract boolean collectPowerUp();
}
