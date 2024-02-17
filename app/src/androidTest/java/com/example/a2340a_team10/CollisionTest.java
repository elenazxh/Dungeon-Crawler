package com.example.a2340a_team10;

import com.example.a2340a_team10.model.Obstacle;
import com.example.a2340a_team10.viewmodel.PlayerView;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CollisionTest {

    private PlayerView playerView;
    private int[] playerPosition;

    @Before
    public void setUp() {
        playerView = new PlayerView();
        playerPosition = new int[]{500, 500};
    }

    @Test
    public void testCollision() {
        Obstacle obstacle1 = new Obstacle(450, 450, 100, 100);
        List<Obstacle> obstacles = Arrays.asList(obstacle1);
        assertTrue(playerView.onObstacle(playerPosition, obstacles));
    }

    @Test
    public void testNoCollision1() {
        Obstacle obstacle1 = new Obstacle(1000, 1000, 20, 20);
        Obstacle obstacle2 = new Obstacle(600, 600, 20, 20);
        List<Obstacle> obstacles = Arrays.asList(obstacle1, obstacle2);
        assertFalse(playerView.onObstacle(playerPosition, obstacles));
    }

    @Test
    public void testNoCollision2() {
        Obstacle obstacle1 = new Obstacle(0, 0, 20, 20);
        Obstacle obstacle2 = new Obstacle(450, 450, 20, 20);
        List<Obstacle> obstacles = Arrays.asList(obstacle1, obstacle2);
        assertFalse(playerView.onObstacle(playerPosition, obstacles));
    }
}

