package com.alibaba.android.vlayout.example;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class ViewPagerActivity extends Activity{

    private InnerPagerAdapter indicatorViewPager;
    private IndicatorViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        viewPager =  (IndicatorViewPager)findViewById(R.id.indicatorViewPager);
        viewPager.setAdapter(new InnerPagerAdapter(this, Arrays.asList("xixi", "hehe", "haha", "ohoh")));
    }

    public static class InnerPagerAdapter extends PagerAdapter {

        private Context context;
        private List<String> list;

        InnerPagerAdapter(Context context, @NonNull List<String> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = View.inflate(context, R.layout.pager_item, null);
            TextView textView = (TextView) itemView.findViewById(R.id.title);
            textView.setText(list.get(position));
            container.addView(itemView);
            itemView.setTag(position);
            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
           container.removeView((View) object);
        }
    }
}
