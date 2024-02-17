package com.example.a2340a_team10.model;

import com.example.a2340a_team10.viewmodel.PlayerView;

public class MoveUpAction implements KeyAction {
    @Override
    public int[] performAction(PlayerView playerView) {
        int posX = playerView.getPos()[0];
        int posY = playerView.getPos()[1];
        int[] position = new int[2];
        posY -= 36;
        position[0] = posX;
        position[1] = posY;
        Player.getPlayer().updatePosition(posX, posY, false);
        return position;
    }
}
