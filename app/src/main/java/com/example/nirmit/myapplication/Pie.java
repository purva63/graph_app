package com.example.nirmit.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class Pie extends AppCompatActivity {
    PieChart Piechart;
    ArrayList<Entry> Pieentriesy;
    ArrayList<String> Pieentriesx;
    int[] values;
    String[] labels;
    int i;
    PieDataSet PieDataSet1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie);
        Intent out=getIntent();
        values =new int[30];
        i=0;
        labels=new String[30];
        //y-aixs data
        Pieentriesy=new ArrayList<>();
        //x-aixs data of type dateffs
        Pieentriesx=new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        DatabaseReference Root= myRef.child("prem-21727");
        Query query =myRef.orderByKey();

        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                String s1=dataSnapshot.getKey();

                int s3=Integer.parseInt(dataSnapshot.getValue()+"");
                int[] c1 = {Color.RED,Color.WHITE,Color.BLUE,Color.GRAY};
                values[i]=s3;
                labels[i]=s1;
                Pieentriesy.add(new Entry(values[i],i));
                Pieentriesx.add(labels[i]);
                Log.d("tag",values[i]+"   "+labels[i] );
                Log.d("tag1",Pieentriesx.get(i));
                Log.d("tag1",Pieentriesy.get(i)+"");
                //set data and datay is just a key
                 PieDataSet1=new PieDataSet(Pieentriesy,"datay");
                //parameters as x-axis data then y-axis data
                PieData Piedata=new PieData(Pieentriesx,PieDataSet1);
                PieDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);
                Piechart.setData(Piedata);
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
        Piechart=(PieChart)findViewById(R.id.Piechart1);
        Piechart.setBackgroundColor(Color.WHITE);
        Piechart.setTouchEnabled(true);
        Log.d("bu",i+"");

    }


}
