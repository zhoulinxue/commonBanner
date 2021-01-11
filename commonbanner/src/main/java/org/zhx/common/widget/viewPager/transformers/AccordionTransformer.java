package org.zhx.common.widget.viewPager.transformers;

import android.view.View;

/**
 * Copyright (C), 2015-2020
 * FileName: AccordionTransformer
 * Author: zx
 * Date: 2020/1/9 9:10
 * Description:
 */
public class AccordionTransformer  extends BaseTransformer {
    @Override
    protected void onTransform(View view, float position) {
        view.setPivotX(position < 0 ? 0 : view.getWidth());
        view.setScaleX(position < 0 ? 1f + position : 1f - position);
    }
}
