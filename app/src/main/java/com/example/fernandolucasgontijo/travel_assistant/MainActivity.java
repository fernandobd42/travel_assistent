package com.example.fernandolucasgontijo.travel_assistant;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import pages.ListPlannedTravel;
import pages.RegisterCar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
    }

    public void goToInitialPage(View v) {
        EditText nameEditTxt = findViewById(R.id.nameEditTxt);

        if (TextUtils.isEmpty(nameEditTxt.getText().toString().trim())) {
            nameEditTxt.setError("Name can't be empty");
            return;
        }

        Intent intent = new Intent(this, RegisterCar.class);
        Bundle params = new Bundle();

        params.putString("name", nameEditTxt.getText().toString());
        intent.putExtras(params);

        startActivity(intent);
    }

    public void goToPlannedList(View v) {
        Intent listPlannedTravel = new Intent(this, ListPlannedTravel.class);
        startActivity(listPlannedTravel);
    }
}
