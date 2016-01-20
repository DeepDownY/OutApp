package com.example.yang.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per);
        Intent Rec = this.getIntent();
        String id = Rec.getStringExtra("id");
        TextView _tt = (TextView)findViewById(R.id.textView2);
        _tt.setText(id);
    }
}
