package org.zhx.common.widget;

import android.util.SparseArray;
import android.view.View;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ProjectName: banner
 * @Package: org.zhx.common.widget
 * @ClassName: ViewHolderHandler
 * @Description:java
 * @Author: zhouxue
 * @CreateDate: 2020/12/18 17:03
 * @UpdateUser:
 * @UpdateDate: 2020/12/18 17:03
 * @UpdateRemark:
 * @Version:1.0
 */
public class ViewHolderHandler {
    private static SparseArray<ViewHolder> map = new SparseArray<>();

    public static void put(int position, ViewHolder holder) {
        map.put(position, holder);
    }

    public static ViewHolder get(int position) {
        return map.get(position);
    }
}
