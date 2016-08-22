package com.iengos.bikerent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by Simone on 10/07/2016.
 */
public class init extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        Intent openLogin = new Intent(init.this, presentationSlides.class);
        // passo all'attivazione dell'activity presentationSlides.java
        startActivity(openLogin);

    }
}
