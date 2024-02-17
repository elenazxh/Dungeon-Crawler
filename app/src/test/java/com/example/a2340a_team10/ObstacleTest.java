package com.example.a2340a_team10;

import static org.junit.Assert.assertEquals;
import com.example.a2340a_team10.model.Obstacle;
import org.junit.Before;
import org.junit.Test;

public class ObstacleTest {
    private Obstacle obstacle;

    @Before
    public void setUp() {
        obstacle = new Obstacle(100, 200, 300, 400);
    }

    @Test
    public void testGetX() {
        assertEquals(100, obstacle.getX());
    }

    @Test
    public void testGetY() {
        assertEquals(200, obstacle.getY());
    }

    @Test
    public void testGetWidth() {
        assertEquals(300, obstacle.getWidth());
    }

    @Test
    public void testGetHeight() {
        assertEquals(400, obstacle.getHeight());
    }
}
