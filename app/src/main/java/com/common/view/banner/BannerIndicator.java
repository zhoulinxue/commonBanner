package com.common.view.banner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.common.view.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2015-2019
 * FileName: BannerIndicator
 * Author: zx
 * Date: 2019/12/20 16:10
 * Description:
 */
public class BannerIndicator extends RelativeLayout {
    private int count;
    private int currentIndex;
    private Context mContext;
    private int selectSrc = R.drawable.selected_indicator;
    private int unSelectedSrc = R.drawable.select_indicator;
    private List<ImageView> mIndicator;
    private LinearLayout mIndicatorContainer;
    private RelativeLayout.LayoutParams containerLp;

    public BannerIndicator(Context context) {
        super(context);
        initView(context);
    }

    public BannerIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public BannerIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        mIndicator = new ArrayList<>();
        mIndicatorContainer = new LinearLayout(context);
        mIndicatorContainer.setOrientation(LinearLayout.HORIZONTAL);
        containerLp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        containerLp.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(mIndicatorContainer, containerLp);
    }

    public void setCount(int count) {
        this.count = count;
        mIndicatorContainer.removeAllViews();
        mIndicator.clear();
        for (int i = 0; i < count; i++) {
            ImageView imageView = new ImageView(mContext);
            imageView.setBackgroundResource(i == 0 ? selectSrc : unSelectedSrc);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(10, 10);
            lp.gravity = Gravity.CENTER;
            lp.rightMargin = 10;
            imageView.setLayoutParams(lp);
            mIndicator.add(imageView);
            mIndicatorContainer.addView(imageView, i);
        }
    }

    public void setSelection(int currentIndex) {
        if (currentIndex <= mIndicator.size()) {
            this.currentIndex = currentIndex;
            for (int i = 0; i < mIndicator.size(); i++) {
                if (i == currentIndex) {
                    mIndicator.get(i).setImageResource(selectSrc);
                } else {
                    mIndicator.get(i).setImageResource(unSelectedSrc);
                }
            }
        }
    }

    public void setSelectSrc(int selectSrc) {
        this.selectSrc = selectSrc;
    }

    public void setUnSelectedSrc(int unSelectedSrc) {
        this.unSelectedSrc = unSelectedSrc;
    }

    public void setHeight(int height) {
        if (mIndicatorContainer != null) {
            containerLp.height = height;
            mIndicatorContainer.setLayoutParams(containerLp);
        }
    }
}
