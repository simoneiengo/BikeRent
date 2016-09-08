package com.iengos.bikerent;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.xwray.passwordview.PasswordView;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Simone on 10/07/2016.
 */
public class login  extends AppCompatActivity{

    public static final String URL = "http://10.113.53.241/BikeRentWeb/";
    public static final String LOGIN_PAGE = "login.php";
    public static final String RESET_PAGE = "reset.php";

    Dialog dialog;
    EditText et_email;
    PasswordView et_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        dialog = new Dialog(login.this);
    }

    // called when login button is clicked
    public void login(View v) {
        et_email = (EditText) findViewById(R.id.et_username);
        et_pass = (PasswordView) findViewById(R.id.et_password);

        //password must be at least 2 characters.
        //email must be at least 5 characters, and must contains @ and .
        if(et_email.getText().toString().length() < 5 || et_pass.getText().toString().length() < 2
                || !et_email.getText().toString().contains("@") || !et_email.getText().toString().contains("@")) {
            Toast.makeText(login.this, getResources().getString(R.string.pd_fill_field), Toast.LENGTH_LONG).show();
            return;
        }

        String p = et_pass.getText().toString();
        RequestParams params = new RequestParams();
        params.put("email", et_email.getText().toString());
        params.put("password", et_pass.getText().toString());

        AsyncHttpClient client = new AsyncHttpClient();

        client.post(URL+LOGIN_PAGE, params, new TextHttpResponseHandler() {
            ProgressDialog prgDialog;

            @Override
            public void onStart() {
                prgDialog = new ProgressDialog(login.this);
                prgDialog.setMessage(getResources().getString(R.string.pd_login_msg));
                //prgDialog.setCancelable(false);
                prgDialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseBody) {
                prgDialog.cancel();
                //Log.i("login_response", responseBody);
                if(responseBody.equals("login_ok")) //TODO: settare variabili dell'account da passare all'activity home
                    startActivity(new Intent(getApplicationContext(), home.class));
                else if(responseBody.equals("login_err")) //login errato
                    Toast.makeText(login.this, getResources().getString(R.string.pd_login_failed), Toast.LENGTH_SHORT).show();
                else    //mysql_query failed, output: error message
                    Toast.makeText(login.this, getResources().getString(R.string.pd_query_failed) + responseBody, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String a, Throwable error) {
                prgDialog.cancel();
                Toast.makeText(login.this, getResources().getString(R.string.pd_login_error), Toast.LENGTH_LONG).show();
            }
        });
    }

    /*============================================================
                  POP-UP MANAGEMENT PASSWORD
    ============================================================*/
    public void getPass(View v) {
            dialog.setContentView(R.layout.get_pass_popup);        // set popup layout
            dialog.setTitle("Get Password");
            dialog.show();                                      // open popup
    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(getApplicationContext(), presentationSlides.class);
        i.putExtra("slideNum", 3);
        startActivity(i);
    }

    public void sendEmailPass(View v){
        //TODO: implementare la richiesta di password tramite email
        EditText etMail = (EditText) findViewById(R.id.etPopupEmail);

        //TODO: check valid mail @ .

        /********** send a first request on reset.php?email=$etMail ********************/
        String p = et_pass.getText().toString();
        RequestParams params = new RequestParams();
        params.put("email", et_email.getText().toString());

        AsyncHttpClient client = new AsyncHttpClient();

        client.post(URL+RESET_PAGE, params, new TextHttpResponseHandler() {
            ProgressDialog prgDialog;

            @Override
            public void onStart() {
                prgDialog = new ProgressDialog(login.this);
                prgDialog.setMessage(getResources().getString(R.string.reset_working));
                prgDialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseBody) {
                prgDialog.cancel();
                if (responseBody.equals("mail_inp")) { //mail is not present
                    Toast.makeText(login.this, getResources().getString(R.string.reset_mail_inp), Toast.LENGTH_SHORT).show();
                } else if (responseBody.equals("mail_ok")) { //email inviata
                    Toast.makeText(login.this, getResources().getString(R.string.reset_mail_ok), Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {    //mysql_query failed, output: error message
                    Toast.makeText(login.this, getResources().getString(R.string.pd_query_failed) + responseBody, Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String a, Throwable error) {
                prgDialog.cancel();
                Toast.makeText(login.this, getResources().getString(R.string.pd_login_error), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void cancelEmailPass(View v){
       dialog.dismiss();
    }
}