package com.lichuang.opengldemo;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * author: lichuang
 * created on: 17/8/26 下午1:59
 * description:
 */

public class MyGLSurfaceView extends GLSurfaceView {
    public MyGLSurfaceView(Context context) {
        super(context);
        setEGLContextClientVersion(2);
        setRenderer(new MyRender());
    }

    public MyGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setEGLContextClientVersion(2);
        setRenderer(new MyRender());
    }


}
