package com.example.yang.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent Rec = this.getIntent();

        String RecString = Rec.getStringExtra("id");
        TextView _tt = (TextView)findViewById(R.id.SearchPage);
        String temp = "what this bug is";
        Toast.makeText(getApplicationContext(),temp,Toast.LENGTH_LONG).show();
    }
}
