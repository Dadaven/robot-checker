package com.example.dao;

import android.database.sqlite.SQLiteDatabase;

public class Dbcont  {
	public static SQLiteDatabase createDB()
	   {
		   SQLiteDatabase sd=SQLiteDatabase.openDatabase("/data/data/com.example.robot/mydb", null,
					SQLiteDatabase.OPEN_READWRITE|SQLiteDatabase.CREATE_IF_NECESSARY);
			
			String BaojingTable="create table if not exists baojings"+"(bjid integer primary key autoincrement ,bjtype varchar(20) ,bjplace varchar(50),bjtime varchar(20))";
			sd.execSQL(BaojingTable);
		   return sd;
	   }
	public static void close(SQLiteDatabase db)
	{
		if (db != null){
				  db.close();
				  db = null;
			  }
	}
	
}
