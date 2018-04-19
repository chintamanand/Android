package com.example.mahe.firebaseauthentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.internal.firebase_auth.zzao;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.auth.UserInfo;

import org.w3c.dom.Text;

import java.util.List;

public class MainProfile extends AppCompatActivity implements View.OnClickListener {
private TextView logout;
private TextView Verify1;
private TextView status;
private TextView statusr;

private FirebaseAuth firebaseAuth;
ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profile);

        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();

        //if NO User is Logged In then he/she goes directly to the Main2Activity.class
        if(firebaseAuth.getCurrentUser()== null){
            finish();
            startActivity(new Intent(getApplicationContext(),Main2Activity.class));
        }
        if(firebaseAuth.getCurrentUser()!= null){
            finish();
            startActivity(new Intent(getApplicationContext(),Mainfunction1.class));
        }

        FirebaseUser user=firebaseAuth.getCurrentUser();
        Toast.makeText(getApplicationContext(),"Welcome "+user.getEmail(),Toast.LENGTH_SHORT).show();

        status=(TextView)findViewById(R.id.textView13);
        logout =(TextView)findViewById(R.id.textView11);
        Verify1=(TextView)findViewById(R.id.textView10);
        statusr=(TextView)findViewById(R.id.textView14);

        //statusr.setText("Account not Verified");

        logout.setOnClickListener(this);
        statusr.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == logout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(getApplicationContext(),Main2Activity.class));
        }
        if(view == statusr){
            verifyEmail();

        }
    }

    private void verifyEmail() {
        final FirebaseUser user=firebaseAuth.getCurrentUser();
        progressDialog.setMessage("Sending Verfication Code....");
        progressDialog.show();
        user.sendEmailVerification().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Verification email sent to " + user.getEmail(), Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(getApplicationContext(),Mainfunction1.class));
                } else {
                    //Log.e(TAG, "sendEmailVerification", task.getException());
                    Toast.makeText(getApplicationContext(), "Failed to send verification email.", Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();
            }
        });
    }


}
