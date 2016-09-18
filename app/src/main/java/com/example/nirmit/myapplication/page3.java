package com.example.nirmit.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class page3 extends AppCompatActivity {
    Button line,bar,scatter,pie,radar,plot,logout;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);
        Intent out2=getIntent();
        bar=(Button)findViewById(R.id.bar);
        bar.setBackgroundColor(Color.rgb(106,239,249));
        line=(Button)findViewById(R.id.line);
        line.setBackgroundColor(Color.rgb(82,249,82));
        pie=(Button)findViewById(R.id.pie);
        pie.setBackgroundColor(Color.rgb(250,152,4));
        scatter=(Button)findViewById(R.id.scatter);
        scatter.setBackgroundColor(Color.rgb(126,13,144));
        radar=(Button)findViewById(R.id.radar);
        radar.setBackgroundColor(Color.rgb(253,47,15));
        logout=(Button)findViewById(R.id.logout);
        logout.setBackgroundColor(Color.rgb(202,216,13));
        mAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()==null)
                {
                    startActivity(new Intent(page3.this,MainActivity.class));
                }
            }
        };
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
            }
        });
        bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent("bar");
                startActivity(in);
            }
        });

        line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent("line");
                startActivity(in);
            }
        });

        pie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent("pie");
                startActivity(in);
            }
        });

        radar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent("radar");
                startActivity(in);
            }
        });

        scatter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent("scatter");
                startActivity(in);
            }
        });


    }

    protected void onStart()
    {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }
}
