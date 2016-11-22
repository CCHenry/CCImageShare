package com.example.henryzheng.ccimageshare.C.ImageList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.henryzheng.ccimageshare.C.Base.BaseActivity;
import com.example.henryzheng.ccimageshare.M.Interface.MyItemClickListener;
import com.example.henryzheng.ccimageshare.M.data.ImageModel;
import com.example.henryzheng.ccimageshare.R;

import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by henryzheng on 2016/9/27.
 */
public class MyRecycleAdapt extends RecyclerView.Adapter<MyRecycleAdapt.MyViewHolder> {
    private final ImageOptions _imageOptions;
    Context _context;
    List<ImageModel> urls;
    LayoutInflater _mLayoutInflater;
    MyItemClickListener myItemClickListener;

    public MyRecycleAdapt(Context context) {
        _context = context;
        _mLayoutInflater = LayoutInflater.from(context);
        urls = new ArrayList<>();
        //设置imageload的加载属性
        _imageOptions = new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(DensityUtil.getScreenWidth()/2), DensityUtil.dip2px(DensityUtil.getScreenHeight()/2))
                .setRadius(DensityUtil.dip2px(5))
                // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setCrop(true) // 很多时候设置了合适的scaleType也不需要它.
                // 加载中或错误图片的ScaleType
//                .setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
//                .setCircular(true)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.mipmap.ic_launcher)
                .setFailureDrawableId(R.mipmap.ic_launcher)
                .setFadeIn(true)

                .build();
    }

    /**
     * 增加url的列表
     * @param imageInfos
     */
    public void addSrc(List<ImageModel> imageInfos) {
        this.urls.addAll(imageInfos);
    }

    /**
     * adapt创建
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = _mLayoutInflater.inflate(R.layout.recycle_list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view, myItemClickListener);
        return holder;
    }

    /**
     * 数据绑定
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        //下载图片和展示
        x.image().bind(holder.iv, urls.get(position).getSmallPicUrl(), _imageOptions, new CustomBitmapLoadCallBack(holder));
//        if (holder.iv.getWidth() > holder.iv.getHeight())
        int width = ((BaseActivity) _context).getWidth() ;
//        int height=(int)(width*3/4+Math.random()*(width*7/4-width*3/4+1));
        holder.iv.setLayoutParams(new RelativeLayout.LayoutParams(width, width));
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

    /**
     * 数据服用Handler
     */
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView iv;
        ProgressBar pb;
        MyItemClickListener _mItemClickListener;

        public MyViewHolder(final View view, MyItemClickListener _mItemClickListener) {
            super(view);
            iv = (ImageView) view.findViewById(R.id.iv);
            pb = (ProgressBar) view.findViewById(R.id.pb);
            view.setOnClickListener(this);
            this._mItemClickListener = _mItemClickListener;
        }

        @Override
        public void onClick(View v) {
            _mItemClickListener.onItemClick(v, getPosition());
        }
    }


    /**
     * 加载图片
     * @param imageModels 图片url的集合
     */
    public void loadImageList(List<ImageModel> imageModels) {
        addSrc(imageModels);
        notifyDataSetChanged();//通知listview更新数据
    }


    public void setOnItemClickListener(MyItemClickListener listener) {
        this.myItemClickListener = listener;
    }

    /**
     * 清除url的缓存
     */
    public void clear() {
        urls.clear();
    }

    /**
     * 返回url的缓存列表
     * @return
     */
    public List<ImageModel> getUrls() {
        return urls;
    }

    /**
     * 图片加载时的回调
     */
    public class CustomBitmapLoadCallBack implements Callback.ProgressCallback<Drawable> {
        private final MyViewHolder holder;

        public CustomBitmapLoadCallBack(MyViewHolder holder) {
            this.holder = holder;
        }

        @Override
        public void onWaiting() {
            this.holder.pb.setProgress(0);
        }

        @Override
        public void onStarted() {
        }

        @Override
        public void onLoading(long total, long current, boolean isDownloading) {
            this.holder.pb.setProgress((int) (current * 100 / total));
        }

        @Override
        public void onSuccess(Drawable result) {
            this.holder.pb.setProgress(100);
        }

        @Override
        public void onError(Throwable ex, boolean isOnCallback) {
        }

        @Override
        public void onCancelled(CancelledException cex) {
        }

        @Override
        public void onFinished() {
        }
    }
}
