package com.demoncube.ninjaadventure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity {

    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
        Intent intentGoToGame = new Intent(this, GameActivity.class);
        View.OnClickListener tlacitkaListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intentGoToGame);
            }
        };

        btnStart.setOnClickListener(tlacitkaListener);
    }

    @Override
    protected void onResume() {
        setUiFlags();
        super.onResume();
    }
}