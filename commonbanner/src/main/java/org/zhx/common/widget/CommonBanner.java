package org.zhx.common.widget;

import android.content.Context;
import android.os.Handler;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import org.zhx.common.R;
import org.zhx.common.widget.indicator.CommonIndicator;
import org.zhx.common.widget.transformers.AccordionTransformer;
import org.zhx.common.widget.transformers.BackgroundToForegroundTransformer;
import org.zhx.common.widget.transformers.BaseTransformer;
import org.zhx.common.widget.transformers.CubeInTransformer;
import org.zhx.common.widget.transformers.CubeOutTransformer;
import org.zhx.common.widget.transformers.DepthPageTransformer;
import org.zhx.common.widget.transformers.FlipHorizontalTransformer;
import org.zhx.common.widget.transformers.FlipVerticalTransformer;
import org.zhx.common.widget.transformers.ForegroundToBackgroundTransformer;
import org.zhx.common.widget.transformers.RotateDownTransformer;
import org.zhx.common.widget.transformers.RotateUpTransformer;
import org.zhx.common.widget.transformers.ScaleInOutTransformer;
import org.zhx.common.widget.transformers.StackTransformer;
import org.zhx.common.widget.transformers.TabletTransformer;
import org.zhx.common.widget.transformers.Transformer;
import org.zhx.common.widget.transformers.ZoomInTransformer;
import org.zhx.common.widget.transformers.ZoomOutSlideTransformer;
import org.zhx.common.widget.transformers.ZoomOutTranformer;


/**
 * Copyright (C), 2015-2019
 * FileName: BannerContainer
 * Author: zx
 * Date: 2019/12/20 14:44
 * Description:
 */
public class CommonBanner extends FrameLayout implements ViewPager.OnPageChangeListener {
    private String TAG = CommonBanner.class.getSimpleName();
    private BannerPegerAdapter mAdapter;
    private BannerAdapter loadBanner;
    private Handler mHandler;
    private LayoutParams containerLp;
    private RelativeLayout.LayoutParams viewPagerLp;
    private ViewPager mViewPager;
    private CommonIndicator mIndicators;
    private RelativeLayout mContainer;
    private static final int containerId = R.id.container_id;
    private int mContainerHeight, mIndicatorHeight;
    private boolean isBelow = false;
    private boolean autoPlay;
    private int index = 0;
    private int BASE_NUM = 1000;

