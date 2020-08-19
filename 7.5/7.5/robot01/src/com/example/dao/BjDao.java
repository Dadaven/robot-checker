package com.example.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.robot.entiy.Bj;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class BjDao {
	SQLiteDatabase db=Dbcont.createDB();
	//获取所有报警
    public List<Bj> getdata()
	{
			List<Bj> lstdata = new ArrayList<Bj>();	
			String sql="select * from baojings";
			Cursor cursor = db.rawQuery(sql,null);
			while(cursor.moveToNext())
			{
				int bjId=cursor.getInt(0);
				String bjType=cursor.getString(1);
				String bjPlace=cursor.getString(2);
				String bjTime=cursor.getString(3);
				Bj bj=new Bj(bjId,bjType,bjPlace,bjTime);
				lstdata.add(bj);
			}
			db.close();		
	    	return lstdata;	
	    }
		//添加报警
		public long addblog(Bj bj)
		{
			  ContentValues  newValues=new ContentValues();
			  newValues.put("bjtype", bj.getBjType());
			  newValues.put("bjplace",bj.getBjPlace());
			  newValues.put("bjtime",bj.getBjTime());
			  return db.insert("baojings", null, newValues);
		}
}
