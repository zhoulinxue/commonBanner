package org.zhx.common.widget;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.View;
import android.view.ViewGroup;

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
    private List<Integer> count;
    private CommonBanner.OnBannerItemClickLisenter onBannerItemClickLisenter;

    public BannerPegerAdapter(ViewPager mPager) {
        this.mPager = mPager;
    }

    @Override
    public int getCount() {
        return count == null ? 0 : count.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        int realPosition = count.get(position);
        if (realPosition == -1) {
            realPosition = getCount() - 2;
        } else if (realPosition == getCount() - 1) {
            realPosition = 0;
        }
        final View view = loadBanner.loadBanner(realPosition);
        view.setOnClickListener(new ChildClickLisenter(realPosition) {
            @Override
            public void onChildClick(View v, int position) {
                if (onBannerItemClickLisenter != null) {
                    onBannerItemClickLisenter.onItemClick(v, position);
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

    public void setCount(List<Integer> count) {
        this.count = count;
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
