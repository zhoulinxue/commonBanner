package com.common;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.DrawableRes;

import org.zhx.common.widget.BannerData;

/**
 * Copyright (C), 2015-2019
 * FileName: PicBanner
 * Author: zx
 * Date: 2019/12/20 15:21
 * Description:
 */
public class PicBanner implements BannerData, Parcelable {
    @DrawableRes
    private int src;
    private String dec;
    private int position;

    public void setPosition(int position) {
        this.position = position;
    }

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

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.src);
        dest.writeString(this.dec);
        dest.writeInt(this.position);
    }

    public PicBanner() {
    }

    protected PicBanner(Parcel in) {
        this.src = in.readInt();
        this.dec = in.readString();
        this.position = in.readInt();
    }

    public static final Parcelable.Creator<PicBanner> CREATOR = new Parcelable.Creator<PicBanner>() {
        @Override
        public PicBanner createFromParcel(Parcel source) {
            return new PicBanner(source);
        }

        @Override
        public PicBanner[] newArray(int size) {
            return new PicBanner[size];
        }
    };
}
