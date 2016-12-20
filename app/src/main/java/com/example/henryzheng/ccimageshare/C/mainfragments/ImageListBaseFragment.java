package com.example.henryzheng.ccimageshare.C.mainfragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.henryzheng.ccimageshare.C.Base.BaseFragment;
import com.example.henryzheng.ccimageshare.C.BigImageShow.BigImageShowActivity;
import com.example.henryzheng.ccimageshare.C.mainfragments.i.MainFragmentInterface;
import com.example.henryzheng.ccimageshare.C.mainfragments.model.ImageListBaseModel;
import com.example.henryzheng.ccimageshare.C.mainfragments.p.MainFragmentsPresenter;
import com.example.henryzheng.ccimageshare.M.Interface.MyItemClickListener;
import com.example.henryzheng.ccimageshare.M.Sql.CCDatabaseOpenHelper;
import com.example.henryzheng.ccimageshare.M.ZuiMeiModel.Image;
import com.example.henryzheng.ccimageshare.M.common.CCLog;
import com.example.henryzheng.ccimageshare.R;
import com.example.henryzheng.ccimageshare.V.MyRecycleView;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.Iterator;
import java.util.List;


@ContentView(R.layout.fragment_image_list_base)
public class ImageListBaseFragment extends BaseFragment implements MyItemClickListener,
        MainFragmentInterface {
    ImageListBaseModel imageListBaseModel;
    String url = "http://lab.zuimeia.com/photo/userpicture/list/?appVersion=2.6" +
            ".3&channel=wallpaper&imsi=460012202301362&systemVersion=23&resolution=1080x1920" +
            "&platform=android&package_name=com.brixd" +
            ".wallpager&page=%d&tag=0&lang=zh-cn&openUDID=862258036210848&page_size=30&timestamp" +
            "=1480672294308";
    @ViewInject(R.id.recycleView)
    private MyRecycleView recyclerView;// recycle组件
    @ViewInject(R.id.swipeRefreshLayout0)
    private SwipeRefreshLayout swipeRefreshLayout;
    private MyRecycleAdapt recycleAdapter;// recycle组件的适配器
    LinearLayoutManager lin;
    MainFragmentsPresenter presenter;

    public static BaseFragment newInstance(ImageListBaseModel imageListBaseModel) {
        ImageListBaseFragment f=new ImageListBaseFragment();
        f.imageListBaseModel=imageListBaseModel;
        return f;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new MainFragmentsPresenter(this, imageListBaseModel.getUrl(), imageListBaseModel.getType());
        recycleAdapter = new MyRecycleAdapt(getActivity());
        lin = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(lin);
        recyclerView.setItemAnimator(new DefaultItemAnimator());// 设置增加或删除条目的动画
        recycleAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(recycleAdapter); // 设置Adapter
        recyclerView.setIsFooterEnable(true);
        swipeRefreshLayout.setProgressViewOffset(false, (int) getResources().getDimension(R.dimen
                .main_fragment_first_head_height) - 90, (int) getResources().getDimension(R.dimen
                .main_fragment_first_head_height) + 90);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadListData(presenter.REFRESH_DATA_TYPE);
            }
        });
        recyclerView.setLoadMoreListener(new MyRecycleView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                presenter.loadListData(presenter.LOAD_MORE_TYPE);
            }
        });
    }


    @Override
    public void onItemClick(View view, int postion) {
        Intent intent = new Intent(getActivity(), BigImageShowActivity.class);
        intent.putExtra("url", recycleAdapter.getUrls().get(postion - 1).getImageUrl());
        startActivity(intent);
    }

    @Override
    public void OnHandlerListener(Message msg) {
        super.OnHandlerListener(msg);
    }

    @Override
    public void onResume() {
        super.onResume();
        CCLog.print("onResume");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        CCLog.print("onDestroyView");
    }

    @Override
    public void loadNewImages(List<Image> images) {
        swipeRefreshLayout.setRefreshing(false);
        recycleAdapter.loadMoreData(images);
        recyclerView.setLoadingMore(false);
    }

    @Override
    public void refreshImages(List<Image> images1) {
        swipeRefreshLayout.setRefreshing(false);
        recycleAdapter.refreshData(images1);

    }


}
