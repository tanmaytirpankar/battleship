package com.example.tanmay.myapp;

/**
 * Created by tanma on 9/29/2017.
 */

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AltActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);
// for song:
        MediaPlayer mp = MediaPlayer.create(this, R.raw.glorious_morning);
        mp.setLooping(true );
        mp.start();
    }
    public void onPlayClick(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);
    }

    public void onRulesClick(View view) {
        Intent intent = new Intent(this, Rules.class);
        startActivity(intent);
    }
}