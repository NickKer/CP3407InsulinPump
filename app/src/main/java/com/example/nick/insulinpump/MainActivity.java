package com.example.nick.insulinpump;


import android.content.Intent;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView systemMessageTextView;
    TextClock textClock;
    TextView batteryPercentage;
    //needed for timer class
    Timer timer = Timer.getInstance();
    ArrayList<SystemStatus> systemStatuses;
    int batLevel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        systemMessageTextView = (TextView) findViewById(R.id.system_message);
        batteryPercentage = (TextView) findViewById(R.id.battery_percentage);
        BatteryManager bm = (BatteryManager)getSystemService(BATTERY_SERVICE);
        batLevel = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);

        batteryPercentage.setText(getString(R.string.battery_percentage, String.format("%s", batLevel)));

        simulatePump();
    }

    public void simulatePump() {
        /*We will put code here that simulates an order of events for the pump in auto mode*/

    }

    public void goToManualMode(View view) {
        Intent intent = new Intent(this, SecondaryActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
