package pages;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.fernandolucasgontijo.travel_assistant.ControllerDB;
import com.example.fernandolucasgontijo.travel_assistant.CreateDB;
import com.example.fernandolucasgontijo.travel_assistant.R;

/**
 * Created by fernandolucasgontijo on 26/06/18.
 */

public class ListRefuel extends AppCompatActivity  {

    private ListView list;
    private ListView listHeader;
    public String planningTravelId;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_refuel);

        String name =  this.getIntent().getStringExtra("name");
        getSupportActionBar().setTitle(name);

        getData();
    }

    public void getData() {
        ControllerDB crud = new ControllerDB(getBaseContext());
        planningTravelId = this.getIntent().getStringExtra("planningTravelId");

        cursor = crud.getTravelHelper(planningTravelId);

        String[] header = new String[]{"ID - MILEAGE TRAVELED - LITERS SUPPLIED - GASOLINE PRICE - NUM REFUEL"};
        String[] fieldNames = new String[]{CreateDB.ID, CreateDB.MILEAGE_TRAVELED, CreateDB.LITERS_SUPPLIED, CreateDB.GASOLINE_PRICE, CreateDB.NUM_REFUEL};
        int[] idViews = new int[]{R.id.id, R.id.mileage_traveled, R.id.liters_supplied, R.id.gasoline_price, R.id.num_refuel};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.list_refuel_item, cursor, fieldNames, idViews);

        ArrayAdapter<String> headerList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, header);

        listHeader = findViewById(R.id.listRefuelHeaders);
        list = findViewById(R.id.listRefuel);

        listHeader.setAdapter(headerList);
        list.setAdapter(adaptador);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String cod, mileageTraveled, litersSupplied, gasolinePrice, numRefuel;
                cursor.moveToPosition(position);
                cod = cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.ID));
                mileageTraveled = cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.MILEAGE_TRAVELED));
                litersSupplied = cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.LITERS_SUPPLIED));
                gasolinePrice = cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.GASOLINE_PRICE));
                numRefuel = cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.NUM_REFUEL));


                Toast.makeText(ListRefuel.this, (cod + " - " + mileageTraveled + " - " + litersSupplied + " - " + gasolinePrice + " - " + numRefuel), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
