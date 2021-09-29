package com.example.eplanettask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.eplanettask.FireBaseNotification.SendNotifiActi;
import com.example.eplanettask.alram.AlaramDataAct;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onAlram(View view) {
        Intent intent = new Intent(MainActivity.this, AlaramDataAct.class);
        startActivity(intent);
    }

    public void onfirebase(View view) {
        Intent intent = new Intent(MainActivity.this, SendNotifiActi.class);
        startActivity(intent);
    }
}