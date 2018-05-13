package com.mit.project2;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      
        Button login; //object
       
        login=(Button) findViewById(R.id.button1);
      // title.setText("WELCOME");
       login.setText("LOGIN");
    
       
       
       
        login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				  final EditText uname;
					EditText pwd;
				 TextView title=(TextView) findViewById(R.id.textView1);
			       
				    String s;
				       
				        uname=(EditText) findViewById(R.id.name);
				        pwd=(EditText) findViewById(R.id.password);
				
				s=uname.getText().toString(); //text will get into
			
				Toast.makeText(getApplicationContext(), "hi", Toast.LENGTH_LONG).show();//pop msg
				Toast.makeText(getApplicationContext(), "WELCOME", Toast.LENGTH_LONG).show();//msg to display with or without time
				
				Intent i=new Intent(MainActivity.this,success.class); //validation first :-current java file to second:-success file to open
				i.putExtra("name", s); //user will enter text stored in s send back to the main activity for verification
				startActivity(i);
				Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_LONG).show();
				
			}
		}  );
        
   
    }


  
    
}
