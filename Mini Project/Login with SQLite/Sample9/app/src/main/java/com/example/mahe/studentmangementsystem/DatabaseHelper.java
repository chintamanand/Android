package com.example.mahe.studentmangementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Student.db";


    public static final String STUDENT_TABLE_NAME = "student";
    //These are the 4 columns Names
    public static final String STUDENT_ROLLNUM = "rollnum";
    public static final String STUDENT_NAME = "name";
    public static final String STUDENT_MARKS = "marks";
    public static final String STUDENT_AGE = "age";
    public static final String STUDENT_GENDER = "gender";
    public static final String STUDENT_ClASS ="class";

    //table-2
    public static final String Principlelogin_TABLE_NAME = "principlelogin";
    public static final String principle_id = "id";
    public static final String username1 = "username";
    public static final String password1 = "password";

    public DatabaseHelper(Context context) {
     //   super(context, name, factory, version);
        super(context,DATABASE_NAME,null,1);
        //it create at the database of writeable database

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + STUDENT_TABLE_NAME + "("+ STUDENT_ROLLNUM + " INTEGER PRIMARY KEY, "+ STUDENT_NAME +" TEXT,"+ STUDENT_MARKS +" INTEGER,"+STUDENT_AGE +" INTEGER,"+ STUDENT_GENDER +" TEXT ,"+ STUDENT_ClASS +" INTEGER)");
       // sqLiteDatabase.execSQL("create table " + Principle_TABLE_NAME + "("+STUDENT_ROLLNUM1+" integer primary key , studentname text,studentage integer)");
        sqLiteDatabase.execSQL("CREATE TABLE " + Principlelogin_TABLE_NAME + "("+ principle_id + " INTEGER PRIMARY KEY, "+ username1 +" TEXT,"+ password1 +" TEXT)");

        int roll1=10;

        //Data for Student Table
        sqLiteDatabase.execSQL("INSERT INTO " + STUDENT_TABLE_NAME  + "(" + STUDENT_ROLLNUM + "," + STUDENT_NAME + ","+ STUDENT_MARKS +","+STUDENT_AGE + ","+ STUDENT_GENDER +","+ STUDENT_ClASS +") VALUES (1,nikhil,91,16,m,10)");

        //Data for Principle Login
        sqLiteDatabase.execSQL("INSERT INTO " + Principlelogin_TABLE_NAME + "(" + principle_id + "," + username1 + ","+ password1 +") VALUES (10,kiran,kiran)");
        sqLiteDatabase.execSQL("INSERT INTO " + Principlelogin_TABLE_NAME + "(" + principle_id + "," + username1 + ","+ password1 +") VALUES (11,anand,anand)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+STUDENT_TABLE_NAME);
        //sqLiteDatabase.execSQL("drop table if exists "+Principle_TABLE_NAME );
        sqLiteDatabase.execSQL("drop table if exists "+Principlelogin_TABLE_NAME );
        onCreate(sqLiteDatabase);
    }


    public void addUser(PrincipleLogin principleLogin) {

        //get writable database
        SQLiteDatabase db = this.getWritableDatabase();

        //create content values to insert
        ContentValues values = new ContentValues();

        //Put username in  @values
        values.put(principle_id,principleLogin.id);

        //Put email in  @values
        values.put(username1 ,principleLogin.username);

        //Put password in  @values
        values.put(password1, principleLogin.password);

        // insert row
        long todo_id = db.insert(Principlelogin_TABLE_NAME, null, values);
    }


    //Authenticate the Principle
    public PrincipleLogin Authenticate(PrincipleLogin principleLogin) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Principlelogin_TABLE_NAME ,// Selecting Table
                new String[]{principle_id, username1, password1},//Selecting columns want to query
                username1 + "=?",
                new String[]{password1+"=?"},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email
            PrincipleLogin principleLogin1 = new PrincipleLogin(cursor.getInt(0), cursor.getString(1), cursor.getString(2));

            //Match both passwords check they are same or not
            //if (PrincipleLogin.password.equalsIgnoreCase(principleLogin1.password)) {
                //return principleLogin1;
           // }
        }

        //if user password does not matches or there is no record with that email then return @false
        return null;
    }


    //principle Login Checking
    public int checkloginp(String username2,String password2){
         SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        //long result1 =sqLiteDatabase.execSQL("select * from "+ Principlelogin_TABLE_NAME +" where username = "+username2+" and password= "+password+" " );
        String[] columns = {principle_id};
        String selection = username1 + " = ?" + " AND " + password1 + " = ?";
        String[] selectionArgs = {username2, password2};
        //String query="select principle_id from "+Principlelogin_TABLE_NAME+" where username = "+username2+" and password= "+password2+" " ;
        Cursor cursor = sqLiteDatabase.query(Principlelogin_TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,              //group the rows
                null,                //filter by row groups
                null);              //The sort order
        //int cursorCount = cursor.getCount();
       // sqLiteDatabase.close();
        if (cursor.getCount() > 0 && cursor!=null)
        {
            cursor.moveToFirst();
            int id=cursor.getInt(0);
            return  id;
        }
        else
            {
            return 0;
            //cursor.close();
        }

    }
    //this is for Student Table-->STUDENT_TABLE_NAME
    public boolean add1(String name,int roll,int marks,int age,String gender,int class1){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        //columns name ,Value

        values.put(STUDENT_NAME,name);
        values.put(STUDENT_ROLLNUM ,roll);
        values.put(STUDENT_MARKS,marks);
        values.put(STUDENT_AGE,age);
        values.put(STUDENT_GENDER,gender);
        values.put(STUDENT_ClASS,class1);
        long result=sqLiteDatabase.insert(STUDENT_TABLE_NAME,null,values);
        //if doesn't insert it return -1 or else it returns inserted values
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }




}
