package com.lqg.Image;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.lqg.Image.bean.LabelBean;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onGridLayout(View view){
        startActivity(new Intent(MainActivity.this,GridLayoutActivity.class));
    }

    public void onFlexLayout(View view){
        startActivity(new Intent(MainActivity.this,FlexboxActivity.class));
    }

    public void onLabelLayout(View view){
        startActivity(new Intent(MainActivity.this, LabelActivity.class));
    }
}
