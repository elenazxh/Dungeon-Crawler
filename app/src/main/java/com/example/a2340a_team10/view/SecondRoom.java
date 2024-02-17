package com.example.a2340a_team10.view;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.Observer;

import com.example.a2340a_team10.R;
import com.example.a2340a_team10.model.*;
import com.example.a2340a_team10.viewmodel.PlayerView;

public class SecondRoom extends AppCompatActivity {

    private PlayerView playerView; // Declare an instance of GameViewModel
    private Player hero;
    private ImageView door;
    private ImageView avatar;
    private ImageView muddy;
    private ImageView imp;

    private ImageView coin;

    private PowerUp blueFlask;
    private ImageView blueFlaskImage;
    private boolean blueCheck;

    private TextView playerNameTextView;
    private TextView chosenDifficulty;
    private MoveKeyActionFactory moveKeyActionFactory = new MoveKeyActionFactory();
    private ScreenSetup screenSetup = new ScreenSetup();
    private Enemy muddyEnemy;
    private Enemy impEnemy;

    private ImageView longRangeWeapon;

    private static final int WEAPON_OFFSET_X = 50;
    private static final int WEAPON_OFFSET_Y = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Player.getPlayer().removeAllObservers();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_room);

        ImageView backgroundImage = findViewById(R.id.backgroundImage);
        RelativeLayout gridView = findViewById(R.id.gridLayout);
        door = findViewById(R.id.door);

        screenSetup.setScreenWidth(getResources().getDisplayMetrics().widthPixels);
        screenSetup.setScreenHeight(getResources().getDisplayMetrics().heightPixels);

        blueFlask = new BlueFlask();
        blueFlaskImage = findViewById(R.id.blueFlask);
        blueCheck = true;

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
        //chosenDifficulty.setText(String.format("Difficulty: %s", screenHeight));

        // Get or display Player
        avatar = findViewById(R.id.avatarImage);
        avatar.setBackgroundResource(hero.getCharacterChoice());
        AnimationDrawable idleAvatar = (AnimationDrawable) avatar.getBackground();
        idleAvatar.start();

        coin = findViewById(R.id.coin);
        AnimationDrawable coinA = (AnimationDrawable) coin.getBackground();
        coinA.start();

        imp = findViewById(R.id.imp);
        AnimationDrawable idleImp = (AnimationDrawable) imp.getBackground();
        idleImp.start();

        muddy = findViewById(R.id.muddy);
        AnimationDrawable idleMuddy = (AnimationDrawable) muddy.getBackground();
        idleMuddy.start();

        EnemyFactory muddyFactory = new MuddyFactory();
        EnemyFactory impFactory = new ImpFactory();
        muddyEnemy = muddyFactory.spawnEnemy();
        impEnemy = impFactory.spawnEnemy();
        Player.getPlayer().addObserver(muddyEnemy);
        Player.getPlayer().addObserver(impEnemy);

        longRangeWeapon = findViewById(R.id.longWeapon);

        // Get the x and y coordinates of the ImageView
        int[] location = new int[2];
        avatar.getLocationOnScreen(location);

        playerView.setPos(location); //x, y coordinates

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
                Intent intent = new Intent(SecondRoom.this, EndingScreen.class);
                startActivity(intent);
                finish();
            }

        });

    }

    private void enemiesMovement() {

        float playerX = avatar.getX();
        float playerY = avatar.getY();

        float muddyX = muddy.getX();
        float muddyY = muddy.getY();

        float muddySpeed = 6;
        if (muddyX < playerX) {
            muddyX += muddySpeed;
        } else if (muddyX > playerX) {
            muddyX -= muddySpeed;
        }

        if (muddyY < playerY) {
            muddyY += muddySpeed;
        } else if (muddyY > playerY) {
            muddyY -= muddySpeed;
        }
        muddy.setX(muddyX);
        muddy.setY(muddyY);
        muddyEnemy.updatePosition((int) muddyX, (int) muddyY);


        float impX = imp.getX();
        float impY = imp.getY();

        float impSpeed = 8;
        if (impX < playerX) {
            impX += impSpeed;
        } else if (impX > playerX) {
            impX -= impSpeed;
        }

        if (impY < playerY) {
            impY += impSpeed;
        } else if (impY > playerY) {
            impY -= impSpeed;
        }
        imp.setX(impX);
        imp.setY(impY);
        impEnemy.updatePosition((int) impX, (int) impY);
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        KeyAction keyAction = moveKeyActionFactory.createKeyAction(keyCode);
        if (keyAction != null) {
            playerView.movePlayer(screenSetup, keyAction);
            avatar.setX(playerView.getPos()[0]);
            avatar.setY(playerView.getPos()[1]);
            updateWeaponPosition(playerView.getPos()[0], playerView.getPos()[1]);
        }

        if (playerView.jump(playerView.getPos()[0], playerView.getPos()[1], 1)) {
            Intent intent = new Intent(SecondRoom.this, ThirdRoom.class);
            startActivity(intent);
            finish();
        }

        // enemy move toward to player
        enemiesMovement();
        hero.updatePosition(playerView.getPos()[0], playerView.getPos()[1], true);

        if (blueFlask.collectPowerUp() && blueCheck) {
            blueCheck = false;
            blueFlaskImage.setVisibility(View.INVISIBLE);
            int[] location = new int[2];
            location[0] = 2800;
            location[1] = 600;
            hero.updatePosition(playerView.getPos()[0], playerView.getPos()[1], false);
            playerView.setPos(location);
            avatar.setX(playerView.getPos()[0]);
            avatar.setY(playerView.getPos()[1]);
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
            Intent intent = new Intent(SecondRoom.this, EndingScreen.class);
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
        if (isEnemyInRange(muddyEnemy)) {
            muddyEnemy.takeDamage();
        }
        if (isEnemyInRange(impEnemy)) {
            impEnemy.takeDamage();
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
        if (muddyEnemy.getHealth() <= 0) {
            handleEnemyDeath(muddyEnemy, muddy);
        }
        if (impEnemy.getHealth() <= 0) {
            handleEnemyDeath(impEnemy, imp);
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
