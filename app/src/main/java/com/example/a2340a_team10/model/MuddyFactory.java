package com.example.a2340a_team10.model;

public class MuddyFactory extends EnemyFactory {
    @Override
    public Enemy spawnEnemy() {
        return new Muddy();
    }
}
