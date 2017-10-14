package com.example.nick.insulinpump;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void systemStatus_systemMessage()throws Exception{
        SystemStatus systemStatusDelivery = new SystemStatus(ErrorType.DELIVERY_FAILURE);
        assertTrue(systemStatusDelivery.systemMessage().equals("Failed to deliver insulin"));

        SystemStatus systemStatusReservoir = new SystemStatus(ErrorType.INSULIN_RESERVOIR_REMOVED);
        assertTrue(systemStatusReservoir.systemMessage().equals("The insulin reservoir has been removed"));

        SystemStatus systemStatusBattery = new SystemStatus(ErrorType.LOW_BATTERY);
        assertTrue(systemStatusBattery.systemMessage().equals("The battery is running low"));

        SystemStatus systemStatusNeedle = new SystemStatus(ErrorType.NEEDLE_ASSMBLY_REMOVED);
        assertTrue(systemStatusNeedle.systemMessage().equals("Needle assembly has been removed"));

        SystemStatus systemStatusPump = new SystemStatus(ErrorType.PUMP_FAILURE);
        assertTrue(systemStatusPump.systemMessage().equals("The pump has failed"));

        SystemStatus systemStatusSensor = new SystemStatus(ErrorType.SENSOR_FAILURE);
        assertTrue(systemStatusSensor.systemMessage().equals("Sensor failed to measure blood sugar level"));

    }
}