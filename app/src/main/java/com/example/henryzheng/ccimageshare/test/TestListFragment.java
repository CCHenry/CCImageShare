package com.example.henryzheng.ccimageshare.test;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.henryzheng.ccimageshare.C.Base.BaseActivity;
import com.example.henryzheng.ccimageshare.C.Base.BaseFragment;
import com.example.henryzheng.ccimageshare.M.utils.CCLog;
import com.example.henryzheng.ccimageshare.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.fragment_test_list)
public class TestListFragment extends BaseFragment implements View.OnClickListener{
    @ViewInject(R.id.recycleView)
    private TestRecycleView recyclerView;// recycle组件
    @ViewInject(R.id.swipeRefreshLayout0)
    private SwipeRefreshLayout swipeRefreshLayout;
    @ViewInject(R.id.btn)
    private Button btn;
    private TestRecycleAdapt recycleAdapter;// recycle组件的适配器
    RecyclerView.LayoutManager _layoutManager;// recycleView的展示状态
    LinearLayoutManager lin;
    int page = 1;
    List<String> list=new ArrayList<>();
    int x=0;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CCLog.print(">>>>>>>>>>>>>>>>>>>>>>>>enter");
        getfillDataToList();
        btn.setOnClickListener(this);
        recycleAdapter = new TestRecycleAdapt( (BaseActivity)getActivity());
        lin = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(lin);
        recyclerView.setItemAnimator(new DefaultItemAnimator());// 设置增加或删除条目的动画
        recyclerView.setAdapter(recycleAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        recycleAdapter.clear();
                        swipeRefreshLayout.setRefreshing(false);
//                        for (int i=0;i<9;i++)
//                        recycleAdapter.loaOnedData("item"+x++);?

                        List<String> list=new ArrayList<String>();
                        for (int i=0;i<9;i++)
                            list.add("item"+x++);
                        recycleAdapter.loadData2(list);
                    }
                },1000);
            }
        });
    }

    private List<String> getfillDataToList() {
        for (int i=0;i<4;i++){
            list.add("item"+x);
            x++;
        }
        return list;
    }

    @Override
    public void onClick(View v) {
        recycleAdapter.clear();

        getfillDataToList();
        getfillDataToList();
        getfillDataToList();
        getfillDataToList();

        recycleAdapter.loadData(list);

    }
}
