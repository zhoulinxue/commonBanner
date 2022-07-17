package org.zhx.common.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @ProjectName: banner
 * @Package: org.zhx.common.widget
 * @ClassName: BaseBannerAdapter
 * @Description:java
 * @Author: zhouxue
 * @CreateDate: 2020/12/18 16:52
 * @UpdateUser:
 * @UpdateDate: 2020/12/18 16:52
 * @UpdateRemark:
 * @Version:1.0
 */
public abstract class SimpleBannerAdapter<T> implements CommonBanner.BannerAdapter {
    private List<T> datas;
    private int resurceLayout;

    public SimpleBannerAdapter(int resurceLayout, List<T> datas) {
        this.datas = datas;
        this.resurceLayout = resurceLayout;
    }

    @Override
    public final View onCreatItem(Context context, final int positon) {
        View view = LayoutInflater.from(context).inflate(resurceLayout, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(v,positon);
            }
        });

        ViewHolder holder = creatViewHolder(view, positon);
        convert(holder, datas.get(positon));
        return view;
    }

    protected final ViewHolder creatViewHolder(View view, int positon) {
        return new ViewHolder(view, positon, this);
    }

    @Override
    public void onItemViewClick(View v) {

    }

    protected abstract void convert(ViewHolder holder, T t);

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }
}
