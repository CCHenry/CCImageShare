package com.example.henryzheng.ccimageshare.C.ImageList;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
public class ImageListFragment extends BaseFragment implements MyItemClickListener {
    String url = "http://lab.zuimeia.com/photo/userpicture/list/?appVersion=2.6.3&channel=wallpaper&imsi=460012202301362&systemVersion=23&resolution=1080x1920&platform=android&package_name=com.brixd.wallpager&page=%d&tag=0&lang=zh-cn&openUDID=862258036210848&page_size=30&timestamp=1480672294308";
    @ViewInject(R.id.recycleView)
    private MyRecycleView recyclerView;// recycle组件
    private List<Image> images;// 图片集合
    private MyRecycleAdapt recycleAdapter;// recycle组件的适配器
    RecyclerView.LayoutManager _layoutManager;// recycleView的展示状态
    LinearLayoutManager lin;
    int page = 1;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            recyclerView.notifyMoreFinish(true);
        }
    };

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
        recyclerView.setLoadMoreListener(new MyRecycleView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                loadListData();
            }
        });
//        loadData();
//        loadRecentBmobData();
        loadListData();
        CCLog.print("onViewCreated");

    }


    @Override
    public void onItemClick(View view, int postion) {
        Intent intent = new Intent(getActivity(), BigImageShowActivity.class);
        intent.putExtra("url", images.get(postion).getImageUrl());
        startActivity(intent);
    }

    @Override
    public void OnHandlerListener(Message msg) {
        super.OnHandlerListener(msg);
    }

    @Override
    public void onResume() {
        super.onResume();
//        loadRecentBmobData();
//        recycleAdapter.clear();
        CCLog.print("onResume");

    }

    /**
     * 网络请求数据
     */
    public void loadListData() {
        String realRequestUrl = String.format(url, page);
        NetWorkUtil.Get(realRequestUrl, null, new MyCallBack<ZuiMeiTotayListResponse>() {
                    @Override
                    public void onSuccess(ZuiMeiTotayListResponse result) {
                        super.onSuccess(result);
//                        CCLog.print(result.toString());
                        if (result.getData().getImages().size() == 30) {
                            transImageUrl(result);
                            loadData(result.getData().getImages());
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
    public void loadData(List<Image> images) {

//        recyclerView.setLayoutManager(lin);
        recycleAdapter.loadImageList(images);
        recyclerView.setLoadingMore(false);

        page++;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        CCLog.print("onDestroyView");
    }

    private void transImageUrl(ZuiMeiTotayListResponse response) {
        String baseurl = "http://wpstatic.zuimeia.com/";
        Iterator<Image> iterator = response.getData().getImages().iterator();
        while (iterator.hasNext()) {
            Image image = iterator.next();
            image.setImageUrl(baseurl + image.getImageUrl() + "?imageMogr/v2/auto-orient/thumbnail/800x600/quality/80");
            image.setOriginImageUrl(baseurl + image.getOriginImageUrl());
            this.images = response.getData().getImages();
        }

    }
}
