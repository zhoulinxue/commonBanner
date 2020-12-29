package org.zhx.common.widget.indicator;

import android.content.Context;

import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Copyright (C), 2015-2019
 * FileName: ClassiceIndicator
 * Author: zx
 * Date: 2019/12/20 16:10
 * Description:
 */
public class ClassiceIndicator extends BaseViewIndicator {


    public ClassiceIndicator(Context mContext) {
        super(mContext);
    }

    @Override
    protected final void onItemUnSelected(View view) {
        view.setBackgroundResource(unSelectedSrc);
        setItemHeightAndWidth(view, nomalWidth, nomalHeight);
    }

    @Override
    public final void onItemSelected(View view) {
        view.setBackgroundResource(selectSrc);
        setItemHeightAndWidth(view, selectWidth, selectHeight);
    }

    @Override
    public void setItemHeightAndWidth(View view, int width, int height) {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) view.getLayoutParams();
        lp.width = selectWidth;
        lp.height = selectHeight;
        view.setLayoutParams(lp);
    }


    @Override
    public View creatIndicatorItem(int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setBackgroundResource(position == 0 ? selectSrc : unSelectedSrc);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(nomalWidth, nomalHeight);
        lp.gravity = Gravity.CENTER;
        lp.rightMargin = indicatorMargin;
        imageView.setLayoutParams(lp);
        return imageView;
    }
}
