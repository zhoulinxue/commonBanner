package org.zhx.common.widget;

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
    private static Map<String, ViewHolder> map = new ConcurrentHashMap<>();

    public static void put(String position, ViewHolder holder) {
        map.put(position, holder);
    }

    public static ViewHolder get(String position) {
        return map.get(position);
    }
}
