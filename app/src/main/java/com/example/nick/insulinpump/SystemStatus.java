package com.example.nick.insulinpump;

import android.content.Context;

public class SystemStatus {
    ErrorType type;
    String systemMessage;
    Context context;
    InsulinNotificationManager insulinNM;

    public SystemStatus(ErrorType type, Context context){
        this.type = type;
        this.context = context;
    }

    public String systemMessage(){
        switch(this.type){
            case LOW_BATTERY:
                this.systemMessage = "The battery is running low";
                break;
            case SENSOR_FAILURE:
                this.systemMessage = "Sensor failed to measure blood sugar level";
                break;
            case PUMP_FAILURE:
                this.systemMessage = "The pump has failed";
                break;
            case DELIVERY_FAILURE:
                this.systemMessage = "Failed to deliver insulin";
                break;
            case NEEDLE_ASSMBLY_REMOVED:
                this.systemMessage = "Needle assembly has been removed";
                break;
            case INSULIN_RESERVOIR_REMOVED:
                this.systemMessage = "The insulin reservoir has been removed";
                break;
            case SUGAR_LOW:
                this.systemMessage = "Your sugar level is low";
                insulinNM = new InsulinNotificationManager(context, "Sugar Level Low", "You're sugar level is below 100 mg/dL", 001);
                break;
            case SUGAR_OK:
                this.systemMessage = "Your sugar level is within acceptable parameters";
                insulinNM = new InsulinNotificationManager(context, "Sugar Level OK", "You're sugar level is within acceptable parameters", 001);
                break;
            case SUGAR_HIGH:
                this.systemMessage = "Your sugar level is high, insulin will be delivered shortly";
                insulinNM = new InsulinNotificationManager(context, "Sugar Level High", "You're sugar level is above 140mg/dL, insulin will be delivered shortly", 001);
                break;
            case INSULIN_RESERVOIR_LOW:
                this.systemMessage = "You have less than 50 units of insulin remaining in your reservoir";
                insulinNM = new InsulinNotificationManager(context, "Reservoir Low", "You have less than 50 units remaining", 002);
                break;
            default:
                break;
        }
        return this.systemMessage;
    }

}
