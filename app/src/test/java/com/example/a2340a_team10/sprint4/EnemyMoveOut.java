package com.example.a2340a_team10.sprint4;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.example.a2340a_team10.viewmodel.EnemyMove;

import org.junit.Before;
import org.junit.Test;

public class EnemyMoveOut {

    private EnemyMove enemyMove;
    private int[] pos = new int[2];
    @Before
    public void setUp(){
        pos[0]=30000;
        pos[1]=40000;
        enemyMove = new EnemyMove(pos);
    }
    @Test
    public void nameIsUpdatedCorrectly(){
        int[] newPos = new int[2];
        newPos=enemyMove.move();
        assertEquals(newPos[0]<30000,true);
    }
}