package com.example.a2340a_team10.model;

public class ZombieFactory extends EnemyFactory {
    @Override
    public Enemy spawnEnemy() {
        return new Zombie();
    }
}