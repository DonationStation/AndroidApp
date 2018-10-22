package donationstation.androidapp.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import donationstation.androidapp.R;


public class MainActivity extends AppCompatActivity {
    public static String TAG = "MY_APP";
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mAuth = FirebaseAuth.getInstance();
        // test
    }
//    @Override
//    public void onStart() {
//        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (currentUser != null) {
//            updateUI();
//        }
        //readLocationDataFile();
//    }

    //will need to update this method later so that method directs to corresponding homepage
//    private void updateUI() {
//        Intent intent = new Intent(this, HomepageActivity.class);
//        startActivity(intent);
//    }

    public void login(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    public void register(View view) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }
}
