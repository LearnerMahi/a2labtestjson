package com.example.a2_lab10_test_json;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HobbiesActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hobbies);

        textView = findViewById(R.id.textView);
        Intent intent= getIntent();
        String test = intent.getStringExtra("hobbie");
        textView.setText(test);

    }
}