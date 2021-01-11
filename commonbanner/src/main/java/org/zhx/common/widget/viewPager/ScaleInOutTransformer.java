package org.zhx.common.widget.viewPager;

import android.view.View;
/**
 * Copyright (C), 2015-2020
 * FileName: ScaleInOutTransformer
 * Author: zx
 * Date: 2020/1/9 9:11
 * Description:
 */
public class ScaleInOutTransformer extends BaseTransformer {

	@Override
	protected void onTransform(View view, float position) {
		view.setPivotX(position < 0 ? 0 : view.getWidth());
		view.setPivotY(view.getHeight() / 2f);
		float scale = position < 0 ? 1f + position : 1f - position;
		view.setScaleX(scale);
		view.setScaleY(scale);
	}

}
