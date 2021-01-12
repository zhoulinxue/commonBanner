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
    public T getPagerView();

    public void attach(RelativeLayout relativeLayout);

    public void setViewDatas(List<View> datas);

    public void setDuration(int duration);

    public void isAutoPlay(boolean isAutoPlay);

    public void setHeight(int height);

    public void setWidth(int width);

    public void setLoopType(LoopType type);
}
