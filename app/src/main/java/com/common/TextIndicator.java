package com.common;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.zhx.common.widget.BannerData;
import org.zhx.common.widget.indicator.BaseViewIndicator;

/**
 * Copyright (C), 2015-2020
 * FileName: TextIndicator
 * Author: zx
 * Date: 2020/1/14 17:38
 * Description: 自定义  游标
 */
public class TextIndicator extends BaseViewIndicator {

    public TextIndicator(Context mContext) {
        super(mContext);
    }

    @Override
    protected void onItemUnSelected(View t) {
        t.setVisibility(View.GONE);
    }

    @Override
    public void onItemSelected(View t) {
        t.setVisibility(View.VISIBLE);
    }

    @Override
    public View initializeIndicatorItem(BannerData data) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.text_indicator_item_layout, null);
        TextView textView = view.findViewById(R.id.item_tv);
        textView.setText((data.getPosition() + 1) + "/" + mDatas.size());
        return view;
    }

    @Override
    public RelativeLayout.LayoutParams initLayoutParam() {
        RelativeLayout.LayoutParams lp = super.initLayoutParam();
        lp.rightMargin = 30;
        return lp;
    }
}
