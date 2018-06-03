package com.example.mahe.studentmangementsystem;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Class declaration with Object Name.
    DatabaseHelper mydb;
    private EditText t1;
    private EditText t2;

    private Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb=new DatabaseHelper(this);

        t1=(EditText)findViewById(R.id.editText);
        t2=(EditText)findViewById(R.id.editText2);
        b1=(Button)findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username2=t1.getText().toString();
                String password2=t2.getText().toString();
                int result=mydb.checkloginp(username2,password2);
                mydb.getReadableDatabase();


                ////id,username,password
                //principle class
                PrincipleLogin p=new PrincipleLogin(10,"anand","anand");
                PrincipleLogin p1=new PrincipleLogin(11,"kiran","kiran");

                //String name,int roll,int marks, int age, String gender, int class1
                student s=new student("nikihil",1,90,17,"m",10);
                student s1= new student("ramesh",2,79,18,"m",11);


                Cursor cursor = SQLiteDatabase.rawQuery("SELECT * FROM " + DatabaseHelper.Principlelogin_TABLE_NAME + " WHERE " + DatabaseHelper.username1 + " = ?" +"AND "+ DatabaseHelper.password1 + " = ?", new String[]{username2, password2});
                if (cursor != null) {
                    if (cursor.getCount() > 0) {
                        Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "Login error", Toast.LENGTH_SHORT).show();
                    }
                }
                cursor.close();

                if(username2.equals(DatabaseHelper.username1) && password2.equals(DatabaseHelper.password1)){
                    Toast.makeText(getApplicationContext(),"Login Successfull",Toast.LENGTH_SHORT).show();
                    finish();
                    Intent intent=new Intent(getApplication(),Main2Activity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Invaild Username and Password",Toast.LENGTH_SHORT).show();
                    t1.setText("");
                    t2.setText("");
                }}});

    }



}

