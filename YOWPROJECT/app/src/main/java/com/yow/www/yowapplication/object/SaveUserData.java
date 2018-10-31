package com.yow.www.yowapplication.object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaveUserData {
    @SerializedName("OTP_Verified")
    @Expose
    private int OTP_Verified;

    public int getOTP_Verified() {
        return OTP_Verified;
    }

    public void setOTP_Verified(int OTP_Verified) {
        this.OTP_Verified = OTP_Verified;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    @SerializedName("Id")
    @Expose
    private int Id;
}
