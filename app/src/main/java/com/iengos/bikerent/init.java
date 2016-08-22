package com.iengos.bikerent;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;



/**
 * Created by Simone on 10/07/2016.
 */
public class init extends AppCompatActivity {

    Handler timeHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_init);
        WebView webView = (WebView) findViewById(R.id.gif_web_view);
        webView.loadUrl("file:///android_asset/logo.gif");
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        // Wait 4s
        timeHandler = new Handler();
        timeHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent openLogin = new Intent(init.this, presentationSlides.class);
                startActivity(openLogin);
            }
        }, 4000);

    }
}
