package com.example.henryzheng.ccimageshare.C.mainfragments.p;

import com.example.henryzheng.ccimageshare.C.mainfragments.i.MainFragmentInterface;
import com.example.henryzheng.ccimageshare.C.mainfragments.model.TodayZuiMeiModel;
import com.example.henryzheng.ccimageshare.M.CallBack.MyCallBack;
import com.example.henryzheng.ccimageshare.M.NetWork.NetWorkUtil;
import com.example.henryzheng.ccimageshare.M.Sql.CCDatabaseOpenHelper;
import com.example.henryzheng.ccimageshare.M.ZuiMeiModel.Image;
import com.example.henryzheng.ccimageshare.M.ZuiMeiModel.ZuiMeiTotayListResponse;
import com.example.henryzheng.ccimageshare.M.utils.CCLog;

import org.xutils.DbManager;

import java.util.Iterator;
import java.util.List;

/**
 * Created by henryzheng on 2016/12/20.
 */
public class MainFragmentsPresenter {
    String realRequestUrl;
    int page = 0;
    String url;
    public static int LOAD_MORE_TYPE = 0;
    public static int REFRESH_DATA_TYPE = 1;
    MainFragmentInterface mainFragmentInterface;
    private List<Image> images;
    String type;
    DbManager db= CCDatabaseOpenHelper.getInstance();

    /**
     * @param mainFragmentInterface
     * @param url                   加载url
     * @param type                  类型，用加载的fragment的类名表示
     */
    public MainFragmentsPresenter(MainFragmentInterface mainFragmentInterface, String url, String
            type) {
        this.mainFragmentInterface = mainFragmentInterface;
        this.url = url;
        this.type = type;
         }

    /**
     * 网络请求数据
     *
     * @param load_data_type
     */
    public void loadListData(final int load_data_type) {
        handlerPage(load_data_type);
        handlerUrl(type);
        NetWorkUtil.Get(realRequestUrl, null, new MyCallBack<ZuiMeiTotayListResponse>() {
                    @Override
                    public void onSuccess(ZuiMeiTotayListResponse result) {
                        super.onSuccess(result);
                        if (result.getData().getImages().size() > 0) {
                            transImageUrl(result);
                            handlerAndShowImages( load_data_type,result);
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
     * 处理图片和展示
     * @param load_data_type
     * @param result
     */
    private void handlerAndShowImages( final int load_data_type,ZuiMeiTotayListResponse result) {
        if (load_data_type == LOAD_MORE_TYPE) {
            mainFragmentInterface.loadNewImages(result.getData().getImages());

        } else if (load_data_type == REFRESH_DATA_TYPE)
            mainFragmentInterface.refreshImages(result.getData().getImages());
    }

    /**
     * 根据刷新和加载处理page
     * @param load_data_type
     */
    private void handlerPage(int load_data_type) {
        if (load_data_type==REFRESH_DATA_TYPE){
            page=1;
        }else
            page++;
    }

    /**
     * 不同的mode用不同的url处理方式
     */
    private void handlerUrl(String type) {
        if (type == TodayZuiMeiModel.class.getName()) {
            realRequestUrl = String.format(url, System.currentTimeMillis() / 1000 - 86400 * 30 *
                    (page - 1));
        } else {
            realRequestUrl = String.format(url, page);
        }
    }

    /**
     * 转化Image格式
     *
     * @param response
     */
    private void transImageUrl(ZuiMeiTotayListResponse response) {
        String baseurl = "http://wpstatic.zuimeia.com/";
        Iterator<Image> iterator = response.getData().getImages().iterator();
        while (iterator.hasNext()) {
            Image image = iterator.next();
            image.setOrigin_image_url(baseurl + image.getImage_url());

            image.setImage_url(baseurl + image.getImage_url() +
                    "?imageMogr/v2/auto-orient/thumbnail/800x600/quality/80");
            this.images = response.getData().getImages();
            image.setType(type);
        }
    }
}
