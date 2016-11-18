package com.sds.study.sqliteapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2016-11-18.
 */

public class MemberDAO{
    SQLiteDatabase db;

    public MemberDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public void insert(Member member){
        String sql="insert into member(id,password) values(?,?)";
        db.execSQL(sql, new String[]{member.getId(), member.getPassword()});
    }

    public List selectAll(){
        ArrayList<Member> list=new ArrayList<Member>();
        String sql="select * from member";
        Cursor rs=db.rawQuery(sql, null);

        while(rs.moveToNext()){
            Member dto = new Member();
            dto.setMember_id(rs.getInt(rs.getColumnIndex("member_id")));
            dto.setId(rs.getString(rs.getColumnIndex("id")));
            dto.setPassword(rs.getString(rs.getColumnIndex("password")));
            list.add(dto);
        }
        return list;
    }

    public void delete(int member_id){
        String sql="delete from member where member_id=?";
        db.execSQL(sql, new String[]{ Integer.toString(member_id)});
    }
    public void update(Member member){
        String sql="update member set id=? , password=? where member_id=?";
        db.execSQL(sql, new String[]{
                member.getId(), member.getPassword() ,Integer.toString(member.getMember_id())
        });
    }
}
