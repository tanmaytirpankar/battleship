package com.example.tanmay.myapp;

/**
 * Created by tanma on 9/29/2017.
 */

import android.content.Intent;
import android.os.Process;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class GameEnd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameend);
        int yourstatus=getIntent().getIntExtra("yourstatus",3);
        int score=getIntent().getIntExtra("score",15);
        ConstraintLayout layout=(ConstraintLayout) findViewById(R.id.layout1);
        EditText txt1=(EditText)findViewById(R.id.editText2);
        EditText txt=(EditText)findViewById(R.id.editText);
        Button btn=(Button)findViewById(R.id.button4);
        txt1.setText("Your score is "+score);
        if(yourstatus==1)
        {
            layout.setBackgroundResource(R.drawable.victory);
            String endstatus="You Win";
            txt.setText(endstatus);
        }
        else
        {
            layout.setBackgroundResource(R.drawable.lost);
            String endstatus="You Lost";
            txt.setText(endstatus);
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Process.killProcess(Process.myPid());
            }
        });
    }
}
