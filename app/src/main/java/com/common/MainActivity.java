package com.common;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.zhx.common.widget.BannerData;
import org.zhx.common.widget.CommonBanner;
import org.zhx.common.widget.LoopType;
import org.zhx.common.widget.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CommonBanner.Bannerloader {
    private int[] mImages = {R.mipmap.b, R.mipmap.d, R.mipmap.e, R.mipmap.f, R.mipmap.g, R.mipmap.h};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CommonBanner banner = findViewById(R.id.banner_layout);

        List<BannerData> datas = new ArrayList<>();

        for (int i = 0; i < mImages.length; i++) {
            PicBanner picBanner = new PicBanner();
            picBanner.setSrc(mImages[i]);
            datas.add(picBanner);
        }
        //设置 banner 数据
        banner.setDatas(datas);
        //设置item 数据回调
        banner.setLoadBanner(this);
        //设置banner 高度
        banner.setHeight(350);
        //设置 导航游标 高度
        banner.setIndicatorHeight(80);
        //设置 游标 背景
        banner.setIndicatorBackgroundRes(R.drawable.shape_indicator_bg);
        //设置游标和内容相对 位置  可选 默认 游标悬浮在 内容底部
//        banner.indicatorBelow();
        //是否自动滚动  可选 默认 不滚动
        banner.autoPlay();
        // 设置 indicator 颜色
        banner.setSelectSrc(R.drawable.selected_indicator);
        // 设置 indicator 选择颜色
        banner.setUnSelectedSrc(R.drawable.select_indicator);
        // 设置切换动画  新增10多种 动画  Transformer 类
        banner.setTransformerType(Transformer.DETH);
        // 设置循环滚动方式
        banner.setLoop(LoopType.REVERSE);
        // 设置滚动间隔时间
        banner.setDelayTime(2000);
        //自定义 切换动画
//        banner.setTransformer();
    }

    @Override
    public View loadBanner(final BannerData data) {
        View view = LayoutInflater.from(this).inflate(R.layout.banner_item_layout, null);
        ImageView imageView = view.findViewById(R.id.banner_img);
        imageView.setImageResource(((PicBanner) data).getSrc());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, data.getPosition() + "", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
