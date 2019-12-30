# commonBanner
# 看效果
![效果图](http://github.com/zhoulinxue/commonBanner/blob/master/gif/1577691131174.gif)

# 基本使用方法

       CommonBanner banner = findViewById(R.id.banner_layout);
       
       List<BannerData> datas = new ArrayList<>();
        
        for (int i = 0; i < 5; i++) {
            PicBanner picBanner = new PicBanner();
            picBanner.setSrc(R.drawable.wx_banner_img);
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
        banner.indicatorBelow();
        
        //是否自动滚动  可选 默认 不滚动
        banner.autoPlay();
