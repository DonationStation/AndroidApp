package donationstation.androidapp.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

import donationstation.androidapp.R;
import donationstation.androidapp.model.DonationItem;

public class AddDonationActivity extends Activity {

    //Initial Object Instantiation
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
    private int numOfItems = 0;
    //Arrays to populate spinners
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
        //populate location spinner (dynamic)
        final ArrayList<String> locations = new ArrayList<>();
        final ArrayAdapter<String> locationAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, locations);
        locationsSpinner.setAdapter(locationAdapter);
        mDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String allLocations = dataSnapshot.getKey().toString();
                locations.add(allLocations);
                numOfItems++;
                locationAdapter.notifyDataSetChanged();
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) { }
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
        //populating static spinners
        populateSpinnerArrays();
    }

    public void addDonationButtonClicked(View view) {
        //add the donation
        addDonationToFirebase();
        //change back to employeeHomepageActivity screen
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
        final String location = locationsSpinner.getSelectedItem().toString();
        String category = categoriesSpinner.getSelectedItem().toString();
        String value = valueBox.getText().toString();
        String shortDescription = shortDescriptionBox.getText().toString();
        String fullDescription = fullDescriptionBox.getText().toString();
        //translating values to donationItem
        String date = month + "/" + day + "/" + year;
        String time = hour + ":" + minute + " '" + amPm;
        double valueDouble = Double.parseDouble(value);
        //creating donation Item
        final DonationItem addedItem = new DonationItem(date, time, location, category, valueDouble,
                shortDescription, fullDescription);
        //Inputting newly donated item to right spot in inventory
        final DatabaseReference inputItemReference = mDatabaseReference.child(location).child("Inventory");
        inputItemReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String currentInventorySize = dataSnapshot.child("size").getValue().toString();
                Integer updatedInvSize = Integer.parseInt(currentInventorySize) + 1;
                String itemKey = "Item " + updatedInvSize;
                inputItemReference.child(itemKey).setValue(addedItem);
                inputItemReference.child("size").setValue(updatedInvSize);

            }

            //Inputting newly donated item to right spot in inventory
//        final DatabaseReference inputItemReference = mDatabaseReference.child(location).child("Inventory");
//        inputItemReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String currentInventorySize = dataSnapshot.child("size").getValue().toString();
//                Integer updatedInvSize = Integer.parseInt(currentInventorySize) + 1;
//                String itemKey = "Item " + updatedInvSize;
//                inputItemReference.child(itemKey).setValue(addedItem);
//                inputItemReference.child("size").setValue(updatedInvSize);
//
//            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
        // populate Category Spinner
        // THIS NEEDS TO BE FILLED IN WITH CATEGORIES
        String[] categories = {"Clothes", "Electronics", "Other"};
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categories);
        categoriesSpinner.setAdapter(categoryAdapter);
    }
}
