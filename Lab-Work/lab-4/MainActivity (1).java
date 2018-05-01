package com.example.databaselab4;



import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	database db;
	Context context = this;
	Button btn,deleteBtn , updateBtn,viewBtn;
	EditText edt1,edt2,edt3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		db = new database(this);
		btn = (Button)findViewById(R.id.button1);
		deleteBtn = (Button)findViewById(R.id.DeleteButton);
		updateBtn = (Button)findViewById(R.id.UpdateButton);
		viewBtn = (Button)findViewById(R.id.button2);
		edt1 = (EditText)findViewById(R.id.editText1);
		edt2 = (EditText)findViewById(R.id.editText2);
		edt3 = (EditText)findViewById(R.id.editText3);
		addAction();
		addDelete();
		addUpdate();
		addView();
	}
	void addView(){
		viewBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent(MainActivity.this,Display.class);
				
				startActivity(i);
			}
		});
			
	}
	void addUpdate(){
		updateBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(edt1.getText().toString().length() == 0 || edt2.getText().toString().length() == 0 || edt3.getText().toString().length() == 0){
					Toast.makeText(getApplicationContext(), "Empty Fields", Toast.LENGTH_LONG).show();
					return;
				}
				
				int rno = Integer.parseInt(edt1.getText().toString());
				String name = edt2.getText().toString();
				int marks = Integer.parseInt(edt3.getText().toString());
				
				db.updateRow(rno, name, marks);
				Intent i=new Intent(MainActivity.this,Display.class);
				
				startActivity(i);
			}
		});
	}
	void addDelete(){
		deleteBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(edt1.getText().toString().length() == 0 ){
					Toast.makeText(getApplicationContext(), "Empty Fields", Toast.LENGTH_LONG).show();
					return;
				}
				// TODO Auto-generated method stub
				AlertDialog.Builder adb = new AlertDialog.Builder(context);
				adb
					.setTitle("Delete")
					.setMessage("Are you sure you want to delete")
					.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							int rno = Integer.parseInt(edt1.getText().toString());
							db.deleteRow(rno);
							Intent i=new Intent(MainActivity.this,Display.class);
							
							startActivity(i);
						}
					})
					.setNegativeButton("No", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							
						}
					});
				AlertDialog alertDialog = adb.create();
				alertDialog.show();
			}
		});
	}
	void addAction(){
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(edt1.getText().toString().length() == 0 || edt2.getText().toString().length() == 0 || edt3.getText().toString().length() == 0){
					Toast.makeText(getApplicationContext(), "Empty Fields", Toast.LENGTH_LONG).show();
					return;
				}
				// TODO Auto-generated method stub
				int rn=Integer.parseInt(edt1.getText().toString());
				int marks=Integer.parseInt(edt3.getText().toString());
				String name=edt2.getText().toString();
				int nr = db.numberOfRows();
				db.insertStudent(rn, name, marks);
				
				if(db.numberOfRows() != nr){
					Intent i=new Intent(MainActivity.this,Display.class);
					
					startActivity(i);
				}
				else{
					Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
				}
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

					///$Bee codes

