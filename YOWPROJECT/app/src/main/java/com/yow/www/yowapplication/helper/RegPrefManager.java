package com.yow.www.yowapplication.helper;


import android.content.Context;
import android.content.SharedPreferences;



import java.lang.reflect.Type;
import java.util.ArrayList;


public class RegPrefManager {

    private SharedPreferences mSharedPreferences;
    private static RegPrefManager mPrefManager;
    public Context mContext;

    private RegPrefManager(Context context) {
        this.mContext=context;
        mSharedPreferences = context.
                getSharedPreferences("digichamps_mentor_app_pref", Context.MODE_PRIVATE);
    }

    public static RegPrefManager getInstance(Context context) {
        if (mPrefManager == null) {
            mPrefManager = new RegPrefManager(context);
        }
        return mPrefManager;
    }
    public void setPhone(String ph) {
        mSharedPreferences.
                edit().putString("ph", ph).apply();
    }


    public String getPhone() {
        return mSharedPreferences.
                getString("ph", null);
    }

    public void logout() {

        mSharedPreferences.edit().clear().apply();
       // mContext.getContentResolver().delete(DbContract.CoinTable.CONTENT_URI,null,null);
       // mContext.getContentResolver().delete(DbContract.NotificationTable.CONTENT_URI,null,null);

        //mContext.getContentResolver().delete(DbContract.ExamTable.CONTENT_URI,null,null);

        //mContext.getContentResolver().delete(DbContract.UserTable.CONTENT_URI,null,null);

    }

    public void setUserId(int userId) {
        mSharedPreferences.
                edit().putInt("userId", userId).apply();
    }


    public int getUserId() {
        return mSharedPreferences.
                getInt("userId", 0);
    }

    public void setFirstName(String  userName) {
        mSharedPreferences.
                edit().putString("firstName", userName).apply();
    }


    public String getFirstName() {
        return mSharedPreferences.
                getString("firstName", null);
    }
    public void setSecondName(String  userName) {
        mSharedPreferences.
                edit().putString("secondName", userName).apply();
    }


    public String getSecondName() {
        return mSharedPreferences.
                getString("secondName", null);
    }

    public String getMobileNumber() {
        return mSharedPreferences.
                getString("mobilenumber", null);
    }

    public void setMobileNumber(String  mobilenumber) {
        mSharedPreferences.
                edit().putString("mobilenumber", mobilenumber).apply();
    }
    public String getEmailNumber() {
        return mSharedPreferences.
                getString("emailid", null);
    }

    public void setEmailNumber(String  emailid) {
        mSharedPreferences.
                edit().putString("emailid", emailid).apply();
    }

    public String getOTP() {
        return mSharedPreferences.
                getString("OTP", null);
    }

    public void setOTP(String OTP) {
        mSharedPreferences.
                edit().putString("OTP", OTP).apply();
    }





}

