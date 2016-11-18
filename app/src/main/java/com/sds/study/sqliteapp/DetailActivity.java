package com.sds.study.sqliteapp;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
    상세보기를 움직이는 애니메이션으로 줘보자!
 */

public class DetailActivity extends AppCompatActivity{
    ImageView img;
    LinearLayout layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        img=(ImageView)findViewById(R.id.img);
        AnimationDrawable drawable=(AnimationDrawable)img.getDrawable();
        drawable.start();

        layout=(LinearLayout)findViewById(R.id.layout);
        AnimationDrawable back=(AnimationDrawable) layout.getBackground();
        back.start();

    }
}
