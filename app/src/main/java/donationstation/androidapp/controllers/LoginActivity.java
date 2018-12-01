package donationstation.androidapp.controllers;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import donationstation.androidapp.R;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private GoogleSignInClient mGoogleSignInClient;
    private final DatabaseReference ref = FirebaseDatabase.
            getInstance().getReference().child("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set up the login form.
        mEmailView = findViewById(R.id.username);

        mPasswordView = findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        findViewById(R.id.sign_in_button).setOnClickListener(this);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
        }
    }


    private void signIn() {
//        System.out.println("Bouncy");
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 0);
        Intent intent = new Intent(this, UserHomepageActivity.class);
        intent.putExtra("userType", "User");
        startActivity(intent);
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {


        // Store values at the time of the login attempt.
        final String email = mEmailView.getText().toString();
        final String password = mPasswordView.getText().toString();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean foundMember = false;
                boolean foundPassword = false;
                boolean accountLocked = false;
                int attempt = 0;
                //String member;
                String tempEmail = email.replace(".", ",");
                for(DataSnapshot member : dataSnapshot.getChildren()) {
                    if(member.getKey().toString().equals(tempEmail)) {
                        foundMember = true;
                        attempt = Integer.parseInt(member.child("attempts").getValue().toString());
                        if(member.child("password").getValue().toString().equals(password)
                                && member.child("accountState").getValue().
                                toString().equals("true")){
                            String memType = dataSnapshot.child(tempEmail).
                                    child("accountType").getValue().toString();
                            ref.child(tempEmail).child("attempts").setValue(0);
                            updateUI(memType);
                            foundPassword = true;
                        }
                    }
                }
                if (!foundMember) {
                    showProgress(false);
                    mEmailView.setError("Username Incorrect");
                    mEmailView.requestFocus();
                } else if (!foundPassword){
                    if (attempt >= 3) {
                        ref.child(tempEmail).child("accountState").setValue(false);
                        mPasswordView.setError("Too many attempts, your account has been locked");
                        showProgress(false);
                    } else {
                        ref.child(tempEmail).child("attempts").setValue(++attempt);
                        showProgress(false);
                        int attemptsLeft = 3 - attempt;
                        mPasswordView.setError("Password Incorrect " + attemptsLeft +
                                " attempts left.");
                    }
                    mPasswordView.requestFocus();

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
            showProgress(true);
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    //will need to update this method later so that method directs to corresponding homepage
    private void updateUI(String member) {
        final String userType = member; // pass the current userType to the next page
        Intent intent;
        if (member != null) {
            switch (member.toLowerCase()) {
                case "admin":
                    intent = new Intent(this, AdminHomepageActivity.class);
                    intent.putExtra("userType", userType);
                    startActivity(intent);
                    break;
                case "user":
                    intent = new Intent(this, UserHomepageActivity.class);
                    intent.putExtra("userType", userType);
                    startActivity(intent);
                    break;
                case "manager":
                    intent = new Intent(this, MainActivity.class);
                    intent.putExtra("userType", userType);
                    startActivity(intent);
                    break;
                case "employee":
                    intent = new Intent(this, EmployeeHomepageActivity.class);
                    intent.putExtra("userType", userType);
                    startActivity(intent);
                    break;
            }
        } else {
            intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    /**
     *
     * @param view view to pass to MainActivity
     * sends the end-consumer to MainActivity
     */
    public void cancel(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     *
     * @param view unused view
     * takes inputted credentials and checks against firebase
     * if successful, logs Member into Homepage corresponding to their
     *             Member sub-type
     */
    public void login(View view) {
        attemptLogin();
    }

    /**
     * Login as a guest user.
     * @param view unused view
     */
    public void loginAsGuest(View view) {
        Intent intent = new Intent(this, UserHomepageActivity.class);
        intent.putExtra("userType", "User");
        startActivity(intent);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

