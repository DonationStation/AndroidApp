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

    private ArrayList<String> info = new ArrayList<>();
    private String address = "null";
    private String city = "null";
    private String key = "null";
    private String latitude = "null";
    private String longitude = "null";
    private String name = "null";
    private String phone = "null";
    private String state = "null";
    private String type = "null";
    private String website = "null";
    private String zip = "null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_detail);


        // Retrieve the position
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            keyString = null;
        } else {
            keyString = bundle.getString("key");
        }


        mDetailView = findViewById(R.id.name_view);
        FDB = FirebaseDatabase.getInstance();
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, info);
        GetDataFirebase();




    }

    void GetDataFirebase() {
        DBR = FDB.getReference().child("Locations").child(keyString);

        DBR.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.getKey().toString().equals("address")) {
                    address = dataSnapshot.getValue().toString();
                } else if (dataSnapshot.getKey().toString().equals("city")) {
                    city = dataSnapshot.getValue().toString();
                } else if (dataSnapshot.getKey().toString().equals("key")) {
                    key = dataSnapshot.getValue().toString();
                } else if (dataSnapshot.getKey().toString().equals("latitude")) {
                    latitude = dataSnapshot.getValue().toString();
                } else if (dataSnapshot.getKey().toString().equals("longitude")) {
                    longitude = dataSnapshot.getValue().toString();
                } else if (dataSnapshot.getKey().toString().equals("name")) {
                    name = dataSnapshot.getValue().toString();
                } else if (dataSnapshot.getKey().toString().equals("phone")) {
                    phone = dataSnapshot.getValue().toString();
                } else if (dataSnapshot.getKey().toString().equals("state")) {
                    state = dataSnapshot.getValue().toString();
                } else if (dataSnapshot.getKey().toString().equals("type")) {
                    type = dataSnapshot.getValue().toString();
                } else if (dataSnapshot.getKey().toString().equals("website")) {
                    website = dataSnapshot.getValue().toString();
                } else if (dataSnapshot.getKey().toString().equals("zip")) {
                    zip = dataSnapshot.getValue().toString();
                }

                mDetailView.setText("Location Name: " + keyString + "\n" +
                        "address: " + address + "\n" +
                        "city: " + city + "\n" +
                        "key: " + key + "\n" +
                        "latitude: " + latitude + "\n" +
                        "longitude: " + longitude + "\n" +
                        "name: " + name + "\n" +
                        "phone: " + phone + "\n" +
                        "state: " + state + "\n" +
                        "type: " + type + "\n" +
                        "website: " + website + "\n" +
                        "zip: " + zip + "\n");
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
