package com.example.nick.insulinpump;

import android.os.Handler;

import java.util.Random;

/**
 * Created by Nick on 24-Sep-17.
 */

// this class runs insulin calculator every 10 minutes

public class Timer {
    public static Timer instance = null;
    Handler handler = new Handler();
    InsulinCalculator insulinCalculator = new InsulinCalculator();
    public Timer() {
        Runnable r = new Runnable() {
            public void run() {
                handler.postDelayed(this, 600000);
                insulinCalculator.calculateInsulin();
            }
        };

        handler.postDelayed(r, 600000);
    }

    public static Timer getInstance() {
        if (instance == null) {
            instance = new Timer();
        }
        return instance;
    }
}
