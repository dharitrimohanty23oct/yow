package com.yow.www.yowapplication.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yow.www.yowapplication.R;
import com.yow.www.yowapplication.client.ApiClient;
import com.yow.www.yowapplication.client.ApiInterface;
import com.yow.www.yowapplication.helper.RegPrefManager;
import com.yow.www.yowapplication.object.LoginResponse;
import com.yow.www.yowapplication.object.NewLoginSuccessData;
import com.yow.www.yowapplication.object.OtpResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpVerificationActivity extends AppCompatActivity {


    private String oTP,pHNUMBER;
    private TextView textPh;
    private AppCompatEditText otp;
    private Button btnNext;
    ApiInterface apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Intent intent = getIntent();
        if(intent!=null){
            oTP = intent.getStringExtra("id");
            pHNUMBER = intent.getStringExtra("phNumber");

        }

        textPh = findViewById(R.id.text1);
        otp = findViewById(R.id.otp);
        btnNext = findViewById(R.id.verify);

        RegPrefManager.getInstance(OtpVerificationActivity.this).setOTP(oTP);
        Toast.makeText(OtpVerificationActivity.this, oTP, Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                otp.setText(oTP);
            }
        },1000);

        textPh.setText("We will auto verify the otp send to " + "\n"
                + pHNUMBER);



       // getLoginResponse();
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    verifyOtp();

            }
        });


    }


    private void verifyOtp(){



        Call<OtpResponse> call = apiService.getOtpResponse(pHNUMBER,
                oTP);
        call.enqueue(new Callback<OtpResponse>() {
            @Override
            public void onResponse(Call<OtpResponse> call, Response<OtpResponse> response) {


                int success = response.body().getSuccess();


                    int verifiyed = response.body().getData().getVerified();
                    if(verifiyed==1){
                        Toast.makeText(OtpVerificationActivity.this,
                                "Otp succesfully verifyed", Toast.LENGTH_SHORT).show();

                        if(oTP.equalsIgnoreCase("0")){
                            Intent in = new Intent(OtpVerificationActivity.this,AccountDetailsActivity.class);
                            startActivity(in);
                        }else{
                            Intent in = new Intent(OtpVerificationActivity.this,DashBoardActivity.class);
                            startActivity(in);
                           // getLoginResponseNew();
                        }


                    }else if(verifiyed == 0){
                        Toast.makeText(OtpVerificationActivity.this,"Please enter correct otp", Toast.LENGTH_SHORT).show();
                       // getLoginResponseNew();

                    }else{
                        Toast.makeText(OtpVerificationActivity.this,
                                "Something went wrong", Toast.LENGTH_SHORT).show();
                    }






            }

            @Override
            public void onFailure(Call<OtpResponse> call, Throwable t) {

                Toast.makeText(OtpVerificationActivity.this,
                        "Some thing went wrong",
                        Toast.LENGTH_SHORT).show();

            }


        });

    }


    private void getLoginResponse(){



        Call<LoginResponse> call = apiService.getLoginResponse(pHNUMBER);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if(response.body().getSuccess()==1){
                    final String otpp = response.body().getData().getOTP();
                    RegPrefManager.getInstance(OtpVerificationActivity.this).setOTP(otpp);
                    Toast.makeText(OtpVerificationActivity.this, otpp, Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            otp.setText(otpp);
                        }
                    },1000);
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                Toast.makeText(OtpVerificationActivity.this, "Some thing went wrong",
                        Toast.LENGTH_SHORT).show();

            }


        });
    }


    public void getLoginResponseNew(){
        Call<NewLoginSuccessData> call = apiService.getNewLoginResponse(pHNUMBER);
        call.enqueue(new Callback<NewLoginSuccessData>() {
            @Override
            public void onResponse(Call<NewLoginSuccessData> call, Response<NewLoginSuccessData> response) {

                if(response.body().getSuccess()==1){

                    /*Intent in = new Intent(OtpVerificationActivity.this,OtpVerificationActivity.class);
                    in.putExtra("id",response.body().getData().getId()+"");
                    in.putExtra("phNumber",
                            pHNUMBER);
                    startActivity(in);*/
                    RegPrefManager.getInstance(OtpVerificationActivity.this).setUserId(response.body().getData().getId());
                    RegPrefManager.getInstance(OtpVerificationActivity.this).setFirstName(response.body().getData().getFirst_Name());
                    RegPrefManager.getInstance(OtpVerificationActivity.this).setSecondName(response.body().getData().getLast_Name());
                    RegPrefManager.getInstance(OtpVerificationActivity.this).setMobileNumber(response.body().getData().getMobile_No());
                    RegPrefManager.getInstance(OtpVerificationActivity.this).setEmailNumber(response.body().getData().getEmail_Id());


                    Intent in = new Intent(OtpVerificationActivity.this,DashBoardActivity.class);
                    startActivity(in);
                   // finish();

                }




            }

            @Override
            public void onFailure(Call<NewLoginSuccessData> call, Throwable t) {

                Toast.makeText(OtpVerificationActivity.this, "Some thing went wrong",
                        Toast.LENGTH_SHORT).show();
                String ti = t.toString();

            }


        });
    }
}
