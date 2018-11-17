package donationstation.androidapp.controllers;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;

import donationstation.androidapp.R;

/**
 * Main page
 */
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

    /**
     *
     * @param view view to pass to LoginActivity
     * sends end-consumer to login screen
     */
    public void login(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    /**
     *
     * @param view view to pass to RegistrationActicity
     * sends end-consumer to registration screen
     */
    public void register(View view) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }
}
