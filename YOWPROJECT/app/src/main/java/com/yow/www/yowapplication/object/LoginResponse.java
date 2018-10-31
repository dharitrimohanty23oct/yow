package com.yow.www.yowapplication.object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by NISHIKANT on 10/22/2018.
 */

public class LoginResponse {


    @SerializedName("Success")
    @Expose
    private int Success;

    @SerializedName("Data")
    @Expose
    private LoginSuccessData Data;


    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int success) {
        Success = success;
    }

    public LoginSuccessData getData() {
        return Data;
    }

    public void setData(LoginSuccessData data) {
        Data = data;
    }
}
