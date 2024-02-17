package com.example.a2340a_team10.view;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.a2340a_team10.R;
import com.example.a2340a_team10.model.*;

public class InitialConfiguration extends AppCompatActivity {
    private TextView selectedChoiceTextView;
    private EditText inputName;

    private RadioGroup difficultySelect;
    private RadioGroup avatarSelect;
    private Button buttonSubmit;
    private Button startBtn;
    private TextView textViewResult;
    private LinearLayout healthBar;

    private Player hero;
    private String difficulty = "";
    private String myName = "";
    private int choice;
    private int healthCount;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial_configuration);

        startBtn = findViewById(R.id.startButton);

        difficultySelect = findViewById(R.id.difficultyRadioGroup);

        selectedChoiceTextView = findViewById(R.id.selectDifficulty);

        avatarSelect = findViewById(R.id.avatar_select);

        buttonSubmit = findViewById(R.id.buttonSubmit);
        inputName = findViewById(R.id.inputName);
        textViewResult = findViewById(R.id.greeting);

        healthBar = findViewById(R.id.diff_healthbar);

        hero = Player.getPlayer();

        difficultySelect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioEasy) {
                    healthBar.removeAllViews();
                    difficulty = "Easy";
                    displayHealth(5);
                    healthCount = 5;
                } else if (checkedId == R.id.radioMedium) {
                    healthBar.removeAllViews();
                    difficulty = "Medium";
                    displayHealth(4);
                    healthCount = 4;
                } else if (checkedId == R.id.radioHard) {
                    healthBar.removeAllViews();
                    difficulty = "Hard";
                    displayHealth(3);
                    healthCount = 3;
                }
                selectedChoiceTextView.setText(String.format("Difficulty: %s", difficulty));
                hero.setDifficulty(difficulty);
                hero.setHealth(healthCount);
                // hero.setMaxHealth(healthCount);
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myName = inputName.getText().toString().trim();
                hero.setName(myName);

                if (!myName.isEmpty()) {
                    // Name is not empty, display it
                    textViewResult.setText(String.format("Welcome, %s!", hero.getName()));
                } else {
                    // Name is empty or contains only whitespace
                    textViewResult.setText("Invalid name.");
                }
            }
        });

        avatarSelect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.char_f_elf) {
                    choice = R.drawable.female_elf;
                } else if (checkedId == R.id.char_m_elf) {
                    choice = R.drawable.male_elf;
                } else if (checkedId == R.id.char_witch) {
                    choice = R.drawable.witch;
                } else if (checkedId == R.id.char_wizard) {
                    choice = R.drawable.wizard;
                } else {
                    choice = R.drawable.female_elf;
                }
                hero.setCharacterChoice(choice);
                displayAvatar(choice);
            }
        });

        startBtn.setOnClickListener(v -> {
            if (hero.getName() == null) {
                textViewResult.setText("Invalid name.");
            } else if (hero.getHealth() == 0.0f) {
                textViewResult.setText("Please choose difficulty.");
            } else if (hero.getCharacterChoice() == 0) {
                textViewResult.setText("Please choose character.");
            } else {
                Intent game = new Intent(InitialConfiguration.this, GameScreen.class);
                startActivity(game);
                finish();
            }
        });
    }

    private void displayHealth(int count) {
        healthBar.setVisibility(View.VISIBLE);

        for (int i = 0; i < count; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.ui_heart_full);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            healthBar.addView(imageView);
        }
    }

    private void displayAvatar(int i) {
        ImageView imageView = (ImageView) findViewById(R.id.avatar_display);
        imageView.setBackgroundResource(i);
        AnimationDrawable idleAvatar = (AnimationDrawable) imageView.getBackground();
        idleAvatar.start();
    }
}