package com.example.mahe.firebaseauthentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private Button register;
private EditText email;
private EditText pass;
private TextView check;

private FirebaseAuth firebaseAuth;
private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth=FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!=null){
            finish();
           startActivity(new Intent(getApplicationContext(),MainProfile.class));
         }

        progressDialog=new ProgressDialog(this);

        register=(Button)findViewById(R.id.button);
        email=(EditText)findViewById(R.id.editText);
        pass=(EditText)findViewById(R.id.editText2);

        check=(TextView)findViewById(R.id.textView2);

        register.setOnClickListener(this);
        check.setOnClickListener(this);
    }
    private  void registeruser(){
        String email1=email.getText().toString().trim();
        String pass1=pass.getText().toString().trim();
        if(TextUtils.isEmpty(email1)){
            Toast.makeText(getApplicationContext(),"empty Email address",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass1)){
            Toast.makeText(getApplicationContext(),"Empty Password!!",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering User....");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email1,pass1).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful()){
                   Toast.makeText(getApplicationContext(),"Registered Successful",Toast.LENGTH_SHORT).show();
                   finish();
                   Intent intent=new Intent(getApplication(),Main2Activity.class);
                   startActivity(intent);
               }
               else{
                   Toast.makeText(getApplicationContext(),"Error in Sign-In",Toast.LENGTH_SHORT).show();
               }
                progressDialog.dismiss();
            }
        });
    }
    @Override
    public void onClick(View view) {
      if(view == register){
           registeruser();
      }
      if(view == check){
          //will sign activity
          Intent intent = new Intent(this, Main2Activity.class);
          startActivity(intent);
      }
    }
}
