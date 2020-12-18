package com.common;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import org.zhx.common.widget.Builder;
import org.zhx.common.widget.CommonBanner;
import org.zhx.common.widget.LoopType;
import org.zhx.common.widget.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CommonBanner.BannerAdapter {
    private int[] mImages = {R.mipmap.b, R.mipmap.d, R.mipmap.e, R.mipmap.f, R.mipmap.g, R.mipmap.h};
    List<PicBanner> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < mImages.length; i++) {
            PicBanner picBanner = new PicBanner();
            picBanner.setSrc(mImages[i]);
            datas.add(picBanner);
        }
//        CommonBanner banner = findViewById(R.id.banner_layout);
        FrameLayout container = findViewById(R.id.banner_container);
        Builder builder = new Builder(this, container);
        //自定义 底部指示牌
//        CommonIndicator indicator = new TextIndicator(this);
        builder.setHeight(350)//设置banner 高度
                .setIndicatorHeight(80)//设置 导航游标 高度
//               .indicatorBelow() //设置游标和内容相对 位置  可选 默认 游标悬浮在 内容底部
                .setAutoPlay(true) //是否自动滚动  可选 默认 不滚动
                .setSelectSrc(R.drawable.selected_indicator)// 设置 indicator 颜色
                .setUnSelectedSrc(R.drawable.select_indicator)// 设置 indicator 选择颜色
                .setTransformer(Transformer.DETH) // 设置切换动画  新增10多种 动画  Transformer 类
                .setLoopType(LoopType.LOOP)// 设置循环滚动方式
                .setDelayTime(2000)// 设置滚动间隔时间
                .setIndicatorBackgroundRes(R.drawable.shape_indicator_bg); //设置 指示器 背景
        //      .setIndicator(indicator); // 自定义 指示器

        CommonBanner banner = builder.build();
        //设置item 数据回调
        banner.setBannerAdapter(this);
        //item 点击事件
        banner.setOnBannerItemClickLisenter(new CommonBanner.OnBannerItemClickLisenter() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(MainActivity.this, position + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View onCreatItem(int positon) {
        View view = LayoutInflater.from(this).inflate(R.layout.banner_item_layout, null);
        ImageView imageView = view.findViewById(R.id.banner_img);
        imageView.setImageResource(((PicBanner) datas.get(positon)).getSrc());
        return view;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
