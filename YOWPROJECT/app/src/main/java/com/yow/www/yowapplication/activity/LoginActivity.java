package com.yow.www.yowapplication.activity;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yow.www.yowapplication.R;
import com.yow.www.yowapplication.client.ApiClient;
import com.yow.www.yowapplication.client.ApiInterface;
import com.yow.www.yowapplication.helper.RegPrefManager;
import com.yow.www.yowapplication.object.LoginRequest;
import com.yow.www.yowapplication.object.LoginResponse;
import com.yow.www.yowapplication.object.NewLoginResponse;
import com.yow.www.yowapplication.object.NewLoginSuccessData;
import com.yow.www.yowapplication.object.SaveUserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    Button login;
    ApiInterface apiService;
    AppCompatEditText phNumber;
    private int validate_user_id;
    private String OTP_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        apiService =
                ApiClient.getClient().create(ApiInterface.class);

        login = findViewById(R.id.login);
        phNumber = findViewById(R.id.email);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(phNumber.length()<10 || phNumber.length()>10){

                    Toast.makeText(LoginActivity.this,
                            "Please enter a correct phone number", Toast.LENGTH_SHORT).show();
                }else{
                    getLoginResponseNew();
                   // getLoginResponse();
                }



            }
        });
    }


    private void getLoginResponse(){



        Call<LoginResponse> call = apiService.getLoginResponse(phNumber.getText().toString().trim());
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if(response.body().getSuccess()==1){
                    final String otpp = response.body().getData().getOTP();
                    OTP_check=otpp;
                    Toast.makeText(LoginActivity.this, otpp, Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                           /* Intent in = new Intent(LoginActivity.this,OtpVerificationActivity.class);
                            in.putExtra("phNumber",
                                    phNumber.getText().toString().trim());
                            startActivity(in);*/
                           if(validate_user_id==0){
                               saveUser();
                           }else {
                               Intent in = new Intent(LoginActivity.this,OtpVerificationActivity.class);
                               in.putExtra("id",OTP_check);
                               in.putExtra("phNumber", phNumber.getText().toString().trim());
                               startActivity(in);
                           }
                        }
                    },1000);
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                Toast.makeText(LoginActivity.this, "Some thing went wrong",
                        Toast.LENGTH_SHORT).show();

            }


        });
    }



    public void getLoginResponseNew(){
        Call<NewLoginSuccessData> call = apiService.getNewLoginResponse(phNumber.getText().toString().trim());
        call.enqueue(new Callback<NewLoginSuccessData>() {
            @Override
            public void onResponse(Call<NewLoginSuccessData> call, Response<NewLoginSuccessData> response) {

                if(response.body().getSuccess()==1){

                   /* Intent in = new Intent(LoginActivity.this,OtpVerificationActivity.class);
                    in.putExtra("id",response.body().getData().getId()+"");
                    in.putExtra("phNumber",
                            phNumber.getText().toString().trim());
                    startActivity(in);*/

                    validate_user_id=response.body().getData().getId();
                   if(validate_user_id==0){
                       Toast.makeText(getApplicationContext(),"New User",Toast.LENGTH_LONG).show();
                       getLoginResponse();
                   }
                   else {
                       RegPrefManager.getInstance(LoginActivity.this).setUserId(response.body().getData().getId());
                       RegPrefManager.getInstance(LoginActivity.this).setFirstName(response.body().getData().getFirst_Name());
                       RegPrefManager.getInstance(LoginActivity.this).setSecondName(response.body().getData().getLast_Name());
                       RegPrefManager.getInstance(LoginActivity.this).setMobileNumber(response.body().getData().getMobile_No());
                       RegPrefManager.getInstance(LoginActivity.this).setEmailNumber(response.body().getData().getEmail_Id());


                       /*Intent in = new Intent(LoginActivity.this,DashBoardActivity.class);
                       startActivity(in);*/
                       getLoginResponse();
                   }

                }




            }

            @Override
            public void onFailure(Call<NewLoginSuccessData> call, Throwable t) {

                Toast.makeText(LoginActivity.this, "Some thing went wrong",
                        Toast.LENGTH_SHORT).show();
                String ti = t.toString();

            }


        });
    }
    private void saveUser(){
        Call<SaveUserResponse> call = apiService.getSaveUserResponse(phNumber.getText().toString().trim(),OTP_check);
        call.enqueue(new Callback<SaveUserResponse>() {
            @Override
            public void onResponse(Call<SaveUserResponse> call, Response<SaveUserResponse> response) {

                if(response.body().getSuccess()==1){
                  int userid=response.body().getData().getId();
                    RegPrefManager.getInstance(LoginActivity.this).setUserId(response.body().getData().getId());
                    Intent in = new Intent(LoginActivity.this,AccountDetailsActivity.class);
                    startActivity(in);
                }

            }

            @Override
            public void onFailure(Call<SaveUserResponse> call, Throwable t) {

                Toast.makeText(LoginActivity.this, "Some thing went wrong",
                        Toast.LENGTH_SHORT).show();

            }


        });
    }

}
