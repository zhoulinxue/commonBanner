package org.zhx.common.widget.indicator;

import android.content.Context;

import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Copyright (C), 2015-2019
 * FileName: DefaultIndicator
 * Author: zx
 * Date: 2019/12/20 16:10
 * Description:
 */
public class DefaultIndicator extends BaseViewIndicator {
    private int indicatorMargin = 18;

    public DefaultIndicator(Context mContext) {
        super(mContext);
    }

    public DefaultIndicator(Context mContext, int indicatorMargin) {
        super(mContext);
        this.indicatorMargin = indicatorMargin;
    }

    @Override
    protected void onItemUnSelected(View view) {
        view.setBackgroundResource(unSelectedSrc);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) view.getLayoutParams();
        lp.width = 10;
        lp.height = 10;
        view.setLayoutParams(lp);
    }

    @Override
    public void onItemSelected(View view) {
        view.setBackgroundResource(selectSrc);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) view.getLayoutParams();
        lp.width = 12;
        lp.height = 12;
        view.setLayoutParams(lp);
    }

    public void setIndicatorMargin(int indicatorMargin) {
        this.indicatorMargin = indicatorMargin;
    }

    @Override
    public View initializeIndicatorItem(int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setBackgroundResource(position == 0 ? selectSrc : unSelectedSrc);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(10, 10);
        lp.gravity = Gravity.CENTER;
        lp.rightMargin = indicatorMargin;
        imageView.setLayoutParams(lp);
        return imageView;
    }
}
