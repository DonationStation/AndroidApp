package donationstation.androidapp.controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;



import donationstation.androidapp.R;

/**
 * Class for a user homepage
 */
public class UserHomepageActivity extends Activity {

    private String userType;
//    private GoogleSignInClient mGoogleSignInClient;
//    private GoogleApiClient mGoogleApiClient;

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

//        findViewById(R.id.g_sign_out).setOnClickListener((View.OnClickListener) this);
//
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                .build();
//        mGoogleApiClient.connect();
//        super.onStart();

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
//        signOut();
//        System.out.println("Bouncy");
//        System.out.println(mGoogleApiClient.isConnected());
//        if (mGoogleApiClient.isConnected()) {
//                    Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
//            mGoogleApiClient.disconnect();
//            mGoogleApiClient.connect();
//        }
    }



//    public void onClick(View v) {
////        System.out.println("Bouncy");
//        switch (v.getId()) {
//            case R.id.g_sign_out:
//                if (mGoogleApiClient.isConnected()) {
////                    Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
//                    mGoogleApiClient.disconnect();
//                    mGoogleApiClient.connect();
//                }
//                break;
//        }
//    }

//    private void signOut() {
//        System.out.println("Bouncy");
//        mGoogleSignInClient.signOut()
//                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//
//                    }
//                });
//    }
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
