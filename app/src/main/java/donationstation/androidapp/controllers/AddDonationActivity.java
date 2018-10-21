package donationstation.androidapp.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import donationstation.androidapp.R;
import donationstation.androidapp.model.DonationItem;

public class AddDonationActivity extends Activity {

    //objects fetched from xml
    private Spinner monthsSpinner;
    private Spinner daysSpinner;
    private Spinner yearsSpinner;
    private Spinner hoursSpinner;
    private Spinner minutesSpinner;
    private Spinner amPmSpinner;
    private Spinner locationsSpinner;
    private Spinner categoriesSpinner;
    private EditText valueBox;
    private EditText shortDescriptionBox;
    private EditText fullDescriptionBox;

    private DatabaseReference mDatabaseReference;

    //arrays of possible options
//    String [] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
//    "Aug", "Sep", "Oct", "Nov", "Dec"};
    ArrayList<String> months = new ArrayList<>();
    ArrayList<String> days = new ArrayList<>();
    ArrayList<String> years = new ArrayList<>();
    ArrayList<String> hours = new ArrayList<>();
    ArrayList<String> minutes = new ArrayList<>();
    ArrayList<String> amPm = new ArrayList<>();








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donation);
        //associate objects
        monthsSpinner = findViewById(R.id.monthSpinner);
        daysSpinner = findViewById(R.id.daySpinner);
        yearsSpinner = findViewById(R.id.yearSpinner);
        hoursSpinner = findViewById(R.id.hourSpinner);
        minutesSpinner = findViewById(R.id.minuteSpinner);
        amPmSpinner = findViewById(R.id.ampmSpinner);
        locationsSpinner = findViewById(R.id.locationSpinner);
        categoriesSpinner = findViewById(R.id.categorySpinner);
        valueBox = findViewById(R.id.donationValueBox);
        shortDescriptionBox = findViewById(R.id.shortDescriptionBox);
        fullDescriptionBox = findViewById(R.id.fullDescriptionBox);
        //associating database
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Locations");


        //populating arrays
        populateSpinnerArrays();

    }

    public void addDonationButtonClicked(View view) {
        addDonationToFirebase();
        Intent intent = new Intent(this, EmployeeHomepageActivity.class);
        startActivity(intent);
    }

    private void addDonationToFirebase() {
        // grabbing values from objects
        String month = monthsSpinner.getSelectedItem().toString();
        String day = daysSpinner.getSelectedItem().toString();
        String year = yearsSpinner.getSelectedItem().toString();
        String hour = hoursSpinner.getSelectedItem().toString();
        String minute = minutesSpinner.getSelectedItem().toString();
        String amPm = amPmSpinner.getSelectedItem().toString();
        String location = locationsSpinner.getSelectedItem().toString();
        String category = categoriesSpinner.getSelectedItem().toString();
        String value = valueBox.getText().toString();
        String shortDescription = shortDescriptionBox.getText().toString();
        String fullDescription = fullDescriptionBox.getText().toString();

        //translating values to donationItem
        String date = month + "/" + day + "/" + year;
        String time = hour + ":" + minute + " '" + amPm;
        double valueDouble = Double.parseDouble(value);

        //creating location
        DonationItem addedItem = new DonationItem(date, time, location, category, valueDouble,
                shortDescription, fullDescription);

        //adding location to database
        mDatabaseReference.child("Location B").child("Inventory").child("Item 1").setValue(addedItem);








    }

    private void populateSpinnerArrays() {
        // populate month spinner
        for (int i = 1; i <= 12; i++) {
            months.add(String.valueOf(i));
        }
        ArrayAdapter<String> monthAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, months);
        monthsSpinner.setAdapter(monthAdapter);

        // populate day spinner
        //days.add("Select Day");
        for (int i = 1; i <= 31; i++) {
            days.add(String.valueOf(i));
        }
        ArrayAdapter<String> dayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, days);
        daysSpinner.setAdapter(dayAdapter);

        //populate year spinner
        //years.add("Select Year");
        for (int i = 2018; i >= 1990; i--) {
            years.add(String.valueOf(i));
        }
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, years);
        yearsSpinner.setAdapter(yearAdapter);

        //populate hour spinner
        //hours.add("Select Hour");
        for (int i = 1; i <= 12; i++) {
            hours.add(String.valueOf(i));
        }
        ArrayAdapter<String> hourAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, hours);
        hoursSpinner.setAdapter(hourAdapter);

        //populate minute spinner
        //minutes.add("Select Minute");
        for (int i = 0; i <= 59; i++) {
            minutes.add(String.valueOf(i));
        }
        ArrayAdapter<String> minuteAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, minutes);
        minutesSpinner.setAdapter(minuteAdapter);

        //populate ampm
        amPm.add("AM");
        amPm.add("PM");
        ArrayAdapter<String> amPmAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, amPm);
        amPmSpinner.setAdapter(amPmAdapter);

        // populate Locations Spinner
        // THIS NEEDS TO BE FILLED IN WITH LOCATIONS
        String[] locations = {"Location A", "Location B"};
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, locations);
        locationsSpinner.setAdapter(locationAdapter);

        // populate Category Spinner
        // THIS NEEDS TO BE FILLED IN WITH CATEGORIES
        String[] categories = {"Cat A", "Cat B"};
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categories);
        categoriesSpinner.setAdapter(categoryAdapter);
    }
}
