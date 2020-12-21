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
implementation 'org.zhx.common:commonBanner:1.3.4'
```
## 适配 非Androidx 项目 ：
build.gradle 中：
```
	dependencies {
	       implementation 'org.zhx.common:commonBanner:1.3.4'
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
     //              .indicatorBelow() //设置游标和内容相对 位置  可选 默认 游标悬浮在 内容底部
                     .setIndicatorItemSelectSrc(R.mipmap.icon_star)// 设置 指示器  item 颜色
                     .setIndicatorItemUnSelectedSrc(R.drawable.select_indicator)// 设置 indicator指示器  item 选中颜色
                     .setIndicatorLayoutBackgroundRes(R.drawable.shape_indicator_bg) //设置 指示器 背景
                     .setTransformer(Transformer.DETH) // 设置切换动画  新增10多种 动画  Transformer 类
                     .setLoopType(LoopType.LOOP)// 设置循环滚动方式
                     .setAutoPlay(true) //是否自动滚动  可选 默认 不滚动
     //              .setIndicator(indecator)// 自定义 指示器
                     .setDelayTime(2000);// 设置滚动间隔时间
             banner.setBuilder(builder);
             //设置item 数据回调
             banner.setBannerAdapter(new SimpleBannerAdapter<ItemData>(R.layout.banner_item_layout, datas) {
                 @Override
                 protected void convert(ViewHolder holder, ItemData item) {
                     ImageView imageView = (ImageView) holder.findViewById(R.id.banner_img);
                     imageView.setImageResource(item.getSrc());
                     holder.addItemViewClick(R.id.banner_tv);
                 }

                 @Override
                 public void onItemViewClick(View v) {
                     Toast.makeText(MainActivity.this, "点击 测试", Toast.LENGTH_SHORT).show();
                 }
             });
             //item 点击事件
             banner.setOnItemClickLisenter(new CommonBanner.OnBannerItemClickLisenter() {
                 @Override
                 public void onItemClick(View v, int position) {
                     Toast.makeText(MainActivity.this, position + " 点击item", Toast.LENGTH_SHORT).show();
                 }
             });
         }

```
## ------------------------分割线--------------------------------
# commonBanner
## 看效果
![效果图](http://github.com/zhoulinxue/commonBanner/blob/master/screenshots/1577691131174.gif)

## 基本使用方法

   ```java
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
    //              .indicatorBelow() //设置游标和内容相对 位置  可选 默认 游标悬浮在 内容底部
                    .setIndicatorItemSelectSrc(R.mipmap.icon_star)// 设置 指示器  item 颜色
                    .setIndicatorItemUnSelectedSrc(R.drawable.select_indicator)// 设置 indicator指示器  item 选中颜色
                    .setIndicatorLayoutBackgroundRes(R.drawable.shape_indicator_bg) //设置 指示器 背景
                    .setTransformer(Transformer.DETH) // 设置切换动画  新增10多种 动画  Transformer 类
                    .setLoopType(LoopType.LOOP)// 设置循环滚动方式
                    .setAutoPlay(true) //是否自动滚动  可选 默认 不滚动
    //              .setIndicator(indecator)// 自定义 指示器
                    .setDelayTime(2000);// 设置滚动间隔时间
            banner.setBuilder(builder);
            //设置item 数据回调
            banner.setBannerAdapter(new SimpleBannerAdapter<ItemData>(R.layout.banner_item_layout, datas) {
                @Override
                protected void convert(ViewHolder holder, ItemData item) {
                    ImageView imageView = (ImageView) holder.findViewById(R.id.banner_img);
                    imageView.setImageResource(item.getSrc());
                    holder.addItemViewClick(R.id.banner_tv);
                }

                @Override
                public void onItemViewClick(View v) {
                    Toast.makeText(MainActivity.this, "点击 测试", Toast.LENGTH_SHORT).show();
                }
            });
            //item 点击事件
            banner.setOnItemClickLisenter(new CommonBanner.OnBannerItemClickLisenter() {
                @Override
                public void onItemClick(View v, int position) {
                    Toast.makeText(MainActivity.this, position + " 点击item", Toast.LENGTH_SHORT).show();
                }
            });
        }

```

