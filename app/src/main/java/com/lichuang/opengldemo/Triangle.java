package com.lichuang.opengldemo;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * author: lichuang
 * created on: 17/8/26 下午2:36
 * description:
 */

public class Triangle {
    private final int mProgram;
    private FloatBuffer mVertexBuffer;

    static final int COORDS_PER_VERTEX = 3;

    static float triangleCoords[] = {
            0.0f,0.6f,0.0f,
            -0.5f,-0.3f,0.0f,
            0.5f,-0.3f,0.0f
    };

    float color[] = { 0.63671875f, 0.76953125f, 0.22265625f, 1.0f };

    private final String vertexShaderCode =
            "attribute vec4 vPosition;" +
            "void main() {" +
            "   gl_Position = vPosition;"+
            "}";

    private final String fragmentShaderCode =
            "precision mediump float;" +
            "uniform vec4 vColor;" +
                    "void main() {" +
                    "   gl_FragColor = vColor;" +
                    "}";
    private int mPositionhandler;
    private final int vertexStride = COORDS_PER_VERTEX * 4;
    private int mColorHandler;
    private final int vertexCount = triangleCoords.length / COORDS_PER_VERTEX;
    public Triangle() {
        ByteBuffer bb = ByteBuffer.allocateDirect(triangleCoords.length*4);
        bb.order(ByteOrder.nativeOrder());
        mVertexBuffer = bb.asFloatBuffer();
        mVertexBuffer.put(triangleCoords);
        mVertexBuffer.position(0);

        int vertexShader = MyRender.loadShader(GLES20.GL_VERTEX_SHADER,vertexShaderCode);
        int fragment = MyRender.loadShader(GLES20.GL_FRAGMENT_SHADER,fragmentShaderCode);
        mProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(mProgram,vertexShader);
        GLES20.glAttachShader(mProgram,fragment);
        GLES20.glLinkProgram(mProgram);
    }

    public void draw() {
        GLES20.glUseProgram(mProgram);
//        int dd = GLES20.glGetAttribLocation(mProgram,"dd");
        mPositionhandler = GLES20.glGetAttribLocation(mProgram,"vPosition");
//        Log.i("lichuang",mPositionhandler+"--"+ dd);
        GLES20.glVertexAttribPointer(mPositionhandler,COORDS_PER_VERTEX,GLES20.GL_FLOAT,false,0,mVertexBuffer);
        // 启用一个指向三角形的顶点数组的 handle
        GLES20.glEnableVertexAttribArray(mPositionhandler);
        mColorHandler = GLES20.glGetUniformLocation(mProgram,"vColor");
        GLES20.glUniform4fv(mColorHandler,1,color,0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES,0,3);
        GLES20.glDisableVertexAttribArray(mPositionhandler);
    }
}
