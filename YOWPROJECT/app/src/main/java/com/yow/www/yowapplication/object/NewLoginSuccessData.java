package com.yow.www.yowapplication.object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by NISHIKANT on 10/23/2018.
 */

public class NewLoginSuccessData {


    @SerializedName("Success")
    @Expose
    private int Success;

    @SerializedName("Data")
    @Expose
    private NewLoginResponse Data;

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int success) {
        Success = success;
    }

    public NewLoginResponse getData() {
        return Data;
    }

    public void setData(NewLoginResponse data) {
        Data = data;
    }
}
