package org.zhx.common.widget.indicator;

import android.content.Context;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import org.zhx.common.widget.BannerData;

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
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) view.getLayoutParams();
        lp.width = 10;
        lp.height = 10;
        view.setLayoutParams(lp);
    }

    @Override
    public void onItemSelected(View view) {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) view.getLayoutParams();
        lp.width = 12;
        lp.height = 12;
        view.setLayoutParams(lp);
    }

    public void setIndicatorMargin(int indicatorMargin) {
        this.indicatorMargin = indicatorMargin;
    }

    @Override
    public View initializeIndicatorItem(BannerData data) {
        ImageView imageView = new ImageView(mContext);
        imageView.setBackgroundResource(data.getPosition() == 0 ? selectSrc : unSelectedSrc);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(10, 10);
        lp.gravity = Gravity.CENTER;
        lp.rightMargin = indicatorMargin;
        imageView.setLayoutParams(lp);
        return imageView;
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
        RelativeLayout.LayoutParams indicatorLp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        indicatorLp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        indicatorLp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        return indicatorLp;
    }

}
