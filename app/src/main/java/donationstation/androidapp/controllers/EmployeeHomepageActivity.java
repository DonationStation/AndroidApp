package donationstation.androidapp.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import donationstation.androidapp.R;

public class EmployeeHomepageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_homepage);
    }
    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void addDonations(View view) {
        Intent intent = new Intent(this, AddDonationActivity.class);
        startActivity(intent);
    }
//    public void viewDonations(View view) {
//        Intent intent = new Intent(this, DonationListActivity.class);
//        startActivity(intent);
//    }
}
