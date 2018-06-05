package com.xu.videoapp;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;

/**
 * Created by yaochufa on 2018/6/5.
 */

public class ViewPagerLayoutManager extends LinearLayoutManager{
    private PagerSnapHelper pagerSnapHelper;

    public ViewPagerLayoutManager(Context context, int orientation) {
        super(context,orientation,false);
        pagerSnapHelper=new PagerSnapHelper();
    }

    @Override
    public void onAttachedToWindow(RecyclerView view) {
        super.onAttachedToWindow(view);
        pagerSnapHelper.attachToRecyclerView(view);
    }
}
