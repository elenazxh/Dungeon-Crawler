package com.example.a2340a_team10.sprint5;

import static org.junit.Assert.assertEquals;

import com.example.a2340a_team10.model.Imp;
import com.example.a2340a_team10.model.Muddy;

import org.junit.Test;

public class TestWeapon {
    Imp imp = new Imp();
    int i = imp.getHealth();

    @Test
    public void TestBeforeDamage() {
        assertEquals(3, imp.getHealth());
    }
    @Test
    public void TestDamage() {
        imp.takeDamage();
        assertEquals(0, imp.getHealth());
    }

    Muddy muddy = new Muddy();
    int i2 = muddy.getHealth();
    @Test
    public void TestDoubleDamage() {
        muddy.takeDamage();
        assertEquals(0, muddy.getHealth());
    }

    Muddy muddy2 = new Muddy();
    int i3 = muddy2.getHealth();
    @Test
    public void TestDoubleDamageWtihRe() {
        muddy2.takeDamage();
        muddy2.takeDamage();
        assertEquals(0, muddy2.getHealth());
    }

    Muddy muddy3 = new Muddy();
    int i4 = muddy3.getHealth();
    @Test
    public void TestTripleDamageWtihRe() {
        muddy3.takeDamage();
        muddy3.takeDamage();
        muddy3.takeDamage();
        assertEquals(0, muddy3.getHealth());
    }
}
