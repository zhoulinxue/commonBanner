package com.common;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.zhx.common.widget.viewPager.Transformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ProjectName: banner
 * @Package: com.common
 * @ClassName: MultEffectActivity
 * @Description:java
 * @Author: 86138
 * @CreateDate: 2020/12/23 17:18
 * @UpdateUser:
 * @UpdateDate: 2020/12/23 17:18
 * @UpdateRemark:
 * @Version:1.0
 */
public class MultEffectActivity extends AppCompatActivity {
    private List<Transformer> mAnimationType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mult_layout);
        mAnimationType = new ArrayList<>();
        mAnimationType.addAll(Arrays.asList(Transformer.values()));
        RecyclerView recyclerView = findViewById(R.id.animate_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new AnimationAdapter(mAnimationType));
    }
}
