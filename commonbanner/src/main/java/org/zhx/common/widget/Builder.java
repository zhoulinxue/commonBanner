package org.zhx.common.widget;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.ColorRes;

import org.zhx.common.R;
import org.zhx.common.widget.indicator.ClassiceIndicator;
import org.zhx.common.widget.indicator.CommonIndicator;
import org.zhx.common.widget.viewPager.transformers.BaseTransformer;
import org.zhx.common.widget.viewPager.transformers.Transformer;

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
    private int indicatorLayoutColor;
    private ViewGroup mParent;
    private int width;
    private int duration;

    public int getWidth() {
        return width;
    }

    public Builder setWidth(int width) {
        this.width = width;
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public Builder setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public Builder(Context mContext, ViewGroup mParent) {
        this.mContext = mContext;
        this.mParent = mParent;
    }

    public Transformer getTransformerType() {
        return mTransformerType;
    }

    public Builder setTransformer(Transformer mTransformerType) {
        this.mTransformerType = mTransformerType;
        return this;
    }

    @Deprecated
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

    /**
     * replaced  by  setIndicatorItemSelectSrc（）
     */

    @Deprecated
    public Builder setSelectSrc(int selectSrc) {
        this.selectSrc = selectSrc;
        return this;
    }

    public Builder setIndicatorItemSelectSrc(int selectSrc) {
        this.selectSrc = selectSrc;
        return this;
    }

    /**
     * replaced  by  setIndicatorItemUnSelectedSrc（）
     */
    @Deprecated
    public int getUnSelectedSrc() {
        return unSelectedSrc;
    }

    public Builder setUnSelectedSrc(int unSelectedSrc) {
        this.unSelectedSrc = unSelectedSrc;
        return this;
    }

    public Builder setIndicatorItemUnSelectedSrc(int unSelectedSrc) {
        this.unSelectedSrc = unSelectedSrc;
        return this;
    }

    public CommonBanner build(CommonBanner banner) {
        banner.setBuilder(this);
        return banner;
    }

    public CommonBanner build() {
        return build(new CommonBanner(mContext));
    }

    public int getIndicatorLayoutBg() {
        return indicatorLayoutBg;
    }

    /**
     * replaced  by  setIndicatorLayoutBackgroundRes（）
     */
    @Deprecated
    public Builder setIndicatorBackgroundRes(int indicatorLayoutBg) {
        this.indicatorLayoutBg = indicatorLayoutBg;
        return this;
    }

    public int getIndicatorLayoutColor() {
        return indicatorLayoutColor;
    }

    public Builder setIndicatorLayoutBackgroundRes(int indicatorLayoutBg) {
        this.indicatorLayoutBg = indicatorLayoutBg;
        return this;
    }

    public Builder setIndicatorLayoutColor(@ColorRes int indicatorLayoutColor) {
        this.indicatorLayoutColor = indicatorLayoutColor;
        return this;
    }

    public Builder indicatorBelow() {
        setBelow(true);
        return this;
    }

    public static Builder getDefault(Context context) {
        Builder builder = new Builder(context);
        builder.setHeight(350)//设置banner 高度
                .setIndicatorHeight(80)//设置 导航游标 高度
//                .indicatorBelow() //设置游标和内容相对 位置  可选 默认 游标悬浮在 内容底部
                .setIndicatorItemSelectSrc(R.drawable.selected_indicator)// 设置 indicator指示器  item 选中颜色
                .setIndicatorItemUnSelectedSrc(R.drawable.select_indicator)// 设置 指示器  item 颜色
                .setIndicatorLayoutColor(R.color.indicator_bg) //设置 指示器 背景
                .setTransformer(Transformer.NONE) // 设置切换动画  新增10多种 动画  Transformer 类
                .setLoopType(LoopType.REVERSE)// 设置循环滚动方式
                .setAutoPlay(true) //是否自动滚动  可选 默认 不滚动
                .setDuration(300)// 设置 动画 持续时间(数字越大  切换动画越慢)
                .setIndicator(new ClassiceIndicator(context))// 自定义 指示器
//                .setIndicator(null)
                .setDelayTime(2000);// 设置滚动间隔时间
        return builder;
    }
}
