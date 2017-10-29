package com.example.nick.insulinpump;

import java.text.SimpleDateFormat;

/**
 * Created by Nick on 24-Sep-17.
 */

public class UserTracker {
    private static UserTracker instance = null;
    private double currentSugarLevel;
    private int reservoirLevel = 300;
    private double sugarLevelBeforeDose;
    private int previousInsulinDose;
    private String insulinDeliveryTimestamp;

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

    public void resetReservoirLevel() {
        reservoirLevel = 300;
    }

    public void setReservoirLevel() {
        reservoirLevel -= previousInsulinDose;
    }

    public String getReservoirLevel() {
        return Integer.toString(reservoirLevel);
    }

    public void setSdf(String insulinDeliveryTimestamp) {
        this.insulinDeliveryTimestamp = insulinDeliveryTimestamp;
    }

    public String getInsulinDeliveryTimestamp() {
        return insulinDeliveryTimestamp;
    }
}
