package donationstation.androidapp.controllers;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.ArrayList;

import donationstation.androidapp.R;

public class AddDonationActivity extends Activity {

    //objects fetched from xml
    private Spinner monthsSpinner;
    private Spinner daysSpinner;
    private Spinner yearsSpinner;
    private Spinner hoursSpinner;
    private Spinner minutesSpinner;
    private Spinner locationsSpinner;
    private Spinner categoriesSpinner;

    //arrays of possible options
    String [] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
    "Aug", "Sep", "Oct", "Nov", "Dec"};
    ArrayList<String> days = new ArrayList<>();
    ArrayList<String> years = new ArrayList<>();
    ArrayList<String> hours = new ArrayList<>();
    ArrayList<String> minutes = new ArrayList<>();








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donation);
        populateSpinnerArrays();

    }

    private void populateSpinnerArrays() {
        // populate month spinner
        monthsSpinner = findViewById(R.id.monthSpinner);
        ArrayAdapter<String> monthAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, months);
        monthsSpinner.setAdapter(monthAdapter);

        // populate day spinner
        //days.add("Select Day");
        for (int i = 1; i <= 31; i++) {
            days.add(String.valueOf(i));
        }
        daysSpinner = findViewById(R.id.daySpinner);
        ArrayAdapter<String> dayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, days);
        daysSpinner.setAdapter(dayAdapter);

        //populate year spinner
        //years.add("Select Year");
        for (int i = 2018; i >= 1990; i--) {
            years.add(String.valueOf(i));
        }
        yearsSpinner = findViewById(R.id.yearSpinner);
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, years);
        yearsSpinner.setAdapter(yearAdapter);

        //populate hour spinner
        //hours.add("Select Hour");
        for (int i = 1; i <= 12; i++) {
            hours.add(String.valueOf(i));
        }
        hoursSpinner = findViewById(R.id.hourSpinner);
        ArrayAdapter<String> hourAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, hours);
        hoursSpinner.setAdapter(hourAdapter);

        //populate minute spinner
        //minutes.add("Select Minute");
        for (int i = 0; i <= 59; i++) {
            minutes.add(String.valueOf(i));
        }
        minutesSpinner = findViewById(R.id.minuteSpinner);
        ArrayAdapter<String> minuteAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, minutes);
        minutesSpinner.setAdapter(minuteAdapter);

        // populate Locations Spinner
        // THIS NEEDS TO BE FILLED IN WITH LOCATIONS
        String[] locations = {"Location A", "Location B"};
        locationsSpinner = findViewById(R.id.locationSpinner);
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, locations);
        locationsSpinner.setAdapter(locationAdapter);

        // populate Category Spinner
        // THIS NEEDS TO BE FILLED IN WITH CATEGORIES
        String[] categories = {"Cat A", "Cat B"};
        categoriesSpinner = findViewById(R.id.categorySpinner);
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categories);
        categoriesSpinner.setAdapter(categoryAdapter);


    }
}
