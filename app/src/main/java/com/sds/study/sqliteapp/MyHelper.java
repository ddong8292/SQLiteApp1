package com.sds.study.sqliteapp;

/*
    안드로에서는 SQLite 데이터베이스의 위치가 이미 /data/data/app재피키지/databases로
     정해져 있기때문에 오직 SQLiteOpenHelper라는 클래스를 통해 접근 및 제어 해야 한다!
     (즉, 임의로 개발자가 디렉토리에 접근 불가!)

     String name=생성할 db파일명
     int version=최초의 버전 넘버(1부터 하자!)

 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyHelper extends SQLiteOpenHelper{
    String TAG;

    public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        TAG=this.getClass().getName();
    }

    //데이터 베이스가 최초의 생성될때 생성된다!!즉, 파일이 존재하지 않을때 이 메서드가 호출된다!
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG,"onCreate호출");

        //어플리케이션에 필요한 테이블은 이 시점에 구축하자!!(쿼리문 수행)
        StringBuffer sql=new StringBuffer();
        sql.append("create table member(");
        sql.append("member_id integer primary key autoincrement");
        sql.append(", id varchar(20)");
        sql.append(", password varchar(20)");
        sql.append(");");

        sqLiteDatabase.execSQL(sql.toString());
        Log.d(TAG,"데이터베이스 구축");
    }

    /*
        유저가 버전을 다른걸로 넘길때, onCreate가 호출하지 않을때, 즉 업그레이드(변경)될때!-->접근이 불가능하기(보안이유)
        때문에 깜빡해서 컬럼명을 넣는것을 잊어버렸을때 쓰게된다!
        파일이 이미 존재하거나, 버전숫자가 틀린경우 개발자가 무언가를 db에 변경하겠다는 의도로 생각하고,
        아래의 메서드를 호출한다!!*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG,"onUpgrade호출");
    }

}
