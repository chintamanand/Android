package com.example.student.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    EditText num1;
    EditText num2;

    Button bu11; //addition
    Button bu13; //multiply
    Button bu14; //division
    Button bu15; //percentage
    Button bu12;  //answer
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num1=(EditText) findViewById(R.id.editText);
        num2=(EditText) findViewById(R.id.editText2);

        bu11=(Button) findViewById(R.id.button11);
        bu12=(Button) findViewById(R.id.button12);
        bu13=(Button) findViewById(R.id.button13);
        bu14=(Button) findViewById(R.id.button14);
        bu15=(Button) findViewById(R.id.button15);


        Result = (TextView) findViewById(R.id.editText3);

        // set a listener
        bu11.setOnClickListener(this);
        bu12.setOnClickListener(this);
        bu13.setOnClickListener(R.id.button13);
        bu14.setOnClickListener(R.id.button14);
        bu15.setOnClickListener(R.id.button15);

    }

    public void onClick()
    {
        // TODO Auto-generated method stub
        float num11 = 0;
        float num22 = 0;
        float result = 0;

        // check if the fields are empty
        if (TextUtils.isEmpty(num1.getText().toString())
                || TextUtils.isEmpty(num2.getText().toString())) {
            return;
        }

        // read EditText and fill variables with numbers
        num11 = Float.parseFloat(num1.getText().toString());
        num22 = Float.parseFloat(num2.getText().toString());

        // defines the button that has been clicked and performs the corresponding operation
        // write operation into oper, we will use it later for output
        switch (v.getId()) {
            case R.id.button11:
                oper = "+";
                result = num11 + num22;
                break;
            case R.id.btnSub:
                oper = "-";
                result = num11 - num22;
                break;
            case R.id.btnMult:
                oper = "*";
                result = num11 * num22;
                break;
            case R.id.btnDiv:
                oper = "/";
                result = num11 / num22;
                break;
            default:
                break;
        }

        // form the output line
        tvResult.setText(num1 + " " + oper + " " + num2 + " = " + result);
    }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
