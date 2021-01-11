package org.zhx.common.widget.viewPager;

import android.widget.RelativeLayout;

import org.zhx.common.widget.CommonBanner;

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
    public T getPagerView();

    public void attach(RelativeLayout relativeLayout);

    public void setBannerAdapter(CommonBanner.BannerAdapter datas);

    public void setOnItemClickLisenter(CommonBanner.OnBannerItemClickLisenter onItemClickLisenter);

    public void setDuration(int duration);

    public void isAutoPlay(boolean isAutoPlay);

    public void setHeight(int height);

    public void setWidth(int width);
}
