package com.example.a2340a_team10;
import com.example.a2340a_team10.model.Player;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class NameValidationTests {
    private Player player;
    @Before
    public void setUp(){
        player = Player.getPlayer();
    }

    @Test
    public void testBlankOrNullName(){
        player.setName("");
        assertEquals("Invalid name.", validateName(player.getName()));
    }

    @Test
    public void testNameWithOnlyWhitespace(){
        player.setName("   ");
        assertEquals("Invalid name.", validateName(player.getName()));
    }

    private String validateName(String name) {
        name = name.toString().trim();
        if (name.isEmpty()){
            return "Invalid name.";
        }
        return "Valid name.";
    }
}
