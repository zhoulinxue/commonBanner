package org.zhx.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import org.zhx.common.widget.indicator.ClassiceIndicator;
import org.zhx.common.widget.indicator.CommonIndicator;
import org.zhx.common.widget.viewPager.IPager;
import org.zhx.common.widget.viewPager.ViewPagerImpl;
import org.zhx.common.widget.viewPager.AccordionTransformer;
import org.zhx.common.widget.viewPager.BackgroundToForegroundTransformer;
import org.zhx.common.widget.viewPager.BaseTransformer;
import org.zhx.common.widget.viewPager.CubeInTransformer;
import org.zhx.common.widget.viewPager.CubeOutTransformer;
import org.zhx.common.widget.viewPager.DepthPageTransformer;
import org.zhx.common.widget.viewPager.FlipHorizontalTransformer;
import org.zhx.common.widget.viewPager.FlipVerticalTransformer;
import org.zhx.common.widget.viewPager.ForegroundToBackgroundTransformer;
import org.zhx.common.widget.viewPager.RotateDownTransformer;
import org.zhx.common.widget.viewPager.RotateUpTransformer;
import org.zhx.common.widget.viewPager.ScaleInOutTransformer;
import org.zhx.common.widget.viewPager.StackTransformer;
import org.zhx.common.widget.viewPager.TabletTransformer;
import org.zhx.common.widget.viewPager.Transformer;
import org.zhx.common.widget.viewPager.ZoomInTransformer;
import org.zhx.common.widget.viewPager.ZoomOutSlideTransformer;
import org.zhx.common.widget.viewPager.ZoomOutTranformer;

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
        containerLp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mContainer = new RelativeLayout(context);
        mContainer.setLayoutParams(containerLp);
        mPager = new ViewPagerImpl(context, this);
        addView(mContainer);
    }

    public void setBannerAdapter(BannerAdapter bannerBanner) {
        if (bannerBanner != null && bannerBanner.getItemCount() != 0) {
            mPager.setBannerAdapter(bannerBanner);
            mIndicators.setDatas(bannerBanner.getItemCount());
        }
    }

    public interface BannerAdapter {
        public View onCreatItem(ViewGroup container, int positon);

        public int getItemCount();

        public void onItemViewClick(View v);
    }

    public interface OnItemClickLisenter {
        public void onItemClick(View v, int position);
    }

    public void setBuilder(Builder builder) {
        if (builder == null) {
            return;
        }
        mContainer.removeAllViews();
        if (mPager != null) {
            mPager.attach(mContainer);
            mPager.setDuration(builder.getDuration());
            mPager.setWidth(builder.getWidth());
            mPager.isAutoPlay(builder.isAutoPlay());
            if (builder.getTransformerType() != null)
                setTransformerType(builder.getTransformerType());
            else if (builder.getTransformer() != null) {
                setTransformer(builder.getTransformer());
            }
        }
        mIndicators = builder.getIndicator();
        if (mIndicators == null) {
            mIndicators = new ClassiceIndicator(getContext());
        }
        mContainer.addView(mIndicators.getIndicatorLayout());
        mIndicators.setHeight(builder.getIndicatorHeight());
        mIndicators.setIndicatorSrc(builder.getUnSelectedSrc());
        mIndicators.setSelectedSrc(builder.getSelectSrc());
        if (builder.getIndicatorLayoutColor() != 0) {
            mIndicators.getIndicatorLayout().setBackgroundColor(builder.getIndicatorLayoutColor());
        } else if (builder.getIndicatorLayoutBg() != 0) {
            mIndicators.getIndicatorLayout().setBackgroundResource(builder.getIndicatorLayoutBg());
        }
        setHeight(builder.getHeight());
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

    public void setOnItemClickLisenter(OnItemClickLisenter onItemClickLisenter) {
        if (mPager != null) {
            mPager.setOnItemClickLisenter(onItemClickLisenter);
        }
    }


    @Override
    public void onScrolling(Object... obj) {

    }

    @Override
    public void onSelected(int position) {
        if (mIndicators != null)
            mIndicators.setSelection(position);
    }

    public void setTransformerType(Transformer mTransformer) {
        if (mTransformer != null) {
            BaseTransformer transformer = null;
            switch (mTransformer) {
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
}
