package com.example.nick.insulinpump;

/**
 * Created by Nick on 24-Sep-17.
 */

public class UserTracker {
    private static UserTracker instance = null;
    private double currentSugarLevel;
    private double sugarLevelBeforeDose;
    private int previousInsulinDose;

    public static UserTracker getInstance() {
        if (instance == null) {
            instance = new UserTracker();
        }
        return instance;
    }

    public void setCurrentSugarLevel(double sugarLevel) {
        currentSugarLevel = sugarLevel;
    }

    public double getCurrentSugarLevel() {
        sugarLevelBeforeDose = currentSugarLevel;
        return currentSugarLevel;
    }

    public void setPreviousInsulinDose(int insulinDose) {
        this.previousInsulinDose = insulinDose;
    }

    public int getPreviousInsulinDose() {
        return previousInsulinDose;
    }
}
