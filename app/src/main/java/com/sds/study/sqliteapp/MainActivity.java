package com.sds.study.sqliteapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String TAG;
    MyHelper myHelper;//데이터베이스 구축
    SQLiteDatabase db;//데이터베이스 쿼리문 제어
    EditText txt_id;
    EditText txt_password;
    ListView listView;
    MyListAdapter myListAdapter;

    static MemberDAO dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getName();

        setContentView(R.layout.activity_main);

        init();//sqlite시점때문에 올리자

        txt_id = (EditText) findViewById(R.id.txt_id);
        txt_password = (EditText) findViewById(R.id.txt_password);
        listView = (ListView) findViewById(R.id.listView);


        myListAdapter = new MyListAdapter(this);
        listView.setAdapter(myListAdapter);

        //아이템리스너 연결
        listView.setOnItemClickListener(this);

    }

    //데이터베이스 초기화 메서드
    public void init() {
        myHelper = new MyHelper(this, "iot.sqlite", null, 1);
        db = myHelper.getWritableDatabase();
        dao = new MemberDAO(db);
    }

    public void regist() {
        //이 앱이 보유한 sqlite 데이터베이스에 insert시킨다!
        String sql = "insert into member(id,password)";
        sql += " values(?,?)";

        String id = txt_id.getText().toString();
        String password = txt_password.getText().toString();

        db.execSQL(sql, new String[]{id, password});

        //등록후 입력창에서 지워버리기
        txt_id.setText("");
        txt_password.setText("");

        Log.d(TAG, "등록완료");

        //ListView 갱신!
        myListAdapter.getList();
        myListAdapter.notifyDataSetChanged();

    }

    public void btnClick(View view) {
        regist();


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, DetailActivity.class);
        startActivity(intent);
    }
}
