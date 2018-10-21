package donationstation.androidapp.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import donationstation.androidapp.R;
import donationstation.androidapp.model.LocationModel;

public class LocationDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);

        // Call location data list and create a textview for the list
        LocationModel locations = LocationModel.INSTANCE;
        TextView locationDetail = findViewById(R.id.locationDetail);

        // Retrieve the position
        Bundle bundle = getIntent().getExtras();
        int position;
        if (bundle == null) {
            position = 0;
        } else {
            position = bundle.getInt("position");
        }

        // assign the detail into locationDetail using position
        String detail = "Name: " + locations.getItems().get(position).getName() + "\n" +
                "Type: " + locations.getItems().get(position).getType() + "\n" +
                "Longitude: " + locations.getItems().get(position).getLongitude() + "\n" +
                "Latitude: " + locations.getItems().get(position).getLatitude() + "\n" +
                "Address: " + locations.getItems().get(position).getAddress() + "\n" +
                "Phone Number: " + locations.getItems().get(position).getPhone();

        locationDetail.setText(detail);
    }
}
