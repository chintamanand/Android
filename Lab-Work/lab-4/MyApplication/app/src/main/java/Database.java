


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String STUDENT_TABLE_NAME = "student";
    public static final String STUDENT_ROLLNUM = "rollnum";
    public static final String STUDENT_NAME = "name";
    public static final String STUDENT_MARKS = "marks";
    public Database(Context context){
        super(context,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("create table student " + "(roll integer primary key, name text,marks integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void OnUpgrade(SQLiteDatabase db,int arg1,int arg2) //( database ,int old version,int new version)
    {
     db.execSQL("DROP TABLE IF EXIST student");
     onCreate(db);
    }
    public void insertStudent(String rollnum, String name, String marks) { //as it doesn't return anything from this
        SQLiteDatabase db = this.getWritableDatabase();  //insert the date to database
        ContentValues contentValues = new ContentValues();
        contentValues.put("roll",rollnum); //(database attribute,string name)
        contentValues.put("name", name);
        contentValues.put("marks", marks);
        db.insert("student", null, contentValues);
    }
    public Cursor getData(int id) {  //get all the data from the student database
        SQLiteDatabase db = this.getReadableDatabase();    //read from the data
        Cursor res =  db.rawQuery( "select * from student" , null ); //( sql query , String selection args )
        return res;
    }

    public int numberOfRows(){  //gives the number of rows
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, STUDENT_TABLE_NAME);
        return numRows;
    }
    public void updateStudent(String rollnum, String name, String marks){
     SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("update student set name = '" + name + "' , marks = " + marks + " where roll = " + rollnum);
    }
    public void deleteStudent(String rollnum )
    {
         SQLiteDatabase db=this.getReadableDatabase();
        db.execSQL("DELETE TABLE FROM STUDENT WHERE roll="+rollnum);

    }

}
