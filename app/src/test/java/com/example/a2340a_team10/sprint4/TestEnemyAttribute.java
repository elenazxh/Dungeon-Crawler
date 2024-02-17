package com.example.a2340a_team10.sprint4;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.example.a2340a_team10.model.Muddy;
import com.example.a2340a_team10.model.Necromancer;
import com.example.a2340a_team10.model.Ogre;
import com.example.a2340a_team10.model.Orc;
import com.example.a2340a_team10.model.Zombie;

import org.junit.Test;
public class TestEnemyAttribute {
    Orc orc = new Orc();
    Ogre orge = new Ogre();

    Zombie zombie = new Zombie();

    Necromancer necromancer = new Necromancer();

    Muddy muddy = new Muddy();

    @Test
    public void nameIsSetCorrectly(){
        Orc orc2 = new Orc();
        assertEquals("Orc", orc2.getEnemyName());
    }

    @Test
    public void nameIsSetCorrectly2(){
        Ogre ogre2 = new Ogre();
        assertEquals("Ogre", ogre2.getEnemyName());
    }

    @Test
    public void nameIsSetCorrectly3(){
        Zombie zombie = new Zombie();
        assertEquals("Zombie", zombie.getEnemyName());
    }

    @Test
    public void nameIsSetCorrectly4(){
        Necromancer necromancer = new Necromancer();
        assertEquals("Necromancer", necromancer.getEnemyName());
    }

    @Test
    public void nameIsSetCorrectly5(){
        Muddy muddy = new Muddy();
        assertEquals("Muddy", muddy.getEnemyName());
    }

    @Test
    public void nameIsSetCorrectly6(){
        Muddy muddy = new Muddy();
        muddy.setEnemyName("Mud");
        assertEquals("Mud", muddy.getEnemyName());
    }

    @Test
    public void nameIsSetCorrectly7(){
        Muddy muddy = new Muddy();
        muddy.setEnemyName("Mu");
        assertEquals("Mu", muddy.getEnemyName());
    }

    @Test
    public void nameIsSetCorrectly8(){
        Orc orc2 = new Orc();
        orc2.setEnemyName("r");
        assertEquals("r", orc2.getEnemyName());
    }

    @Test
    public void nameIsSetCorrectly9(){
        Ogre ogre2 = new Ogre();
        ogre2.setEnemyName("g");
        assertEquals("g", ogre2.getEnemyName());
    }

    @Test
    public void nameIsSetCorrectly10(){
        Zombie zombie = new Zombie();
        zombie.setEnemyName("f");
        assertEquals("f", zombie.getEnemyName());
    }

    @Test
    public void nameIsSetCorrectly11(){
        Necromancer necromancer = new Necromancer();
        necromancer.setEnemyName("d");
        assertEquals("d", necromancer.getEnemyName());
    }

    @Test
    public void nameIsSetCorrectly12(){
        Necromancer necromancer = new Necromancer();
        Zombie zombie = new Zombie();
        zombie.setEnemyName("d");
        necromancer.setEnemyName("d");
        assertEquals(zombie.getEnemyName(), necromancer.getEnemyName());
    }

    @Test
    public void nameIsSetCorrectly13(){
        Necromancer necromancer = new Necromancer();
        Zombie zombie = new Zombie();
        zombie.setEnemyName("d");
        necromancer.setEnemyName("d");
        Ogre ogre2 = new Ogre();
        ogre2.setEnemyName("g");
        assertEquals("g", ogre2.getEnemyName());
    }


}
