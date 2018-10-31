package com.yow.www.yowapplication.client;

/**
 * Created by NISHIKANT on 10/22/2018.
 */


import com.yow.www.yowapplication.object.LoginRequest;
import com.yow.www.yowapplication.object.LoginResponse;
import com.yow.www.yowapplication.object.NewLoginResponse;
import com.yow.www.yowapplication.object.NewLoginSuccessData;
import com.yow.www.yowapplication.object.OtpResponse;
import com.yow.www.yowapplication.object.SaveUserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {

    @POST("Services/User/get_otp")
    @FormUrlEncoded
    Call<LoginResponse> getLoginResponse(@Field("Mobile_No") String Mobile_No);


    @POST("Services/User/validate_otp")
    @FormUrlEncoded
    Call<OtpResponse> getOtpResponse(@Field("Mobile_No") String Mobile_No,
                                     @Field("OTP") String OTP);

    @POST("Services/User/validate_user")
    @FormUrlEncoded
    Call<NewLoginSuccessData> getNewLoginResponse(@Field("Mobile_No") String Mobile_No);

    @POST("Services/User/save_user.php")
    @FormUrlEncoded
    Call<SaveUserResponse> getSaveUserResponse(@Field("Mobile_No") String Mobile_No,
                                               @Field("OTP") String OTP);

}
