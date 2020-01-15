package org.zhx.common.widget.indicator;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import org.zhx.common.widget.BannerData;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2015-2020
 * FileName: BaseViewIndicator
 * Author: zx
 * Date: 2020/1/14 11:23
 * Description:
 */
public abstract class BaseViewIndicator implements CommonIndicator<View> {
    protected Context mContext;
    protected List<BannerData> mDatas;
    protected List<View> mIndicator;
    protected int selectSrc;
    protected int unSelectedSrc;
    protected int currentIndex;
    private ViewGroup mIndicatorContainer;

    public BaseViewIndicator(Context mContext) {
        this.mContext = mContext;
        initView(mContext);
    }

    protected void initView(Context context) {
        mContext = context;
        mIndicator = new ArrayList<>();
        mIndicatorContainer = initializeLayout(context);
        RelativeLayout.LayoutParams lp = initLayoutParam();
        if (lp != null)
            mIndicatorContainer.setLayoutParams(lp);
    }

    @Override
    public void setDatas(List<BannerData> datas) {
        this.mDatas = datas;
        mIndicatorContainer.removeAllViews();
        mIndicator.clear();
        if (datas == null || datas.size() == 0) {
            return;
        }
        for (int i = 0; i < mDatas.size(); i++) {
            mDatas.get(i).setPosition(i + 1);
            View item = initializeIndicatorItem(mDatas.get(i));
            mIndicator.add(item);
            mIndicatorContainer.addView(item, i);
        }
    }


    @Override
    public void setSelection(int currentIndex) {
        if (currentIndex <= mIndicator.size()) {
            this.currentIndex = currentIndex;
            notifyData();
        }
    }

    @Override
    public ViewGroup getIndicatorLayout() {
        return mIndicatorContainer;
    }

    @Override
    public void setLayoutParams(RelativeLayout.LayoutParams indicatorLp) {
        if (indicatorLp != null) {
            mIndicatorContainer.setLayoutParams(indicatorLp);
        }
    }

    private void notifyData() {
        for (int i = 0; i < mIndicator.size(); i++) {
            if (i == currentIndex) {
                onItemSelected(mIndicator.get(i));
                mIndicator.get(i).setBackgroundResource(selectSrc);
            } else {
                onItemUnSelected(mIndicator.get(i));
                mIndicator.get(i).setBackgroundResource(unSelectedSrc);
            }
        }
    }

    protected abstract void onItemUnSelected(View t);

    public void setSelectedSrc(int selectSrc) {
        this.selectSrc = selectSrc;
        notifyData();
    }

    public void setIndicatorSrc(int unSelectedSrc) {
        this.unSelectedSrc = unSelectedSrc;
        notifyData();
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public void setHeight(int height) {
        if (getIndicatorLayout() != null) {
            ViewGroup.LayoutParams lp = getIndicatorLayout().getLayoutParams();
            lp.height = height;
            getIndicatorLayout().setLayoutParams(lp);
        }
    }

    public abstract void onItemSelected(View t);
}
