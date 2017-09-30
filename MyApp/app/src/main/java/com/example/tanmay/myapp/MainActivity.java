package com.example.tanmay.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {
    int[] shipspositions=new int[5];

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final int[] playerno = {1};
        final ImageView img1= (ImageView) findViewById(R.id.img1);
        final ImageView img2= (ImageView) findViewById(R.id.img2);
        final ImageView img3= (ImageView) findViewById(R.id.img3);
        final ImageView img4= (ImageView) findViewById(R.id.img4);
        final ImageView img5= (ImageView) findViewById(R.id.img5);
        img1.setImageResource(R.drawable.ship);
        img2.setImageResource(R.drawable.ship);
        img3.setImageResource(R.drawable.ship);
        img4.setImageResource(R.drawable.ship);
        img5.setImageResource(R.drawable.ship);
        final GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
        final Button button= (Button) findViewById(R.id.nextb);
        final DatabaseReference gameData = FirebaseDatabase.getInstance().getReference();

        gameData.child("playerno").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int temp=dataSnapshot.getValue(int.class);
                if (temp==1) {
                    playerno[0] = 2;
                    gameData.child("playerno").setValue(playerno[0]);
                    Toast.makeText(MainActivity.this,"You are player "+playerno[0],Toast.LENGTH_SHORT).show();
                }
                else if (temp==2){
                    gameData.child("playerno").setValue(playerno[0]);
                    playerno[0] = 1;
                    Toast.makeText(MainActivity.this,"You are player "+playerno[0],Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        Toast.makeText(MainActivity.this,""+playerno[0],Toast.LENGTH_SHORT).show();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameData.child("playerno").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int temp =dataSnapshot.getValue(int.class);
                        if(temp==2) {
                            Intent nextActivity = new Intent(MainActivity.this, GameActivity.class);
                            nextActivity.putExtra("shipPos", shipspositions);
                            nextActivity.putExtra("playerno", playerno);
                            startActivity(nextActivity);
                        }
                        else {
                            Toast.makeText(MainActivity.this,"Waiting for another player",Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(MainActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();
                ImageAdapter imgadapter= (ImageAdapter) gridview.getAdapter();
                imgadapter.changeToShip(position);
                gridview.setAdapter(imgadapter);
                if(imgadapter.count==1) {
                    img1.setImageResource(R.drawable.blank);
                    shipspositions[0]=position;
                }
                else if(imgadapter.count==2) {
                    img2.setImageResource(R.drawable.blank);
                    shipspositions[1]=position;
                }
                else if(imgadapter.count==3) {
                    img3.setImageResource(R.drawable.blank);
                    shipspositions[2]=position;
                }
                else if(imgadapter.count==4) {
                    img4.setImageResource(R.drawable.blank);
                    shipspositions[3]=position;
                }
                else if(imgadapter.count==5) {
                    img5.setImageResource(R.drawable.blank);
                    shipspositions[4]=position;
                }
            }
        });
    }

}
