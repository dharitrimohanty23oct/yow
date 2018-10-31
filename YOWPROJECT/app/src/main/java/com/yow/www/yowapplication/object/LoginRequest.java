package com.yow.www.yowapplication.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by NISHIKANT on 10/22/2018.
 */

public class LoginRequest {

    @SerializedName("Mobile_No")
    private String MobileNum;

    public String getMobileNum() {
        return MobileNum;
    }

    public void setMobileNum(String mobileNum) {
        MobileNum = mobileNum;
    }
}
