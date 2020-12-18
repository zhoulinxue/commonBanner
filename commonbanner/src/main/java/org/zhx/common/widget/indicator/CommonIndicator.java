package org.zhx.common.widget.indicator;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Copyright (C), 2015-2020
 * FileName: CommonIndicator
 * Author: zx
 * Date: 2020/1/14 10:51
 * Description:
 */
public interface CommonIndicator<T> {
    public void setDatas(int datas);

    public T creatIndicatorItem(int position);

    public ViewGroup initializeLayout(Context context);

    public ViewGroup getIndicatorLayout();

    public void setLayoutParams(RelativeLayout.LayoutParams indicatorLp);

    public void setSelection(int position);

    public int getItemCount();

    public void setSelectedSrc(int selectSrc);

    public void setIndicatorSrc(int unSelectedSrc);

    public void setHeight(int height);

    public RelativeLayout.LayoutParams initLayoutParam();

    public void setItemHeightAndWidth(View view, int width, int height);

    RelativeLayout.LayoutParams getLayoutParams();
}
