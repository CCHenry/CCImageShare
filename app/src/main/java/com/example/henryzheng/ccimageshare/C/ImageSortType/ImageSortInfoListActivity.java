package com.example.henryzheng.ccimageshare.C.ImageSortType;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.henryzheng.ccimageshare.C.Base.BaseActivity;
import com.example.henryzheng.ccimageshare.C.Base.BaseFragment;
import com.example.henryzheng.ccimageshare.C.mainfragments.ImageListBaseFragment;
import com.example.henryzheng.ccimageshare.C.mainfragments.model.ImageListBaseModel;
import com.example.henryzheng.ccimageshare.C.mainfragments.model.ImageSortInfoListModel;
import com.example.henryzheng.ccimageshare.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_image_sort_info_list)
public class ImageSortInfoListActivity extends BaseActivity {
//    @ViewInject(R.id.fragment_container)
//    private fra rl0;
@ViewInject(R.id.tv0)
private TextView tv0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager manager=getFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        Intent intent=getIntent();
        String url=intent.getStringExtra("loadListUrl");
        String title=intent.getStringExtra("title");
        tv0.setText(title);
        ImageListBaseModel imageListBaseModel=new ImageSortInfoListModel();
        imageListBaseModel.setUrl(url);
       BaseFragment fragment= ImageListBaseFragment.newInstance(imageListBaseModel);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment).commit();
    }
}
