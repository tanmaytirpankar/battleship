package com.example.tanmay.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class GameActivity extends AppCompatActivity {
    GridStatus status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        final GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
        final ImageAdapter imgadapter= (ImageAdapter) gridview.getAdapter();
        final int[] score = {25};
        final int[] token = new int[1];
        final int[] shipPos2=new int[5];
        final DatabaseReference initplayer=FirebaseDatabase.getInstance().getReference();
        final DatabaseReference player1turn=FirebaseDatabase.getInstance().getReference("player1smoves");
        final DatabaseReference player2turn=FirebaseDatabase.getInstance().getReference("player2smoves");

        final int[] shipPos1=getIntent().getIntArrayExtra("shipPos");
        final int playerno=getIntent().getIntExtra("playerno",3);
        if(playerno==1){
        initplayer.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                shipPos2[0]=dataSnapshot.child("init2").child("ship1").getValue(int.class);
                shipPos2[1]=dataSnapshot.child("init2").child("ship2").getValue(int.class);
                shipPos2[2]=dataSnapshot.child("init2").child("ship3").getValue(int.class);
                shipPos2[3]=dataSnapshot.child("init2").child("ship4").getValue(int.class);
                shipPos2[4]=dataSnapshot.child("init2").child("ship5").getValue(int.class);
                status=new GridStatus(shipPos1,shipPos2);
                token[0] =1;
                Toast.makeText(GameActivity.this, "Your turn.", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        }
        else
        {
            initplayer.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    shipPos2[0]=dataSnapshot.child("init1").child("ship1").getValue(int.class);
                    shipPos2[1]=dataSnapshot.child("init1").child("ship2").getValue(int.class);
                    shipPos2[2]=dataSnapshot.child("init1").child("ship3").getValue(int.class);
                    shipPos2[3]=dataSnapshot.child("init1").child("ship4").getValue(int.class);
                    shipPos2[4]=dataSnapshot.child("init1").child("ship5").getValue(int.class);
                    for (int i = 0; i < 5; i++) {
                        imgadapter.changeToShip(shipPos1[i]);
                    }
                    status=new GridStatus(shipPos1,shipPos2);
                    token[0]=0;
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (token[0]==1){
                if (status.otherplayer[position]==-1)
                    {
                        status.otherplayer[position]=1;
                        token[0]=0;
                        score[0]--;
                        if (status.hisships[0]==position || status.hisships[1]==position || status.hisships[2]==position || status.hisships[3]==position || status.hisships[4]==position) {
                            imgadapter.changeToHit(position);
                            //Try adding a delay here.
                            status.destroyhisship(position);
                            score[0]=score[0]+2;
                        }
                        else
                            imgadapter.changeToMiss(position);
                        if(playerno==1)
                            initplayer.child("player1smoves").setValue(position);
                        else
                            initplayer.child("player2smoves").setValue(position);
                        if (status.checkforwin(status.hisdestroyedships))
                        {
                            Intent nextActivity = new Intent(GameActivity.this, GameEnd.class);
                            int yourstatus=1;
                            nextActivity.putExtra("yourstatus",yourstatus);
                            nextActivity.putExtra("score",score[0]);
                            finish();
                            startActivity(nextActivity);
                        }
                        else
                            imgadapter.histurn(status.myships,status.mydestroyedships,status.thisplayer);
                    }
                }
            }
        });
        if(playerno==2) {
            player1turn.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    int position = dataSnapshot.getValue(int.class);
                    if(status!=null) {
                        Toast.makeText(GameActivity.this, "Your turn.", Toast.LENGTH_SHORT).show();
                        if (status.thisplayer[position] == -1) {
                            status.thisplayer[position] = 1;
                            token[0]=1;
                            if (status.myships[0] == position || status.myships[1] == position || status.myships[2] == position || status.myships[3] == position || status.myships[4] == position) {
                                status.destroymyship(position);
                            }
                            if (status.checkforlose(status.mydestroyedships))
                            {
                                Intent nextActivity = new Intent(GameActivity.this, GameEnd.class);
                                int yourstatus=0;
                                nextActivity.putExtra("yourstatus",yourstatus);
                                nextActivity.putExtra("score",score[0]);
                                finish();
                                startActivity(nextActivity);
                            }
                            else
                                imgadapter.myturn(status.hisships, status.hisdestroyedships, status.otherplayer);
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        if(playerno==1){
            player2turn.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    int position = dataSnapshot.getValue(int.class);
                    if (status != null)
                    {
                        Toast.makeText(GameActivity.this, "Your turn.", Toast.LENGTH_SHORT).show();
                        if (status.thisplayer[position] == -1) {
                            status.thisplayer[position] = 1;
                            token[0]=1;
                            if (status.myships[0] == position || status.myships[1] == position || status.myships[2] == position || status.myships[3] == position || status.myships[4] == position) {
                                status.destroymyship(position);
                            }
                            if (status.checkforlose(status.mydestroyedships))
                            {
                                Intent nextActivity = new Intent(GameActivity.this, GameEnd.class);
                                int yourstatus=0;
                                nextActivity.putExtra("yourstatus",yourstatus);
                                nextActivity.putExtra("score",score[0]);
                                finish();
                                startActivity(nextActivity);
                            }
                            else
                                imgadapter.myturn(status.hisships, status.hisdestroyedships, status.otherplayer);                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
}
