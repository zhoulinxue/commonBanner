package com.common;

import android.util.Log;
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
public class AnimationAdapter extends RecyclerView.Adapter<AnimationAdapter.ItemViewHolder> {
    private List<Transformer> data;
    private int[] mImages = {R.mipmap.b, R.mipmap.d, R.mipmap.e, R.mipmap.f, R.mipmap.g, R.mipmap.h};
    List<ItemData> datas = new ArrayList<>();
    private String TAG = AnimationAdapter.class.getSimpleName();

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
    public ItemViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.animation_item_layout, null);
        return new ItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder viewHolder, int position) {
        CommonBanner banner = viewHolder.itemView.findViewById(R.id.banner_layout);
        boolean hasInit = banner.isInit();
        Log.e(TAG, "onBindViewHolder, banner.isInit:"+ hasInit);
        if (!hasInit) {
            setBanner(banner, viewHolder, position);
        }
    }

    private void setBanner(CommonBanner banner, final ItemViewHolder viewHolder, int position) {
        Builder builder = Builder.getDefault(viewHolder.itemView.getContext());
        builder.setTransformer(data.get(position));
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
                Toast.makeText(v.getContext(), "点击 测试", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemClick(View v, int position) {
                // item 被点击
                Toast.makeText(v.getContext(), "position: " + position + " click ..... itemView", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        int count = (data == null ? 0 : data.size());
        Log.e(TAG,"getItemCount, count:"+count);
        return count;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        public ItemViewHolder(View itemView) {
            super(itemView);
        }
    }
}
