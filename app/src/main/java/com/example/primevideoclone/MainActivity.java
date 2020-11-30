package com.example.primevideoclone;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.primevideoclone.Adapter.PagerAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabIndicator, categoryTab; //
    ViewPager mainViewPager; //

    AppBarLayout appBarLayout; //
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewPager = findViewById(R.id.mainViewPager);
        tabIndicator = findViewById(R.id.tab_indicator);
        categoryTab = findViewById(R.id.tabLayout);

        appBarLayout = findViewById(R.id.appbar);

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), categoryTab.getTabCount());
        mainViewPager.setAdapter(pagerAdapter);


        categoryTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                mainViewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }


}