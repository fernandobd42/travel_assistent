package com.example.fernandolucasgontijo.travel_assistant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by fernandolucasgontijo on 22/06/18.
 */

public class ControllerDB {

    private CreateDB bank;
    private SQLiteDatabase db;

    public ControllerDB(Context context) {
        bank = new CreateDB(context);
    }


    /**
     * START of TravelPlanning Methods
     */

    public String createTravelPlanning(String nome, Integer fuel_tank, Float average_km, Integer mileage_traveled, Float gasoline_price, Float bus_ticket, Integer how_many_passengers) {
        ContentValues values = new ContentValues();

        db = bank.getWritableDatabase();

        values.put(CreateDB.NAME, nome);
        values.put(CreateDB.FUEL_TANK, fuel_tank);
        values.put(CreateDB.AVERAGE_KM, average_km);
        values.put(CreateDB.MILEAGE_TRAVELED, mileage_traveled);
        values.put(CreateDB.GASOLINE_PRICE, gasoline_price);
        values.put(CreateDB.BUS_TICKET, bus_ticket);
        values.put(CreateDB.HOW_MANY_PASSENGERS, how_many_passengers);
        long result = db.insert(CreateDB.TABLE_TRAVEL_PLANNING, null, values);
        db.close();

        if (result > 0) {
            return "Criado com sucesso!";
        }

        return "Error ao inserir Travel Planning no banco";
    }

    public Cursor getTravelPlannings() {
        Cursor cursor;
        String [] fields = {CreateDB.ID, CreateDB.NAME, CreateDB.FUEL_TANK, CreateDB.AVERAGE_KM, CreateDB.MILEAGE_TRAVELED, CreateDB.GASOLINE_PRICE, CreateDB.BUS_TICKET, CreateDB.HOW_MANY_PASSENGERS};

        db = bank.getReadableDatabase();
        cursor = db.query(CreateDB.TABLE_TRAVEL_PLANNING, fields, null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public Cursor getTravelPlanningbyId(int id) {
        Cursor cursor;
        String [] fields = {CreateDB.ID, CreateDB.NAME, CreateDB.FUEL_TANK, CreateDB.AVERAGE_KM, CreateDB.MILEAGE_TRAVELED, CreateDB.GASOLINE_PRICE, CreateDB.BUS_TICKET, CreateDB.HOW_MANY_PASSENGERS};

        db = bank.getReadableDatabase();
        String where = CreateDB.ID + " = " + id;
        cursor = db.query(CreateDB.TABLE_TRAVEL_PLANNING, fields, where, null, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public void updateTravelPlanning(int id, String nome, Integer fuel_tank, Float average_km, Integer mileage_traveled, Float gasoline_price, Float bus_ticket, Integer how_many_passengers) {
        ContentValues values = new ContentValues();
        String where;

        db = bank.getWritableDatabase();

        where = CreateDB.ID + "=" + id;
        values.put(CreateDB.NAME, nome);
        values.put(CreateDB.FUEL_TANK, fuel_tank);
        values.put(CreateDB.AVERAGE_KM, average_km);
        values.put(CreateDB.MILEAGE_TRAVELED, mileage_traveled);
        values.put(CreateDB.GASOLINE_PRICE, gasoline_price);
        values.put(CreateDB.BUS_TICKET, bus_ticket);
        values.put(CreateDB.HOW_MANY_PASSENGERS, how_many_passengers);
        db.update(CreateDB.TABLE_TRAVEL_PLANNING, values, where,null);
        db.close();
    }

    public void deleteTravelPlanning(int id) {
        String where = CreateDB.ID + "=" + id;
        db = bank.getReadableDatabase();
        db.delete(CreateDB.TABLE_TRAVEL_PLANNING, where,null);
        db.close();
    }

    /**
     * END of TravelPlanning Methods
     */

    /**
     * --------------------------------------------
     */

    /**
     * START of TravelHelper Methods
     */

    public String createTravelHelper(Integer Id_Travel_Planning, Integer mileage_traveled, Integer liters_supplied, Float gasoline_price, Integer num_refuel) {
        ContentValues values = new ContentValues();

        db = bank.getWritableDatabase();

        values.put(CreateDB.ID_TRAVEL_PLANNING, Id_Travel_Planning);
        values.put(CreateDB.MILEAGE_TRAVELED, mileage_traveled);
        values.put(CreateDB.LITERS_SUPPLIED, liters_supplied);
        values.put(CreateDB.GASOLINE_PRICE, gasoline_price);
        values.put(CreateDB.NUM_REFUEL, num_refuel);
        long result = db.insert(CreateDB.TABLE_TRAVEL_HELPER, null, values);
        db.close();

        if (result > 0) {
            return "Criado com sucesso!";
        }

        return "Error ao inserir Travel Planning no banco";
    }

    public Cursor getTravelHelper(String planningTravelId) {
        Cursor cursor;
        String [] fields = {CreateDB.ID, CreateDB.ID_TRAVEL_PLANNING, CreateDB.MILEAGE_TRAVELED, CreateDB.LITERS_SUPPLIED, CreateDB.GASOLINE_PRICE, CreateDB.NUM_REFUEL};

        db = bank.getReadableDatabase();
        cursor = db.query(CreateDB.TABLE_TRAVEL_HELPER, fields, "id_travel_planning = ?", new String[] { planningTravelId }, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public Cursor getTravelHelperbyId(int id) {
        Cursor cursor;
        String [] fields = {CreateDB.ID, CreateDB.ID_TRAVEL_PLANNING, CreateDB.MILEAGE_TRAVELED, CreateDB.LITERS_SUPPLIED, CreateDB.GASOLINE_PRICE, CreateDB.NUM_REFUEL};

        db = bank.getReadableDatabase();
        String where = CreateDB.ID + " = " + id;
        cursor = db.query(CreateDB.TABLE_TRAVEL_PLANNING, fields, where, null, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public void updateTravelHelper(int id, Integer Id_Travel_Planning, Integer mileage_traveled, Integer liters_supplied, Float gasoline_price, Integer num_refuel) {
        ContentValues values = new ContentValues();
        String where;

        db = bank.getWritableDatabase();

        where = CreateDB.ID + "=" + id;
        values.put(CreateDB.ID_TRAVEL_PLANNING, Id_Travel_Planning);
        values.put(CreateDB.MILEAGE_TRAVELED, mileage_traveled);
        values.put(CreateDB.LITERS_SUPPLIED, liters_supplied);
        values.put(CreateDB.GASOLINE_PRICE, gasoline_price);
        values.put(CreateDB.NUM_REFUEL, num_refuel);
        long result = db.insert(CreateDB.TABLE_TRAVEL_HELPER, null, values);
        db.update(CreateDB.TABLE_TRAVEL_HELPER, values, where,null);
        db.close();
    }

    public void deleteTravelHelper(int id) {
        String where = CreateDB.ID + "=" + id;
        db = bank.getReadableDatabase();
        db.delete(CreateDB.TABLE_TRAVEL_HELPER, where,null);
        db.close();
    }

    /**
     * END of TravelHelper Methods
     */
}
