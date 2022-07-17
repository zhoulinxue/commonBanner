package org.zhx.common.widget.viewPager;

import android.view.View;
import android.widget.RelativeLayout;

import org.zhx.common.widget.CommonBanner;
import org.zhx.common.widget.LoopType;

import java.util.List;

/**
 * @ProjectName: banner
 * @Package: org.zhx.common.widget.pager
 * @ClassName: IPager
 * @Description:java
 * @Author: 86138
 * @CreateDate: 2021/1/11 11:13
 * @UpdateUser:
 * @UpdateDate: 2021/1/11 11:13
 * @UpdateRemark:
 * @Version:1.0
 */
public interface IPager<T> {
    /**
     * 获取实现pager  的 View
     * 默认实现 是 ViewPager
     * @return
     */
    public T getPagerView();

    /**
     * Pager  需要放置的 父布局
     * @param relativeLayout
     */
    public void attach(RelativeLayout relativeLayout);

    /**
     *  item Data
     * @param adapter
     */
    public void setBannerAdapter(CommonBanner.BannerAdapter adapter);

    /**
     * item 自动切换 间隔时间
     * @param duration
     */
    public void setDuration(int duration);

    /**
     * 是否 自动滚动
     * @param isAutoPlay
     */
    public void autoPlay(boolean isAutoPlay);

    /**
     * 设置 pager  高度
     * @param height
     */
    public void setHeight(int height);

    /**
     * 设置pager 宽度
     * @param width
     */
    public void setWidth(int width);

    /**
     *  设置 滚动 方式  LOOP:无线循环    REVERSE:往复循环
     * @param type
     */
    public void setLoopType(LoopType type);

    /**
     * 暂停 自动滚动
     */
    public void onPause();

    /**
     * 恢复自动 滚动
     */
    public void onRestart();

    void setDelayTime(long delayTime);

    void notifyDataChange();
}
