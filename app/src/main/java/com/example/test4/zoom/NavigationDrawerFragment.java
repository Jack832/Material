package com.example.test4.zoom;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {
    public static final String keyuser = "userlearneddrawer";
    public static final String PREFNAME = "testpref";
    private RecyclerView recyclerView;
    private ActionBarDrawerToggle mtoggle;
    private DrawerLayout mDralayout;
    private boolean muserlearned;
    private boolean mfromsaved;
    private View containerview;
    private Adapterinformation adapter;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    public static List<Rcycleinformation> getdata() {
        List<Rcycleinformation> info1 = new ArrayList<>();
        int[] icon = {R.drawable.ic_action_home, R.drawable.up};
        String[] title = {"first", "second"};
//        for(int i=0;i<title.length && i<icon.length;i++)

        for (int i = 0; i < 25; i++) {
            Rcycleinformation inform1 = new Rcycleinformation();
            inform1.imageid = icon[i % icon.length];
            inform1.title = title[i % title.length];
            info1.add(inform1);

        }
        return info1;
    }

    public static void savetoprefrence(Context context, String prefname, String prefvalue) {
        SharedPreferences shared = context.getSharedPreferences(PREFNAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putString(prefname, prefvalue);
        editor.apply();
    }

    public static String reasdfromprefrence(Context context, String prefname, String prefvalue) {
        SharedPreferences sharedd = context.getSharedPreferences(PREFNAME, Context.MODE_PRIVATE);
        return sharedd.getString(prefname, prefvalue);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        muserlearned = Boolean.valueOf(reasdfromprefrence(getActivity(), keyuser, "false")); //method
        if (savedInstanceState != null) {
            mfromsaved = true;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_navigationact, container, true);
        recyclerView = (RecyclerView) layout.findViewById(R.id.drawerlist);
        adapter = new Adapterinformation(getActivity(), getdata());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }

    public void setUp(int fragmentid, DrawerLayout layout, Toolbar tool) {
        containerview = getActivity().findViewById(fragmentid);
        mDralayout = layout;
        mtoggle = new ActionBarDrawerToggle(getActivity(),
                layout, tool, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!muserlearned) {
                    muserlearned = true;
                    savetoprefrence(getActivity(), keyuser, muserlearned + ""); //saved to pref method
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();

            }
        };
        if (!muserlearned && !mfromsaved) {
            mDralayout.openDrawer(containerview);
        }
        mDralayout.setDrawerListener(mtoggle);
        mDralayout.post(new Runnable() {
            @Override
            public void run() {
                mtoggle.syncState();
            }
        });


    }

}
