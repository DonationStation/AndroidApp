package donationstation.androidapp.controllers;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.HashMap;
import java.util.Map;

import donationstation.androidapp.R;
import donationstation.androidapp.model.Member;

public class RegistrationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner accountSpinner;

    private Member _account;
    private EditText mPasswordView;
    private EditText mNameView;
    private EditText mUserView;
    private EditText mEmailView;
    private Spinner mAccountType;
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //grab dialog widgets for info later on
        accountSpinner = findViewById(R.id.accountType);

        //array of possible account types
        String [] accountTypes = {"Select Account Type", "Admin", "User", "Manager", "Employee"};

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, accountTypes);
        accountSpinner.setAdapter(adapter);

        //associating boxes in gui to objects
        mEmailView = findViewById(R.id.email);
        mPasswordView = findViewById(R.id.password);
        mNameView = findViewById(R.id.name);
        mUserView = findViewById(R.id.username);
        mAccountType = findViewById(R.id.accountType);
    }

    private void attemptRegistration() {
        final String email = mEmailView.getText().toString();
        final String password = mPasswordView.getText().toString();
        final String username = mUserView.getText().toString();
        final String name = mNameView.getText().toString();
        final String accountType = mAccountType.getSelectedItem().toString();


        // Todo
        if (accountType.equals("Select Account Type")) {
        }
        if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty() && !username.isEmpty() &&
                !accountType.isEmpty()) {
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String tempEmail = email.replace(".", ",");
                    if (dataSnapshot.child(tempEmail).exists()) {
                        mEmailView.setError("email already registered");
                        mEmailView.requestFocus();
                    } else {
                        writeNewUser(name, email, password, username, accountType);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } else {
            Toast.makeText(RegistrationActivity.this, "Please complete the information"
                    , Toast.LENGTH_SHORT).show();
        }
    }

    /**
     *
     * @param view view to pass to MainActivity
     * sends user back to the main screen
     */
    public void main(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        _account.setAccount(parent.getItemAtPosition(position).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        _account.setAccount("Guest");
    }

    /**
     *
     * @param view unused view
     * takes end-consumer's inputted registration information
     *             and attempts to register them.
     */
    public void accept(View view) {
        attemptRegistration();
    }
    private void updateUI(Member user, String accountType) {
        Intent intent;
        if (user != null) {
            switch (accountType.toLowerCase()) {
                case "admin":
                    intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    break;
                case "user":
                    intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    break;
                case "manager":
                    intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    break;
                case "employee":
                    intent = new Intent(this, EmployeeHomepageActivity.class);
                    startActivity(intent);
                    break;
            }
        }else {
            intent = new Intent(this, RegistrationActivity.class);
            startActivity(intent);
        }
    }

    /**
     *
     * @param name name pulled from consumer's input in activity_registration
     * @param email email pulled from consumer's input in activity_registration
     *              to properly register, name must not be in firebase
     * @param password password pulled from consumer's input in activity_registration
     * @param username username pulled from consumer's input in activity_registration
     * @param accountType accountType pulled from consumer's input in activity_registration
     *                    pulled from spinner with selective sub-type
     * used to create a new member in firebase of certain, defined subtypes
     */
    public void writeNewUser(String name, String email, String password, String username,
                              String accountType) {
        String key = email.replace(".", ",");
        Member user = new Member(name, email, password, username, accountType);
        if (accountType.equalsIgnoreCase("employee") ||
                accountType.equalsIgnoreCase("manager") ) {
            user.setLocation("AFD Station 4");
        }
        //adds user to /users in database
        ref.child(key).setValue(user);
        updateUI(user, accountType); //redirects to corresponding homepage

    }

}
