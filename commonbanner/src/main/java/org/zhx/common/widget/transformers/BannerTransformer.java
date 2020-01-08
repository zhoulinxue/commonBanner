package org.zhx.common.widget.transformers;

import android.view.View;

/**
 * Copyright (C), 2015-2020
 * FileName: BannerTransformer
 * Author: zx
 * Date: 2020/1/8 13:52
 * Description:
 */
public interface BannerTransformer {
    public void onMove(View itemView,Direction direction,float process);
}
