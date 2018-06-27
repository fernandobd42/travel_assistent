package pages;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fernandolucasgontijo.travel_assistant.ControllerDB;
import com.example.fernandolucasgontijo.travel_assistant.R;

/**
 * Created by fernandolucasgontijo on 03/05/18.
 */

public class PlanTravel extends AppCompatActivity {

    public String name;
    public String fuelTank;
    public String averageKM;
    public String mileageTraveled;
    public String gasolinePrice;
    public String busTicket;
    public String howManyTravelersValue;
    public TextView howManyTravelersValueTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan_travel);

        Bundle args = getIntent().getExtras();
        name = args.getString("name");
        fuelTank = args.getString("fuelTank");
        averageKM = args.getString("averageKm");

        getSupportActionBar().setTitle(name);

        SeekBar howManyTravelersSeekBar = findViewById(R.id.howManyTravelersSeekBar);
        howManyTravelersSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);

        int progress = howManyTravelersSeekBar.getProgress();
        howManyTravelersValueTV = findViewById(R.id.howManyTravelersValue);
        howManyTravelersValueTV.setText(String.valueOf(progress));
    }

    SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            howManyTravelersValueTV.setText(String.valueOf(progress));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // called when the user first touches the SeekBar
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // called after the user finishes moving the SeekBar
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void goToListPlannedTravelPage(View v) {
        EditText mileageTraveledET = findViewById(R.id.mileageToBeTraveledEditText);
        EditText gasolinePriceET = findViewById(R.id.gasolinePriceEditText);
        EditText busTicketET = findViewById(R.id.busTicketPriceEditText);

        if (TextUtils.isEmpty(mileageTraveledET.getText().toString().trim())) {
            mileageTraveledET.setError("Mileage to be traveled can't be empty");
            return;
        }

        if (TextUtils.isEmpty(gasolinePriceET.getText().toString().trim())) {
            gasolinePriceET.setError("Gasoline liter price can't be empty");
            return;
        }

        if (TextUtils.isEmpty(busTicketET.getText().toString().trim())) {
            busTicketET.setError("Bus ticket can't be empty");
            return;
        }

        Intent intent = new Intent(this, ListPlannedTravel.class);

        mileageTraveled = mileageTraveledET.getText().toString();
        gasolinePrice = gasolinePriceET.getText().toString();
        busTicket = busTicketET.getText().toString();
        howManyTravelersValue = howManyTravelersValueTV.getText().toString();

        createTravelPlanning(v);
        startActivity(intent);
    }

    public void createTravelPlanning(View v) {
        ControllerDB crud = new ControllerDB(getBaseContext());
        String result = crud.createTravelPlanning(name, Integer.valueOf(fuelTank), Float.valueOf(averageKM), Integer.valueOf(mileageTraveled), Float.valueOf(gasolinePrice), Float.valueOf(busTicket), Integer.valueOf(howManyTravelersValue));
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

        finish();
    }
}