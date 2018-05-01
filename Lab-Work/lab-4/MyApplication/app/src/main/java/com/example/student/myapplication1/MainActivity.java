package com.example.student.myapplication1;



import android.os.Bundle;  //import android.database.sqlite
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    database db;
    Context context=this;
    Button viewbtn,deletebtn,updatebtn;
    EditText edt1,edt2,edt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new database(this);
        viewbtn=(Button)findViewById(R.id.button);
        deletebtn=(Button)findViewById(R.id.button3);
        updatebtn=(Button)findViewById(R.id.button2);

        edt1=(EditText)findViewById(R.id.editText); //name edittext
        edt2=(EditText)findViewById(R.id.editText2); //rollno edittext
        edt3=(EditText)findViewById(R.id.editText3); //marks edittext
         addAction();
         addDelete();
         addUpdate();
    }
    public void addAction()
    {
        viewbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(edt1.getText().toString().length() == 0 || edt2.getText().toString().length() == 0 || edt3.getText().toString().length() == 0){
                    Toast.makeText(getApplicationContext(), "Empty Fields", Toast.LENGTH_LONG).show();
                    return;
                }
                // TODO Auto-generated method stub
                int rn=Integer.parseInt(edt2.getText().toString());
                int marks=Integer.parseInt(edt3.getText().toString());
                String name=edt1.getText().toString();
                int nr = db.numberOfRows();
                db.insertStudent(rn, name, marks);

                if(db.numberOfRows()!= nr){
                    Intent i=new Intent(MainActivity.this,Display.class);

                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    }
    public void addUpdate()
    {
        updatebtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(edt1.getText().toString().length() == 0 || edt2.getText().toString().length() == 0 || edt3.getText().toString().length() == 0){
                    Toast.makeText(getApplicationContext(), "Empty Fields", Toast.LENGTH_LONG).show();
                    return;
                }

                int rno = Integer.parseInt(edt2.getText().toString());
                String name = edt1.getText().toString();
                int marks = Integer.parseInt(edt3.getText().toString());

                db.updateRow(rno, name, marks);
                Intent i=new Intent(MainActivity.this,Display.class);

                startActivity(i);
            }
        });
    }
    public void addDelete()
    {
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edt1.getText().toString().length()=='0'||edt2.getText().toString().length()=='0' ||edt3.getText().toString().length()=='0')
                {
                    Toast.makeText(getApplicationContext(),"empty fields",Toast.LENGTH_LONG).show());
                    return();
                }
                AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(context);
                  alertDialogBuilder.setMessage("want to delete tables from database");
                  alertDialogBuilder.setCancelable(false);
                  alertDialogBuilder.setPositiveButton("YES",new DialogInterface.OnClickListener(){

                  });
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
