package com.example.henryzheng.ccimageshare.C.main;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.henryzheng.ccimageshare.C.Base.BaseActivity;
import com.example.henryzheng.ccimageshare.C.Base.BaseFragment;
import com.example.henryzheng.ccimageshare.C.mainfragments.ImageListBaseFragment;
import com.example.henryzheng.ccimageshare.C.mainfragments.model.HotContributorModel;
import com.example.henryzheng.ccimageshare.C.mainfragments.model.TodayZuiMeiModel;
import com.example.henryzheng.ccimageshare.C.mainfragments.model.ZuiMeiBestModel;
import com.example.henryzheng.ccimageshare.M.data.ImageModel;
import com.example.henryzheng.ccimageshare.M.utils.CCLog;
import com.example.henryzheng.ccimageshare.R;
import com.example.henryzheng.ccimageshare.V.MainFragmentViewPage;
import com.example.henryzheng.ccimageshare.V.NavigationView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

@ContentView(R.layout.fragment_main)
public class MainFragment extends BaseFragment {
    private static final int TO_CROP = 0x01;
    BaseActivity context;
    @ViewInject(R.id.mainPage)
    private MainFragmentViewPage mainPager;
    @ViewInject(R.id.rl0)
    private RelativeLayout rl0;
    @ViewInject(R.id.rl)
    private RelativeLayout rl;
    @ViewInject(R.id.nv)
    private NavigationView nv;
    private List<BaseFragment> _fragments;
    int currentIndex = 0;//当前的fragment Index

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initFragment();

        mainPager.setAdapter(new MainPageAdapt(getActivity().getSupportFragmentManager(),
                _fragments));
        context = (BaseActivity) getActivity();
        nv.setMainPage(mainPager);

        mainPager.setDisplayView(rl);
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    private void initFragment() {
        _fragments = new ArrayList<>();
        _fragments.add(ImageListBaseFragment.newInstance(new TodayZuiMeiModel()));
        _fragments.add(ImageListBaseFragment.newInstance(new HotContributorModel()));
        _fragments.add(ImageListBaseFragment.newInstance(new ZuiMeiBestModel()));
    }

    @Event(value = R.id.rl0, type = View.OnClickListener.class)
    private void OnClick(View view) {

        /**
         * 查找图片
         */
//        startPhotoCrop(CCFileUtil.getHandleFilePath(MyContonts.tempSubmitFile), TO_CROP);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 从相册返回的数据
        if (requestCode == TO_CROP) {

            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                CCLog.print("onActivityResult:" + uri.getPath());
//                    Bitmap smallBitmap = CCPictureUtil.getSmallBitmap((BaseActivity)
// getActivity(), uri);
//                    CCPictureUtil.saveBitmap(smallBitmap, MyContonts.smallImageCacheDir,
// MyContonts.smallImageName);
//                    final BmobFile bigImageFile = new BmobFile(new File(MyHelp.getRealFilePath
// (getActivity(), uri)));
//                    final BmobFile smallImageFile = new BmobFile(new File(MyContonts
// .smallImageCacheDir + "/" + MyContonts.smallImageName));
//                    uploadPicture(bigImageFile, smallImageFile);
            }
        }


    }

    /**
     * 上传大图片和缩略图
     *
     * @param bigImageFile
     * @param smallImageFile
     */
    private void uploadPicture(final BmobFile bigImageFile, final BmobFile smallImageFile) {
        smallImageFile.upload(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    bigImageFile.upload(new UploadFileListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                CCLog.print(bigImageFile.getFileUrl());
                                savePictureToServer(bigImageFile, smallImageFile);
                            }
                        }
                    });
                }
            }
        });

    }

    /**
     * /保存图片到服务端的表
     *
     * @param bigImageFile
     * @param smallImageFile
     */
    private void savePictureToServer(BmobFile bigImageFile, BmobFile smallImageFile) {
        ImageModel imageModel = new ImageModel();
        imageModel.setUsername(getBmobUser().getUsername());
        imageModel.setBigPicUrl(bigImageFile.getFileUrl());
        imageModel.setSmallPicUrl(smallImageFile.getFileUrl());
        imageModel.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null)
                    CCLog.print("cause:" + e.getCause() + ",errorcode" + e.getErrorCode());
                else
                    CCLog.print("submit Success:" + s);
            }
        });
    }

    /**
     * 裁剪图片
     *
     *
     * @param filePath 保存的文件路径
     */
    public void startPhotoCrop(String filePath, int tag) {
        Uri uri = Uri.fromFile(new File(filePath));
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
        intent.setType("image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 2);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 600);
        intent.putExtra("outputY", 300);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        startActivityForResult(intent, tag);

    }

    private class MainPageAdapt extends FragmentPagerAdapter {
        private List<BaseFragment> _fragments;

        public MainPageAdapt(FragmentManager fm, List<BaseFragment> fragment) {
            super(fm);
            _fragments = fragment;
        }

        @Override
        public Fragment getItem(int position) {
            return _fragments.get(position);
        }

        @Override
        public int getCount() {
            return _fragments.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
            CCLog.print("perform to delete fragment" + position);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mainPager.removeAllViews();
    }

//    public int getCurrentItem() {
//        return mainPager.getCurrentItem();
//    }
}
