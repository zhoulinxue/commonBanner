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
public class ViewHolder {
    public View itemView;
    private int position;
    private final SparseArray<View> views;

    public ViewHolder(View itemView, int position) {
        this.itemView = itemView;
        this.position = position;
        this.views = new SparseArray<>();
        ViewHolderHandler.put(position, this);
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
}
