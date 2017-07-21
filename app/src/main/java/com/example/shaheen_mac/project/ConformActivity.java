package com.example.shaheen_mac.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import projtetst.shaheen.com.pro.R;

public class ConformActivity extends AppCompatActivity {
ImageButton continueS;
    sqlite mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conform);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        continueS=(ImageButton)findViewById(R.id.imageButton7);
mydb=new sqlite(this);

        mydb.cleardata();
        continueS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(getApplicationContext(),dispctivity.class));




            }
        });



    }

}
