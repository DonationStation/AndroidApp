package donationstation.androidapp.controllers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import donationstation.androidapp.R;
import donationstation.androidapp.model.Location;
import donationstation.androidapp.model.LocationModel;

public class LocationListActivity extends AppCompatActivity {
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("LocationJSON");
    //LocationModel locations = LocationModel.INSTANCE;
    LocationModel list = new LocationModel();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);

        // Call location data list

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot locations: dataSnapshot.getChildren()) {
                    Location loc = locations.getValue(Location.class);
                    int key = Integer.parseInt(dataSnapshot.child("Key").getValue().toString());
                    String name = dataSnapshot.child("Name").getValue().toString();
                    String latitude = dataSnapshot.child("Latitude").getValue().toString();
                    String longitude = dataSnapshot.child("Longitude").getValue().toString();
                    String address = dataSnapshot.child("Address").getValue().toString();
                    String city = dataSnapshot.child("City").getValue().toString();
                    String state = dataSnapshot.child("State").getValue().toString();
                    String zip = dataSnapshot.child("Zip").getValue().toString();
                    String type = dataSnapshot.child("Type").getValue().toString();
                    String phone = dataSnapshot.child("Phone").getValue().toString();
                    String website = dataSnapshot.child("Website").getValue().toString();
                    loc = new Location(key, name, latitude, longitude, address, city, state,
                            zip, type, phone, website);
                    list.addItem(loc);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // Instantiate recyclerView
        RecyclerView locationLists = (RecyclerView) findViewById(R.id.locationLists);
        locationLists.setLayoutManager(new LinearLayoutManager(this));

        // Initialize locationNames for the list on locationList Activity
//        String[] locationNames = {locations.getItems().get(0).getName(), locations.getItems().get(1).getName(),
//                locations.getItems().get(2).getName(), locations.getItems().get(3).getName(),
//                locations.getItems().get(4).getName(), locations.getItems().get(5).getName()};

        // Initialize locationNames for the list on locationList Activity
        String[] locationNames = new String[6];
        for (int i = 0; i < 6; i++) {
            locationNames[i] = list.findItemByKey(i).getName();
        }
        // Set the list on the recyclerView adapter
        locationLists.setAdapter(new locationListAdapter(locationNames));
    }


    // RecyclerView Adapter
    public class locationListAdapter extends RecyclerView.Adapter<locationListAdapter.locationListViewHolder> {

        private String[] data;

        // Take a list
        public locationListAdapter(String[] data) {
            this.data = data;
        }

        @NonNull
        @Override
        public locationListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.list_location_layout, parent, false);
            return new locationListViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull locationListViewHolder holder, int position) {
            // Set the position value as final to pass it to LocationDetailActivity
            // position is the index on the order of vertical list on this activity
            final int pos = position;
            String title = data[position];
            holder.locationName.setText(title);

            // When click each item
            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LocationListActivity.this, LocationDetailActivity.class);
                    // Get ready for passing position to the next activity
                    intent.putExtra("position", pos);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return data.length;
        }

        public class locationListViewHolder extends RecyclerView.ViewHolder {
            public TextView locationName;
            public LinearLayout linearLayout;

            public locationListViewHolder(View itemView) {
                super(itemView);
                locationName = (TextView) itemView.findViewById(R.id.locationName);
                linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
            }
        }
    }
}
