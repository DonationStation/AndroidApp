package donationstation.androidapp.controllers;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import donationstation.androidapp.R;
import donationstation.androidapp.model.GMailSender;

//imports to send mail
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ForgotPasswordActivity extends AppCompatActivity {
    private EditText mEmail;
    private TextView mResult;
    private final DatabaseReference ref = FirebaseDatabase.
            getInstance().getReference().child("users");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        mEmail = findViewById(R.id.email);
        mResult = findViewById(R.id.resultBox);

    }

    /**
     * method to send password to inputted email
     * checks if email is in database
     */
    public void attemptSendPassword(View view) {
        // grabbing text
        final String email = mEmail.getText().toString();
        // check that email is in database
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            boolean emailFound = false;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String tempEmail = email.replace(".", ",");
                for(DataSnapshot member : dataSnapshot.getChildren()) {
                    if(member.getKey().toString().equals(tempEmail)) {
                        emailFound = true;
                        System.out.println("Email found!");
                        final String email = member.getKey().toString();
                        final String password = member.child("password").getValue().toString();
                        sendEmail(email, password);
                    }
                }

                if(!emailFound) {
                    mResult.setText("Email Hasn't Been Registered!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }



        });
    }

    private void sendEmail(final String email, final String password) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    GMailSender sender = new GMailSender("donationstation2018@gmail.com",
                            "cs234086a");
                    String emailTo = email.replace(",", ".");
                    System.out.println("Attempting to email " + emailTo);
                    String subject = "Your Password for DonationStation!";
                    String body = "Donation Station Account: " + emailTo + " has associated Password: " +
                        password;
                    sender.sendMail(subject, body,
                            "donationstation2018@gmail.com", emailTo);
                    mResult.setText("Email Has Been Sent!");
                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                }
            }

        }).start();
//        GMailSender sender = new GMailSender("donationstation2018@gmail.com",
//                "cs234086a");
//        String emailTo = email.replace(",", ".");
//        System.out.println("Attempting to email " + emailTo);
//        String subject = "Your Password for DonationStation!";
//        String body = "Donation Station Account: " + emailTo + " has associated Password: " +
//                password;
//        try {
//            sender.sendMail(subject, body, "donationstation2018@gmail.com", emailTo);
//            System.out.println("should have sent...");
//        } catch (Exception e) {
//            System.out.println("Something went wrong...");
//
//        }




    }


}
