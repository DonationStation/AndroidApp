package donationstation.androidapp.controllers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import donationstation.androidapp.R;

public class DonationDetailActivity extends AppCompatActivity {

    private FirebaseDatabase FDB;
    private DatabaseReference DBR;
    private TextView mDetailView;
    private String keyString;
    private String locationName;

    private ArrayList<String> info = new ArrayList<>();
    private String category = "null";
    private String date = "null";
    private String fullDes = "null";
    private String location = "null";
    private String shortDes = "null";
    private String time = "null";
    private String value = "null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_detail);

        // Retrieve the passed data
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            keyString = null;
            locationName = null;
        } else {
            keyString = bundle.getString("key");
            locationName = bundle.getString("locationName");
        }

        mDetailView = findViewById(R.id.name_view);
        FDB = FirebaseDatabase.getInstance();
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, info);
        GetDataFirebase();
    }

    void GetDataFirebase() {
        DBR = FDB.getReference().child("Locations").child(locationName).child("Inventory").child(keyString);

        DBR.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.getKey().toString().equals("category")) {
                    category = dataSnapshot.getValue().toString();
                } else if (dataSnapshot.getKey().toString().equals("date")) {
                    date = dataSnapshot.getValue().toString();
                } else if (dataSnapshot.getKey().toString().equals("fullDes")) {
                    fullDes = dataSnapshot.getValue().toString();
                } else if (dataSnapshot.getKey().toString().equals("location")) {
                    location = dataSnapshot.getValue().toString();
                } else if (dataSnapshot.getKey().toString().equals("shortDes")) {
                    shortDes = dataSnapshot.getValue().toString();
                } else if (dataSnapshot.getKey().toString().equals("time")) {
                    time = dataSnapshot.getValue().toString();
                } else if (dataSnapshot.getKey().toString().equals("value")) {
                    value = dataSnapshot.getValue().toString();
                }

                mDetailView.setText("Item Name: " + keyString + "\n" +
                        "category: " + category + "\n" +
                        "date: " + date + "\n" +
                        "location: " + location + "\n" +
                        "shortDes: " + shortDes + "\n" +
                        "fullDes: " + fullDes + "\n" +
                        "value: " + value + "\n" +
                        "time: " + time + "\n");
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
