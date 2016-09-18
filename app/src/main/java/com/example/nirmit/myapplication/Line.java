package com.example.nirmit.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class Line extends AppCompatActivity {
    LineChart Linechart;
    ArrayList<Entry> Lineentriesy;
    ArrayList<String> Lineentriesx;
    int[] values;
    String[] labels;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line);
        Intent out=getIntent();
        values =new int[30];
        i=0;
        labels=new String[30];
        //y-aixs data
        Lineentriesy=new ArrayList<>();
        //x-aixs data of type dateffs
        Lineentriesx=new ArrayList<>();
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
                Lineentriesy.add(new Entry(values[i],i));
                Lineentriesx.add(labels[i]);
                Log.d("tag",values[i]+"   "+labels[i] );
                Log.d("tag1",Lineentriesx.get(i));
                Log.d("tag1",Lineentriesy.get(i)+"");
                //set data and datay is just a key
                LineDataSet LineDataSet1=new LineDataSet(Lineentriesy,"datay");
                //parameters as x-axis data then y-axis data
                LineData Linedata=new LineData(Lineentriesx,LineDataSet1);
                LineDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);

                Linechart.setData(Linedata);
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
        Linechart=(LineChart)findViewById(R.id.Linechart1);
        Start();
        Log.d("bu",i+"");

    }

    public void Start()
    {

        Linechart.setBackgroundColor(Color.WHITE);
        Linechart.setTouchEnabled(true);
        Linechart.setDragEnabled(true);
        Linechart.setScaleEnabled(true);
        Log.d("end",i+"");
    }
}
