package com.dagf.dialoglibrary.dialog.adapter;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class LazyViewPager extends ViewPager {

    private VipagAdapter mPagerAdapter;

    public LazyViewPager(@NonNull Context context) {
        super(context);
    }

    public LazyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mPagerAdapter != null) {
            super.setAdapter(mPagerAdapter);
          //  mPageIndicator.setViewPager(this);
        }
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
    }

    public void storeAdapter(VipagAdapter pagerAdapter) {
        mPagerAdapter = pagerAdapter;
    }
}
