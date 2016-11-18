package com.sds.study.sqliteapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/*
    리스트뷰에 보여질 화면이 단일 위젯이 아닌, 2개 이상으로 구성된 복합 위젯일 경우
    개발자가 디자인을 정의하므로, 어댑터를 재정의한다!!
 */

public class MyListAdapter extends BaseAdapter {
    Context context;
    SQLiteDatabase db;
    String TAG;
    ArrayList<Member> memberList;


    public MyListAdapter(Context context) {
        this.context = context;
        TAG = this.getClass().getName();
        MainActivity mainActivity = (MainActivity) context;
        db = mainActivity.db;

        getList();
    }

    //데이터베이스로부터 레코드 가져오기!!
    public void getList() {
        memberList=(ArrayList)MainActivity.dao.selectAll();
    }


    @Override
    public int getCount() {
        return memberList.size();
    }

    //한개만 호출!
    @Override
    public Object getItem(int i) {
        return memberList.get(i);
    }

    //시퀀스값!
    @Override
    public long getItemId(int i) {
        return memberList.get(i).getMember_id();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = null; //누가 여기에 들어올지 모른다..
        Member member = memberList.get(i);
        if (view != null) {//이미 생성된 아이템이라면(해당 index에 아이템이 이미 채워져 있다면)
            view = convertView;
            MemberItem item = (MemberItem) view;
            item.setMember(member);

        } else {//최초 생성이라면(해당 index에 아무것도 없는 상태라면)
            view = new MemberItem(context, member);
        }

        return view;
    }
}
