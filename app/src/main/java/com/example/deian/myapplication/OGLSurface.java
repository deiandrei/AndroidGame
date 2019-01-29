package com.example.deian.myapplication;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.DisplayMetrics;

import com.example.deian.myapplication.Engine.Core.Manager;

public class OGLSurface extends GLSurfaceView {

    private final OGLRenderer mRenderer;

    public OGLSurface(Context context) {
        super(context);

        Manager.Instance().Setup(context);

        setEGLContextClientVersion(3);
        mRenderer = new OGLRenderer();

        setRenderer(mRenderer);
    }
}
