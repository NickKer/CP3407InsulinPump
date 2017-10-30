package com.example.nick.insulinpump;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int VERSION = 2;
    private Context context;

    DatabaseHandler(Context context) {
        super(context, "insulin_tracking.db", null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(context.getString(R.string.create_table_1, "InsulinDelivered", "InsulinDeliveryId", "TimeStamp", "AmountDelivered"));
        sqLiteDatabase.execSQL(context.getString(R.string.create_table_2, "SugarLevel", "SugarLevelId", "InsulinDeliveryId", "SugarLevelMeasured", "SugarLevelType"));
        sqLiteDatabase.execSQL(context.getString(R.string.create_table_3, "SystemMessages", "MessageId", "MessageType", "MessageText"));

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(context.getString(R.string.delete_table, "InsulinDelivery"));
        sqLiteDatabase.execSQL(context.getString(R.string.delete_table, "SugarLevel"));
        sqLiteDatabase.execSQL(context.getString(R.string.delete_table, "SystemMessages"));
        onCreate(sqLiteDatabase);
    }
}