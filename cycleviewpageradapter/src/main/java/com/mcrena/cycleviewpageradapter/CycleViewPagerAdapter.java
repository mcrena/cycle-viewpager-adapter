package com.mcrena.cycleviewpageradapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Dashkevich Makar
 * mcrena@mail.ru  on 18.05.17.
 */

public abstract class CycleViewPagerAdapter<T> extends PagerAdapter implements ViewPager.OnPageChangeListener {

    private List<T> objects;
    private boolean isCyclic = false;
    private int cycles = 100;

    /**
     * No need to call setAdapter on ViewPager, this constructor do it
     *
     * @param objects   - call getItem in getView method to create views from objects
     * @param viewPager - ViewPager to work on
     * @param isCyclic  - default true
     */
    public CycleViewPagerAdapter(List<T> objects, ViewPager viewPager, int startPosition, boolean isCyclic) {
        this.objects = objects;
        this.isCyclic = isCyclic;
        viewPager.setAdapter(this);
        viewPager.addOnPageChangeListener(this);
        viewPager.setCurrentItem(objects.size() * cycles + startPosition);
    }

    public CycleViewPagerAdapter(List<T> objects, ViewPager viewPager, boolean isCyclic) {
        this(objects, viewPager, 0, isCyclic);
    }

    /**
     * @param cycles - default 100
     */
    public void setCycles(int cycles) {
        this.cycles = cycles;
    }

    @Override
    public int getCount() {
        return isCyclic ? Integer.MAX_VALUE : 0;
    }

    public T getItem(int position) {
        return objects.get(position);
    }

    private int getTruePosition(int position) {
        if (isCyclic) {
            position = position % objects.size();
        }
        return position;
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        View v = getView(container.getContext(), getTruePosition(position));
        container.addView(v);
        return v;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object == view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    protected abstract View getView(Context context, int position);

    protected abstract void pageSelected(int position, int size);


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        pageSelected(getTruePosition(position), objects.size());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
