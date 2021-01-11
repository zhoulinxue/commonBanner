package org.zhx.common.widget.indicator;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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
    protected int mDatas;
    protected List<View> mIndicator;
    protected int selectSrc;
    protected int unSelectedSrc;
    protected int currentIndex;
    protected int nomalWidth = 10;
    protected int nomalHeight = 10;
    protected int selectWidth = 12;
    protected int selectHeight = 12;
    protected ViewGroup mIndicatorContainer;
    protected int indicatorMargin = 18;

    public BaseViewIndicator(Context mContext) {
        this.mContext = mContext;
        initView(mContext);
    }

    public BaseViewIndicator(Context mContext, int indicatorMargin) {
        this.mContext = mContext;
        this.indicatorMargin = indicatorMargin;
        initView(mContext);
    }

    protected void initView(Context context) {
        mContext = context;
        mIndicator = new ArrayList<>();
        mIndicatorContainer = initializeLayout(context);
    }


    @Override
    public void setDatas(int datas) {
        this.mDatas = datas;
        mIndicatorContainer.removeAllViews();
        mIndicator.clear();
        if (datas <= 1) {
            return;
        }
        for (int i = 0; i < datas; i++) {
            View item = creatIndicatorItem(i);
            mIndicator.add(item);
            attachToGroup(item, i);
        }
    }

    public void attachToGroup(View item, int i) {
        ViewGroup.LayoutParams lp = item.getLayoutParams();
        if (lp != null)
            mIndicatorContainer.addView(item, lp);
        else
            mIndicatorContainer.addView(item, i);
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

    private void notifyData() {
        for (int i = 0; i < mIndicator.size(); i++) {
            if (i == currentIndex) {
                onItemSelected(mIndicator.get(i));
            } else {
                onItemUnSelected(mIndicator.get(i));
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
        return mDatas;
    }

    @Override
    public void setHeight(int height) {
        if (getIndicatorLayout() != null) {
            ViewGroup.LayoutParams lp = getIndicatorLayout().getLayoutParams();
            lp.height = height;
            getIndicatorLayout().setLayoutParams(lp);
        }
    }

    @Override
    public ViewGroup initializeLayout(Context context) {
        LinearLayout mIndicatorContainer = new LinearLayout(context);
        mIndicatorContainer.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
        mIndicatorContainer.setGravity(Gravity.CENTER);
        mIndicatorContainer.setOrientation(LinearLayout.HORIZONTAL);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mIndicatorContainer.setLayoutParams(lp);
        return mIndicatorContainer;
    }


    @Override
    public void setItemHeightAndWidth(View view, int width, int height) {

    }

    public int getNomalWidth() {
        return nomalWidth;
    }

    public void setNomalWidth(int nomalWidth) {
        this.nomalWidth = nomalWidth;
    }

    public int getNomalHeight() {
        return nomalHeight;
    }

    public void setNomalHeight(int nomalHeight) {
        this.nomalHeight = nomalHeight;
    }

    public int getSelectWidth() {
        return selectWidth;
    }

    public void setSelectWidth(int selectWidth) {
        this.selectWidth = selectWidth;
    }

    public int getSelectHeight() {
        return selectHeight;
    }

    public void setSelectHeight(int selectHeight) {
        this.selectHeight = selectHeight;
    }

    public int getIndicatorMargin() {
        return indicatorMargin;
    }

    public void setIndicatorMargin(int indicatorMargin) {
        this.indicatorMargin = indicatorMargin;
    }

    public abstract void onItemSelected(View t);
}
