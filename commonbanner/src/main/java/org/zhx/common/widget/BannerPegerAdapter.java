package org.zhx.common.widget;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * Copyright (C), 2015-2019
 * FileName: BannerPegerAdapter
 * Author: zx
 * Date: 2019/12/20 15:04
 * Description:
 */
public class BannerPegerAdapter extends PagerAdapter {
    private Context mContxt;
    private CommonBanner.BannerAdapter mAdapter;
    private LoopType mLoopType;

    public BannerPegerAdapter(Context mContxt) {
        this.mContxt = mContxt;
    }

    public void setLoopType(LoopType loopType) {
        this.mLoopType = loopType;
        notifyDataSetChanged();
    }

    public void setAdapter(CommonBanner.BannerAdapter adapter) {
        Log.e("zxxx","setAdapter ");
        this.mAdapter = adapter;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return (mAdapter == null || mAdapter.getItemCount() == 0) ? 0 : (mLoopType == LoopType.LOOP) ? Integer.MAX_VALUE : mAdapter.getItemCount();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        int realPosition = position % mAdapter.getItemCount();
        View view = mAdapter.onCreatItem(mContxt, realPosition);
        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = (View) object;
        container.removeView(view);
        view = null;
    }

}
