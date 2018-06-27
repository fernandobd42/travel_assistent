package pages;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fernandolucasgontijo.travel_assistant.ControllerDB;
import com.example.fernandolucasgontijo.travel_assistant.CreateDB;
import com.example.fernandolucasgontijo.travel_assistant.R;

/**
 * Created by fernandolucasgontijo on 22/06/18.
 */

public class TravelHelper extends AppCompatActivity {

    Cursor cursor;
    String planningTravelId;
    public String mileageTraveled;
    public String litersSupplied;
    public String gasolinePrice;
    public String numRefuel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.travel_helper);

        getData();
    }

    public void getData(){
        ControllerDB crud = new ControllerDB(getBaseContext());
        Bundle args = getIntent().getExtras();
        planningTravelId = args.getString("id");

        cursor = crud.getTravelPlanningbyId(Integer.parseInt(planningTravelId));
        getSupportActionBar().setTitle(cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.NAME)));
    }

    public void goToListRefuel(View v) {
        EditText mileageTraveledET = findViewById(R.id.mileageTraveledEditText);
        EditText litersSuppliedET = findViewById(R.id.litersSuppliedEditText);
        EditText gasolinePriceET = findViewById(R.id.gasolinePriceEditText);
        EditText numRefuelET = findViewById(R.id.numRefuelditText);

        if (TextUtils.isEmpty(mileageTraveledET.getText().toString().trim())) {
            mileageTraveledET.setError("Mileage to be traveled can't be empty");
            return;
        }

        if (TextUtils.isEmpty(litersSuppliedET.getText().toString().trim())) {
            litersSuppliedET.setError("Liters supplied can't be empty");
            return;
        }


        if (TextUtils.isEmpty(gasolinePriceET.getText().toString().trim())) {
            gasolinePriceET.setError("Gasoline liter price can't be empty");
            return;
        }

        if (TextUtils.isEmpty(numRefuelET.getText().toString().trim())) {
            numRefuelET.setError("Num Refuel can't be empty");
            return;
        }

        Intent intent = new Intent(this, ListRefuel.class);

        Bundle params = new Bundle();
        params.putString("planningTravelId", planningTravelId);
        intent.putExtras(params);

        mileageTraveled = mileageTraveledET.getText().toString();
        litersSupplied = litersSuppliedET.getText().toString();
        gasolinePrice = gasolinePriceET.getText().toString();
        numRefuel = numRefuelET.getText().toString();

        createTravelHelper(v);
        startActivity(intent);
    }

    public void createTravelHelper(View v) {
        ControllerDB crud = new ControllerDB(getBaseContext());
        String result = crud.createTravelHelper(Integer.valueOf(planningTravelId), Integer.valueOf(mileageTraveled), Integer.valueOf(litersSupplied), Float.valueOf(gasolinePrice), Integer.valueOf(numRefuel));
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

        finish();
    }
}
