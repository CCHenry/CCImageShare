package com.example.henryzheng.ccimageshare.C.mainfragments;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.henryzheng.ccimageshare.C.Base.BaseActivity;
import com.example.henryzheng.ccimageshare.M.Interface.MyItemClickListener;
import com.example.henryzheng.ccimageshare.M.ZuiMeiModel.Image;
import com.example.henryzheng.ccimageshare.M.utils.CCLog;
import com.example.henryzheng.ccimageshare.M.utils.DateUtil;
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
    private static final int HEAD_TYPE = 0;
    private static final int DATA_TYPE = 1;
    private static final int FOOT_TYPE = 2;

    private final ImageOptions _imageOptions;
    Context _context;
    List<Image> images;
    LayoutInflater _mLayoutInflater;
    MyItemClickListener myItemClickListener;

    public MyRecycleAdapt(Context context) {
        _context = context;
        _mLayoutInflater = LayoutInflater.from(context);
        images = new ArrayList<>();
        //设置imageload的加载属性

        _imageOptions = new ImageOptions.Builder()
//                .setSize(0,0)
                .setRadius(DensityUtil.dip2px(5))
                // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setCrop(true) // 很多时候设置了合适的scaleType也不需要它.
                // 加载中或错误图片的ScaleType
//                .setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
//                .setCircular(true)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
//                .setLoadingDrawableId(R.drawable.load)
//                .setLoadingDrawable()
//                .setFailureDrawableId(R.mipmap.ic_launcher)
                .setUseMemCache(true)
                .setFadeIn(true)
                .build();
    }

    /**
     * 增加url的列表
     *
     * @param images
     */
    public void addSrc(List<Image> images) {
        for (int i = 0; i < images.size(); i++) {
            this.images.add(images.get(i));
        }

    }

    /**
     * adapt创建
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEAD_TYPE) {
            View view = _mLayoutInflater.inflate(R.layout.layout_recycle_head, parent, false);
            MyViewHolder holder = new MyViewHolder(HEAD_TYPE, view, myItemClickListener);
            return holder;
        } else if (viewType == FOOT_TYPE) {
            View view = _mLayoutInflater.inflate(R.layout.layout_recycle_foot, parent, false);
            MyViewHolder holder = new MyViewHolder(FOOT_TYPE, view, myItemClickListener);
            return holder;
        } else {
            View view = _mLayoutInflater.inflate(R.layout.recycle_list_item, parent, false);
            MyViewHolder holder = new MyViewHolder(DATA_TYPE, view, myItemClickListener);
            return holder;
        }
    }
    /**
     * 数据服用Handler
     */
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView iv;
        ProgressBar pb;
        MyItemClickListener _mItemClickListener;
        ImageView load;
        TextView tv0,tv1,tv2,tv3,tv4;
        ImageView iv1;
        public MyViewHolder(int viewType, final View view, MyItemClickListener _mItemClickListener) {
            super(view);
            if (viewType == HEAD_TYPE) {

            } else if (viewType == FOOT_TYPE) {

            } else {
                iv = (ImageView) view.findViewById(R.id.iv);
                pb = (ProgressBar) view.findViewById(R.id.pb);
                view.setOnClickListener(this);
                load= (ImageView) view.findViewById(R.id.load);
                tv0=(TextView) view.findViewById(R.id.tv0);
                tv1=(TextView) view.findViewById(R.id.tv1);
                tv2=(TextView) view.findViewById(R.id.tv2);
                tv3=(TextView) view.findViewById(R.id.tv3);
                tv4=(TextView) view.findViewById(R.id.tv4);
                iv1=(ImageView)view.findViewById(R.id.iv1);
                this._mItemClickListener = _mItemClickListener;
            }

        }

        @Override
        public void onClick(View v) {
            _mItemClickListener.onItemClick(v, getPosition());
        }
    }
    /**
     * 数据绑定
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        //下载图片和展示
        if (getItemCount() > 0) {
            if (position > 0 &&position <getItemCount()-1) {
                x.image().bind(holder.iv, images.get(position - 1).getImage_url(), _imageOptions, new CustomBitmapLoadCallBack(holder));
                int width = ((BaseActivity) _context).getWidth();
                RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(width, width * 3 / 4);
                holder.iv.setLayoutParams(params);
                holder.iv1.setLayoutParams(params);
                Image image=images.get(position-1);
                holder.tv3.setText(image.getDescription());
                holder.tv4.setText(image.getUp_times()+"");

                if (image.getPub_time()!=null) {
                    if (image.getPub_time().contains("-")) {
                        String[] arr = DateUtil.getFormDateFromDate(image.getPub_time());
                        holder.tv0.setText(arr[1]);
                        holder.tv1.setText(arr[0]);
                        holder.tv2.setText(arr[2]);
                    }
//                holder.tv3.setText(image.getUp_times());
                }else{
                    holder.tv0.setVisibility(View.GONE);
                    holder.tv1.setVisibility(View.GONE);
                    holder.tv2.setVisibility(View.GONE);
                }

            }
        }
    }

    @Override
    public int getItemCount() {
        if (images.size()>0){
        return images.size() + 2;}
        else
            return 0;
    }




    /**
     * 上拉加载图片
     *
     * @param images 图片url的集合
     */
    public void loadMoreData(List<Image> images) {
        addSrc(images);
        notifyDataSetChanged();
        CCLog.print("loadMoreData:" + this.images.size());
    }

    /**
     * 下拉刷新图片
     *
     * @param images 图片url的集合
     */
    public void refreshData(List<Image> images) {
        this.images.clear();
        notifyDataSetChanged();
        for (int i=images.size()-1;i>=0;i--){
            this.images.add(0,images.get(i));
            notifyItemInserted(0);
        }
    }


    public void setOnItemClickListener(MyItemClickListener listener) {
        this.myItemClickListener = listener;
    }

    /**
     * 清除url的缓存
     */
    public void clear() {
        images.clear();
    }

    /**
     * 返回url的缓存列表
     *
     * @return
     */
    public List<Image> getImages() {
        return images;
    }

    /**
     * 图片加载时的回调
     */
    public class CustomBitmapLoadCallBack implements Callback.ProgressCallback<Drawable> {
        private final MyViewHolder holder;
        AnimationDrawable mAnimate;
        public CustomBitmapLoadCallBack(MyViewHolder holder) {
            this.holder = holder;
        }

        @Override
        public void onWaiting() {
            this.holder.pb.setProgress(0);
            this.holder.pb.setVisibility(View.INVISIBLE);
            this.holder.load.setVisibility(View.VISIBLE);
            mAnimate = (AnimationDrawable) this.holder.load.getBackground();
            mAnimate.setOneShot(false);
            mAnimate.start();
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

            this.holder.pb.setVisibility(View.GONE);
            this.holder.pb.setProgress(100);
            this.holder.load.setVisibility(View.GONE);
            mAnimate.stop();
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

    @Override
    public long getItemId(int position) {
        CCLog.print("getItemId:" + position);
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (images.size() > 0) {
            if (position == 0) {
                return HEAD_TYPE;
            } else if (position == getItemCount()-1) {
                return FOOT_TYPE;
            } else {
                return DATA_TYPE;
            }
        } else {
            return DATA_TYPE;
        }
    }
    }
