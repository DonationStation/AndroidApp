package donationstation.androidapp.controllers;

import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.EditText;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;

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


//        Button mRegistrationSignInButton = findViewById(R.id.registerButton);
//        mRegistrationSignInButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                attemptRegistration();
//            }
//        });
    }

    private void attemptRegistration() {
        final String email = mEmailView.getText().toString();
        final String password = mPasswordView.getText().toString();
        final String username = mUserView.getText().toString();
        final String name = mNameView.getText().toString();
        String accountType = mAccountType.getSelectedItem().toString();

        // Todo
        if (accountType.equals("Select Account Type")) {
        }


//        if (checkRegistration(email, username)) {
//            Registration newUser;
//            switch (accountType) {
//                case "User":
//                    newUser = new User(name, email, password, username);
//                    break;
//                case "Admin":
//                    newUser = new Admin(name, email, password, username);
//                    break;
////                case "Manager":
////                    break;
////                case "Employee":
////                    break;
//                default:
//                    newUser = new User(name, email, password, username);
//                    break;
//            }
//            Registration.getRegistrationArray().add(newUser);
//        }
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        final DatabaseReference regRef = ref.child("users");
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d("success", "createdUserWithEmail");
                            FirebaseUser user = mAuth.getCurrentUser();
//                            DatabaseReference mDatabase;
//                            mDatabase = FirebaseDatabase.getInstance().getReference();
//                            mDatabase.push().setValue(new Registration(name, email, password, username) {
//                            });
                            updateUI();
                        } else{
                            Log.w("failure", "didNotCreateUserWithEmail", task.getException());
                        }
                    }
                });
    }

    private boolean checkRegistration(String email, String username) {
        // Check if there is a same account with the same name
        if (Registration.getRegistrationArray() == null) {
            return true;
        }
        if (Registration.getRegistrationArray().isEmpty()) {
            return true;
        }
        for (Registration member : Registration.getRegistrationArray()) {
            if (member.getUsername().equals(username) || member.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
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
        attemptRegistration();
        //Intent intent = new Intent(this, LoginActivity.class);
        //startActivity(intent);
    }
    private void updateUI() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
