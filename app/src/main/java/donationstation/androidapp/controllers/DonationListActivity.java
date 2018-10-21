package donationstation.androidapp.controllers;

import android.os.Bundle;
import android.app.Activity;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;

import donationstation.androidapp.R;
import donationstation.androidapp.model.Donation;
import donationstation.androidapp.model.SimpleModel;

public class DonationListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_list);
    }

}
