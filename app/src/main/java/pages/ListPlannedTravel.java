package pages;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.fernandolucasgontijo.travel_assistant.ControllerDB;
import com.example.fernandolucasgontijo.travel_assistant.CreateDB;
import com.example.fernandolucasgontijo.travel_assistant.R;

/**
 * Created by fernandolucasgontijo on 22/06/18.
 */

public class ListPlannedTravel extends AppCompatActivity {

    private ListView list;
    private ListView listHeader;
    Cursor cursor;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_planned_travel);

        getData();
    }

    public void getData(){
        ControllerDB crud = new ControllerDB(getBaseContext());

        intent = new Intent(this, PlannedTravel.class);
        cursor = crud.getTravelPlannings();

        String[] header = new String[] {"ID - NAME - TANK - AVERAGE KM - MILEAGE TRAVELED - GASOLINE PRICE - BUS TICKET - HOW MANY PASSENGERS"};
        String[] fieldNames = new String[] {CreateDB.ID, CreateDB.NAME, CreateDB.FUEL_TANK, CreateDB.AVERAGE_KM, CreateDB.MILEAGE_TRAVELED, CreateDB.GASOLINE_PRICE, CreateDB.BUS_TICKET, CreateDB.HOW_MANY_PASSENGERS};
        int[] idViews = new int[] {R.id.id, R.id.name, R.id.fuel_tank, R.id.average_km, R.id.mileage_traveled, R.id.gasoline_price, R.id.bus_ticket, R.id.how_many_passengers};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.list_planned_travel_item, cursor, fieldNames, idViews);

        ArrayAdapter<String> headerList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, header);

        listHeader = findViewById(R.id.listPlannedTravelHeaders);
        list = findViewById(R.id.listPlannedTravel);

        listHeader.setAdapter(headerList);
        list.setAdapter(adaptador);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String cod, name, fuelTank, averageKM, mileageTraveled, gasolinePrice, busTicket, howManyTravelersValue;
                cursor.moveToPosition(position);
                cod = cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.ID));
                name = cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.NAME));
                fuelTank = cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.FUEL_TANK));
                averageKM = cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.AVERAGE_KM));
                mileageTraveled = cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.MILEAGE_TRAVELED));
                gasolinePrice = cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.GASOLINE_PRICE));
                busTicket = cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.BUS_TICKET));
                howManyTravelersValue = cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.HOW_MANY_PASSENGERS));

                Bundle params = new Bundle();
                params.putString("id", cod);
                params.putString("name", name);
                params.putString("fuelTank", fuelTank);
                params.putString("averageKm", averageKM);
                params.putString("mileageTraveled", mileageTraveled);
                params.putString("gasolinePrice", gasolinePrice);
                params.putString("busTicket", busTicket);
                params.putString("howManyPassengers", howManyTravelersValue);
                intent.putExtras(params);


                startActivity(intent);
            }
        });

    }
}
