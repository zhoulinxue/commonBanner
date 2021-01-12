package org.zhx.common.widget.viewPager;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.RelativeLayout;

import androidx.viewpager.widget.ViewPager;

import org.zhx.common.R;
import org.zhx.common.widget.BannerPegerAdapter;
import org.zhx.common.widget.IContact;
import org.zhx.common.widget.LoopType;
import org.zhx.common.widget.viewPager.transformers.FixedSpeedScroller;

import java.lang.reflect.Field;
import java.util.List;

import static org.zhx.common.widget.LoopType.LOOP;

/**
 * @ProjectName: banner
 * @Package: org.zhx.common.widget.pager
 * @ClassName: ViewPagerImpl
 * @Description:java
 * @Author: 86138
 * @CreateDate: 2021/1/11 11:19
 * @UpdateUser:
 * @UpdateDate: 2021/1/11 11:19
 * @UpdateRemark:
 * @Version:1.0
 */
public class ViewPagerManager implements IPager<ViewPager>, ViewPager.OnPageChangeListener {
    private RelativeLayout.LayoutParams viewPagerLp;
    private Context mContext;
    private ViewPager mViewPager;
    private static final int containerId = R.id.container_id;
    private BannerPegerAdapter mAdapter;
    private Handler mHandler;
    private IContact mContact;
    private boolean isAutoPlay;//是否 自动播放
    private long delayTime = 3000;// 自动滚动时间
    private boolean REVERSE = false;//是否到最後一個item
    private LoopType LOOP_TYPE = LOOP;// 循环方式
    private int index = 0;// 当前 位置
    private int BASE_NUM = 1000;
    private int count;

    public ViewPagerManager(Context context, IContact mContact) {
        this.mContext = context;
        this.mContact = mContact;
        mHandler = new Handler();
        viewPagerLp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        mViewPager = new ViewPager(context);
        mViewPager.setId(containerId);
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setLayoutParams(viewPagerLp);
        mAdapter = new BannerPegerAdapter();
        mViewPager.setAdapter(mAdapter);
    }

    @Override
    public ViewPager getPagerView() {
        return mViewPager;
    }

    @Override
    public void attach(RelativeLayout relativeLayout) {
        relativeLayout.addView(mViewPager, viewPagerLp);
    }

    @Override
    public void setViewDatas(List<View> datas) {
        if (mAdapter != null) {
            mAdapter.setDatas(datas);
            count = datas.size();
        }
        if (LOOP_TYPE == LOOP) {
            index = count * BASE_NUM;
            while (index > Integer.MAX_VALUE) {
                index = count * BASE_NUM / 2;
                BASE_NUM = BASE_NUM / 2;
            }
            mViewPager.setCurrentItem(index);
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (mContact != null) {
            mContact.onScrolling(position, positionOffset, positionOffsetPixels);
        }
    }

    @Override
    public void onPageSelected(int position) {
        index = position;
        int realIndex = position % count;
        if (mContact != null) {
            mContact.onSelected(realIndex);
        }
        if (LOOP != LOOP_TYPE) {
            if (realIndex == count - 1)
                REVERSE = true;
            if (realIndex == 0)
                REVERSE = false;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state) {
            case ViewPager.SCROLL_STATE_DRAGGING:
                mHandler.removeCallbacks(playRunable);
                break;
            case ViewPager.SCROLL_STATE_IDLE:
                if (index == mAdapter.getCount() - 1 && LOOP == LOOP_TYPE)
                    mViewPager.setCurrentItem(1, false);
                autoPlay(isAutoPlay);
                break;
            default:
                break;
        }
    }

    public void setDuration(int duration) {
        try {
            // 通过class文件获取mScroller属性
            Field mField = ViewPager.class.getDeclaredField("mScroller");
            mField.setAccessible(true);
            FixedSpeedScroller mScroller = new FixedSpeedScroller(mViewPager.getContext(), new AccelerateInterpolator());
            mScroller.setDuration(duration);
            mField.set(mViewPager, mScroller);
        } catch (Exception e) {

        }
    }

    @Override
    public void autoPlay(boolean isAutoPlay) {
        this.isAutoPlay = isAutoPlay;
        if (this.isAutoPlay) {
            mHandler.removeCallbacks(playRunable);
            mHandler.postDelayed(playRunable, delayTime);
        }
    }

    @Override
    public void setHeight(int height) {
        if (height == 0) {
            return;
        }
        viewPagerLp = (RelativeLayout.LayoutParams) mViewPager.getLayoutParams();
        viewPagerLp.height = height;
        mViewPager.setLayoutParams(viewPagerLp);
    }

    @Override
    public void setWidth(int width) {
        if (width == 0) {
            return;
        }
        viewPagerLp = (RelativeLayout.LayoutParams) mViewPager.getLayoutParams();
        viewPagerLp.width = width;
        mViewPager.setLayoutParams(viewPagerLp);
    }

    @Override
    public void setLoopType(LoopType type) {
        this.LOOP_TYPE = type;
    }

    @Override
    public void onPause() {
        if (isAutoPlay) {
            mHandler.removeCallbacks(playRunable);
        }
    }

    @Override
    public void onRestart() {
        if (isAutoPlay) {
            mHandler.postDelayed(playRunable, delayTime);
        }
    }

    private Runnable playRunable = new Runnable() {
        @Override
        public void run() {
            if (!REVERSE || LOOP == LOOP_TYPE)
                index++;
            else {
                index--;
            }
            if (mAdapter != null && isAutoPlay) {
                mViewPager.setCurrentItem(index, true);
                autoPlay(isAutoPlay);
            }
        }
    };
}
