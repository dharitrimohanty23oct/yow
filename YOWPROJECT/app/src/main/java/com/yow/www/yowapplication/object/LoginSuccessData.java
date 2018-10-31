package com.yow.www.yowapplication.object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by NISHIKANT on 10/22/2018.
 */

public class LoginSuccessData {


    @SerializedName("OTP_Id")
    @Expose
    private int OTP_Id;

    @SerializedName("OTP")
    @Expose
    private String OTP;

    public int getOTP_Id() {
        return OTP_Id;
    }

    public void setOTP_Id(int OTP_Id) {
        this.OTP_Id = OTP_Id;
    }

    public String getOTP() {
        return OTP;
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }
}
