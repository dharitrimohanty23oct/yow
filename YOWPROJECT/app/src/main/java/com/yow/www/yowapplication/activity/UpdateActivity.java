package com.yow.www.yowapplication.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yow.www.yowapplication.R;
import com.yow.www.yowapplication.helper.Constants;
import com.yow.www.yowapplication.helper.RegPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private ImageView img;
    private String namevalue;
    private TextView tvToolbar;
    private String userid,otp,email;
    private EditText nameEd;
    private Button updateBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        init();
    }
    private void init(){
        userid=String.valueOf(RegPrefManager.getInstance(this).getUserId());
        otp=RegPrefManager.getInstance(this).getOTP();

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        img=toolbar.findViewById(R.id.img);
        tvToolbar=findViewById(R.id.tvToolbar);
        toolbar.setOnClickListener(this);
        updateBut=findViewById(R.id.updateBut);
        nameEd=findViewById(R.id.nameEd);
        updateBut.setOnClickListener(this);

        Intent intent=getIntent();
        Bundle  bd=intent.getExtras();
        if(bd!=null){
            namevalue=(String)bd.get("Value");

        }

        if(namevalue.equalsIgnoreCase("Name")){
            tvToolbar.setText("Update Name");
        }else if(namevalue.equalsIgnoreCase("Mobile")){
            tvToolbar.setText("Update Mobile Number");
        }
        else {
            tvToolbar.setText("Update Email");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.toolbar:
                Intent toolbar=new Intent(UpdateActivity.this,AccountDetailsActivity.class);
                startActivity(toolbar);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out); // for fwd
                break;
            case R.id.updateBut:
                new updateAsync().execute();
                break;
        }
    }

    private class updateAsync extends AsyncTask< Void, Void, String> {
       /* protected void onPreExecute() {
            progressDialog = new ProgressDialog(getContext());
            progressDialog.show();
            progressDialog.setCancelable(false);
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            progressDialog.setContentView(R.layout.progressdialog);
        }
*/


        protected String doInBackground(Void... unused) {


            String result = null;



            try {
                URL url=null;
                JSONObject postDataParams = null;
                if(namevalue.equalsIgnoreCase("Name")) {
                    String update_name = Constants.UPDATE_NAME;
                     url = new URL(update_name); // here is your URL path
                     postDataParams = new JSONObject();
                    postDataParams.put("Id", userid);
                    postDataParams.put("First_Name", nameEd.getText().toString());
                    postDataParams.put("Last_Name", " ");

                }
                else if(namevalue.equalsIgnoreCase("Mobile")){
                    String update_name = Constants.UPDATE_MOBILE;
                    url = new URL(update_name); // here is your URL path
                    postDataParams = new JSONObject();
                    postDataParams.put("Id", userid);
                    postDataParams.put("Mobile_No", nameEd.getText().toString());
                    postDataParams.put("OTP", otp);
                }
                else {
                    String update_name = Constants.UPDATE_EMAIL;
                    url = new URL(update_name); // here is your URL path
                    postDataParams = new JSONObject();
                    postDataParams.put("Id", userid);
                    postDataParams.put("Email_Id", nameEd.getText().toString());

                }

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode=conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in=new BufferedReader(new
                            InputStreamReader(
                            conn.getInputStream()));

                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                }
                else {
                    return new String("false : "+responseCode);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return result;
        }

        protected void onPostExecute(String result) {
            Log.d("result_common:",result);

            if (result != null) {
                if(result.equals("false : 404")){
                    //   listview_list.removeFooterView(pb);

                }else{
                    try {
                        //  listview_list.removeFooterView(pb);

                        JSONObject object=new JSONObject(result);
                        String Success=object.getString("Success");
                        if(Success.equals("1")){
                            JSONObject object1=object.getJSONObject("Data");
                            Toast.makeText(getApplicationContext(),"Profile Update Successfully",Toast.LENGTH_LONG).show();
                            if(namevalue.equalsIgnoreCase("Name")) {
                                RegPrefManager.getInstance(UpdateActivity.this).setFirstName(nameEd.getText().toString());
                                RegPrefManager.getInstance(UpdateActivity.this).setSecondName(" ");
                            }
                            else if(namevalue.equalsIgnoreCase("Mobile")) {
                                RegPrefManager.getInstance(UpdateActivity.this).setMobileNumber(nameEd.getText().toString());

                            }
                            else {
                                RegPrefManager.getInstance(UpdateActivity.this).setEmailNumber(nameEd.getText().toString());
                            }
                            Intent i=new Intent(UpdateActivity.this, AccountDetailsActivity.class);
                            //i.putExtra("Value","Name");
                            startActivity(i);
                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out); // for fwd
                        }else {

                        }
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));



        }
        //Log.d("result:",result.toString());
        return result.toString();
    }

}
