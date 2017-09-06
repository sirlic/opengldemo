package com.lichuang.opengldemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private MyGLSurfaceView mGlview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGlview = new MyGLSurfaceView(this);
        setContentView(mGlview);
    }
}
