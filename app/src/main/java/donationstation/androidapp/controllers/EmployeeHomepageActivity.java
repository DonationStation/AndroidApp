package donationstation.androidapp.controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import donationstation.androidapp.R;

/**
 * Class to represent homepage for employees
 */
public class EmployeeHomepageActivity extends Activity {

    private String userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_homepage);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            userType = null;
        } else {
            userType = bundle.getString("userType");
        }
    }

    /**
     *
     * @param view view to pass to MainActivity
     * logs the Member of sub- type Employee out
     */
    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    /**
     *
     * @param view view to pass to AddDonationActivity
     * allows the Member of sub- type Employee to add a Donation
     */
    public void addDonations(View view) {
        Intent intent = new Intent(this, AddDonationActivity.class);
        startActivity(intent);
    }
    /**
     *
     * @param view view to pass to LocationListActivity
     * allows the Member of sub- type Employee to add a view list of Locations
     */
    public void viewDonations(View view) {
        Intent intent = new Intent(this, LocationListActivity.class);
        intent.putExtra("userType", userType);
        startActivity(intent);
    }
}
