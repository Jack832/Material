package com.example.test4.zoom;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

/**
 * Created by bridgelabz4 on 12/1/16.
 */
public class ActionusingTabLibrary extends ActionBarActivity implements MaterialTabListener {
    private Toolbar toolbar;
    private MaterialTabHost materialTab;
    private ViewPager view;
    private Viewpageradapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_tab);
        toolbar = (Toolbar) findViewById(R.id.intoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        materialTab = (MaterialTabHost) findViewById(R.id.materialTabHost);
        view = (ViewPager) findViewById(R.id.pages);
        adapter = new Viewpageradapter(getSupportFragmentManager());
        view.setAdapter(adapter);

        view.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                materialTab.setSelectedNavigationItem(position);
            }
        });
        for (int i = 0; i < adapter.getCount(); i++) {
            materialTab.addTab(
                    materialTab.newTab()
                            .setIcon(adapter.geticon(i))
                                    //setText(adapter.getPageTitle(i))
                            .setTabListener(this)
            );
        }

    }


    @Override
    public void onTabSelected(MaterialTab tab) {
        view.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }

    public class Viewpageradapter extends FragmentStatePagerAdapter {
        int Icon[] = {R.drawable.audio, R.drawable.ic_heart_50, R.drawable.up, R.drawable.add, R.drawable.ic_action_home};

        public Viewpageradapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return myfragment.getInstance(position);
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getResources().getStringArray(R.array.tabs)[position];
        }

        private Drawable geticon(int position) {
            return getResources().getDrawable(Icon[position]);
        }
    }
}
