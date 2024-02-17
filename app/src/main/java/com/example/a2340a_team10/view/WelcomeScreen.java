package com.example.a2340a_team10.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.a2340a_team10.R;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        Button startButton = (Button) findViewById(R.id.startButton);
        Button endButton = (Button) findViewById(R.id.endButton);

        startButton.setOnClickListener(v -> {
            Intent game = new Intent(WelcomeScreen.this, InitialConfiguration.class);
            startActivity(game);
            finish();
        });

        endButton.setOnClickListener(v -> {

            finish();
        });

    }
}