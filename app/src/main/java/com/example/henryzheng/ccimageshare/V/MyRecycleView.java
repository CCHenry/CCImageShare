package com.example.henryzheng.ccimageshare.V;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

import com.example.henryzheng.ccimageshare.C.mainfragments.MyRecycleAdapt;

/**
 * Created by zzf on 16/12/7.
 */
public class MyRecycleView extends RecyclerView {

    /**
     * item 类型
     */
    public final static int TYPE_NORMAL = 0;
    public final static int TYPE_HEADER = 1;//头部--支持头部增加一个headerView
    public final static int TYPE_FOOTER = 2;//底部--往往是loading_more
    public final static int TYPE_LIST = 3;//代表item展示的模式是list模式
    public final static int TYPE_STAGGER = 4;//代码item展示模式是网格模式
Context context;
    private boolean mIsFooterEnable = false;//是否允许加载更多

    /**
     * 自定义实现了头部和底部加载更多的adapter
     */
    private RecyclerView.Adapter adapt;
    /**
     * 标记是否正在加载更多，防止再次调用加载更多接口
     */
    private boolean mIsLoadingMore=false;
    /**
     * 标记加载更多的position
     */
    private int mLoadMorePosition;
    /**
     * 加载更多的监听-业务需要实现加载数据
     */
    private LoadMoreListener mListener;

    public MyRecycleView(Context context) {
        super(context);

        init(context);
    }

    public MyRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyRecycleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    /**
     * 初始化-添加滚动监听
     * <p/>
     * 回调加载更多方法，前提是
     * <pre>
     *    1、有监听并且支持加载更多：null != mListener && mIsFooterEnable
     *    2、目前没有在加载，正在上拉（dy>0），当前最后一条可见的view是否是当前数据列表的最好一条--及加载更多
     * </pre>
     */
    private void init(Context context) {
    this.context=context;
        super.addOnScrollListener(new OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                 super.onScrolled(recyclerView, dx, dy);
                if (null != mListener && mIsFooterEnable && !mIsLoadingMore && dy > 0) {
                    int lastVisiblePosition = getLastVisiblePosition();
                    if (lastVisiblePosition + 1 == adapt.getItemCount()) {
                        setLoadingMore(true);
                        mLoadMorePosition = lastVisiblePosition;
                        mListener.onLoadMore();
                    }
                }
            }
        });
    }

    /**
     * 设置加载更多的监听
     *
     * @param listener
     */
    public void setLoadMoreListener(LoadMoreListener listener) {
        mListener = listener;
    }

    /**
     * 设置正在加载更多
     *
     * @param loadingMore
     */
    public void setLoadingMore(boolean loadingMore) {
        this.mIsLoadingMore = loadingMore;
    }

    /**
     * 加载更多监听
     */
    public interface LoadMoreListener {
        /**
         * 加载更多
         */
        void onLoadMore();
    }

    /**
     * 获取最后一条展示的位置
     *
     * @return
     */
    private int getLastVisiblePosition() {
        int position;
        if (getLayoutManager() instanceof LinearLayoutManager) {
            position = ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
        } else if (getLayoutManager() instanceof GridLayoutManager) {
            position = ((GridLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
        } else if (getLayoutManager() instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) getLayoutManager();
            int[] lastPositions = layoutManager.findLastVisibleItemPositions(new int[layoutManager.getSpanCount()]);
            position = getMaxPosition(lastPositions);
        } else {
            position = getLayoutManager().getItemCount() - 1;
        }
        return position;
    }

    /**
     * 获得最大的位置
     *
     * @param positions
     * @return
     */
    private int getMaxPosition(int[] positions) {
        int size = positions.length;
        int maxPosition = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            maxPosition = Math.max(maxPosition, positions[i]);
        }
        return maxPosition;
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        this.adapt = (MyRecycleAdapt) adapter;
    }

    public void setIsFooterEnable(boolean mIsFooterEnable) {
        this.mIsFooterEnable = mIsFooterEnable;
    }
    public void notifyMoreFinish(boolean hasMore) {
//        setAutoLoadMoreEnable(hasMore);

        getAdapter().notifyItemRemoved(mLoadMorePosition);
        mIsLoadingMore = false;
    }

}
