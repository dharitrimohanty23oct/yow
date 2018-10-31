package com.yow.www.yowapplication.frgament;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yow.www.yowapplication.R;


public class BottomFragment extends Fragment {

    public BottomFragment() {
        // Required empty public constructor
    }


    public static BottomFragment newInstance(String param1, String param2) {
        BottomFragment fragment = new BottomFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom, container, false);
    }


}
