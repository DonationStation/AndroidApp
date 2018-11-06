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
    public void logout(View view) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void viewLocationsList(View view) {
        Intent intent = new Intent(this, LocationListActivity.class);
        intent.putExtra("userType", userType);
        startActivity(intent);
    }
    public void viewLocationsMap(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("userType", userType);
        startActivity(intent);
    }
    public void searchItems(View view) {
        Intent intent = new Intent(this, UserItemSearchActivity.class);
        startActivity(intent);
    }
}
