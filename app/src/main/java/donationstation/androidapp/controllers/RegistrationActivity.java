package donationstation.androidapp.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.EditText;


import donationstation.androidapp.R;
import donationstation.androidapp.model.Registration;
import donationstation.androidapp.model.User;
import donationstation.androidapp.model.Admin;

public class RegistrationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner accountSpinner;

    private Registration _account;
    private EditText mPasswordView;
    private EditText mNameView;
    private EditText mUserView;
    private EditText mEmailView;
    private Spinner mAccountType;

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


        Button mRegistrationSignInButton = findViewById(R.id.registerButton);
        mRegistrationSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegistration();
            }
        });
    }

    private void attemptRegistration() {
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String username = mUserView.getText().toString();
        String name = mNameView.getText().toString();
        String accountType = mAccountType.getSelectedItem().toString();


        System.out.println(email + password + username + name + accountType);
    }

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

    public void accept(View view) {
        String final
        Registration aUser;

        // make object based on the one's account_type
        if (accountSpinner.toString().equals("User")) {
            aUser = new User();
        } else if (accountSpinner.equals("Admin")) {
            aUser = new Admin();
        }

        // Check if there is a same account with the same name
        for (Registration r : Registration.getRegistrationArray()) {
            if (r.equals(aUser)) {
                error message
                Intent intent = new Intent(this, RegistrationActivity.class);
                startActivity(intent);
            }
        }

        // Add a new user to array and send to homepage.
        add it to array, and go to signin screen
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }
}
