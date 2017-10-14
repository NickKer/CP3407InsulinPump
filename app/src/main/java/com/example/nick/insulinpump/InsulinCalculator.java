package com.example.nick.insulinpump;

import android.content.Context;

/**
 * Created by Nick on 15-Oct-17.
 */

public class InsulinCalculator {
    UserTracker userTracker = UserTracker.getInstance();
    private int insulinDose;
    private double sugarLevel;

    //will be activated every 10 minutes (not yet implemented in Timer class)
    public void calculateInsulin() {
        sugarLevel = userTracker.getCurrentSugarLevel();
        double sugarLevelAboveMax = (sugarLevel - 140);
        if (sugarLevelAboveMax % 50 == 0) {
            while (sugarLevel > 140) {
                insulinDose += 1;
                sugarLevel -= 50;
                userTracker.setCurrentSugarLevel(sugarLevel);
            }
        }
    }
}

