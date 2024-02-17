package com.example.a2340a_team10.viewmodel;
import android.widget.ImageView;

import com.example.a2340a_team10.model.Enemy;

import java.util.Random;

public class EnemyMove {
    private int count;
    private int posX;
    private int posY;
    private int direction;

    public EnemyMove(int[] pos) {
        this.count = 6;
        this.posX = pos[0];
        this.posY = pos[1];
        this.direction = 0;
    }

    public int[] move() {
        int[] positions = new int[2];
        if (count == 0) {
            count = 6;
            Random random = new Random();
            int randomNumber = random.nextInt(8);
            direction = randomNumber;
        }
        switch (direction) {
        case 1:
            posY += 17;
            posX += 17;
            break;
        case 2:
            posX += 20;
            break;
        case 3:
            posY -= 17;
            posX += 17;
            break;
        case 4:
            posY -= 20;
            break;
        case 5:
            posY -= 17;
            posX -= 17;
            break;
        case 6:
            posX -= 20;
            break;
        case 7:
            posY += 17;
            posX -= 17;
            break;
        default:
            posY += 20;
        }
        if (posX < 20) {
            posX += 300;
        }
        if (posY < 20) {
            posY += 250;
        }
        if (posX > 3000) {
            posX -= 300;
        }
        if (posY > 1100) {
            posY -= 250;
        }
        count -= 1;
        positions[0] = posX;
        positions[1] = posY;
        return positions;
    }

    public void displayMove(ImageView enemyImage, Enemy enemy) {
        int[] pos = move();
        enemy.updatePosition(pos[0], pos[1]);
        enemyImage.setX(pos[0]);
        enemyImage.setY(pos[1]);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
