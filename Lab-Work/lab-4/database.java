package com.example.databaselab4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class database extends SQLiteOpenHelper{
	public static final String DATABASE_NAME = "MyDBName.db";
	   public static final String STUDENT_TABLE_NAME = "student";
	   public static final String STUDENT_ROLLNUM = "rollnum";
	   public static final String STUDENT_NAME = "name";
	   public static final String STUDENT_MARKS = "marks";
	public database(Context context){
	      super(context,DATABASE_NAME,null,1);
	   }
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(
		         "create table student " +
		         "(rno integer primary key, name text,marks integer)"
		      );
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS student");
	      onCreate(db);
	}
	
	
	public void insertStudent (int rn , String name,int marks) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues contentValues = new ContentValues();
			contentValues.put("rno", rn);
			contentValues.put("name", name);
			contentValues.put("marks", marks);
			db.insert("student", null, contentValues);
	   }
	
	public int numberOfRows(){
	      SQLiteDatabase db = this.getReadableDatabase();
	      int numRows = (int) DatabaseUtils.queryNumEntries(db, STUDENT_TABLE_NAME);
	      return numRows;
	   }
	public Cursor getData() {
	      SQLiteDatabase db = this.getReadableDatabase();
	      Cursor res =  db.rawQuery( "select * from student ", null );
	      return res;
	   }
	public void deleteRow(int rno){
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL("delete from student where rno = " + rno);
		
	}
	public void updateRow(int rno , String name , int marks){
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL("update student set name = '" + name + "' , marks = " + marks + " where rno = " + rno);
	}
}

					/// $Bee codes

