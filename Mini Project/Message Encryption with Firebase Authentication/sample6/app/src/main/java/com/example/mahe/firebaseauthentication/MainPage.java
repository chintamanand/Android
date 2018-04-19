package com.example.mahe.firebaseauthentication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class MainPage extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;
    private EditText email;
    private Button reset;
    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        firebaseAuth=FirebaseAuth.getInstance();

        //if NO User is Logged In then he/she goes directly to the Main2Activity.class
        if(firebaseAuth.getCurrentUser()== null){
            finish();
            startActivity(new Intent(getApplicationContext(),Main2Activity.class));
        }

        if(firebaseAuth.getCurrentUser()!= null){
            finish();
            startActivity(new Intent(getApplicationContext(),Main2Activity.class));
        }
        email=(EditText)findViewById(R.id.editText10);
        reset=(Button)findViewById(R.id.button7);
        reset.setOnClickListener(this);
        back=(Button)findViewById(R.id.button3);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == reset){
            String email1 = email.getText().toString()  .trim();

            if (TextUtils.isEmpty(email1)) {
                Toast.makeText(getApplicationContext(), "Enter your email!", Toast.LENGTH_SHORT).show();
            }
            firebaseAuth.sendPasswordResetEmail(email1).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Check email to reset your password!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Fail to send reset password email!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        if(view == back){
           finish();
           startActivity(new Intent(getApplicationContext(),Main2Activity.class));
        }
    }
}
