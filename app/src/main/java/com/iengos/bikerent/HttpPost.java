package com.iengos.bikerent;

import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.concurrent.Callable;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.protocol.HTTP;

/**
 * Created by davidevanore on 08/09/16.
 *
 * This class implements a web request (POST)
 */

public class HttpPost {
    //callbacks
    private Callable<Void> onStart_cbk, onFailure_cbk, onSuccess_cbk;

    private String url;

    public HttpPost(String url, Callable<Void> onStart_cbk, Callable<Void> onFailure_cbk, Callable<Void> onSuccess_cbk) {
        this.onStart_cbk = onStart_cbk;
        this.onFailure_cbk = onFailure_cbk;
        this.onSuccess_cbk = onSuccess_cbk;
        this.url = url;
    }

    public void post(RequestParams params) {
        AsyncHttpClient client = new AsyncHttpClient();

        client.post(url, params, new TextHttpResponseHandler() {
            ProgressDialog prgDialog;

            @Override
            public void onStart() {
                try {
                    onStart_cbk.call();
                } catch (Exception e) {
                    Log.e("onStart()", e.toString());
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseBody) {
                try {
                    onSuccess_cbk.call();
                } catch (Exception e) {
                    Log.e("onSuccess()", e.toString());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String a, Throwable error) {
                try {
                    onFailure_cbk.call();
                } catch (Exception e) {
                    Log.e("onFailure()", e.toString());
                }
            }
        });

    }
}
