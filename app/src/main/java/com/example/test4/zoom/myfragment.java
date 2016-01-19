package com.example.test4.zoom;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by bridgelabz4 on 12/1/16.
 */
public  class myfragment extends Fragment{

        private TextView textview;
        public static myfragment getInstance(int position){
            myfragment mfrag= new myfragment();
            Bundle bundle= new Bundle();
            bundle.putInt("position", position);
            mfrag.setArguments(bundle);
            return mfrag;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View layout=inflater.inflate(R.layout.my_fragmenttabs,container,false);
            textview= (TextView)layout.findViewById(R.id.position);
            Bundle arg=getArguments();
            if(arg != null)
            {
                textview.setText("page selected"+arg.getInt("position"));
            }

            return  layout;
        }
    }

