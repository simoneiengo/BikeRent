package com.iengos.bikerent;

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

    public static final String URL = "http://bikerent.comxa.com/login.php";

    EditText et_user;
    PasswordView et_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    // called when login button is clicked
    public void login(View v) {
        et_user = (EditText) findViewById(R.id.et_username);
        et_pass = (PasswordView) findViewById(R.id.et_password);

        String p = et_pass.getText().toString();
        RequestParams params = new RequestParams();
        params.put("username", et_user.getText().toString());
        params.put("password", et_pass.getText().toString());

        AsyncHttpClient client = new AsyncHttpClient();

        client.post(URL, params, new TextHttpResponseHandler() {
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
                Log.i("login_response", responseBody);
                if(responseBody.equals("login_ok")) //TODO: settare variabili dell'account da passare all'activity home
                    startActivity(new Intent(getApplicationContext(), home.class));
                else
                    Toast.makeText(login.this, getResources().getString(R.string.pd_login_failed), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String a, Throwable error) {
                prgDialog.cancel();
                Toast.makeText(login.this, getResources().getString(R.string.pd_login_error), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(getApplicationContext(), presentationSlides.class);
        i.putExtra("slideNum", 3);
        startActivity(i);
    }
}