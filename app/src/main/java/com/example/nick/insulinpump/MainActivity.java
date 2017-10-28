package com.example.nick.insulinpump;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextClock;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView systemMessageTextView;
    TextClock textClock;
    ArrayList<SystemStatus> systemStatuses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        systemMessageTextView = (TextView) findViewById(R.id.system_message);

        simulatePump();
    }

    public void simulatePump(){
        /*We will put code here that simulates an order of events for the pump in auto mode*/

    }

    public void goToManualMode(View view) {
        Intent intent = new Intent(this, SecondaryActivity.class);
        startActivity(intent);
    }
}
