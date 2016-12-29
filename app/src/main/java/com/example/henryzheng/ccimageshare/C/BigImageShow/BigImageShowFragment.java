package com.example.henryzheng.ccimageshare.C.BigImageShow;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.henryzheng.ccimageshare.C.Base.BaseFragment;
import com.example.henryzheng.ccimageshare.C.mainfragments.i.MainFragmentInterface;
import com.example.henryzheng.ccimageshare.C.mainfragments.model.ImageListBaseModel;
import com.example.henryzheng.ccimageshare.C.mainfragments.p.MainFragmentsPresenter;
import com.example.henryzheng.ccimageshare.M.ZuiMeiModel.Image;
import com.example.henryzheng.ccimageshare.R;
import com.example.henryzheng.ccimageshare.V.MyViewPage2;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.fragment_big_image_show)
public class BigImageShowFragment extends BaseFragment implements MainFragmentInterface {
    private static int position = 0;
    @ViewInject(R.id.viewPage0)
    private MyViewPage2 viewPager;
    static List<Image> images = new ArrayList<>();
    static int imageAmout = 300;
    static List<BigImageFragment> bigImageFragments;
    private static MainFragmentsPresenter presenter;
    static ImageListBaseModel imageListBaseModel;
    static List<Image> existImages = new ArrayList<>();
    boolean isFirstEnter=true;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public static void setImagesAndModel(List<Image> loadImages, ImageListBaseModel
            imageListBaseModel, int loadPosition) {
        images = loadImages;
        position = loadPosition;
        initFragments();
        BigImageShowFragment.imageListBaseModel = imageListBaseModel;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (isFirstEnter){
        presenter = new MainFragmentsPresenter(this, imageListBaseModel.getUrl(),
                imageListBaseModel.getType());
        presenter.setPage(images.size() / 30);
        if (bigImageFragments != null)
            viewPager.setAdapter(new MyViewPageAdapt(getActivity().getSupportFragmentManager()));
        viewPager.setCurrentItem(position);
            isFirstEnter=false;
        }}

    private static void initFragments() {
        bigImageFragments = new ArrayList<>();
        for (int i = 0; i < imageAmout; i++) {
            BigImageFragment bigImageFragment = new BigImageFragment();
            bigImageFragments.add(bigImageFragment);
            if (i < images.size()) {
                bigImageFragment.setBigImage(images.get(i));
            }
        }
    }

    /**
     * 增加图片信息到fragments中
     *
     * @param addImages
     */
    private synchronized static void addImageToFragment(List<Image> addImages) {
        for (int i = 0; i < 30; i++) {
            bigImageFragments.get(images.size() + i).setBigImage(addImages.get(i));
        }
        images.addAll(addImages);
    }


    class MyViewPageAdapt extends FragmentStatePagerAdapter {

        public MyViewPageAdapt(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position > images.size()) {
                    presenter.loadListData(presenter.LOAD_MORE_TYPE);
            }
            return bigImageFragments.get(position);

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }



        @Override
        public int getCount() {
            return bigImageFragments.size();
        }
    }

    @Override
    public void loadNewImages(List<Image> images) {
        addImageToFragment(images);
    }

    @Override
    public void refreshImages(List<Image> images) {

    }

}
