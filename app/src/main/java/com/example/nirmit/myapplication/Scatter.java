package com.example.nirmit.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class Scatter extends AppCompatActivity {
    ScatterChart Scatterchart;
    ArrayList<Entry> Scatterentriesy;
    ArrayList<String> Scatterentriesx;
    int[] values;
    String[] labels;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scatter);
        Intent out=getIntent();
        values =new int[30];
        i=0;
        labels=new String[30];
        //y-aixs data
        Scatterentriesy=new ArrayList<>();
        //x-aixs data of type dateffs
        Scatterentriesx=new ArrayList<>();
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
                Scatterentriesy.add(new Entry(values[i],i));
                Scatterentriesx.add(labels[i]);
                Log.d("tag",values[i]+"   "+labels[i] );
                Log.d("tag1",Scatterentriesx.get(i));
                Log.d("tag1",Scatterentriesy.get(i)+"");
                //set data and datay is just a key
                ScatterDataSet ScatterDataSet1=new ScatterDataSet(Scatterentriesy,"datay");
                //parameters as x-axis data then y-axis data
                ScatterData Scatterdata=new ScatterData(Scatterentriesx,ScatterDataSet1);
                ScatterDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);
                Scatterchart.setData(Scatterdata);
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
        Scatterchart=(ScatterChart)findViewById(R.id.Scatterchart1);
        Scatterchart.setDescription("Description");
        Log.d("bu",i+"");
        Scatterchart.setBackgroundColor(Color.WHITE);
        Scatterchart.setTouchEnabled(true);
        Scatterchart.setDragEnabled(true);
        Scatterchart.setScaleEnabled(true);
        Log.d("end",i+"");
    }


}