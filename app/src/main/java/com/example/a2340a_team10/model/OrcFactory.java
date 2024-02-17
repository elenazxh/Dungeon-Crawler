package com.example.a2340a_team10.model;

public class OrcFactory extends EnemyFactory {
    @Override
    public Enemy spawnEnemy() {
        return new Orc();
    }
}