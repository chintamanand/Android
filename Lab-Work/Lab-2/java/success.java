package com.mit.project2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class success extends Activity {
	Bundle b; //it can restore to current file
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.success);
	        b=getIntent().getExtras();
	        String s=b.getString("name"); //identifier used in the main activity.java to check (used here)
	        TextView t=(TextView) findViewById(R.id.textView1);
	        t.setText(s);
	        t.setTextSize(25);
	        
	        
}}
