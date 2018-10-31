package com.yow.www.yowapplication.object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by NISHIKANT on 10/23/2018.
 */

public class NewLoginResponse {


    @SerializedName("Id")
    @Expose
    private int Id;

    @SerializedName("First_Name")
    @Expose
    private String First_Name;

    @SerializedName("Last_Name")
    @Expose
    private String Last_Name;

    @SerializedName("Mobile_No")
    @Expose
    private String Mobile_No;

    @SerializedName("Mobile_Verified")
    @Expose
    private String Mobile_Verified;

    @SerializedName("Email_Id")
    @Expose
    private String Email_Id;

    @SerializedName("Email_Verified")
    @Expose
    private int Email_Verified;

    @SerializedName("Added_On")
    @Expose
    private String Added_On;

    @SerializedName("Updated_On")
    @Expose
    private String Updated_On;


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public String getMobile_No() {
        return Mobile_No;
    }

    public void setMobile_No(String mobile_No) {
        Mobile_No = mobile_No;
    }

    public String getMobile_Verified() {
        return Mobile_Verified;
    }

    public void setMobile_Verified(String mobile_Verified) {
        Mobile_Verified = mobile_Verified;
    }

    public String getEmail_Id() {
        return Email_Id;
    }

    public void setEmail_Id(String email_Id) {
        Email_Id = email_Id;
    }

    public int getEmail_Verified() {
        return Email_Verified;
    }

    public void setEmail_Verified(int email_Verified) {
        Email_Verified = email_Verified;
    }

    public String getAdded_On() {
        return Added_On;
    }

    public void setAdded_On(String added_On) {
        Added_On = added_On;
    }

    public String getUpdated_On() {
        return Updated_On;
    }

    public void setUpdated_On(String updated_On) {
        Updated_On = updated_On;
    }
}
