package com.example.a2340a_team10;

import org.junit.Before;
import org.junit.Test;
import org.junit.BeforeClass;

import com.example.a2340a_team10.model.KeyAction;
import com.example.a2340a_team10.model.MoveKeyActionFactory;
import com.example.a2340a_team10.model.Obstacle;
import com.example.a2340a_team10.model.Player;
import com.example.a2340a_team10.model.ScreenSetup;
import com.example.a2340a_team10.viewmodel.PlayerView;


import static org.junit.Assert.*;

import android.view.KeyEvent;

import java.util.Arrays;

public class PlayerMovementTest {
    static int[] pos = new int[2];
    static ScreenSetup screenSetup;
    static MoveKeyActionFactory moveKeyActionFactory;
    static PlayerView playerView;
    @BeforeClass
    public static void setupBeforeClass() {
        pos[0] = 500;
        pos[1] = 500;
        Obstacle obstacle1 = new Obstacle(360, 0, 400, 330);
        Obstacle obstacle2 = new Obstacle(2200,0, 400, 330);
        screenSetup = new ScreenSetup(3000, 3000, Arrays.asList(obstacle1, obstacle2));
        moveKeyActionFactory = new MoveKeyActionFactory();
    }

    @Before
    public void setUp(){
        playerView = new PlayerView(true);
        playerView.setPos(pos);
    }
    @Test
    public void testMoveDown() {
        KeyAction keyAction = moveKeyActionFactory.createKeyAction(KeyEvent.KEYCODE_DPAD_DOWN);
        playerView.movePlayer(screenSetup, keyAction);
        int[] newPos = playerView.getPos();
        assertEquals(true, newPos[0] == pos[0]);
        assertEquals(true, newPos[1] > pos[1]);
    }

    @Test
    public void testMoveLeft() {
        KeyAction keyAction = moveKeyActionFactory.createKeyAction(KeyEvent.KEYCODE_DPAD_LEFT);
        playerView.movePlayer(screenSetup, keyAction);
        int[] newPos = playerView.getPos();
        assertEquals(true, newPos[0] < pos[0]);
        assertEquals(true, newPos[1] == pos[1]);
    }

    @Test
    public void testMoveRight() {
        KeyAction keyAction = moveKeyActionFactory.createKeyAction(KeyEvent.KEYCODE_DPAD_RIGHT);
        playerView.movePlayer(screenSetup, keyAction);
        int[] newPos = playerView.getPos();
        assertEquals(true, newPos[0] > pos[0]);
        assertEquals(true, newPos[1] == pos[1]);
    }

    @Test
    public void testMoveUp() {
        KeyAction keyAction = moveKeyActionFactory.createKeyAction(KeyEvent.KEYCODE_DPAD_UP);
        playerView.movePlayer(screenSetup, keyAction);
        int[] newPos = playerView.getPos();
        assertEquals(true, newPos[0] == pos[0]);
        assertEquals(true, newPos[1] < pos[1]);
    }
}
