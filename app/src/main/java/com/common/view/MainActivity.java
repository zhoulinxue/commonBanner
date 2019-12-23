package com.common.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.common.view.banner.BannerData;
import com.common.view.banner.CommonBanner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CommonBanner.Bannerloader {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CommonBanner banner = findViewById(R.id.banner_layout);
        List<BannerData> datas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PicBanner picBanner = new PicBanner();
            picBanner.setSrc(R.drawable.wx_banner_img);
            datas.add(picBanner);
        }
        banner.setDatas(datas);
        banner.setLoadBanner(this);
        banner.setHeight(350);
        banner.setIndicatorHeight(80);
        banner.setIndicatorBackgroundRes(R.drawable.shape_indicator_bg);
        banner.indicatorBelow();
        banner.autoPlay();
    }

    @Override
    public View loadBanner(BannerData data) {
        View view = LayoutInflater.from(this).inflate(R.layout.banner_item_layout, null);
        ImageView imageView = view.findViewById(R.id.banner_img);
        imageView.setImageResource(((PicBanner) data).getSrc());
        return view;
    }
}
