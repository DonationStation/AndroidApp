package donationstation.androidapp.controllers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

import donationstation.androidapp.R;

public class UserItemSearchActivity extends AppCompatActivity {

    DatabaseReference mDatabaseReference;
    Spinner locationSpinner, categorySpinner;
    EditText nameSearch;
    final ArrayList<String> locations = new ArrayList<>();
    final ArrayList<String> categories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_item_search);

        locationSpinner = findViewById(R.id.searchByLocation);
        categorySpinner = findViewById(R.id.searchByCategory);
        nameSearch = findViewById(R.id.searchName);


        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        // Populate location spinner (dynamic)
        DatabaseReference mLocationReference = mDatabaseReference.child("Locations");
        locations.add("All Locations");
        final ArrayAdapter<String> locationAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, locations);
        locationSpinner.setAdapter(locationAdapter);
        mLocationReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String allLocations = dataSnapshot.getKey().toString();
                locations.add(allLocations);
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

        // Populate category spinner
        DatabaseReference mCategoryReference = mDatabaseReference.child("Categories");
        categories.add("All Categories");
        final ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categories);
        categorySpinner.setAdapter(categoryAdapter);
        mCategoryReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String allCategories = dataSnapshot.getValue().toString();
                categories.add(allCategories);
                categoryAdapter.notifyDataSetChanged();
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
    }

    /**
     *
     * @param view view to pass to DonationListActivity
     * takes user's inputted search requirements and generates list of results
     */
    public void searchByFilters(View view) {
        Intent intent = new Intent(this, DonationListActivity.class);

        // Get ready for pass the values
        ArrayList<String> locationArray = new ArrayList<>();
        ArrayList<String> categoryArray = new ArrayList<>();
        String location = locationSpinner.getSelectedItem().toString();
        String category = categorySpinner.getSelectedItem().toString();
        String name = nameSearch.getText().toString();

        // Set locationArray in terms of which an user selected.
        // for locationArray
        if (location.equals("All Locations")) {
            locationArray.addAll(locations);
        } else {
            locationArray.add(location);
        }
        // for categoryArray
        if (category.equals("All Categories")) {
            categoryArray.addAll(categories);
        } else {
            categoryArray.add(category);
        }

        // Pass the values.
        intent.putExtra("locationSearch", locationArray);
        intent.putExtra("categorySearch", categoryArray);
        intent.putExtra("nameSearch", name);

        startActivity(intent);
    }
}
