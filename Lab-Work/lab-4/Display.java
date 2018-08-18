package com.example.databaselab4;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.view.Menu;
import android.widget.TextView;

public class Display extends Activity {
	Bundle b;
	String details;
	database db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display);
		b=getIntent().getExtras();
		db = new database(this);
		TextView tv = (TextView)findViewById(R.id.textView1);
		Cursor res =db.getData();
		details="";
		while(res.moveToNext()){
			
			details += res.getString(0) + " " + res.getString(1) + " " + res.getString(2);
			details += '\n';
		}
		tv.setText(details);
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display, menu);
		return true;
	}

}
					///$Bee codes
