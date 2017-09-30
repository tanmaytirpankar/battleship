package com.example.tanmay.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        DatabaseReference getdata1=FirebaseDatabase.getInstance().getReference("initialise1");
        DatabaseReference getdata2=FirebaseDatabase.getInstance().getReference("initialise2");
        final int[] shipPos1=getIntent().getIntArrayExtra("shipPos");
        int[] shipPos2;
        int playerno=getIntent().getIntExtra("playerno",3);
        final GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
        final ImageAdapter imgadapter= (ImageAdapter) gridview.getAdapter();
        Init init=new Init(shipPos1);
        if (playerno==1)
            getdata1.setValue(init);
        else if(playerno==2)
            getdata2.setValue(init);
        getdata1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int temp=dataSnapshot.getValue(int.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        final GridStatus status=new GridStatus(shipPos1,imgadapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(GameActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();
                if (status.otherplayer[position]==-1)
                {
                    status.otherplayer[position]=1;
                    if (status.hisships[0]==position || status.hisships[1]==position || status.hisships[2]==position || status.hisships[3]==position || status.hisships[4]==position) {
                        imgadapter.changeToHit(position);
                    }
                    else
                        {
                        imgadapter.changeToMiss(position);
                    }
                }
            }});
    }
}
