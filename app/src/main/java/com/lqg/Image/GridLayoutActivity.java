package com.lqg.Image;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GridLayoutActivity extends AppCompatActivity {
    private GridLayoutManager mGirdLayoutManager;
    private GalleryAdapter mGalleryAdapter;
    private List<Integer> imageList;
    private List<ImageBean> mImageBeanList;
    private RecyclerView gallleryRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_layout);
        initView();
        initData();
        addHeaderView();
        addBottomView();
        gallleryRecyclerView.setAdapter(mGalleryAdapter);
    }

    private void addBottomView() {
        View view = LayoutInflater.from(GridLayoutActivity.this).inflate(R.layout.adater_gallery_bottom, gallleryRecyclerView, false);
        mGalleryAdapter.setBottomView(view);
    }

    private void addHeaderView() {
        View view = LayoutInflater.from(GridLayoutActivity.this).inflate(R.layout.adater_gallery_header, gallleryRecyclerView, false);
        mGalleryAdapter.setHeaderView(view);
    }

    private void initView() {
        gallleryRecyclerView = findViewById(R.id.gallery_recyclerview);
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
        mGirdLayoutManager = new GridLayoutManager(this, ScreenUtils.getScreenWidth(GridLayoutActivity.this));
        gallleryRecyclerView.setLayoutManager(mGirdLayoutManager);
        mGalleryAdapter = new GalleryAdapter(GridLayoutActivity.this,mImageBeanList);
    }
}
