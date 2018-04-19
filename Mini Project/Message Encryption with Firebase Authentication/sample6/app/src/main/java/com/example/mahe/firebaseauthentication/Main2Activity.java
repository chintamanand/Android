package com.example.mahe.firebaseauthentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{
private EditText Email;
private EditText Passw;
private Button signin;
private Button forgot1;
private TextView register;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();

         //if User is Already Logged In then he/she goes directly to the MainProfile.class
        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(),MainProfile.class));
        }

        Email=(EditText)findViewById(R.id.editText3);
        Passw=(EditText)findViewById(R.id.editText4);
        signin=(Button)findViewById(R.id.button2);
        forgot1=(Button)findViewById(R.id.button10);

        //new created textview --register
        register=(TextView)findViewById(R.id.textView9);

        signin.setOnClickListener(this);
        forgot1.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == signin){
            //check user creditnial against the firebase Data
            performLogin();
        }
        if(view == forgot1){
            progressDialog.setMessage("Sending Request to Server...");
            progressDialog.show();
            String email1 = Email.getText().toString().trim();
            if (TextUtils.isEmpty(email1)) {
                Toast.makeText(getApplicationContext(), "Enter your email!", Toast.LENGTH_SHORT).show();
            }
            firebaseAuth.sendPasswordResetEmail(email1).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Check your email to reset your password!", Toast.LENGTH_SHORT).show();
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Fail to send reset password email!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        if(view == register){
            finish();
            Intent i=new Intent(this,MainActivity.class);
            startActivity(i);
        }
    }


    private void performLogin() {
        String email=Email.getText().toString().trim();
        String password=Passw.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(),"empty Email address",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(),"Empty Password!!",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Sigining...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Log.d(TAG, "login success");

                            Toast.makeText(getApplicationContext(), "login success", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                            startActivity(new Intent(getApplicationContext(),MainProfile.class));
                        } else {
                            //Log.e(TAG, "Login fail", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                });

    }
}
