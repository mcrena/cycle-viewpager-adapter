package com.mcrena.samplecycleviewpager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.mcrena.cycleviewpageradapter.CycleViewPagerAdapter;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        String[] items = {"1", "2", "3", "4", "5", "6", "7", "8"};
        new CycleViewPagerAdapter<String>(Arrays.asList(items), viewPager, true) {
            @Override
            protected View getView(int position) {
                TextView txt = new TextView(MainActivity.this);
                txt.setText(getItem(position));
                return txt;
            }

            @Override
            protected void pageSelected(int position, int count) {
                TextView txtCount = (TextView) findViewById(R.id.txtCount);
                txtCount.setText(String.format("%d/%d", ++position, count));
            }
        };
    }
}
