package com.example.nick.insulinpump;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondaryActivity extends AppCompatActivity implements View.OnClickListener {
    UserTracker userTracker = UserTracker.getInstance();
    InsulinCalculator insulinCalculator = new InsulinCalculator();
    EditText sugarLevelInput;
    TextView doseDelivered;
    Button submitBloodSugarLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        sugarLevelInput = (EditText) findViewById(R.id.sugar_level_input);
        doseDelivered = (TextView) findViewById(R.id.dose_delivered);
        submitBloodSugarLevel = (Button) findViewById(R.id.submit_blood_sugar_level);
        submitBloodSugarLevel.setOnClickListener(this);
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
                insulinCalculator.calculateInsulin();
                doseDelivered.setText(userTracker.getPreviousInsulinDose() + " units");
                break;
        }
    }
}
