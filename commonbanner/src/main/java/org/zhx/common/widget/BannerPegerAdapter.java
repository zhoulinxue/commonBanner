package org.zhx.common.widget;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

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
    private ViewPager mPager;
    private float itemHeight;
    private LoopType mLoopType;
    private CommonBanner.OnBannerItemClickLisenter onBannerItemClickLisenter;

    public BannerPegerAdapter(ViewPager mPager) {
        this.mPager = mPager;
    }

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
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        final View view = loadBanner.loadBanner(getDatas().get(position));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onBannerItemClickLisenter != null) {
                    onBannerItemClickLisenter.onItemClick(getDatas().get(position));
                }
            }
        });
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

    public void setOnBannerItemClickLisenter(CommonBanner.OnBannerItemClickLisenter onBannerItemClickLisenter) {
        this.onBannerItemClickLisenter = onBannerItemClickLisenter;
    }

    public void setLoopType(LoopType mLoopType) {
        this.mLoopType = mLoopType;
    }
}
