package com.shibuyaxpress.asistenciadealumnostecsup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        Intent launcher=new Intent(this,Login.class);
        startActivity(launcher);
        finish();
    }
}
