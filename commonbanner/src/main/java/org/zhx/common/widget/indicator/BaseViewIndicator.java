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
    private ViewGroup mIndicatorContainer;
    private  RelativeLayout.LayoutParams mLp;

    public BaseViewIndicator(Context mContext) {
        this.mContext = mContext;
        initView(mContext);
    }

    protected void initView(Context context) {
        mContext = context;
        mIndicator = new ArrayList<>();
        mIndicatorContainer = initializeLayout(context);
        mLp = initLayoutParam();
        if (mLp != null)
            mIndicatorContainer.setLayoutParams(mLp);
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
            View item = initializeIndicatorItem(i);
            mIndicator.add(item);
            ViewGroup.LayoutParams lp = item.getLayoutParams();
            if (lp != null)
                mIndicatorContainer.addView(item, lp);
            else
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
    public RelativeLayout.LayoutParams getLayoutParams() {
        return mLp;
    }

    @Override
    public ViewGroup initializeLayout(Context context) {
        LinearLayout mIndicatorContainer = new LinearLayout(context);
        mIndicatorContainer.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
        mIndicatorContainer.setGravity(Gravity.CENTER);
        mIndicatorContainer.setOrientation(LinearLayout.HORIZONTAL);
        return mIndicatorContainer;
    }

    @Override
    public RelativeLayout.LayoutParams initLayoutParam() {
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        return lp;
    }

    public abstract void onItemSelected(View t);
}
