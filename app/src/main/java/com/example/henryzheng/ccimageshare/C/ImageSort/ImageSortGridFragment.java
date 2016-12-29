package com.example.henryzheng.ccimageshare.C.ImageSort;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.henryzheng.ccimageshare.C.Base.BaseFragment;
import com.example.henryzheng.ccimageshare.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.fragment_image_sort_grid)
public class ImageSortGridFragment extends BaseFragment {
    @ViewInject(R.id.recycleView)
    private RecyclerView recyclerView;
//    private
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        recyclerView.setLayoutManager(lin);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());// 设置增加或删除条目的动画
//        recycleAdapter.setOnItemClickListener(this);
//        recyclerView.setAdapter(recycleAdapter); // 设置Adapter
    }
}
