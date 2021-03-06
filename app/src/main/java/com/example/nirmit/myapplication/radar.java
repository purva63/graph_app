package com.example.nirmit.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class radar extends AppCompatActivity {
    RadarChart Radarchart;
    ArrayList<Entry> Radarentriesy;
    ArrayList<String> Radarentriesx;
    int[] values;
    String[] labels;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar);
        Intent out=getIntent();
        values =new int[30];
        i=0;
        labels=new String[30];
        //y-aixs data
        Radarentriesy=new ArrayList<>();
        //x-aixs data of type dateffs
        Radarentriesx=new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        DatabaseReference Root= myRef.child("prem-21727");
        Query query =myRef.orderByKey();

        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                String s1=dataSnapshot.getKey();

                int s3=Integer.parseInt(dataSnapshot.getValue()+"");

                values[i]=s3;
                labels[i]=s1;
                Radarentriesy.add(new Entry(values[i],i));
                Radarentriesx.add(labels[i]);
                Log.d("tag",values[i]+"   "+labels[i] );
                Log.d("tag1",Radarentriesx.get(i));
                Log.d("tag1",Radarentriesy.get(i)+"");
                //set data and datay is just a key
                RadarDataSet RadarDataSet1=new RadarDataSet(Radarentriesy,"datay");
                //parameters as x-axis data then y-axis data
                RadarData Radardata=new RadarData(Radarentriesx,RadarDataSet1);
                RadarDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);

                Radarchart.setData(Radardata);
                i++;
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {Log.d("blah",i+"");}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
        Log.d("bo",i+"");
        Radarchart=(RadarChart)findViewById(R.id.Radarchart1);
        Radarchart.setBackgroundColor(Color.WHITE);
        Start();
        Log.d("bu",i+"");

    }

    public void Start()
    {
        Radarchart.setTouchEnabled(true);
        Log.d("end",i+"");
    }
}
