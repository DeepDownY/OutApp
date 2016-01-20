package com.example.yang.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Intent Rec = this.getIntent();
        String List = Rec.getStringExtra("List");
        TextView _tt = (TextView)findViewById(R.id.textView3);
        _tt.setText(List);
    }
}
