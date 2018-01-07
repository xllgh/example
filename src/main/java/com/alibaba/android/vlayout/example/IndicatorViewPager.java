package com.alibaba.android.vlayout.example;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/6.
 */

public class IndicatorViewPager extends FrameLayout implements ViewPager.OnPageChangeListener{
    public IndicatorViewPager(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public IndicatorViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public IndicatorViewPager(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }
    private ViewPager viewPager;
    private LinearLayout dotContainer;
    private List<ImageView> dotViewList = new ArrayList<>();
    private Context context;
    private void initView(Context context) {
        View.inflate(context, R.layout.pager_container, this);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        dotContainer = (LinearLayout) findViewById(R.id.dot_container);
        this.context = context;
    }


    public void setAdapter(PagerAdapter adapter) {
        int count = adapter.getCount();
        dotContainer.removeAllViews();
        dotViewList.clear();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(15,
                15);
        layoutParams.setMargins(5, 5, 5, 5);
        Log.e("xll", "setAdapter count:" + count);
        for (int i = 0; i < count; i++) {

            ImageView view = new ImageView(context);
            view.setLayoutParams(layoutParams);
            view.setBackgroundResource(R.drawable.dot);
            if (i == 0) {
                view.setSelected(true);
            }
            dotContainer.addView(view);
            dotViewList.add(view);
        }

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
//            public Rect(int left, int top, int right, int bottom) {

        Rect rect = new Rect(dotContainer.getLeft(),dotContainer.getTop(), dotContainer.getRight(), dotContainer.getBottom());
        invalidateChild(dotContainer, rect);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.e("xll", "onPageSelected:" + position);
        for (int i = 0; i < dotViewList.size(); i++) {
            ImageView view = dotViewList.get(i);
            if (i == position) {
                view.setSelected(true);
            } else {
                view.setSelected(false);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
