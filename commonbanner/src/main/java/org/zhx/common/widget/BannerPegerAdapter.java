package org.zhx.common.widget;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import android.view.View;
import android.view.ViewGroup;


/**
 * Copyright (C), 2015-2019
 * FileName: BannerPegerAdapter
 * Author: zx
 * Date: 2019/12/20 15:04
 * Description:
 */
public class BannerPegerAdapter extends PagerAdapter {
    private CommonBanner.BannerAdapter loadBanner;
    private int count;
    private CommonBanner.OnItemClickLisenter onItemClickLisenter;

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        int realPosition = position % loadBanner.getItemCount();
        final View view = loadBanner.onCreatItem(container, realPosition);
        view.setOnClickListener(new ChildClickLisenter(realPosition) {
            @Override
            public void onChildClick(View v, int position) {
                if (onItemClickLisenter != null) {
                    onItemClickLisenter.onItemClick(v, position);
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

    public void setCount(int count) {
        this.count = count;
        notifyDataSetChanged();
    }

    public void setLoadBanner(CommonBanner.BannerAdapter loadBanner) {
        this.loadBanner = loadBanner;
        notifyDataSetChanged();
    }

    public void setOnItemClickLisenter(CommonBanner.OnItemClickLisenter onBannerItemClickLisenter) {
        this.onItemClickLisenter = onBannerItemClickLisenter;
    }
}
