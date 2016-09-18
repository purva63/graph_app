package com.example.nirmit.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.firebase.client.Firebase;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class Bar extends AppCompatActivity {
    BarChart barchart;
    ArrayList<BarEntry> barentriesy;
    ArrayList<String> barentriesx;
    int[] values;
    String[] labels;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);
        Intent out=getIntent();
        values =new int[30];
        i=0;
        labels=new String[30];
        //y-aixs data
        barentriesy=new ArrayList<>();
        //x-aixs data of type dateffs
        barentriesx=new ArrayList<>();
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
                barentriesy.add(new BarEntry(values[i],i));
                barentriesx.add(labels[i]);
                Log.d("tag",values[i]+"   "+labels[i] );
                Log.d("tag1",barentriesx.get(i));
                Log.d("tag1",barentriesy.get(i)+"");
                //set data and datay is just a key
                BarDataSet barDataSet1=new BarDataSet(barentriesy,"datay");
                //parameters as x-axis data then y-axis data
                BarData bardata=new BarData(barentriesx,barDataSet1);
                barDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);

                barchart.setData(bardata);
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
        barchart=(BarChart)findViewById(R.id.barchart1);
        Start();
        Log.d("bu",i+"");

    }

    public void Start()
    {

        barchart.setBackgroundColor(Color.WHITE);
        barchart.setTouchEnabled(true);
        barchart.setDragEnabled(true);
        barchart.setScaleEnabled(true);
        Log.d("end",i+"");
    }
}
