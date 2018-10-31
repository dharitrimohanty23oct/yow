package com.yow.www.yowapplication.frgament;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yow.www.yowapplication.R;
import com.yow.www.yowapplication.activity.AccountDetailsActivity;
import com.yow.www.yowapplication.activity.DashBoardActivity;
import com.yow.www.yowapplication.activity.EmergencyContactActivity;
import com.yow.www.yowapplication.helper.RegPrefManager;

public class AccountFragment extends Fragment {

    public AccountFragment() {
        // Required empty public constructor
    }

    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();

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
        View v= inflater.inflate(R.layout.fragment_account, container, false);
        RelativeLayout relName=(RelativeLayout)v.findViewById(R.id.relName);
        TextView tvContacts=(TextView)v.findViewById(R.id.tvContacts);
        TextView tvPhone=(TextView)v.findViewById(R.id.tvPhone);
        TextView tvEmail=(TextView)v.findViewById(R.id.tvEmail);
        TextView tvName=(TextView)v.findViewById(R.id.tvName);

        String firstname=RegPrefManager.getInstance(getActivity()).getFirstName();
        String secondname=RegPrefManager.getInstance(getActivity()).getSecondName();
        String mobilenumber=RegPrefManager.getInstance(getActivity()).getMobileNumber();
        String email=RegPrefManager.getInstance(getActivity()).getEmailNumber();
        tvPhone.setText(mobilenumber);
        tvEmail.setText(email);
        tvName.setText(firstname+" "+secondname);


        tvContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(), EmergencyContactActivity.class);
                startActivity(i);
                getActivity().overridePendingTransition(R.anim.slide_in, R.anim.slide_out); // for fwd

            }
        });
        relName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(), AccountDetailsActivity.class);
                startActivity(i);
                getActivity().overridePendingTransition(R.anim.slide_in, R.anim.slide_out); // for fwd

            }
        });
        return v;
    }

    @Override
    public void onResume() {

        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){

                    // handle back button
                    Intent i=new Intent(getActivity(), DashBoardActivity.class);
                    startActivity(i);

                    return true;

                }

                return false;
            }
        });
    }

}
