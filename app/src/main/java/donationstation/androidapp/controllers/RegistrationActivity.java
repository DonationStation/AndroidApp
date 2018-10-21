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
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import donationstation.androidapp.R;
import donationstation.androidapp.model.Employee;
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
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

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

//        final FirebaseAuth mAuth = FirebaseAuth.getInstance(); //ref to database
//        mAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if(task.isSuccessful()){ //able to create user
//                            Log.d("success", "createdUserWithEmail");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            writeNewUser(name, email, password, username, accountType);
//                            updateUI(user, accountType); //redirects to corresponding homepage
//                        } else{ //failed at creating user
//                            Log.w("failure", "didNotCreateUserWithEmail", task.getException());
//                            Toast.makeText(RegistrationActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null, null);
//                        }
//                    }
//                });
        writeNewUser(name, email, password, username, accountType);
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
    }
    private void updateUI(Registration user, String accountType) {
        Intent intent;
        if (user != null) {
            switch (accountType) {
                case "Admin":
                    intent = new Intent(this, HomepageActivity.class);
                    startActivity(intent);
                    break;
                case "User":
                    intent = new Intent(this, HomepageActivity.class);
                    startActivity(intent);
                    break;
                case "Manager":
                    intent = new Intent(this, HomepageActivity.class);
                    startActivity(intent);
                    break;
                case "Employee":
                    intent = new Intent(this, EmployeeHomepageActivity.class);
                    startActivity(intent);
                    break;
            }
        }else {
            intent = new Intent(this, RegistrationActivity.class);
            startActivity(intent);
        }
//        if (user.getEmail().compareTo("test@example.com") == 0) {
//            Intent intent = new Intent(this, HomepageActivity.class);
//            startActivity(intent);
//        } else {
//            Intent intent = new Intent(this, EmployeeHomepageActivity.class);
//            startActivity(intent);
//        }
    }
    private void writeNewUser(String name, String email, String password, String username,
                              String accountType) {
        //adds user to /users in database
        String key = ref.child("users").push().getKey();
        Registration user = new Registration (name, email, password, username, accountType);
        Map<String, Object> postValues = user.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/users/" + key, postValues);

        ref.updateChildren(childUpdates);
        updateUI(user, accountType); //redirects to corresponding homepage

    }

}
