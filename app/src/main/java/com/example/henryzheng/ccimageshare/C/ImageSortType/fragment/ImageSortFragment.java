package com.example.henryzheng.ccimageshare.C.ImageSortType.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.example.henryzheng.ccimageshare.C.Base.BaseFragment;
import com.example.henryzheng.ccimageshare.C.ImageSortType.ImageSortInfoListActivity;
import com.example.henryzheng.ccimageshare.C.ImageSortType.adapt.ImageSortTypeAdapt;
import com.example.henryzheng.ccimageshare.M.Interface.MyItemClickListener;
import com.example.henryzheng.ccimageshare.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 图片类型选择Fragment
 */
@ContentView(R.layout.fragment_select_image_type)
public class ImageSortFragment extends BaseFragment {
    @ViewInject(R.id.recycleView)
    private RecyclerView _recyclerView;
    private ImageSortTypeAdapt _adapt;
    private List<Map<String, String>> _list;
    String waitFormatUrl = "http://lab.zuimeia.com/wallpaper/category/1/tag/%d/list/?appVersion=2" +
            ".6.3&channel=wallpaper&imsi=460012202301362&systemVersion=23&resolution=1080x1920" +
            "&platform=android&package_name=com.brixd" +
            ".wallpager&page=1&lang=zh-cn&openUDID=862258036210848&page_size=30&timestamp" +
            "=1482979226514";
    private String[] titles = new String[]{
            "热血运动",
            "素雅",
            "唯美",
            "美女",
            "二次元",
            "文艺范",
            "美食",
            "风景",
            "插画",
            "植物",
            "建筑",

    };
    private String[] urls = new String[]{
            "http://img.pconline.com" +
                    ".cn/images/upload/upc/tx/wallpaper/1307/10/c2/23152106_1373425282143.jpg",
            "http://i2.sinaimg.cn/travel/2013/1220/U8822P704DT20131220105515.jpg",
            "http://tupian.enterdesk.com/2014/lxy/2014/05/24/2/11.jpg",
            "http://c.hiphotos.baidu.com/image/pic/item/b7003af33a87e95097d47eac14385343faf2b42b" +
                    ".jpg",
            "http://i0.hdslb.com/bfs/archive/decf3cc64149dde20a6b6b6ca2933d22bffce6dd.jpg",
            "http://h.jiaju.sina.com.cn/images/2013/0312/U4093P897DT20130312091940.jpg",
            "http://img1.imgtn.bdimg.com/it/u=2977549189,1146316290&fm=21&gp=0.jpg",
            "http://tupian.enterdesk.com/2013/mxy/12/10/15/3.jpg",
            "http://img5q.duitang.com/uploads/item/201505/22/20150522003153_S2kQU.thumb.700_0.jpeg",
            "http://img0.imgtn.bdimg.com/it/u=3023602300,2118512604&fm=21&gp=0.jpg",
            "http://pic.58pic.com/58pic/13/84/50/27f58PICrWk_1024.jpg"
    };
    private int[] tags = new int[]{
            5004,
            37,
            40,
            38,
            1071,
            34,
            19,
            18,
            24,
            26,
            23

    };

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _adapt = new ImageSortTypeAdapt(getActivity(), 2);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, RecyclerView
                .VERTICAL);
        _recyclerView.setLayoutManager(manager);
        _recyclerView.setAdapter(_adapt);
        _recyclerView.setItemAnimator(new DefaultItemAnimator());// 设置增加或删除条目的动画
        _adapt.loadImgList(getImageInfoList());
        _adapt.setOnItemClickListener(new MyItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Intent intent = new Intent(getActivity(), ImageSortInfoListActivity.class);
                intent.putExtra("loadListUrl",String.format(waitFormatUrl,tags[postion]));
                intent.putExtra("title",titles[postion]);
                startActivity(intent);
            }
        });
    }

    /**
     * 返回图片的url和title列表
     *
     * @return
     */
    private List<Map<String, String>> getImageInfoList() {
        _list = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("url", urls[i]);
            map.put("title", titles[i]);
            map.put("imageUrl",String.format(waitFormatUrl,tags[i]));
            _list.add(map);
        }
        return _list;
    }


}
