package org.zhx.common.widget.indicator;

import android.content.Context;
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

    public T initializeIndicatorItem(int position);

    public ViewGroup initializeLayout(Context context);

    public ViewGroup getIndicatorLayout();

    public void setLayoutParams(RelativeLayout.LayoutParams indicatorLp);

    public void setSelection(int position);

    public int getItemCount();

    public void setSelectedSrc(int selectSrc);

    public void setIndicatorSrc(int unSelectedSrc);

    public void setHeight(int height);

    public RelativeLayout.LayoutParams initLayoutParam();


    RelativeLayout.LayoutParams getLayoutParams();
}
