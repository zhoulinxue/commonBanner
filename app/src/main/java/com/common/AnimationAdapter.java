package com.common;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.zhx.common.widget.Builder;
import org.zhx.common.widget.CommonBanner;
import org.zhx.common.widget.LoopType;
import org.zhx.common.widget.SimpleBannerAdapter;
import org.zhx.common.widget.ViewHolder;
import org.zhx.common.widget.viewPager.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: banner
 * @Package: com.common
 * @ClassName: AnimationAdapter
 * @Description:java
 * @Author: 86138
 * @CreateDate: 2020/12/23 17:35
 * @UpdateUser:
 * @UpdateDate: 2020/12/23 17:35
 * @UpdateRemark:
 * @Version:1.0
 */
public class AnimationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Transformer> data;
    private int[] mImages = {R.mipmap.b, R.mipmap.d, R.mipmap.e, R.mipmap.f, R.mipmap.g, R.mipmap.h};
    List<ItemData> datas = new ArrayList<>();

    public AnimationAdapter(List<Transformer> data) {
        this.data = data;

        for (int i = 0; i < mImages.length; i++) {
            ItemData picBanner = new ItemData();
            picBanner.setSrc(mImages[i]);
            datas.add(picBanner);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.animation_item_layout, null);
        final ItemViewHolder viewHolder = new ItemViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int position) {
        CommonBanner banner = viewHolder.itemView.findViewById(R.id.banner_layout);
        banner.setTransformerType(data.get(position));
        Builder builder = new Builder(viewHolder.itemView.getContext());
        //自定义 底部指示牌
        builder.setHeight(350)//设置banner 高度
                .setIndicatorHeight(80)//设置 导航游标 高度
//              .indicatorBelow() //设置游标和内容相对 位置  可选 默认 游标悬浮在 内容底部
                .setIndicatorItemSelectSrc(R.mipmap.icon_star)// 设置 指示器  item 颜色
                .setIndicatorItemUnSelectedSrc(R.drawable.select_indicator)// 设置 indicator指示器  item 选中颜色
                .setIndicatorLayoutBackgroundRes(R.drawable.shape_indicator_bg) //设置 指示器 背景
                .setTransformer(Transformer.DETH) // 设置切换动画  新增10多种 动画  Transformer 类
                .setLoopType(LoopType.LOOP)// 设置循环滚动方式
                .setAutoPlay(true) //是否自动滚动  可选 默认 不滚动
//              .setIndicator(indecator)// 自定义 指示器
                .setDuration(500) //动画 执行时间  为了 让大家 看清楚效果  设置慢点
                .setDelayTime(2000);// 设置滚动间隔时间
        banner.setBuilder(builder);
        //设置item 数据回调
        banner.setBannerAdapter(new SimpleBannerAdapter<ItemData>(R.layout.banner_item_layout, datas) {
            @Override
            protected void convert(ViewHolder holder, ItemData item) {
                ImageView imageView = (ImageView) holder.findViewById(R.id.banner_img);
                TextView textView = (TextView) holder.findViewById(R.id.banner_tv);
                textView.setText(data.get(viewHolder.getLayoutPosition()).toString());
                imageView.setImageResource(item.getSrc());
                holder.addItemViewClick(R.id.banner_tv);
            }

            @Override
            public void onItemViewClick(View v) {
                Toast.makeText(viewHolder.itemView.getContext(), "点击 测试", Toast.LENGTH_SHORT).show();
            }
        });
        //item 点击事件
        banner.OnBannerItemClickLisenter(new CommonBanner.OnBannerItemClickLisenter() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(viewHolder.itemView.getContext(), position + " 点击item", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        public ItemViewHolder(View itemView) {
            super(itemView);
        }
    }
}
