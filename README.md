# commonBanner

## 有问题请 提交 isuue/(QQ:194093798) 谢谢大家 持续更新
![效果图](https://img-blog.csdnimg.cn/20200107201324273.gif)
## 集成：
```
allprojects {
    repositories {
        jcenter()
    }
}
```
```
implementation 'org.zhx.common:commonBanner:1.2.2'
```
## 适配 非Androidx 项目 ：
build.gradle 中：
```
	dependencies {
	       implementation 'org.zhx.common:commonBanner:1.2.2'
	}
```
并且 gradle.properties中 添加：
```
android.useAndroidX=true
android.enableJetifier=true
```
或者
## 基本使用方法
```java
     builder.setHeight(350)//设置banner 高度
                   .setIndicatorHeight(80)//设置 导航游标 高度
   //               .indicatorBelow() //设置游标和内容相对 位置  可选 默认 游标悬浮在 内容底部
                   .setAutoPlay(true) //是否自动滚动  可选 默认 不滚动
                   .setSelectSrc(R.drawable.selected_indicator)// 设置 indicator 颜色
                   .setUnSelectedSrc(R.drawable.select_indicator)// 设置 indicator 选择颜色
                   .setTransformerType(Transformer.DETH) // 设置切换动画  新增10多种 动画  Transformer 类
                   .setLoopType(LoopType.LOOP)// 设置循环滚动方式
                   .setDelayTime(2000)// 设置滚动间隔时间
   //                .setIndicator(indicator)
                   .setIndicatorBackgroundRes(R.drawable.shape_indicator_bg); //设置 游标 背景
   //                .setTransformer(); //自定义 切换动画
           CommonBanner banner = builder.build();
           //设置 banner 数据
           banner.setDatas(datas.size());
           //设置item 数据回调
           banner.setLoadBanner(this);
           //item 点击事件
           banner.setOnBannerItemClickLisenter(new CommonBanner.OnBannerItemClickLisenter() {
               @Override
               public void onItemClick(View v, int position) {
                   Toast.makeText(MainActivity.this, position + "", Toast.LENGTH_SHORT).show();
               }
           });
```
## ------------------------分割线--------------------------------

## androidx 请用1.0.1 版本
```
implementation 'org.zhx.common:commonBanner:1.0.0'
``` 
 ## v4包 请用1.0.0
```
implementation 'org.zhx.common:commonBanner:1.0.1'
```
##1.0.0 新增切换效果
# commonBanner
## 看效果
![效果图](http://github.com/zhoulinxue/commonBanner/blob/master/screenshots/1577691131174.gif)

##1.0.0及 以前 基本使用方法

   ```java
   
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
        //banner.indicatorBelow();
        //是否自动滚动  可选 默认 不滚动
        banner.autoPlay();
        // 设置 indicator 颜色
        banner.setSelectSrc(R.drawable.selected_indicator);
        // 设置 indicator 选择颜色
        banner.setUnSelectedSrc(R.drawable.select_indicator);
        // 切换动画 二选一 
        // 设置切换动画  新增10多种 动画  Transformer 类 0.3.0版本加入
        // 设置切换动画  新增10多种 动画  Transformer 类
         banner.setTransformerType(Transformer.DETH);
        // 设置循环滚动方式
         banner.setLoop(LoopType.REVERSE);
        // 设置滚动间隔时间
         banner.setDelayTime(2000);
        //自定义 切换动画
    }
```

