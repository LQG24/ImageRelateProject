package com.lqg.Image;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lqg.Image.bean.ImageBean;

import java.util.List;

/**
 * Created by Administrator on 2019/7/28.
 */

public class FlexBoxAdapter extends RecyclerView.Adapter<FlexBoxAdapter.ViewHolder> {
    private List<ImageBean> mPicturesBeanList;
    private Context mContext;
    private float screenWidth;

    public FlexBoxAdapter(Context context, List<ImageBean>  imageBeans) {
        this.mPicturesBeanList = imageBeans;
        this.mContext = context;
        filterData();
    }

    private void filterData() {
        screenWidth = (float) ScreenUtils.getScreenWidth(mContext);
        int i = 0;
        while (i < this.mPicturesBeanList.size()) {
            if (i % 2 == 0 && i + 1 < this.mPicturesBeanList.size()) {
                long divisor = (long) (screenWidth * (this.mPicturesBeanList.get(i + 1).getHeight()) * (this.mPicturesBeanList.get(i).getWidth()));
                long beDivisor = this.mPicturesBeanList.get(i + 1).getWidth() * this.mPicturesBeanList.get(i).getHeight() + this.mPicturesBeanList.get(i).getWidth() * this.mPicturesBeanList.get(i + 1).getHeight();
                int w = (int) (divisor / beDivisor);
                int h = w * this.mPicturesBeanList.get(i).getHeight() / this.mPicturesBeanList.get(i).getWidth();
                this.mPicturesBeanList.get(i).setChangeWidth(w);
                this.mPicturesBeanList.get(i).setChangeHeight(h);

                int tW = (int) (screenWidth - screenWidth * this.mPicturesBeanList.get(i + 1).getHeight() * this.mPicturesBeanList.get(i).getWidth() / (this.mPicturesBeanList.get(i + 1).getWidth() * this.mPicturesBeanList.get(i).getHeight() + this.mPicturesBeanList.get(i).getWidth() * this.mPicturesBeanList.get(i + 1).getHeight()));
                int tH = tW * this.mPicturesBeanList.get(i + 1).getHeight() / this.mPicturesBeanList.get(i + 1).getWidth();
                this.mPicturesBeanList.get(i + 1).setChangeWidth(tW);
                this.mPicturesBeanList.get(i + 1).setChangeHeight(tH);
            }

            if (i == mPicturesBeanList.size() - 1 && mPicturesBeanList.size() % 2 == 1) {
                int w = (int) screenWidth;
                int h = w * mPicturesBeanList.get(i).getHeight() / mPicturesBeanList.get(i).getWidth();
                this.mPicturesBeanList.get(i).setChangeWidth(w);
                this.mPicturesBeanList.get(i).setChangeHeight(h);
            }
            i++;
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adater_details_works_item, null, false);
        FlexBoxAdapter.ViewHolder viewHolder = new FlexBoxAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        int height = mPicturesBeanList.get(i).getChangeHeight();
        int width = mPicturesBeanList.get(i).getChangeWidth();
        if (mPicturesBeanList.size() % 2 == 1 && i == mPicturesBeanList.size() - 1) {
            height = mPicturesBeanList.get(i).getChangeHeight() >= SizeUtils.dp2px(100) ? SizeUtils.dp2px(100) : mPicturesBeanList.get(i).getChangeHeight();
            width = (int) screenWidth;
        }

        RequestOptions options = new RequestOptions().override(width, height).centerCrop();
        Glide.with(mContext).load(mPicturesBeanList.get(i).getResourceId()).apply(options).into(viewHolder.mImageView);
    }


    @Override
    public int getItemCount() {
        return this.mPicturesBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.mine_photo_iv);
        }
    }
}

