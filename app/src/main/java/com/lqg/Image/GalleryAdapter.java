package com.lqg.Image;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lqg.Image.bean.ImageBean;


import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
    private List<ImageBean> mPicturesBeanList;
    private Context mContext;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    public static final int TYPE_BOTTOM = 2;
    private View mHeaderView;
    private View mBottomView;
    private float screenWidth;


    public GalleryAdapter(Context context, List<ImageBean> imageBeans) {
        this.mContext = context;
        this.mPicturesBeanList = imageBeans;
        filterData();
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == TYPE_HEADER || getItemViewType(position) == TYPE_BOTTOM
                            ? gridManager.getSpanCount() : mPicturesBeanList.get(position - 1).getChangeWidth();
                }
            });
        }
    }


    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    public void setBottomView(View bottomView) {
        mBottomView = bottomView;
        notifyItemInserted(mPicturesBeanList.size() + 1);
    }


    public View getBottomView() {
        return mBottomView;
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null) return TYPE_NORMAL;
        if (mBottomView == null) return TYPE_NORMAL;
        if (position == 0) return TYPE_HEADER;
        if (position == this.getItemCount() - 1) return TYPE_BOTTOM;
        return TYPE_NORMAL;
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
        if (mHeaderView != null && i == TYPE_HEADER) return new ViewHolder(mHeaderView);
        if (mBottomView != null && i == TYPE_BOTTOM) return new ViewHolder(mBottomView);
        View view = LayoutInflater.from(mContext).inflate(R.layout.adater_details_works_item, null, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        if (getItemViewType(i) == TYPE_HEADER) return;
        if (getItemViewType(i) == TYPE_BOTTOM) return;
        final int position = getRealPosition(viewHolder);
        int height = mPicturesBeanList.get(position).getChangeHeight();
        int width = mPicturesBeanList.get(position).getChangeWidth();
        //TODO 最后一张图片高度最高设置100dp,宽度铺满全屏
        if (mPicturesBeanList.size() % 2 == 1 && position == mPicturesBeanList.size() - 1) {
            height = mPicturesBeanList.get(position).getChangeHeight() >= SizeUtils.dp2px(100) ? SizeUtils.dp2px(100) : mPicturesBeanList.get(position).getChangeHeight();
            width = (int) screenWidth;
        }

        RequestOptions options = new RequestOptions().override(width, height).centerCrop();
        Glide.with(mContext).load(mPicturesBeanList.get(position).getResourceId()).apply(options).into(viewHolder.mImageView);
//        Glide.with(mContext).load(R.mipmap.girl1).apply(options).into(viewHolder.mImageView);
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }


    @Override
    public int getItemCount() {
        return mPicturesBeanList.size() + 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            if (itemView == mHeaderView) return;
            mImageView = itemView.findViewById(R.id.mine_photo_iv);
        }
    }

}
