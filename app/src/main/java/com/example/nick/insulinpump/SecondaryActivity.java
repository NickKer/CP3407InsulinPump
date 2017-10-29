package com.example.nick.insulinpump;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;

public class SecondaryActivity extends AppCompatActivity implements View.OnClickListener {
    UserTracker userTracker = UserTracker.getInstance();
    InsulinNotificationManager insulinNM;
    TextClock textClock;
    //needed for timer class
    Timer timer = Timer.getInstance();
    TextView reservoirLevel;
    EditText sugarLevelInput;
    TextView doseDelivered;
    Button submitBloodSugarLevel;
    Handler handler = new Handler();

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


        // changes notification every 10 seconds based on sugar level.
        Runnable r = new Runnable() {
            public void run() {
                handler.postDelayed(this, 10000);
                if (userTracker.getCurrentSugarLevel() < 80) {
                    insulinNM = new InsulinNotificationManager(getApplicationContext(), "Sugar Level Low", "You're sugar level is below 100 mg/dL", 001);
                } else if (userTracker.getCurrentSugarLevel() >= 80 && userTracker.getCurrentSugarLevel() <= 140) {
                    insulinNM = new InsulinNotificationManager(getApplicationContext(), "Sugar Level OK", "You're sugar level is within acceptable parameters", 001);
                } else if (userTracker.getCurrentSugarLevel() > 140) {
                    insulinNM = new InsulinNotificationManager(getApplicationContext(), "Sugar Level High", "You're sugar level is above 140mg/dL", 001);
                }
                doseDelivered.setText("Last Dose: " + textClock.getText() + " " + userTracker.getPreviousInsulinDose() + " units ");
                reservoirLevel.setText(userTracker.getReservoirLevel());
                if (Integer.parseInt(userTracker.getReservoirLevel()) < 50) {
                    insulinNM = new InsulinNotificationManager(getApplicationContext(), "Reservoir Low", "You have less than 50 units remaining", 002);
                }
            }
        };

        handler.postDelayed(r, 10000);
    }

    public void goToAutoMode(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        navigateUpTo(intent);
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
