package com.example.nick.insulinpump;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

public class SecondaryActivity extends AppCompatActivity {
    UserTracker userTracker = UserTracker.getInstance();
    InsulinCalculator insulinCalculator = new InsulinCalculator();
    EditText sugarLevelInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        sugarLevelInput = (EditText) findViewById(R.id.sugar_level_input);

        sugarLevelInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                userTracker.setCurrentSugarLevel(Double.parseDouble(s.toString()));
            }
        });
    }

    public void goToAutoMode(View view){
        Intent intent = new Intent(this, MainActivity.class);
        navigateUpTo(intent);
    }
}
