package org.zhx.common.widget.transformers;

import android.view.View;

/**
 * Copyright (C), 2015-2020
 * FileName: BackgroundToForegroundTransformer
 * Author: zx
 * Date: 2020/1/9 9:11
 * Description:
 */
public class BackgroundToForegroundTransformer extends BaseTransformer{
    @Override
    protected void onTransform(View view, float position) {
        final float height = view.getHeight();
        final float width = view.getWidth();
        final float scale = min(position < 0 ? 1f : Math.abs(1f - position), 0.5f);

        view.setScaleX(scale);
        view.setScaleY(scale);
        view.setPivotX(width * 0.5f);
        view.setPivotY(height * 0.5f);
        view.setTranslationX(position < 0 ? width * position : -width * position * 0.25f);
    }

}