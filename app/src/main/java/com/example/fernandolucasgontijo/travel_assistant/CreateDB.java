package com.example.fernandolucasgontijo.travel_assistant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by fernandolucasgontijo on 20/06/18.
 */

public class CreateDB extends SQLiteOpenHelper {

    private static final String DB_NAME="travel_assistant.db";
    public static final String TABLE_TRAVEL_PLANNING="travel_planning";
    public static final String ID="_id";
    public static final String NAME="name_user";
    public static final String FUEL_TANK="fuel_tank";
    public static final String AVERAGE_KM="average_km";
    public static final String MILEAGE_TRAVELED="mileage_traveled";
    public static final String GASOLINE_PRICE="gasoline_price";
    public static final String BUS_TICKET="bus_ticket";
    public static final String HOW_MANY_PASSENGERS="how_many_passenger";

    public static final String TABLE_TRAVEL_HELPER="travel_helper";
    public static final String ID_TRAVEL_PLANNING="id_travel_planning";
    public static final String LITERS_SUPPLIED="liters_supplied";
    public static final String NUM_REFUEL="num_refuel";


    public static final int VERSAO=2;

    public CreateDB(Context content) {
        super(content, DB_NAME, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TRAVEL_PLANNING = "CREATE TABLE "+ TABLE_TRAVEL_PLANNING +" ( "
                + ID + " integer primary key autoincrement,"
                + NAME + " text,"
                + FUEL_TANK + " integer,"
                + AVERAGE_KM + " float,"
                + MILEAGE_TRAVELED + " integer,"
                + GASOLINE_PRICE + " float,"
                + BUS_TICKET + " float,"
                + HOW_MANY_PASSENGERS + " integer)";

        String TRAVEL_HELPER = "CREATE TABLE "+ TABLE_TRAVEL_HELPER + " ( "
                + ID + " integer primary key autoincrement,"
                + ID_TRAVEL_PLANNING + " integer,"
                + LITERS_SUPPLIED + " integer,"
                + GASOLINE_PRICE + " float,"
                + MILEAGE_TRAVELED + " integer,"
                + NUM_REFUEL + " integer,"
                + "FOREIGN KEY("+ ID_TRAVEL_PLANNING +") REFERENCES "+ TABLE_TRAVEL_PLANNING +"("+ ID +"))";



        db.execSQL(TRAVEL_PLANNING);
//        db.execSQL(TRAVEL_HELPER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRAVEL_PLANNING);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRAVEL_HELPER);
        onCreate(db);
    }
}
