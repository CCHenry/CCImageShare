package com.example.henryzheng.ccimageshare.C.ImageList;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.henryzheng.ccimageshare.C.Base.BaseFragment;
import com.example.henryzheng.ccimageshare.C.ImageShowRecycle.RecycleItemDecoration;
import com.example.henryzheng.ccimageshare.M.Interface.MyItemClickListener;
import com.example.henryzheng.ccimageshare.M.common.CCLog;
import com.example.henryzheng.ccimageshare.M.data.ImageInfo;
import com.example.henryzheng.ccimageshare.M.data.ImageModel;
import com.example.henryzheng.ccimageshare.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


@ContentView(R.layout.fragment_image_list)
public class ImageListFragment extends BaseFragment implements MyItemClickListener {
    @ViewInject(R.id.recycleView)
    private RecyclerView recyclerView;// recycle组件
    private List<ImageInfo> _imageInfos;// 图片集合
    private MyRecycleAdapt recycleAdapter;// recycle组件的适配器
    RecyclerView.LayoutManager _layoutManager;// recycleView的展示状态


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycleAdapter = new MyRecycleAdapt(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());// 设置增加或删除条目的动画
        recycleAdapter.setOnItemClickListener(this);
        recyclerView.addItemDecoration(new RecycleItemDecoration(15));
        recyclerView.setAdapter(recycleAdapter); // 设置Adapter
//        loadData();
        loadRecentBmobData();
    }

    public void loadRecentBmobData() {
        BmobQuery<ImageModel> query = new BmobQuery<ImageModel>();
        //查询playerName叫“比目”的数据
//        query.add("playerName", "比目");
        //返回50条数据，如果不加上这条语句，默认返回10条数据
        query.setLimit(50);
        //执行查询方法
        query.findObjects(new FindListener<ImageModel>() {
            @Override
            public void done(List<ImageModel> object, BmobException e) {
                if (e == null) {
                    CCLog.print("查询成功：共" + object.size() + "条数据。");
                    for (ImageModel gameScore : object) {
                        CCLog.print(gameScore.getBigPicUrl());
                        //获得playerName的信息
                        gameScore.getUsername();
                        //获得数据的objectId信息
                        gameScore.getObjectId();
                        //获得createdAt数据创建时间（注意是：createdAt，不是createAt）
                        gameScore.getCreatedAt();

                    }
                    loadData(object, new LinearLayoutManager(getActivity()));
                } else {
                    CCLog.print("失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
    }

    public void loadData(List<ImageModel> imageInfos, RecyclerView.LayoutManager layoutManager) {
        _layoutManager = layoutManager;
        recyclerView.setLayoutManager(_layoutManager);
        recycleAdapter.loadImageList(imageInfos);
    }

    @Override
    public void onItemClick(View view, int postion) {

    }

    @Override
    public void OnHandlerListener(Message msg) {
        super.OnHandlerListener(msg);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadRecentBmobData();
        recycleAdapter.clear();

    }
}
