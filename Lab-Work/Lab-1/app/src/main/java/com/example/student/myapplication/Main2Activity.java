package com.example.student.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button button;

        button = (Button) findViewById(R.id.login);
        // title.setText("WELCOME");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
               final EditText word=(EditText) findViewById(R.id.editText3);
                final String word1=word.getText().toString();
                String word2="";
                int l=word1.length();
                int i,j;
                for(i=l-1;i>=0;i--){
                   word2=word2+word1.charAt(i);
                }
                Toast.makeText(getApplicationContext(), word2, Toast.LENGTH_LONG).show();
            }
        });
    }
}