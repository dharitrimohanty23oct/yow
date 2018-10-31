package com.yow.www.yowapplication.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.yow.www.yowapplication.R;
import com.yow.www.yowapplication.helper.RegPrefManager;

public class AccountDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvName,tvMobile,tvEmail;
    private Toolbar toolbar;
    private ImageView img;
    private LinearLayout nameLinear,emailLinear,mobileLinear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        //custom view

        init();

    }

    private void init(){
        tvName=(TextView)findViewById(R.id.tvName);
        tvName.setOnClickListener(this);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        img=toolbar.findViewById(R.id.img);
        toolbar.setOnClickListener(this);
        tvMobile=findViewById(R.id.tvMobile);
        tvMobile.setOnClickListener(this);
        tvEmail=(TextView)findViewById(R.id.tvEmail);
        nameLinear=(LinearLayout)findViewById(R.id.nameLinear);
        nameLinear.setOnClickListener(this);
        emailLinear=(LinearLayout)findViewById(R.id.emailLinear);
        emailLinear.setOnClickListener(this);
        mobileLinear=(LinearLayout)findViewById(R.id.mobileLinear);
        mobileLinear.setOnClickListener(this);

        String firstname=RegPrefManager.getInstance(AccountDetailsActivity.this).getFirstName();
        String secondname=RegPrefManager.getInstance(AccountDetailsActivity.this).getSecondName();
        String mobilenumber=RegPrefManager.getInstance(AccountDetailsActivity.this).getMobileNumber();
        String email=RegPrefManager.getInstance(AccountDetailsActivity.this).getEmailNumber();

        tvName.setText(firstname+" "+secondname);
        tvMobile.setText(mobilenumber);
        tvEmail.setText(email);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.nameLinear:
                Intent i=new Intent(AccountDetailsActivity.this, UpdateActivity.class);
                i.putExtra("Value","Name");
                startActivity(i);

                overridePendingTransition(R.anim.slide_in, R.anim.slide_out); // for fwd
                break;
            case R.id.toolbar:
                Intent img=new Intent(AccountDetailsActivity.this, DashBoardActivity.class);
                startActivity(img);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out); // for fwd
                break;
            case R.id.mobileLinear:
                Intent mobile=new Intent(AccountDetailsActivity.this, UpdateActivity.class);
                mobile.putExtra("Value","Mobile");
                startActivity(mobile);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out); // for fwd
                break;
            case R.id.emailLinear:
                Intent email=new Intent(AccountDetailsActivity.this, UpdateActivity.class);
                email.putExtra("Value","email");
                startActivity(email);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out); // for fwd
                break;
        }
    }
}
