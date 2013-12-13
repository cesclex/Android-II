package com.udb.modulo2.clase15;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public DBHelper(Context context) {
		super(context, "DemoDB", null, 1);
	}

	public void onCreate(SQLiteDatabase db) {
		StringBuilder builder=new StringBuilder();
		builder.setLength(0);
		builder.append("CREATE VIRTUAL TABLE NAMES USING FTS3");
		builder.append("(");
		builder.append("name TEXT) ");		
		db.execSQL(builder.toString());
		builder=new StringBuilder();

		InsertData(db);

	}

	 void InsertData(SQLiteDatabase db)
	 {
		 ContentValues cv=new ContentValues();
			 cv.put("name","John");
			 db.insert("NAMES", "name", cv);
			 cv.put("name","Jack");
			 db.insert("NAMES", "name", cv);
			 cv.put("name","Ann");
			 db.insert("NAMES", "name", cv);
			 cv.put("name","Adam");
			 db.insert("NAMES", "name", cv);
			 cv.put("name","Sarah");
			 db.insert("NAMES", "name", cv);

	 }

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public ArrayList<String> getNames(){
		ArrayList<String> names=new ArrayList<String>();
		Cursor c=this.getReadableDatabase().rawQuery("select * FROM Names", null);
		while(c.moveToNext()){
			String name=c.getString(0);
			names.add(name);
		}
		c.close();
		return names;
	}

	public ArrayList<String> getNamesSearch(String query){
		ArrayList<String> names = new ArrayList<String>();
		Cursor c=this.getReadableDatabase().rawQuery("select * FROM Names WHERE name like '"+query+"%'", null);
		while(c.moveToNext()){
			String name=c.getString(0);
			names.add(name);
		}
		c.close();
		return names;
	}

}
