package com.example.a2340a_team10;

import com.example.a2340a_team10.viewmodel.PlayerView;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoundaryTest {
    private PlayerView playerView;

    @Before
    public void setUp() {
        playerView = new PlayerView(true);
    }

    @Test
    public void cannotBeyondLeftEdge() {
        int[] position = {-10, 300};
        assertFalse(playerView.inBoundary(position));
    }

    @Test
    public void cannotBeyondRightEdge() {
        int[] position = {3001, 300};
        assertFalse(playerView.inBoundary(position));
    }

    @Test
    public void cannotBeyondTopEdge() {
        int[] position = {400, -10};
        assertFalse(playerView.inBoundary(position));
    }

    @Test
    public void cannotBeyondBottomEdge() {
        int[] position = {400, 1200};
        assertFalse(playerView.inBoundary(position));
    }

    @Test
    public void canMoveWithinScreen() {
        int[] position = {400, 300};
        assertTrue(playerView.inBoundary(position));
    }
}