    private boolean isPause;
    private long delayTime = 3000;
    private LoopType loopType = LoopType.LOOP;
    private boolean isUp = false;
    private Runnable playRunable = new Runnable() {
        @Override
        public void run() {
            if (!isUp || LoopType.LOOP == loopType)
                index++;
            else {
                index--;
            }
            if (mAdapter != null && autoPlay) {
                mViewPager.setCurrentItem(index, true);
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

    private void initView(Context context) {
        mHandler = new Handler();
        containerLp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mContainer = new RelativeLayout(context);
        mContainer.setLayoutParams(containerLp);

        viewPagerLp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        mViewPager = new ViewPager(context);
        mViewPager.setId(containerId);
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setLayoutParams(viewPagerLp);
        mAdapter = new BannerPegerAdapter();
        mViewPager.setAdapter(mAdapter);
        mContainer.addView(mViewPager, viewPagerLp);
        addView(mContainer);
    }

    private void setDatas(int size) {
        index = size * BASE_NUM;
        while (index > Integer.MAX_VALUE) {
            index = size * BASE_NUM / 2;
            BASE_NUM = BASE_NUM / 2;
        }
        mIndicators.setDatas(size);
        if (mAdapter != null) {
            mAdapter.setCount(size);
        }
        mViewPager.setCurrentItem(index);
    }

    protected void setLoopType(LoopType loop) {
        loopType = loop;
    }

    @Override
    public void onPageSelected(int position) {
        index = position;
        int realIndex = position % mIndicators.getItemCount();
        if (mIndicators != null) {
            if (LoopType.LOOP == loopType && mIndicators.getItemCount() != 0) {
                mIndicators.setSelection(realIndex);
            } else {
                mIndicators.setSelection(realIndex);
                if (realIndex == mIndicators.getItemCount() - 1)
                    isUp = true;
                if (realIndex == 0)
                    isUp = false;
            }
        }
        if (autoPlay)
            autoPlay();
    }

    public void setBannerAdapter(BannerAdapter loadBanner) {
        this.loadBanner = loadBanner;
        if (loadBanner != null && loadBanner.getItemCount() != 0) {
            setDatas(loadBanner.getItemCount());
        }
        if (mAdapter != null) {
            mAdapter.setLoadBanner(loadBanner);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.e(TAG, position + "onPageScrolled");

    }


    @Override
    public void onPageScrollStateChanged(int state) {
        Log.e(TAG, state + "onPageScrollStateChanged");
        switch (state) {
            case ViewPager.SCROLL_STATE_DRAGGING:
                mHandler.removeCallbacks(playRunable);
                break;
            case ViewPager.SCROLL_STATE_IDLE:
                if (index == mAdapter.getCount() - 1 && LoopType.LOOP == loopType)
                    mViewPager.setCurrentItem(1, false);
                if (autoPlay)
                    autoPlay();
                break;
            default:
                break;
        }
    }

    public void setBuilder(Builder builder) {
        if (builder != null)
            builder.build(this);
    }

    public interface BannerAdapter {
        public View onCreatItem(ViewGroup container, int positon);

        public int getItemCount();

        public void onItemViewClick(View v);
    }

    protected void setHeight(int height) {
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

    protected void setWidth(int width) {
        if (mViewPager != null) {
            containerLp.width = width;
            mContainer.setLayoutParams(containerLp);
        }
        if (mContainer != null) {
            containerLp.width = width;
            mContainer.setLayoutParams(containerLp);
        }
    }

    protected void setSelectSrc(int selectSrc) {
        if (mIndicators != null) {
            mIndicators.setSelectedSrc(selectSrc);
        }
    }

    protected void setUnSelectedSrc(int unSelectedSrc) {
        if (mIndicators != null) {
            mIndicators.setIndicatorSrc(unSelectedSrc);
        }
    }

    protected void setIndicatorHeight(int height) {
        mIndicatorHeight = height;
        if (mIndicators != null) {
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mIndicators.getIndicatorLayout().getLayoutParams();
            lp.height = height;
            mIndicators.setLayoutParams(lp);
        }
    }

    protected void indicatorBelow() {
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

    protected void setIndicatorBackgroundRes(@DrawableRes int res) {
        if (mIndicators != null) {
            mIndicators.getIndicatorLayout().setBackgroundResource(res);
        }
    }

    protected void setIndicatorBackgroundColor(@ColorRes int res) {
        if (mIndicators != null) {
            mIndicators.getIndicatorLayout().setBackgroundColor(mContainer.getContext().getResources().getColor(res));
        }
    }

    protected void autoPlay() {
        autoPlay = true;
        mHandler.removeCallbacks(playRunable);
        mHandler.postDelayed(playRunable, delayTime);
    }

    public synchronized void onPause() {
        if (autoPlay) {
            isPause = true;
            mHandler.removeCallbacks(playRunable);
        }
    }

    protected void setDelayTime(long delayTime) {
        this.delayTime = delayTime;
    }

    public synchronized void onRestart() {
        if (isPause) {
            isPause = false;
            autoPlay();
        }
    }

    protected void setTransformerType(Transformer mTransformer) {
        if (mTransformer != null) {
            BaseTransformer transformer = null;
            switch (mTransformer) {
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

    protected void setTransformer(BaseTransformer transformer) {
        if (mViewPager != null && transformer != null) {
            mViewPager.setPageTransformer(true, transformer);
        }
    }

    protected CommonIndicator getIndicator() {
        return mIndicators;
    }

    protected void setIndicator(CommonIndicator mIndicators) {
        this.mIndicators = mIndicators;
        mContainer.addView(mIndicators.getIndicatorLayout(), mIndicators.getLayoutParams());
    }

    public void setOnItemClickLisenter(OnBannerItemClickLisenter onBannerItemClickLisenter) {
        if (mAdapter != null) {
            mAdapter.setOnBannerItemClickLisenter(onBannerItemClickLisenter);
        }
    }

    public interface OnBannerItemClickLisenter {
        public void onItemClick(View v, int position);
    }
}
