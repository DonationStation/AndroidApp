package donationstation.androidapp.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import donationstation.androidapp.R;

public class UserHomepageActivity extends Activity {

    private String userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_homepage);

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
     * logs the Member of sub-type User out
     */
    public void logout(View view) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    /**
     *
     * @param view view to pass to LocationListActivity
     * takes Member of sub-type Employee to list of locations
     */
    public void viewLocationsList(View view) {
        Intent intent = new Intent(this, LocationListActivity.class);
        intent.putExtra("userType", userType);
        startActivity(intent);
    }
    /**
     *
     * @param view view to pass to MapsActivity
     * takes Member of sub-type User to a map of current locations
     */
    public void viewLocationsMap(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("userType", userType);
        startActivity(intent);
    }
    /**
     *
     * @param view view to pass to UserItemSearchActivity
     * takes Member of sub-type User to item search page
     */
    public void searchItems(View view) {
        Intent intent = new Intent(this, UserItemSearchActivity.class);
        startActivity(intent);
    }
}
