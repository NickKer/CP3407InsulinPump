package com.example.nick.insulinpump;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Nick on 15-Oct-17.
 */
// this class delivers insulin if it is above a certain threshold, when called.
public class InsulinCalculator extends Application {
    UserTracker userTracker = UserTracker.getInstance();
    private int insulinDose;
    private double sugarLevel;
    DatabaseHandler dbHandler;

    public void calculateInsulin() {
        sugarLevel = userTracker.getCurrentSugarLevel();
        if (sugarLevel >= 150) {
            while (sugarLevel > 140) {
                insulinDose += 1;
                sugarLevel -= 50;
                userTracker.setCurrentSugarLevel(sugarLevel);
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat insulinDeliveryTimestamp = new SimpleDateFormat("hh:mm a", Locale.getDefault());
                userTracker.setSdf(insulinDeliveryTimestamp.format(cal.getTime()));
            }
            userTracker.setPreviousInsulinDose(insulinDose);
            userTracker.setReservoirLevel();

            //Inserts values to the Insulin Delivered table
            dbHandler = new DatabaseHandler(getApplicationContext());
            SQLiteDatabase db = dbHandler.getWritableDatabase();
            ContentValues deliveredContentValues = new ContentValues();
            deliveredContentValues.put("AmountDelivered", insulinDose);
            db.insert("InsulinDelivered", null, deliveredContentValues);

            //Gets the ID used for last Insulin Delivery Id
            int deliveryId = 0;
            Cursor cursor = db.rawQuery("SELECT * FROM InsulinDelivered ORDER BY InsulinDeliveryId DESC LIMIT 1", null);
            if (cursor.moveToNext()){
                deliveryId = cursor.getInt(0);
            }

            //Inserts values into the Sugar Level table
            ContentValues sugarContentValues = new ContentValues();
            sugarContentValues.put("InsulinDeliveryId", deliveryId);
            sugarContentValues.put("SugarLevelMeasured", sugarLevel);
            double sugarLevelBeforeDose = userTracker.getSugarLevelBeforeDose();
            if (sugarLevelBeforeDose < 80) {
                sugarContentValues.put("SugarLevelType", "LOW");
            } else if (sugarLevelBeforeDose >= 80 && sugarLevelBeforeDose <= 140) {
                sugarContentValues.put("SugarLevelType", "OK");
            } else if (sugarLevelBeforeDose > 140) {
                sugarContentValues.put("SugarLevelType", "HIGH");
            }
            db.insert("SugarLevel", null,sugarContentValues);

            Cursor cursor1 = db.rawQuery("SELECT * FROM SugarLevel", null);
            for (int i = 0; i < 10; i++) {
                if (cursor1.moveToNext()) {
                    System.out.println(cursor1.getString(0) + cursor1.getString(1) + cursor1.getString(2) + cursor1.getString(3));
                }
            }

            db.close();

            insulinDose = 0;
        }
    }
}

