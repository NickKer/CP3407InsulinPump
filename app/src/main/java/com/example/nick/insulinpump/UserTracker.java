package com.example.nick.insulinpump;


class UserTracker {
    private static UserTracker instance = null;
    private double currentSugarLevel;
    private int reservoirLevel = 300;
    private double sugarLevelBeforeDose;
    private int previousInsulinDose;

    static UserTracker getInstance() {
        if (instance == null) {
            instance = new UserTracker();
        }
        return instance;
    }

    void setCurrentSugarLevel(double sugarLevel) {
        currentSugarLevel = sugarLevel;
    }

    double getCurrentSugarLevel() {
        sugarLevelBeforeDose = currentSugarLevel;
        return currentSugarLevel;
    }

    double getSugarLevelBeforeDose(){
        return sugarLevelBeforeDose;
    }

    void setPreviousInsulinDose(int insulinDose) {
        this.previousInsulinDose = insulinDose;
    }

    int getPreviousInsulinDose() {
        return previousInsulinDose;
    }

    public void resetReservoirLevel() {
        reservoirLevel = 300;
    }

    void setReservoirLevel() {
        reservoirLevel -= previousInsulinDose;
    }

    String getReservoirLevel() {
        return Integer.toString(reservoirLevel);
    }
}
