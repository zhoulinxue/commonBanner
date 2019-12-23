package com.common.view;

import android.net.Uri;

import androidx.annotation.DrawableRes;

import com.common.view.banner.BannerData;

/**
 * Copyright (C), 2015-2019
 * FileName: PicBanner
 * Author: zx
 * Date: 2019/12/20 15:21
 * Description:
 */
public class PicBanner implements BannerData {
    @DrawableRes
    private int src;
    private String dec;

    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }

    @Override
    public String getPath() {
        return null;
    }

    @Override
    public String getTitle() {
        return getDec();
    }
}
