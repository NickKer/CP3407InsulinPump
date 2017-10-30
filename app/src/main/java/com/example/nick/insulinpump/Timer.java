package com.example.nick.insulinpump;

import android.os.Handler;

// this class runs insulin calculator every 10 minutes

class Timer {
    private static Timer instance = null;
    private Handler handler = new Handler();
    private InsulinCalculator insulinCalculator = new InsulinCalculator();

    private Timer() {
        Runnable r = new Runnable() {
            public void run() {
                handler.postDelayed(this, 600000);
                insulinCalculator.calculateInsulin();
            }
        };

        handler.postDelayed(r, 600000);
    }

    static Timer getInstance() {
        if (instance == null) {
            instance = new Timer();
        }
        return instance;
    }
}
