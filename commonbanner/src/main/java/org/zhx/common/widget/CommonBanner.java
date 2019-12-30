package org.zhx.common.widget;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.DrawableRes;
import androidx.viewpager.widget.ViewPager;

import org.zhx.common.R;

import java.util.List;


/**
 * Copyright (C), 2015-2019
 * FileName: BannerContainer
 * Author: zx
 * Date: 2019/12/20 14:44
 * Description:
 */
public class CommonBanner extends FrameLayout implements ViewPager.OnPageChangeListener {
    private List<BannerData> mDatas;
    private BannerPegerAdapter mAdapter;
    private Bannerloader loadBanner;
    private Handler mHandler;
    private LayoutParams containerLp;
    private RelativeLayout.LayoutParams viewPagerLp, indicatorLp;
    private ViewPager mViewPager;
    private BannerIndicator mIndicators;
    private RelativeLayout mContainer;
    private static final int containerId = R.id.container_id;
    private int mContainerHeight, mIndicatorHeight;
    private boolean isBelow = false;
    private boolean autoPlay, isPressed;
    private int index = 0;
    private long delayTime = 3000;
    private boolean isPause;
    private Runnable playRunable = new Runnable() {
        @Override
        public void run() {
            if (!isPressed) {
                index++;
                if (mAdapter != null && autoPlay) {
                    if (index >= mAdapter.getCount()) {
                        index = 0;
                    }
                    mViewPager.setCurrentItem(index, true);
                    if (mIndicators != null) {
                        mIndicators.setSelection(index);
                    }
                    autoPlay();
                }
            } else {
                autoPlay();
            }
        }
    };


    public CommonBanner(Context context) {
        super(context);
        initView(context);
    }

    public CommonBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CommonBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void initView(Context context) {
        mHandler = new Handler();
        containerLp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        mContainer = new RelativeLayout(context);
        mContainer.setLayoutParams(containerLp);
        indicatorLp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        viewPagerLp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        mViewPager = new ViewPager(context);
        mViewPager.setId(containerId);
        mViewPager.addOnPageChangeListener(this);
        mIndicators = new BannerIndicator(context);
        mViewPager.setLayoutParams(viewPagerLp);
        indicatorLp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mIndicators.setLayoutParams(indicatorLp);
        mAdapter = new BannerPegerAdapter();
        mViewPager.setAdapter(mAdapter);
        mContainer.addView(mViewPager, viewPagerLp);
        mContainer.addView(mIndicators, indicatorLp);
        addView(mContainer);
        mViewPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        isPressed = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        isPressed = false;
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    public void setDatas(List<BannerData> datas) {
        this.mDatas = datas;
        if (mAdapter != null) {
            mAdapter.setDatas(datas);
        }
        if (datas != null && datas.size() != 0)
            mIndicators.setCount(datas.size());
    }

    public void setLoadBanner(Bannerloader loadBanner) {
        this.loadBanner = loadBanner;
        if (mAdapter != null) {
            mAdapter.setLoadBanner(loadBanner);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        index = position;
        if (mIndicators != null) {
            mIndicators.setSelection(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public interface Bannerloader {
        public View loadBanner(BannerData data);
    }

    public void setHeight(int height) {
        mContainerHeight = height;
        if (mViewPager != null) {
            viewPagerLp.height = height;
            mViewPager.setLayoutParams(viewPagerLp);
        }
        if (mContainer != null) {
            int totalhHight = height;
            if (mIndicatorHeight != 0 && isBelow) {
                totalhHight += mIndicatorHeight;
            }
            containerLp.height = totalhHight;
            mContainer.setLayoutParams(containerLp);
        }
    }

    public void setWidth(int width) {
        if (mViewPager != null) {
            containerLp.width = width;
            mContainer.setLayoutParams(containerLp);
        }
        if (mContainer != null) {
            containerLp.width = width;
            mContainer.setLayoutParams(containerLp);
        }
    }

    public void setSelectSrc(int selectSrc) {
        if (mIndicators != null) {
            mIndicators.setSelectSrc(selectSrc);
        }
    }

    public void setUnSelectedSrc(int unSelectedSrc) {
        if (mIndicators != null) {
            mIndicators.setUnSelectedSrc(unSelectedSrc);
        }
    }

    public void setIndicatorHeight(int height) {
        mIndicatorHeight = height;
        if (mIndicators != null) {
            indicatorLp.height = height;
            mIndicators.setLayoutParams(indicatorLp);
            mIndicators.setHeight(height);
        }
    }

    public void indicatorBelow() {
        isBelow = true;
        if (mIndicators != null) {
            int height = RelativeLayout.LayoutParams.WRAP_CONTENT;
            if (mIndicatorHeight != 0) {
                height = mIndicatorHeight;
            }
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, height);
            layoutParams.addRule(RelativeLayout.BELOW, containerId);
            mIndicators.setLayoutParams(layoutParams);
        }
        setHeight(mContainerHeight);
    }

    public void setIndicatorBackgroundRes(@DrawableRes int res) {
        if (mIndicators != null) {
            mIndicators.setBackgroundResource(res);
        }
    }

    public void autoPlay() {
        autoPlay = true;
        mHandler.postDelayed(playRunable, delayTime);
    }

    public synchronized void onPause() {
        if (autoPlay) {
            isPause = true;
            mHandler.removeCallbacks(playRunable);
        }
    }

    public long getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(long delayTime) {
        this.delayTime = delayTime;
    }

    public synchronized void onRestart() {
        if (isPause) {
            isPause = false;
            autoPlay();
        }
    }
}
