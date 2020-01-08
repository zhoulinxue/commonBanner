package org.zhx.common.widget.transformers;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Copyright (C), 2015-2020
 * FileName: GalleryTransformer
 * Author: zx
 * Date: 2020/1/8 14:08
 * Description:
 */
public class GalleryTransformer implements ViewPager.PageTransformer {
    private static final float MAX_ALPHA = 0.5f;
    private static final float MAX_SCALE = 0.9f;

    @Override
    public void transformPage(@NonNull View page, float position) {
        if (position < -1 || position > 1) {
            //不可见区域
            page.setAlpha(MAX_ALPHA);
            page.setScaleX(MAX_SCALE);
            page.setScaleY(MAX_SCALE);
        } else {
            //透明度效果
            if (position <= 0) {
                //pos区域[-1,0)
                page.setAlpha(MAX_ALPHA + MAX_ALPHA * (1 + position));
            } else {
                //pos区域[0,1]
                page.setAlpha(MAX_ALPHA + MAX_ALPHA * (1 - position));
            }
            //缩放效果
            float scale = Math.max(MAX_SCALE, 1 - Math.abs(position));
            page.setScaleX(scale);
            page.setScaleY(scale);
        }
    }
}
