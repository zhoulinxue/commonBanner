package org.zhx.common.widget.viewPager.transformers;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * @ProjectName: banner
 * @Package: org.zhx.common.widget.transformers
 * @ClassName: Scoller
 * @Description:java
 * @Author: 86138
 * @CreateDate: 2020/12/24 9:32
 * @UpdateUser:
 * @UpdateDate: 2020/12/24 9:32
 * @UpdateRemark:
 * @Version:1.0
 */
public class FixedSpeedScroller extends Scroller {
    private int mDuration = 1000;

    public FixedSpeedScroller(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public FixedSpeedScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    /**
     * 设置切换时间
     */
    public void setDuration(int time) {
        mDuration = time;
    }

    /**
     * 获取切换时间
     */
    public int getmDuration() {
        return mDuration;
    }
}
