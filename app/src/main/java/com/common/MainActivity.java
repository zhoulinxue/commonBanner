package com.common;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import org.zhx.common.widget.CommonBanner;
import org.zhx.common.widget.SimpleBannerAdapter;
import org.zhx.common.widget.Builder;
import org.zhx.common.widget.LoopType;
import org.zhx.common.widget.ViewHolder;
import org.zhx.common.widget.viewPager.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private int[] mImages = {R.mipmap.b, R.mipmap.d, R.mipmap.e, R.mipmap.f, R.mipmap.g, R.mipmap.h};
    List<ItemData> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < mImages.length; i++) {
            ItemData picBanner = new ItemData();
            picBanner.setSrc(mImages[i]);
            datas.add(picBanner);
        }
        CommonBanner banner = findViewById(R.id.banner_layout);
        Builder builder = new Builder(this);
        //自定义 底部指示牌
        builder.setHeight(350)//设置banner 高度
                .setIndicatorHeight(80)//设置 导航游标 高度
//                .indicatorBelow() //设置游标和内容相对 位置  可选 默认 游标悬浮在 内容底部
                .setIndicatorItemSelectSrc(R.drawable.select_indicator)// 设置 指示器  item 颜色
                .setIndicatorItemUnSelectedSrc(R.drawable.selected_indicator)// 设置 indicator指示器  item 选中颜色
                .setIndicatorLayoutBackgroundRes(R.drawable.shape_indicator_bg) //设置 指示器 背景
                .setTransformer(Transformer.DETH) // 设置切换动画  新增10多种 动画  Transformer 类
                .setLoopType(LoopType.REVERSE)// 设置循环滚动方式
                .setAutoPlay(true) //是否自动滚动  可选 默认 不滚动
                .setDuration(300)// 设置 动画 持续时间(数字越大  切换动画越慢)
//                .setIndicator(new TextIndicator(this))// 自定义 指示器
                .setDelayTime(2000);// 设置滚动间隔时间
        banner.setBuilder(builder);
        //设置item 数据回调
        banner.setBannerAdapter(new SimpleBannerAdapter<ItemData>(R.layout.banner_item_layout, datas) {
            @Override
            protected void convert(ViewHolder holder, ItemData item) {
                //填充 item 数据
                ImageView imageView = (ImageView) holder.findViewById(R.id.banner_img);
                imageView.setImageResource(item.getSrc());
                holder.addItemViewClick(R.id.banner_tv);
            }

            @Override
            public void onItemViewClick(View v) {
                // item 中 某个 View 被点击
                Toast.makeText(MainActivity.this, "点击 测试", Toast.LENGTH_SHORT).show();
            }
        });
        //item 点击事件
        banner.OnBannerItemClickLisenter(new CommonBanner.OnBannerItemClickLisenter() {
            @Override
            public void onItemClick(View v, int position) {
                // item 被点击
                Toast.makeText(MainActivity.this, position + " 点击item", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.animate_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MultEffectActivity.class));
            }
        });
    }

}
