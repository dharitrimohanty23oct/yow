package com.yow.www.yowapplication.object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaveUserResponse {
    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int success) {
        Success = success;
    }

    public SaveUserData getData() {
        return Data;
    }

    public void setData(SaveUserData data) {
        Data = data;
    }

    @SerializedName("Success")
    @Expose
    private int Success;
    @SerializedName("Data")
    @Expose
    private SaveUserData Data;
}
