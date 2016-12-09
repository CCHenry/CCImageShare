package com.example.henryzheng.ccimageshare.test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.henryzheng.ccimageshare.M.Interface.MyItemClickListener;
import com.example.henryzheng.ccimageshare.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by henryzheng on 2016/9/27.
 */
public class TestRecycleAdapt extends RecyclerView.Adapter<TestRecycleAdapt.MyViewHolder> {
    Context _context;
    List<String> datas;
    LayoutInflater _mLayoutInflater;
    MyItemClickListener myItemClickListener;

    public TestRecycleAdapt(Context context) {
        _context = context;
        _mLayoutInflater = LayoutInflater.from(context);
        datas = new ArrayList<>();
        //设置imageload的加载属性


    }

    /**
     * 增加url的列表
     * @param images
     */
    public void addSrc(List<String> images) {
        this.datas.addAll(images);
    }

    /**
     * adapt创建
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = _mLayoutInflater.inflate(R.layout.recycle_test_list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
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
//        x.image().bind(holder.iv, urls.get(position).getImageUrl(), _imageOptions, new CustomBitmapLoadCallBack(holder));
////        if (holder.iv.getWidth() > holder.iv.getHeight())
//        int width = ((BaseActivity) _context).getWidth() ;
////        int height=(int)(width*3/4+Math.random()*(width*7/4-width*3/4+1));
//        holder.iv.setLayoutParams(new RelativeLayout.LayoutParams(width, width*3/4));
        holder.tv.setText(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    /**
     * 数据服用Handler
     */
    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        public MyViewHolder(final View view) {
            super(view);
            tv= (TextView) view.findViewById(R.id.tv0);
        }


    }


    /**
     * 加载图片
     * @param datas 图片url的集合
     */
    public void loadData(List<String> datas) {
        addSrc(datas);
        notifyDataSetChanged();//通知listview更新数据
    }
    /**
     * 加载图片
     * @param data 图片url的集合
     */
    public void loaOnedData(String data) {
        datas.add(0,data);
        notifyItemInserted(0);
    }
    /**
     * 加载图片
     * @param data 图片url的集合
     */
    public void loadData2(List<String> data) {
        datas.clear();
        notifyDataSetChanged();
        for (int i=0;i<data.size();i++) {
            datas.add(0,data.get(i));
            notifyItemInserted(0);
        }
//        int count=data.size();
//
//    notifyItemMoved(data.size(),count);
}
    public void setOnItemClickListener(MyItemClickListener listener) {
        this.myItemClickListener = listener;
    }

    /**
     * 清除url的缓存
     */
    public void clear() {
        datas.clear();
    }

    /**
     * 返回url的缓存列表
     * @return
     */
    public List<String> getDatas() {
        return datas;
    }

    /**
     * 图片加载时的回调
     */


//    @Override
//    public long getItemId(int position) {
//        CCLog
//        return super.getItemId(position);
//    }
}
