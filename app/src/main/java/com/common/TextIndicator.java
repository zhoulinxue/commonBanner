package com.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
    public void attachToGroup(View item, int i) {
        super.attachToGroup(item, i);
        item.setVisibility(View.GONE);
    }

    @Override
    public View creatIndicatorItem(int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.text_indicator_item_layout, null);
        TextView textView = view.findViewById(R.id.item_tv);
        textView.setText((position + 1) + "/" + mDatas);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        lp.rightMargin = 30;
        view.setLayoutParams(lp);
        return view;
    }

}
