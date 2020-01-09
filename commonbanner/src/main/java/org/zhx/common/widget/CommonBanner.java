package org.zhx.common.widget;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import org.zhx.common.R;
import org.zhx.common.widget.transformers.AccordionTransformer;
import org.zhx.common.widget.transformers.BackgroundToForegroundTransformer;
import org.zhx.common.widget.transformers.BaseTransformer;
import org.zhx.common.widget.transformers.CardTransformer;
import org.zhx.common.widget.transformers.CubeInTransformer;
import org.zhx.common.widget.transformers.CubeOutTransformer;
import org.zhx.common.widget.transformers.DepthPageTransformer;
import org.zhx.common.widget.transformers.FlipHorizontalTransformer;
import org.zhx.common.widget.transformers.FlipVerticalTransformer;
import org.zhx.common.widget.transformers.ForegroundToBackgroundTransformer;
import org.zhx.common.widget.transformers.GalleryTransformer;
import org.zhx.common.widget.transformers.RotateDownTransformer;
import org.zhx.common.widget.transformers.RotateUpTransformer;
import org.zhx.common.widget.transformers.ScaleInOutTransformer;
import org.zhx.common.widget.transformers.StackTransformer;
import org.zhx.common.widget.transformers.TabletTransformer;
import org.zhx.common.widget.transformers.Transformer;
import org.zhx.common.widget.transformers.ZoomInTransformer;
import org.zhx.common.widget.transformers.ZoomOutSlideTransformer;
import org.zhx.common.widget.transformers.ZoomOutTranformer;

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
    private boolean autoPlay;
    private int index = 0;
    private long delayTime = 3000;
    private boolean isPause;
    private Runnable playRunable = new Runnable() {
        @Override
        public void run() {

            index++;
            if (mAdapter != null && autoPlay) {
                if (index >= mAdapter.getCount()) {
                    index = 0;
                }
                mViewPager.setCurrentItem(index, index != 0 && index != mAdapter.getCount());
                if (mIndicators != null) {
                    mIndicators.setSelection(index);
                }
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
        containerLp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mContainer = new RelativeLayout(context);
        mContainer.setLayoutParams(containerLp);
        indicatorLp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        viewPagerLp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        mViewPager = new ViewPager(context);
        mViewPager.setClipChildren(false);
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
        switch (state) {
            case ViewPager.SCROLL_STATE_DRAGGING:
                mHandler.removeCallbacks(playRunable);
                break;
            case ViewPager.SCROLL_STATE_IDLE:
                if (autoPlay)
                    autoPlay();
                break;
            default:
                break;
        }
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

    public void setTransformerType(Transformer mTransformer) {
        if (mTransformer != null) {
            BaseTransformer transformer = null;
            switch (mTransformer) {
                case CARD:
                    transformer = new CardTransformer();
                    break;
                case GALLERY:
                    mViewPager.setCurrentItem(mAdapter.getCount() / 2);
                    transformer = new GalleryTransformer();
                    break;
                case DETH:
                    transformer = new DepthPageTransformer();
                    break;
                case ACCORDION:
                    transformer = new AccordionTransformer();
                    break;
                case B_TO_F:
                    transformer = new BackgroundToForegroundTransformer();
                    break;
                case F_TO_B:
                    transformer = new ForegroundToBackgroundTransformer();
                    break;
                case CUBE_IN:
                    transformer = new CubeInTransformer();
                    break;
                case UBE_OUT:
                    transformer = new CubeOutTransformer();
                    break;
                case FLIP_HORIZONTAL:
                    transformer = new FlipHorizontalTransformer();
                    break;
                case FLIP_VERTICAL:
                    transformer = new FlipVerticalTransformer();
                    break;
                case ROTATE_DOWN:
                    transformer = new RotateDownTransformer();
                    break;
                case ROTATE_UP:
                    transformer = new RotateUpTransformer();
                    break;
                case SCALE:
                    transformer = new ScaleInOutTransformer();
                    break;
                case STACK:
                    transformer = new StackTransformer();
                    break;
                case TABLET:
                    transformer = new TabletTransformer();
                    break;
                case ZOOM_IN:
                    transformer = new ZoomInTransformer();
                    break;
                case ZOOM_OUT:
                    transformer = new ZoomOutTranformer();
                    break;
                case ZOOM_OUT_SLIDE:
                    transformer = new ZoomOutSlideTransformer();
                    break;

            }
            setTransformer(transformer);
        }
    }

    public void setTransformer(BaseTransformer transformer) {
        if (mViewPager != null) {
            mViewPager.setPageTransformer(true, transformer);
        }
    }
}
