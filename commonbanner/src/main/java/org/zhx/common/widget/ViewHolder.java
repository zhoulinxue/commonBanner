package org.zhx.common.widget;

import android.util.SparseArray;
import android.view.View;

import androidx.annotation.IdRes;

/**
 * @ProjectName: banner
 * @Package: org.zhx.common.widget
 * @ClassName: ViewHolder
 * @Description:java
 * @Author: zhouxue
 * @CreateDate: 2020/12/18 17:00
 * @UpdateUser:
 * @UpdateDate: 2020/12/18 17:00
 * @UpdateRemark:
 * @Version:1.0
 */
public class ViewHolder implements View.OnClickListener {
    public View itemView;
    private int position;
    private final SparseArray<View> views;
    private CommonBanner.BannerAdapter adapter;

    public ViewHolder(View itemView, int position, CommonBanner.BannerAdapter adapter) {
        this.itemView = itemView;
        this.position = position;
        this.adapter = adapter;
        this.views = new SparseArray<>();
    }


    public int getPosition() {
        return position;
    }

    public View findViewById(@IdRes int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return view;
    }

    public void addItemViewClick(@IdRes int id) {
        addItemViewClick(id, this);
    }

    public void addItemViewClick(@IdRes int id, View.OnClickListener listener) {
        View view = findViewById(id);
        if (view != null) {
            view.setOnClickListener(listener);
        }
    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            adapter.onItemViewClick(v);
        }
    }
}
