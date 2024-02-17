package com.example.a2340a_team10.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.a2340a_team10.R;
import com.example.a2340a_team10.model.*;
import com.example.a2340a_team10.viewmodel.EnemyMove;
import com.example.a2340a_team10.viewmodel.PlayerView;
import com.example.a2340a_team10.model.Obstacle;
import java.util.Arrays;


public class GameScreen extends AppCompatActivity {
    private Player hero;
    private PlayerView playerView;
    private ImageView avatar;
    private ImageView door;
    private PowerUp redFlask;
    private ImageView redFlaskImage;
    private boolean redCheck;
    private TextView playerNameTextView;
    private TextView chosenDifficulty;
    private MoveKeyActionFactory moveKeyActionFactory = new MoveKeyActionFactory();
    private Obstacle obstacle1 = new Obstacle(360, 0, 400, 330);
    private Obstacle obstacle2 = new Obstacle(2200, 0, 400, 330);
    private ScreenSetup screenSetup = new ScreenSetup(Arrays.asList(obstacle1, obstacle2));
    private ImageView orc;
    private ImageView zombie;
    private ImageView coin;
    private Enemy orcEnemy;
    private Enemy zombieEnemy;
    private EnemyMove orcMove;
    private EnemyMove zombieMove;
    private int orcIP = 1;
    private int zombieIP = 1;

    private ImageView longRangeWeapon;

