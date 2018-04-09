package com.example.student.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.LoginFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login;

        login=(Button) findViewById(R.id.login);
        // title.setText("WELCOME");



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                final EditText username = (EditText) findViewById(R.id.editText2);
                EditText password = (EditText) findViewById(R.id.editText);
                final String user = username.getText().toString();
                final String pwd = password.getText().toString();
                if (user.equals("admin") && pwd.equals("admin")) {
                    Toast.makeText(getApplicationContext(), "hi welcome admin", Toast.LENGTH_LONG).show();
                    Intent i=new Intent(MainActivity.this,Main2Activity.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"INCORRECT LOGIN",Toast.LENGTH_LONG).show();
                }

            }
        } );
        }
    }


