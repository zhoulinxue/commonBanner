package com.common;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.zhx.common.widget.BannerData;
import org.zhx.common.widget.indicator.BaseViewIndicator;
import org.zhx.common.widget.indicator.CommonIndicator;

import java.util.List;

/**
 * Copyright (C), 2015-2020
 * FileName: TextIndicator
 * Author: zx
 * Date: 2020/1/14 17:38
 * Description:
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
        TextView textView = new TextView(mContext);
        textView.setText(data.getPosition() + "/" + mDatas.size());
        textView.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
        textView.setTextSize(10);
        return textView;
    }

    @Override
    public ViewGroup initializeLayout(Context context) {
        return new FrameLayout(context);
    }

    @Override
    public RelativeLayout.LayoutParams initLayoutParam() {
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        return lp;
    }
}
