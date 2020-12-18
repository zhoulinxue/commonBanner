package org.zhx.common.widget;

import android.view.View;

/**
 * @ProjectName: banner
 * @Package: org.zhx.common.widget
 * @ClassName: ChildClickLisenter
 * @Description:java
 * @Author: zhouxue
 * @CreateDate: 2020/12/4 19:08
 * @UpdateUser:
 * @UpdateDate: 2020/12/4 19:08
 * @UpdateRemark:
 * @Version:1.0
 */
abstract class ChildClickLisenter implements View.OnClickListener {
    private int position;

    public ChildClickLisenter(int position) {
        this.position = position;
    }

    @Override
    public final void onClick(View v) {
        onChildClick(v, position);
    }

    public abstract void onChildClick(View v, int position);

}
