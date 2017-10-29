package com.example.nick.insulinpump;

/**
 * Created by Nick on 15-Oct-17.
 */
// this class delivers insulin if it is above a certain threshold, when called.
public class InsulinCalculator {
    UserTracker userTracker = UserTracker.getInstance();
    private int insulinDose;
    private double sugarLevel;

    public void calculateInsulin() {
        sugarLevel = userTracker.getCurrentSugarLevel();
        if (sugarLevel >= 150) {
            while (sugarLevel > 140) {
                insulinDose += 1;
                sugarLevel -= 50;
                userTracker.setCurrentSugarLevel(sugarLevel);
            }
            userTracker.setPreviousInsulinDose(insulinDose);
            userTracker.setReservoirLevel();
            insulinDose = 0;
        }
    }
}

