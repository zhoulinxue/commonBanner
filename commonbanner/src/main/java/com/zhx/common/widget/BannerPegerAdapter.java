package com.zhx.common.widget;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/**
 * Copyright (C), 2015-2019
 * FileName: BannerPegerAdapter
 * Author: zx
 * Date: 2019/12/20 15:04
 * Description:
 */
public class BannerPegerAdapter extends PagerAdapter {
    private List<BannerData> dataList;
    private CommonBanner.Bannerloader loadBanner;

    @Override
    public int getCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = loadBanner.loadBanner(getDatas().get(position));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public List<BannerData> getDatas() {
        return dataList;
    }

    public void setDatas(List<BannerData> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public void setLoadBanner(CommonBanner.Bannerloader loadBanner) {
        this.loadBanner = loadBanner;
    }
}
