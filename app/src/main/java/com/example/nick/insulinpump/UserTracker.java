package com.example.nick.insulinpump;

/**
 * Created by Nick on 24-Sep-17.
 */

public class UserTracker {
    private static UserTracker instance = null;
    double currentSugarLevel;
    int insulinDose;
    double sugarLevelBeforeDose;
    int previousInsulinDose;

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
        System.out.println(sugarLevelBeforeDose);
        return currentSugarLevel;
    }
}
