package com.example.henryzheng.ccimageshare.C.mainfragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.henryzheng.ccimageshare.C.Base.BaseFragment;
import com.example.henryzheng.ccimageshare.C.BigImageShow.BigImageShowActivity;
import com.example.henryzheng.ccimageshare.M.CallBack.MyCallBack;
import com.example.henryzheng.ccimageshare.M.Interface.MyItemClickListener;
import com.example.henryzheng.ccimageshare.M.NetWork.NetWorkUtil;
import com.example.henryzheng.ccimageshare.M.ZuiMeiModel.Image;
import com.example.henryzheng.ccimageshare.M.ZuiMeiModel.ZuiMeiTotayListResponse;
import com.example.henryzheng.ccimageshare.M.common.CCLog;
import com.example.henryzheng.ccimageshare.R;
import com.example.henryzheng.ccimageshare.V.MyRecycleView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.Iterator;
import java.util.List;


@ContentView(R.layout.fragment_image_list)
public class ZuiMeiBestFragment extends BaseFragment implements MyItemClickListener {
    String url = "http://lab.zuimeia.com/photo/photography/list/?appVersion=2.6" +
            ".3&channel=wallpaper&imsi=460012202301362&systemVersion=23&resolution=1080x1920" +
            "&platform=android&package_name=com.brixd" +
            ".wallpager&page=%d&tag=0&lang=zh-cn&openUDID=862258036210848&page_size=30&timestamp" +
            "=1480672294308";
    @ViewInject(R.id.recycleView)
    private MyRecycleView recyclerView;// recycle组件
    @ViewInject(R.id.swipeRefreshLayout0)
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<Image> images;// 图片集合
    private MyRecycleAdapt recycleAdapter;// recycle组件的适配器
    RecyclerView.LayoutManager _layoutManager;// recycleView的展示状态
    LinearLayoutManager lin;
    int page = 1;
    private static int LOAD_MORE_TYPE = 0;
    private static int REFRESH_DATA_TYPE = 1;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recycleAdapter = new MyRecycleAdapt(getActivity());
        lin = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(lin);
        recyclerView.setItemAnimator(new DefaultItemAnimator());// 设置增加或删除条目的动画
        recycleAdapter.setOnItemClickListener(this);
//        recyclerView.addItemDecoration(new RecycleItemDecoration(15));
        recyclerView.setAdapter(recycleAdapter); // 设置Adapter
        recyclerView.setIsFooterEnable(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                loadListData(REFRESH_DATA_TYPE);
//                testRequest();

            }
        });
        recyclerView.setLoadMoreListener(new MyRecycleView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                loadListData(LOAD_MORE_TYPE);
            }
        });
        CCLog.print("onViewCreated");
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

    /**
     * 网络请求数据
     *
     * @param load_data_type
     */
    public void loadListData(final int load_data_type) {
        String realRequestUrl = String.format(url, page);
        NetWorkUtil.Get(realRequestUrl, null, new MyCallBack<ZuiMeiTotayListResponse>() {
                    @Override
                    public void onSuccess(ZuiMeiTotayListResponse result) {
                        super.onSuccess(result);
//                        CCLog.print(result.toString());
                        if (result.getData().getImages().size() > 0) {
                            transImageUrl(result);
                            loadData(load_data_type, result.getData().getImages());
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        super.onError(ex, isOnCallback);
                        CCLog.print("ex:" + ex.getMessage() + ",isOnCallback:" + isOnCallback);

                    }
                }
        );

    }

    /**
     * 加载数据
     */
    public void loadData(int load_data_type, List<Image> images) {
        swipeRefreshLayout.setRefreshing(false);
        if (load_data_type == LOAD_MORE_TYPE) {
            recycleAdapter.loadMoreData(images);
            recyclerView.setLoadingMore(false);
        } else if (load_data_type == REFRESH_DATA_TYPE) {
            recycleAdapter.refreshData(images);
        }
        page++;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        CCLog.print("onDestroyView");
    }

    /**
     * 转化
     * @param response
     */
    private void transImageUrl(ZuiMeiTotayListResponse response) {
        String baseurl = "http://wpstatic.zuimeia.com/";
        Iterator<Image> iterator = response.getData().getImages().iterator();
        while (iterator.hasNext()) {
            Image image = iterator.next();
            image.setImageUrl(baseurl + image.getImageUrl() +
                    "?imageMogr/v2/auto-orient/thumbnail/800x600/quality/80");
            image.setOriginImageUrl(baseurl + image.getOriginImageUrl());
            this.images = response.getData().getImages();
        }
    }

    /**
     * 测试url
     */
    public void testRequest() {
        String todayFormatUrltest = "http://lab.zuimeia.com/wallpaper/category/1/?appVersion=2.6" +
                ".3&channel=wallpaper&imsi=460012202301362&systemVersion=23&resolution=1080x1920" +
                "&platform=android&req_version_code=2&package_name=com.brixd" +
                ".wallpager&time=1456329600&lang=zh-cn&openUDID=862258036210848&page_size=30";
        String todayFormatUrl = "http://lab.zuimeia.com/wallpaper/category/1/?appVersion=2.6" +
                ".3&channel=wallpaper&imsi=460012202301362&systemVersion=23&resolution=1080x1920" +
                "&platform=android&req_version_code=2&package_name=com.brixd" +
                ".wallpager&time=%s&lang=zh-cn&openUDID=862258036210848&page_size=30";

//        String todayUrl = String.format(todayFormatUrl, System.currentTimeMillis() / 1000);
                String todayUrl = String.format(todayFormatUrl,System.currentTimeMillis() / 1000-86400*3);

        CCLog.print("loadurl:" + todayUrl);
        NetWorkUtil.Get(todayUrl, null, new MyCallBack<ZuiMeiTotayListResponse>() {
                    @Override
                    public void onSuccess(ZuiMeiTotayListResponse result) {
                        super.onSuccess(result);
//                        CCLog.print(result.toString());
                        CCLog.print("success");
                        if (result.getData().getImages().size() > 0) {
//                            transImageUrl(result);
//                            loadData(load_data_type,result.getData().getImages());
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        super.onError(ex, isOnCallback);
                        CCLog.print("error");
                        CCLog.print("ex:" + ex.getMessage() + ",isOnCallback:" + isOnCallback);
                        swipeRefreshLayout.setRefreshing(false);

                    }
                }
        );
    }
}
