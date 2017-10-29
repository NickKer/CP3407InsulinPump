package com.example.nick.insulinpump;

import android.content.Intent;
import android.os.BatteryManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import java.util.Locale;

public class SecondaryActivity extends AppCompatActivity implements View.OnClickListener {
    UserTracker userTracker = UserTracker.getInstance();
    InsulinNotificationManager insulinNM;
    TextClock textClock;
    TextView systemMessage;
    TextView batteryPercentage;
    //needed for timer class
    Timer timer = Timer.getInstance();
    TextView reservoirLevel;
    EditText sugarLevelInput;
    TextView doseDelivered;
    Button submitBloodSugarLevel;
    Handler handler = new Handler();
    SystemStatus systemStatus;
    int batLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        sugarLevelInput = (EditText) findViewById(R.id.sugar_level_input);
        doseDelivered = (TextView) findViewById(R.id.dose_delivered);
        submitBloodSugarLevel = (Button) findViewById(R.id.submit_blood_sugar_level);
        submitBloodSugarLevel.setOnClickListener(this);
        textClock = (TextClock) findViewById(R.id.textClock);
        reservoirLevel = (TextView) findViewById(R.id.reservoir_level);
        systemMessage = (TextView) findViewById(R.id.system_message);
        batteryPercentage = (TextView) findViewById(R.id.battery_percentage);
        BatteryManager bm = (BatteryManager)getSystemService(BATTERY_SERVICE);
        batLevel = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);

        batteryPercentage.setText(getString(R.string.battery_percentage, String.format("%s", batLevel)));

        // changes notification every 10 seconds based on sugar level.
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
                doseDelivered.setText("Last Dose: " + textClock.getText() + " " + userTracker.getPreviousInsulinDose() + " units ");
                reservoirLevel.setText(userTracker.getReservoirLevel());
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

    public void goToAutoMode(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        navigateUpTo(intent);
        finish();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit_blood_sugar_level:
                userTracker.setCurrentSugarLevel(Double.parseDouble(sugarLevelInput.getText().toString()));
                break;
        }
    }
}
