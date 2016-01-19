package com.example.test4.zoom;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test4.zoom.Tabs.SlidingTabLayout;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    //Variables
    private Toolbar toolbar;
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.intoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewPager = (ViewPager) findViewById(R.id.pages);
        viewPager.setAdapter(new Mypager(getSupportFragmentManager()));
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.tabs);
        slidingTabLayout.setCustomTabView(R.layout.customtab, R.id.texttabs);
        slidingTabLayout.setDistributeEvenly(true);

        slidingTabLayout.setBackgroundColor(getResources().getColor(R.color.primarycolor));
        slidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.accent));
        slidingTabLayout.setViewPager(viewPager);

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().
                findFragmentById(R.id.navigation_drawer);
        //drawerFragment.setUp((DrawerLayout)findViewById(R.id.fragment_nav),toolbar);
        drawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.Drawermain), toolbar);
        ImageView image = new ImageView(this);
        image.setImageResource(R.drawable.add);
        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setBackgroundDrawable(R.drawable.button_action_red)
                .setContentView(image).build();
        ImageView icon1 = new ImageView(this);
        icon1.setImageResource(R.drawable.audio);
        ImageView icon2 = new ImageView(this);
        icon2.setImageResource(R.drawable.up);
        ImageView icon3 = new ImageView(this);
        icon3.setImageResource(R.drawable.dhag);

        SubActionButton.Builder subbuilder = new SubActionButton.Builder(this);
        subbuilder.setBackgroundDrawable(getResources().getDrawable(R.drawable.sub_button_touch));
        SubActionButton subaudio = subbuilder.setContentView(icon1).build();
        SubActionButton subup = subbuilder.setContentView(icon2).build();
        SubActionButton subdhag = subbuilder.setContentView(icon3).build();

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(subaudio).addSubActionView(subup).addSubActionView(subdhag)
                .attachTo(actionButton).build();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.wifi) {
            startActivity(new Intent(this, Subact.class));
        }
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);

        }
        if (id == R.id.tab_bar) {
            Intent i2 = new Intent(this, ActionusingTabLibrary.class);
            startActivity(i2);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }

    class Mypager extends FragmentPagerAdapter {
        int[] icon1 = {R.drawable.dhag, R.drawable.ic_heart_50, R.drawable.ic_action_home, R.drawable.ic_heart_50, R.drawable.ic_action_home};

        String tabs[] = getResources().getStringArray(R.array.tabs);

        public Mypager(FragmentManager fm) {
            super(fm);
            tabs = getResources().getStringArray(R.array.tabs);

        }

        @Override
        public CharSequence getPageTitle(int position) {
            Drawable draw = getResources().getDrawable(icon1[position]);
            draw.setBounds(0, 0, 96, 96);
            ImageSpan span = new ImageSpan(draw);
            SpannableString spanString = new SpannableString(" ");
            spanString.setSpan(span, 0, spanString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return spanString;
        }

        @Override
        public Fragment getItem(int position) {
            myfragment myfragment = MainActivity.myfragment.getInstance(position);
            return myfragment;
        }

        @Override
        public int getCount() {
            return 5;
        }
    }

    public static class myfragment extends Fragment {
        private TextView textview;

        public static myfragment getInstance(int position) {
            myfragment mfrag = new myfragment();
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            mfrag.setArguments(bundle);
            return mfrag;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View layout = inflater.inflate(R.layout.my_fragmenttabs, container, false);
            textview = (TextView) layout.findViewById(R.id.position);
            Bundle arg = getArguments();
            if (arg != null) {
                textview.setText("page selected" + arg.getInt("position"));
            }
            return layout;
        }
    }
}