    private static final int WEAPON_OFFSET_X = 50;
    private static final int WEAPON_OFFSET_Y = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Player.getPlayer().removeAllObservers();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);

        ImageView backgroundImage = findViewById(R.id.backgroundImage);
        RelativeLayout gridView = findViewById(R.id.gridLayout);

        door = findViewById(R.id.door);
        redFlask = new RedFlask();
        redFlaskImage = findViewById(R.id.redFlask);
        redCheck = true;

        screenSetup.setScreenWidth(getResources().getDisplayMetrics().widthPixels);
        screenSetup.setScreenHeight(getResources().getDisplayMetrics().heightPixels);

        // Calculate the number of grid lines you want horizontally and vertically
        int numHorizontalLines = 5; // Change this to the desired number
        int numVerticalLines = 5;   // Change this to the desired number

        // Calculate the width and height of each grid cell
        int cellWidth = backgroundImage.getWidth() / numHorizontalLines;
        int cellHeight = backgroundImage.getHeight() / numVerticalLines;

        // Draw horizontal grid lines
        for (int i = 1; i < numHorizontalLines; i++) {
            View line = new View(this);
            line.setBackgroundColor(Color.BLACK); // Change the color as needed
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = 0;
            params.topMargin = i * cellHeight;
            line.setLayoutParams(params);
            gridView.addView(line);
        }

        // Draw vertical grid lines
        for (int i = 1; i < numVerticalLines; i++) {
            View line = new View(this);
            line.setBackgroundColor(Color.BLACK); // Change the color as needed
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    1, RelativeLayout.LayoutParams.MATCH_PARENT);
            params.leftMargin = i * cellWidth;
            params.topMargin = 0;
            line.setLayoutParams(params);
            gridView.addView(line);
        }

        hero = Player.getPlayer();
        playerView = new ViewModelProvider(this).get(PlayerView.class);
        TextView scoreTextView = findViewById(R.id.scoreTextView);
        // Observe the scoreLiveData to update the score in real-time
        playerView.getScoreLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer score) {
                // Update the score TextView
                scoreTextView.setText("Score: " + score);
            }
        });


        // Display player name
        playerNameTextView = findViewById(R.id.playerNameTextView);
        playerNameTextView.setText(String.format("Name: %s", hero.getName()));

        // Display difficulty
        chosenDifficulty = findViewById(R.id.difficultyTextView);
        chosenDifficulty.setText(String.format("Difficulty: %s", hero.getDifficulty()));


        // Get or display Player
        avatar = findViewById(R.id.avatarImage);
        avatar.setBackgroundResource(hero.getCharacterChoice());
        AnimationDrawable idleAvatar = (AnimationDrawable) avatar.getBackground();
        idleAvatar.start();

        coin = findViewById(R.id.coin);
        AnimationDrawable coinA = (AnimationDrawable) coin.getBackground();
        coinA.start();

        orc = findViewById(R.id.orc);
        AnimationDrawable idleImp = (AnimationDrawable) orc.getBackground();
        idleImp.start();
        int[] orcP = new int[2];
        orc.getLocationOnScreen(orcP);
        orcMove = new EnemyMove(orcP);


        zombie = findViewById(R.id.zombie);
        AnimationDrawable idleMuddy = (AnimationDrawable) zombie.getBackground();
        idleMuddy.start();
        int[] zomP = new int[2];
        zombie.getLocationOnScreen(zomP);
        zombieMove = new EnemyMove(zomP);

        EnemyFactory orcFactory = new OrcFactory();
        EnemyFactory zombieFactory = new ZombieFactory();
        orcEnemy = orcFactory.spawnEnemy();
        Player.getPlayer().addObserver(orcEnemy);
        zombieEnemy = zombieFactory.spawnEnemy();
        Player.getPlayer().addObserver(zombieEnemy);

        longRangeWeapon = findViewById(R.id.longWeapon);

        int[] location = new int[2];
        avatar.getLocationOnScreen(location);
        playerView.setPos(location); // x, y coordinate

        // Display starting health
        LinearLayout health = findViewById(R.id.healthShow);
        health.setVisibility(View.VISIBLE);
        for (int i = 0; i < hero.getHealth(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.ui_heart_full);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            health.addView(imageView);
        }

        // Handle navigation to the ending screen (temporary button)
        Button nextScreenButton = findViewById(R.id.endGameButton);
        nextScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the ending screen (replace with actual navigation code)
                Intent intent = new Intent(GameScreen.this, EndingScreen.class);
                startActivity(intent);
                finish();
            }
        });


    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (orcIP == 1) {
            orc.setX(2000);
            orc.setY(1050);
            int[] orcP = new int[2];
            orc.getLocationOnScreen(orcP);
            orcMove = new EnemyMove(orcP);
            orcIP = 0;
        }
        if (zombieIP == 1) {
            zombie.setX(1000);
            zombie.setY(1050);
            int[] zomP = new int[2];
            zombie.getLocationOnScreen(zomP);
            zombieMove = new EnemyMove(zomP);
            zombieIP = 0;
        }

        KeyAction keyAction = moveKeyActionFactory.createKeyAction(keyCode);
        if (keyAction != null) {
            playerView.movePlayer(screenSetup, keyAction);
            avatar.setX(playerView.getPos()[0]);
            avatar.setY(playerView.getPos()[1]);
            updateWeaponPosition(playerView.getPos()[0], playerView.getPos()[1]);
        }

        if (playerView.jump(playerView.getPos()[0], playerView.getPos()[1], 1)) {
            Intent intent = new Intent(GameScreen.this, SecondRoom.class);
            startActivity(intent);
            finish();
        }

        orcMove.displayMove(orc, orcEnemy);
        zombieMove.displayMove(zombie, zombieEnemy);

        hero.updatePosition(playerView.getPos()[0], playerView.getPos()[1], true);

        if (redFlask.collectPowerUp() && redCheck) {
            redCheck = false;
            redFlaskImage.setVisibility(View.INVISIBLE);
            hero.setHealth(hero.getHealth() + 2);
        }

        // Display updated health
        LinearLayout health = findViewById(R.id.healthShow);
        health.setVisibility(View.VISIBLE);
        health.removeAllViews();
        for (int i = 0; i < hero.getHealth(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.ui_heart_full);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            health.addView(imageView);
        }

        if (hero.getHealth() == 0) {
            Intent intent = new Intent(GameScreen.this, EndingScreen.class);
            startActivity(intent);
        }



        int[] coinPos = new int[2];
        coin.getLocationOnScreen(coinPos);
        int offsetY = 130;
        int coinX = coinPos[0];
        int coinY = coinPos[1] - offsetY;

        int playerX = playerView.getPos()[0];
        int playerY = playerView.getPos()[1];

        if (playerView.isTouchingCoin(playerX, playerY, coinX, coinY)) {
            if (coin.getVisibility() == View.VISIBLE) {
                playerView.increaseScore(50);
            }
            coin.setVisibility(View.GONE);
        }

        if (keyCode == KeyEvent.KEYCODE_L) {
            performAttack();
            longRangeWeapon.animate().setDuration(300);
            longRangeWeapon.animate().setDuration(300);
            if (longRangeWeapon.getRotation() < 90) {
                longRangeWeapon.animate().rotationBy(180);
            } else {
                longRangeWeapon.animate().rotationBy(-180);
            }
        }

        return super.onKeyDown(keyCode, event);
    }

    private void performAttack() {
        if (isEnemyInRange(orcEnemy)) {
            orcEnemy.takeDamage();
        }
        if (isEnemyInRange(zombieEnemy)) {
            zombieEnemy.takeDamage();
        }
    }

    private boolean isEnemyInRange(Enemy enemy) {
        int attackRange = 300;
        int dx = enemy.getPosX() - playerView.getPos()[0];
        int dy = enemy.getPosY() - playerView.getPos()[1];
        return dx * dx + dy * dy <= attackRange * attackRange;
    }

    private void updateWeaponPosition(int playerPosX, int playerPosY) {
        longRangeWeapon.setX(playerPosX + WEAPON_OFFSET_X);
        longRangeWeapon.setY(playerPosY + WEAPON_OFFSET_Y);
    }

    private Handler gameUpdateHandler = new Handler();
    private Runnable gameUpdateRunnable = new Runnable() {
        @Override
        public void run() {
            gameLogicUpdate();
            gameUpdateHandler.postDelayed(this, 300);
        }
    };

    private void gameLogicUpdate() {
        if (orcEnemy.getHealth() <= 0) {
            handleEnemyDeath(orcEnemy, orc);
        }
        if (zombieEnemy.getHealth() <= 0) {
            handleEnemyDeath(zombieEnemy, zombie);
        }
    }

    private void handleEnemyDeath(Enemy enemy, ImageView enemyImageView) {
        if (enemyImageView.getVisibility() != View.GONE) {
            enemyImageView.setVisibility(View.GONE);
        }
        Player.getPlayer().removeObserver(enemy);
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameUpdateHandler.post(gameUpdateRunnable); // Start the game loop
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameUpdateHandler.removeCallbacks(gameUpdateRunnable); // Stop the game loop
    }
}