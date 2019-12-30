package org.zhx.common.widget;

import java.util.List;

/**
 * Copyright (C), 2015-2019
 * FileName: Commbaner
 * Author: zx
 * Date: 2019/12/20 14:45
 * Description:
 */
public interface Commbaner {
    public void setDatas(List<BannerData> datas);

    public BannerData getDataByPosition(int position);
}
