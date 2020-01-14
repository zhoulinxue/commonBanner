package org.zhx.common.widget;

import android.content.Context;

import org.zhx.common.widget.indicator.CommonIndicator;
import org.zhx.common.widget.transformers.BaseTransformer;
import org.zhx.common.widget.transformers.Transformer;

/**
 * Copyright (C), 2015-2020
 * FileName: Builder
 * Author: zx
 * Date: 2020/1/14 15:08
 * Description:
 */
public class Builder {
    private Context mContext;
    private CommonIndicator mIndicator;
    private long delayTime = 3000;
    private LoopType loopType = LoopType.LOOP;
    private boolean autoPlay;
    private boolean isBelow = false;
    private int mContainerHeight, mIndicatorHeight;
    protected int selectSrc;
    protected int unSelectedSrc;
    private BaseTransformer transformer;
    private Transformer mTransformerType;
    private int indicatorLayoutBg;

    public Transformer getTransformerType() {
        return mTransformerType;
    }

    public Builder setTransformerType(Transformer mTransformerType) {
        this.mTransformerType = mTransformerType;
        return this;
    }

    public BaseTransformer getTransformer() {
        return transformer;
    }

    public void setTransformer(BaseTransformer transformer) {
        this.transformer = transformer;
    }

    public boolean isBelow() {
        return isBelow;
    }

    public void setBelow(boolean below) {
        isBelow = below;
    }

    public boolean isAutoPlay() {
        return autoPlay;
    }

    public Builder setAutoPlay(boolean autoPlay) {
        this.autoPlay = autoPlay;
        return this;
    }

    public Builder(Context mContext) {
        this.mContext = mContext;
    }

    public long getDelayTime() {
        return delayTime;
    }

    public Builder setDelayTime(long delayTime) {
        this.delayTime = delayTime;
        return this;
    }

    public LoopType getLoopType() {
        return loopType;
    }

    public Builder setLoopType(LoopType loopType) {
        this.loopType = loopType;
        return this;
    }

    public CommonIndicator getIndicator() {
        return mIndicator;
    }

    public Builder setIndicator(CommonIndicator mIndicator) {
        this.mIndicator = mIndicator;
        return this;
    }

    public int getHeight() {
        return mContainerHeight;
    }

    public Builder setHeight(int containerHeight) {
        this.mContainerHeight = containerHeight;
        return this;
    }

    public int getIndicatorHeight() {
        return mIndicatorHeight;
    }

    public Builder setIndicatorHeight(int indicatorHeight) {
        this.mIndicatorHeight = indicatorHeight;
        return this;
    }

    public int getSelectSrc() {
        return selectSrc;
    }

    public Builder setSelectSrc(int selectSrc) {
        this.selectSrc = selectSrc;
        return this;
    }

    public int getUnSelectedSrc() {
        return unSelectedSrc;
    }

    public Builder setUnSelectedSrc(int unSelectedSrc) {
        this.unSelectedSrc = unSelectedSrc;
        return this;
    }

    public CommonBanner build() {
        CommonBanner banner = new CommonBanner(mContext);
        banner.setLoop(getLoopType());
        banner.setDelayTime(getDelayTime());
        banner.setIndicatorHeight(getIndicatorHeight());
        banner.setHeight(getHeight());
        banner.setSelectSrc(getSelectSrc());
        banner.setUnSelectedSrc(getUnSelectedSrc());
        banner.setTransformer(getTransformer());
        banner.setTransformerType(getTransformerType());
        if (isAutoPlay())
            banner.autoPlay();
        if (isBelow())
            banner.indicatorBelow();
        if (getIndicator() != null)
            banner.setIndicator(getIndicator());
        banner.setIndicatorBackgroundRes(getIndicatorLayoutBg());
        return banner;
    }

    public int getIndicatorLayoutBg() {
        return indicatorLayoutBg;
    }

    public Builder setIndicatorBackgroundRes(int indicatorLayoutBg) {
        this.indicatorLayoutBg = indicatorLayoutBg;
        return this;
    }

    public Builder indicatorBelow() {
        setBelow(true);
        return this;
    }
}
