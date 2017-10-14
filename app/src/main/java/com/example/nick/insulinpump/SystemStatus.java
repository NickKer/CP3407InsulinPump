package com.example.nick.insulinpump;

public class SystemStatus {
    ErrorType type;
    String systemMessage;

    public SystemStatus(ErrorType type){
        this.type = type;
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
            default:
                break;
        }
        return this.systemMessage;
    }

}
