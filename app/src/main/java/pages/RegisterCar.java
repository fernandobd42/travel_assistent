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

import com.example.fernandolucasgontijo.travel_assistant.R;

/**
 * Created by fernandolucasgontijo on 25/04/18.
 */

public class RegisterCar extends AppCompatActivity {

    public String name;
    public TextView fuelTankValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_car);

        Bundle args = getIntent().getExtras();
        name = args.getString("name");

        getSupportActionBar().setTitle(name);
        SeekBar fuelTankSeekBar = findViewById(R.id.fuelTankSeekBar);
        fuelTankSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);

        int progress = fuelTankSeekBar.getProgress();
        fuelTankValue = findViewById(R.id.fuelTankValue);
        fuelTankValue.setText(String.valueOf(progress));
    }

    SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            fuelTankValue.setText(String.valueOf(progress));
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

    public void goToPlanTravelPage(View v) {
        EditText averageKmText = findViewById(R.id.averageKmEditTxt);

        if (TextUtils.isEmpty(averageKmText.getText().toString().trim())) {
            averageKmText.setError("Average Km can't be empty");
            return;
        }

        Intent intent = new Intent(this, PlanTravel.class);
        Bundle params = new Bundle();

        params.putString("name", name);
        params.putString("fuelTank", fuelTankValue.getText().toString());
        params.putString("averageKm", averageKmText.getText().toString());
        intent.putExtras(params);

        startActivity(intent);
    }
}
