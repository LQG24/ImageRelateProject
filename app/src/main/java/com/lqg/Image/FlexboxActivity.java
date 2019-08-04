package com.lqg.Image;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.lqg.Image.bean.ImageBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlexboxActivity extends AppCompatActivity {
    private List<Integer> imageList;
    private List<ImageBean> mImageBeanList;
    private RecyclerView gallleryRecyclerView;
    private FlexboxLayoutManager mFlexboxLayoutManager;
    private FlexBoxAdapter mFlexBoxAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flexbox);
        initView();
        initData();

    }

    private void initView() {
        gallleryRecyclerView = findViewById(R.id.gallery_recyclerview);
        mFlexboxLayoutManager = new FlexboxLayoutManager();
        mFlexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        mFlexboxLayoutManager.setJustifyContent(JustifyContent.SPACE_BETWEEN);
//        mFlexboxLayoutManager.setAlignContent(AlignContent.SPACE_BETWEEN);
        mFlexboxLayoutManager.setAlignItems(AlignItems.FLEX_START);
        gallleryRecyclerView.setLayoutManager(mFlexboxLayoutManager);

    }

    private void initData() {
        imageList = new ArrayList<>(Arrays.asList(R.mipmap.girl1,R.mipmap.girl2,R.mipmap.girl3,R.mipmap.girl4,R.mipmap.girl5
                ,R.mipmap.girl6,R.mipmap.girl7,R.mipmap.girl8,R.mipmap.girl9));
        mImageBeanList = new ArrayList<>();
        for (int i=0;i<imageList.size();i++){
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(getResources(),imageList.get(i),options);
            int width = options.outWidth;
            int height = options.outHeight;
            ImageBean imageBean = new ImageBean();
            imageBean.setResourceId(imageList.get(i));
            imageBean.setWidth(width);
            imageBean.setHeight(height);
            mImageBeanList.add(imageBean);
        }
        mFlexBoxAdapter = new FlexBoxAdapter(FlexboxActivity.this,mImageBeanList);
        gallleryRecyclerView.setAdapter(mFlexBoxAdapter);
        gallleryRecyclerView.setHasFixedSize(false);
    }
}
