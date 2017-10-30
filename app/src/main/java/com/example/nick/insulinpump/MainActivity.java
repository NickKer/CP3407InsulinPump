package com.example.nick.insulinpump;

import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    static Context context;
    UserTracker userTracker = UserTracker.getInstance();
    TextClock textClock;
    TextView systemMessage;
    TextView batteryPercentage;
    TextView reservoirLevel;
    TextView doseDelivered;
    //needed for timer class
    Timer timer = Timer.getInstance();
    SystemStatus systemStatus;
    int batLevel;
    Random rand = new Random();
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        systemMessage = (TextView) findViewById(R.id.system_message);
        doseDelivered = (TextView) findViewById(R.id.dose_delivered);
        batteryPercentage = (TextView) findViewById(R.id.battery_percentage);
        reservoirLevel = (TextView) findViewById(R.id.reservoir_level);
        textClock = (TextClock) findViewById(R.id.textClock);
        BatteryManager bm = (BatteryManager) getSystemService(BATTERY_SERVICE);

        batLevel = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);

        batteryPercentage.setText(getString(R.string.battery_percentage, String.format("%s", batLevel)));

        generateBloodSugar();
        simulatePump();

    }

    public void simulatePump() {
        Runnable r = new Runnable() {
            public void run() {
                handler.postDelayed(this, 10000);
                batteryPercentage.setText(getString(R.string.battery_percentage, String.format("%s", batLevel)));
                if (userTracker.getCurrentSugarLevel() < 80) {
                    systemStatus = new SystemStatus(ErrorType.SUGAR_LOW, getApplicationContext());
                    systemMessage.setText(systemStatus.systemMessage());
                } else if (userTracker.getCurrentSugarLevel() >= 80 && userTracker.getCurrentSugarLevel() <= 140) {
                    systemStatus = new SystemStatus(ErrorType.SUGAR_OK, getApplicationContext());
                    systemMessage.setText(systemStatus.systemMessage());
                } else if (userTracker.getCurrentSugarLevel() > 140) {
                    systemStatus = new SystemStatus(ErrorType.SUGAR_HIGH, getApplicationContext());
                    systemMessage.setText(systemStatus.systemMessage());
                }
                doseDelivered.setText("Last Dose: " + userTracker.getInsulinDeliveryTimestamp() + " " + userTracker.getPreviousInsulinDose() + " units ");
                reservoirLevel.setText(getString(R.string.reservoir_level_notif, String.format("%s", userTracker.getReservoirLevel())));
                if (Integer.parseInt(userTracker.getReservoirLevel()) < 50) {
                    systemStatus = new SystemStatus(ErrorType.INSULIN_RESERVOIR_LOW, getApplicationContext());
                    systemMessage.setText(systemStatus.systemMessage());
                }
                if (batLevel <= 15) {
                    systemStatus = new SystemStatus(ErrorType.LOW_BATTERY, getApplicationContext());
                    systemMessage.setText(systemStatus.systemMessage());
                }
            }
        };
        handler.postDelayed(r, 10000);
    }

    public void generateBloodSugar() {
        Runnable r = new Runnable() {
            public void run() {
                handler.postDelayed(this, 600000);
                userTracker.setCurrentSugarLevel(rand.nextInt(250 - 50) + 50);
            }
        };
        handler.postDelayed(r, 600000);
    }

    public void goToManualMode(View view) {
        Intent intent = new Intent(this, SecondaryActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
