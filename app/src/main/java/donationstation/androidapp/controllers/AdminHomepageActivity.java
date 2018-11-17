package donationstation.androidapp.controllers;


import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import donationstation.androidapp.R;

public class AdminHomepageActivity extends AppCompatActivity {

    DatabaseReference mDatabaseReference;
    EditText emailSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_homepage);
    }


    /**
     * Change an user's account state to opposite.
     * @param view unused view
     */
    public void changeAccountState(View view) {
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);

        emailSearch = findViewById(R.id.emailSearch);
        final String email = emailSearch.getText().toString();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference mUserReference = mDatabaseReference.child("users");

        mUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean isChanged = false;

                String tempEmail = email.replace(".", ",");
                for (DataSnapshot member : dataSnapshot.getChildren()) {
                    if (member.getKey().toString().equals(tempEmail)) {
                        String tempState = member.child("accountState").getValue().toString();
                        System.out.println("tempStateAccount is " + tempState);
                        if (tempState.equals("true")) {
                            mUserReference.child(tempEmail).child("accountState").setValue(false);
                            Toast.makeText(AdminHomepageActivity.this,
                                    email + "'s status has changed from unlocked to locked", Toast.LENGTH_SHORT).show();
                        } else {
                            mUserReference.child(tempEmail).child("accountState").setValue(true);
                            Toast.makeText(AdminHomepageActivity.this,
                                    email + "'s status has changed from locked to unlocked", Toast.LENGTH_SHORT).show();
                        }
                        isChanged = true;
                    }
                }


                if (!isChanged) {
                    Toast.makeText(AdminHomepageActivity.this, email + " does not exist.", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
