package org.zhx.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import org.zhx.common.widget.indicator.ClassiceIndicator;
import org.zhx.common.widget.indicator.CommonIndicator;
import org.zhx.common.widget.viewPager.IPager;
import org.zhx.common.widget.viewPager.ViewPagerManager;
import org.zhx.common.widget.viewPager.transformers.AccordionTransformer;
import org.zhx.common.widget.viewPager.transformers.BackgroundToForegroundTransformer;
import org.zhx.common.widget.viewPager.transformers.BaseTransformer;
import org.zhx.common.widget.viewPager.transformers.CubeInTransformer;
import org.zhx.common.widget.viewPager.transformers.CubeOutTransformer;
import org.zhx.common.widget.viewPager.transformers.DepthPageTransformer;
import org.zhx.common.widget.viewPager.transformers.FlipHorizontalTransformer;
import org.zhx.common.widget.viewPager.transformers.FlipVerticalTransformer;
import org.zhx.common.widget.viewPager.transformers.ForegroundToBackgroundTransformer;
import org.zhx.common.widget.viewPager.transformers.RotateDownTransformer;
import org.zhx.common.widget.viewPager.transformers.RotateUpTransformer;
import org.zhx.common.widget.viewPager.transformers.ScaleInOutTransformer;
import org.zhx.common.widget.viewPager.transformers.StackTransformer;
import org.zhx.common.widget.viewPager.transformers.TabletTransformer;
import org.zhx.common.widget.viewPager.transformers.Transformer;
import org.zhx.common.widget.viewPager.transformers.ZoomInTransformer;
import org.zhx.common.widget.viewPager.transformers.ZoomOutSlideTransformer;
import org.zhx.common.widget.viewPager.transformers.ZoomOutTranformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: banner
 * @Package: org.zhx.common
 * @ClassName: CommonBanner2
 * @Description:java
 * @Author: 86138
 * @CreateDate: 2021/1/11 11:45
 * @UpdateUser:
 * @UpdateDate: 2021/1/11 11:45
 * @UpdateRemark:
 * @Version:1.0
 */
public class CommonBanner extends FrameLayout implements IContact {
    private IPager mPager;// banner
    private CommonIndicator mIndicators; // indicator
    private LayoutParams containerLp; // banner layoutParams
    private RelativeLayout mContainer;// view container
    private int mContainerHeight, mIndicatorHeight;
    private boolean isBelow = false;// 布局 是否重叠
    private Context mContext;
    private boolean isInit = false;

    public CommonBanner(@NonNull Context context) {
        super(context);
        init(context);
    }

    public CommonBanner(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CommonBanner(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        containerLp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mContainer = new RelativeLayout(context);
        mContainer.setLayoutParams(containerLp);
        mPager = new ViewPagerManager(context, this);
        addView(mContainer);
    }

    public void setBannerAdapter(BannerAdapter bannerBanner) {
        if (mPager != null) {
            mPager.setBannerAdapter(bannerBanner);

            if (mIndicators != null) {
                mIndicators.setDatas(bannerBanner.getItemCount());
            }
        }
    }

    public interface BannerAdapter {
        public View onCreatItem(Context context, int positon);

        public int getItemCount();

        public void onItemViewClick(View v);

        public void onItemClick(View v, int position);
    }

    public boolean isInit() {
        return isInit;
    }

    public synchronized void setBuilder(Builder builder) {
        if (builder == null && !isInit) {
            return;
        }
        isInit = true;
        mContainer.removeAllViews();
        if (mPager != null) {
            mPager.attach(mContainer);
            mPager.setDuration(builder.getDuration());
            mPager.setWidth(builder.getWidth());
            mPager.setLoopType(builder.getLoopType());
            mPager.setDelayTime(builder.getDelayTime());
            mPager.autoPlay(builder.isAutoPlay());
            if (builder.getTransformerType() != null)
                setTransformerType(builder.getTransformerType());
            else if (builder.getTransformer() != null) {
                setTransformer(builder.getTransformer());
            }
        }

        mIndicators = builder.getIndicator();

        if (mIndicators != null) {
            mContainer.addView(mIndicators.getIndicatorLayout());
            mIndicators.setHeight(builder.getIndicatorHeight());
            mIndicators.setIndicatorSrc(builder.getUnSelectedSrc());
            mIndicators.setSelectedSrc(builder.getSelectSrc());
            if (builder.getIndicatorLayoutColor() != 0) {
                mIndicators.getIndicatorLayout().setBackgroundColor(getResources().getColor(builder.getIndicatorLayoutColor()));
            } else if (builder.getIndicatorLayoutBg() != 0) {
                mIndicators.getIndicatorLayout().setBackgroundResource(builder.getIndicatorLayoutBg());
            }

            isBelow = builder.isBelow();
            mIndicatorHeight = builder.getIndicatorHeight();
            setHeight(builder.getHeight());
        }
    }


    protected void setHeight(int height) {
        mContainerHeight = height;
        if (mPager != null) {
            mPager.setHeight(height);
        }
        if (mContainer != null) {
            int totalhHight = height;
            if (mIndicatorHeight != 0 && isBelow) {
                totalhHight += mIndicatorHeight;
            }
            containerLp.height = totalhHight;
            mContainer.setLayoutParams(containerLp);
        }
    }

    @Override
    public void onScrolling(Object... obj) {

    }

    @Override
    public void onSelected(int position) {
        if (mIndicators != null) {
            mIndicators.setSelection(position);
        }
    }

    public void setTransformerType(Transformer mTransformer) {
        if (mTransformer != null) {
            BaseTransformer transformer = null;
            switch (mTransformer) {
                case NONE:
                    break;
                case DETH:
                    transformer = new DepthPageTransformer();
                    break;
                case ACCORDION:
                    transformer = new AccordionTransformer();
                    break;
                case B_TO_F:
                    transformer = new BackgroundToForegroundTransformer();
                    break;
                case F_TO_B:
                    transformer = new ForegroundToBackgroundTransformer();
                    break;
                case CUBE_IN:
                    transformer = new CubeInTransformer();
                    break;
                case UBE_OUT:
                    transformer = new CubeOutTransformer();
                    break;
                case FLIP_HORIZONTAL:
                    transformer = new FlipHorizontalTransformer();
                    break;
                case FLIP_VERTICAL:
                    transformer = new FlipVerticalTransformer();
                    break;
                case ROTATE_DOWN:
                    transformer = new RotateDownTransformer();
                    break;
                case ROTATE_UP:
                    transformer = new RotateUpTransformer();
                    break;
                case SCALE:
                    transformer = new ScaleInOutTransformer();
                    break;
                case STACK:
                    transformer = new StackTransformer();
                    break;
                case TABLET:
                    transformer = new TabletTransformer();
                    break;
                case ZOOM_IN:
                    transformer = new ZoomInTransformer();
                    break;
                case ZOOM_OUT:
                    transformer = new ZoomOutTranformer();
                    break;
                case ZOOM_OUT_SLIDE:
                    transformer = new ZoomOutSlideTransformer();
                    break;

            }
            setTransformer(transformer);
        }
    }

    public void setTransformer(ViewPager.PageTransformer transformer) {
        if (transformer != null) {
            if (mPager != null && mPager.getPagerView() instanceof ViewPager)
                ((ViewPager) mPager.getPagerView()).setPageTransformer(true, transformer);
        }
    }

    public void onPause() {
        if (mPager != null) {
            mPager.onPause();
        }
    }

    public void onRestart() {
        if (mPager != null) {
            mPager.onRestart();
        }
    }
}
