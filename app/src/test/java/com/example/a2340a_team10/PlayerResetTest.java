package com.example.a2340a_team10;
import com.example.a2340a_team10.model.Player;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerResetTest {

    @Test
    public void playerIsResetAfterClear() {
        Player player = Player.getPlayer();
        player.setName("name");
        player.setHealth(100);
        player.setDifficulty("Easy");
        player.setCharacterChoice(1);
        Player.clear();

        assertNull(Player.getPlayer().getName());
        assertEquals(0, Player.getPlayer().getHealth());
        assertNull(Player.getPlayer().getDifficulty());
        assertEquals(0, Player.getPlayer().getCharacterChoice());
        assertEquals(300, Player.getPlayer().getScore());
        assertEquals(0, Player.getPlayer().getPosX(), 0.0);
        assertEquals(0, Player.getPlayer().getPosY(), 0.0);
    }
}

