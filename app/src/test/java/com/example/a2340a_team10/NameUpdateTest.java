package com.example.a2340a_team10;
import com.example.a2340a_team10.model.Player;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class NameUpdateTest {
    private Player p;
    @Before
    public void setUp(){
        p = Player.getPlayer();
        p.setName("Old");
    }
    @Test
    public void nameIsUpdatedCorrectly(){
        String newName = "New";
        p.setName(newName);
        assertEquals(newName, p.getName());
    }
}