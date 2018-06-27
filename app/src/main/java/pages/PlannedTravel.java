package pages;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.fernandolucasgontijo.travel_assistant.R;

/**
 * Created by fernandolucasgontijo on 06/05/18.
 */

public class PlannedTravel extends AppCompatActivity {

    public String planningTravelId;
    public String name;
    public String busMsg;
    public String carMsg;
    public TextView carInfos;
    public TextView busInfo;
    public TextView carInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.planned_travel);

        Bundle args = getIntent().getExtras();
        planningTravelId = args.getString("id");
        name = args.getString("name");
        String fuelTank = args.getString("fuelTank");
        String averageKm = args.getString("averageKm");
        String mileageTraveled = args.getString("mileageTraveled");
        String gasolinePrice = args.getString("gasolinePrice");
        String busTicket = args.getString("busTicket");
        String howManyPassengers = args.getString("howManyPassengers");
        Float howManyKms = calculateKms(new Integer(fuelTank), new Float(averageKm));

        getSupportActionBar().setTitle(name);

        carInfos = findViewById(R.id.carInfos);
        busInfo = findViewById(R.id.busInfo);
        carInfo = findViewById(R.id.carInfo);

        carInfos.setText("The car has capacity of " + fuelTank + " liters, and make average of " + averageKm + " km per liter. \n"
                + "So, with the fuel tank full you can travel " + howManyKms + " kms");

        calculeTravel(Integer.parseInt(mileageTraveled), Float.parseFloat(gasolinePrice), Float.parseFloat(busTicket), Integer.parseInt(howManyPassengers), Float.parseFloat(averageKm));
    }

    public Float calculateKms(Integer fuelTank, Float averageKm) {
        return fuelTank * averageKm;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void calculeTravel(Integer mileageTraveled, Float gasolinePrice, Float busTicket, Integer howManyPassengers, Float averageKm) {
        Float carTravelPrice = (mileageTraveled / averageKm) * gasolinePrice;
        Float carTravelSharedByPassengers = carTravelPrice / howManyPassengers;
        Float busTravelPrice = busTicket * howManyPassengers;

        if (mileageTraveled <= 200) {
            if (howManyPassengers > 1) {
                busMsg = "By Bus you'll pay individually $" + busTicket + ", but you and your passengers will pay $" + busTravelPrice + " adding the value each one.";
                carMsg = "By Car you and your passengers will pay " + carTravelSharedByPassengers + " each one, to suply fuel tank";
            } else {
                busMsg = "By Bus you'll pay individually $" + busTicket;
                carMsg = "By Car you'll pay " + carTravelPrice + "to suply fuel tank";
            }
        } else if (mileageTraveled > 200) {
            if (howManyPassengers > 1) {
                busMsg = "By Bus you'll pay individually $" + busTicket + ", but you and your passengers will pay $" + busTravelPrice + " adding the value each one.";
                carMsg = "By Car you and your passengers will pay " + carTravelSharedByPassengers + " each one, to suply fuel tank, but you and your passengers will pay $" + carTravelPrice + " adding the value each one.";
            } else {
                busMsg = "By Bus you'll pay individually $" + busTicket;
                carMsg = "By Car you'll pay " + carTravelPrice + " to suply fuel tank";
            }
        }

        busInfo.setText(busMsg);
        carInfo.setText(carMsg);
    }

    public void goToTravelHelper(View v) {
        Intent intent = new Intent(this, TravelHelper.class);
        Bundle params = new Bundle();

        params.putString("id", planningTravelId);
        intent.putExtras(params);
        startActivity(intent);
    }

    public void goToListRefuel(View v) {
        Intent intent = new Intent(this, ListRefuel.class);
        Bundle params = new Bundle();

        params.putString("planningTravelId", planningTravelId);
        params.putString("name", name);
        intent.putExtras(params);
        startActivity(intent);
    }
}