package com.example.ikoi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    CardView pindahHalMonioring;
    CardView pindahHalControling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pindahHalMonioring = findViewById(R.id.widget1);
        pindahHalControling = findViewById(R.id.widget2);

        pindahHalMonioring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MonitoringActivity.class);
                startActivity(i);
            }
        });

        pindahHalControling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ControlingActivity.class);
                startActivity(i);
            }
        });
    }
}
