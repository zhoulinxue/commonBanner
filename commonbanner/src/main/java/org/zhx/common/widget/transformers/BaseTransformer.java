package org.zhx.common.widget.transformers;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;
/**
 * Copyright (C), 2015-2020
 * FileName: BaseTransformer
 * Author: zx
 * Date: 2020/1/9 9:11
 * Description:
 */
public abstract class BaseTransformer implements  ViewPager.PageTransformer {
    protected abstract void onTransform(View page, float position);
    protected void onPostTransform(View page, float position) {
    }
    protected void onPreTransform(View page, float position) {
        final float width = page.getWidth();

        page.setRotationX(0);
        page.setRotationY(0);
        page.setRotation(0);
        page.setScaleX(1);
        page.setScaleY(1);
        page.setPivotX(0);
        page.setPivotY(0);
        page.setTranslationY(0);
        page.setTranslationX(isPagingEnabled() ? 0f : -width * position);

        if (hideOffscreenPages()) {
            page.setAlpha(position <= -1f || position >= 1f ? 0f : 1f);
            page.setEnabled(false);
        } else {
            page.setEnabled(true);
            page.setAlpha(1f);
        }
    }
    protected static final float min(float val, float min) {
        return val < min ? min : val;
    }
    protected boolean isPagingEnabled() {
        return false;
    }
    protected boolean hideOffscreenPages() {
        return true;
    }

    @Override
    public final void transformPage(@NonNull View view, float v) {
        onPreTransform(view, v);
        onTransform(view, v);
        onPostTransform(view, v);
    }
}
