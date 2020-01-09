package org.zhx.common.widget.transformers;

import androidx.annotation.NonNull;

import android.view.View;

/**
 * Copyright (C), 2015-2020
 * FileName: CardTransformer
 * Author: zx
 * Date: 2020/1/8 15:43
 * Description:
 */
public class CardTransformer extends BaseTransformer{
    private int mOffset = 60;

    @Override
    protected void onTransform(View page, float position) {
        if (position <= 0) {
            page.setRotation(45 * position);
            page.setTranslationX((page.getWidth() / 2 * position));
        } else {
            //移动X轴坐标，使得卡片在同一坐标
            page.setTranslationX(-position * page.getWidth());
            //缩放卡片并调整位置
            float scale = (page.getWidth() - mOffset * position) / page.getWidth();
            page.setScaleX(scale);
            page.setScaleY(scale);
            //移动Y轴坐标
            page.setTranslationY(position * mOffset);
        }
    }
}
