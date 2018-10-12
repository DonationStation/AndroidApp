package donationstation.androidapp.controllers;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

//import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import donationstation.androidapp.R;
import donationstation.androidapp.controllers.HomepageActivity;
import donationstation.androidapp.controllers.RegistrationActivity;
import donationstation.androidapp.model.Registration;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "user"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    //private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

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

//        Button mEmailSignInButton = findViewById(R.id.email_sign_in_button);
//        mEmailSignInButton.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                attemptLogin();
//            }
//        });
//
//
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        final FirebaseAuth  mAuth = FirebaseAuth.getInstance();
//        if (mAuth != null) {
//            return;
//        }
        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                      if (task.isSuccessful()) {
                          Log.d("success", "signInWithEmail:success");
                          FirebaseUser user = mAuth.getCurrentUser();
                          updateUI(user);
                      } else {
                          Log.w("failure", "signInWithEmail:failure", task.getException());
                      }
                    }
                });
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);


        boolean cancel = false;
        View focusView = null;

//        // Check for a valid password, if the user entered one.
//        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
//            mPasswordView.setError(getString(R.string.error_invalid_password));
//            focusView = mPasswordView;
//            cancel = true;
//        }
//
//        // Check for a valid email address.
//        if (TextUtils.isEmpty(email)) {
//            mEmailView.setError(getString(R.string.error_field_required));
//            focusView = mEmailView;
//            cancel = true;
//        } else if (!isEmailValid(email)) {
//            mEmailView.setError(getString(R.string.error_invalid_email));
//            focusView = mEmailView;
//            cancel = true;
//        }

//        if (cancel) {
//            // There was an error; don't attempt login and focus the first
//            // form field with an error.
//            focusView.requestFocus();
//        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            //mAuthTask = new UserLoginTask(email, password);
            //mAuthTask.onPostExecute(true);
        //}
    }

//    private boolean isEmailValid(String email) {
//        //TODO: Replace this with your own logic
//        return email.contains("@");
//    }
//
//    private boolean isPasswordValid(String password) {
//        //TODO: Replace this with your own logic
//        return password.length() > 4;
//    }

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

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
//    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
//
//        protected final String mEmail;
//        protected final String mPassword;
//
//        UserLoginTask(String email, String password) {
//            mEmail = email;
//            mPassword = password;
//        }
//
//        @Override
//        protected Boolean doInBackground(Void... params) {
//            // TODO: attempt authentication against a network service.
//
//            try {
//                // Simulate network access.
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                return false;
//            }
//
////            TODO: register the new account here.
//            if(!mEmail.isEmpty() && !mPassword.isEmpty()) {
////
////                registration();
//                return false;
//            }
//            return true;
//        }

//        // Remove after M3 and uncomment method below
//        @Override
//        protected void onPostExecute(final Boolean success) {
//            mAuthTask = null;
//            showProgress(false);
//            for (Registration member : Registration.getRegistrationArray()) {
//                if ((member.getUsername().equals(mEmail) && member.getPassword().equals(mPassword)) || (member.getEmail().equals(mEmail) && member.getPassword().equals(mPassword))) {
//                    updateUI();
//                    return;
//                }
//            }
////            mPasswordView.setError(getString(R.string.error_incorrect_password));
//            mPasswordView.setError("Username or Password Incorrect");
//
//            mPasswordView.requestFocus();
//        }

        // @Override
        // protected void onPostExecute(final Boolean success) {
        //     mAuthTask = null;
        //     showProgress(false);

        //     if (success) {
        //         homepage();
        //     } else {
        //         mPasswordView.setError(getString(R.string.error_incorrect_password));
        //         mPasswordView.requestFocus();
        //     }
        // }
    //}
    //will need to update this method later so that method directs to corresponding homepage
    private void updateUI(FirebaseUser user) {
        if (user.getEmail().compareTo("test@example.com") == 0) {
         Intent intent = new Intent(this, HomepageActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, EmployeeHomepageActivity.class);
            startActivity(intent);
        }

    }
    private void registration() {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    public void cancel(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        attemptLogin();
    }
}

