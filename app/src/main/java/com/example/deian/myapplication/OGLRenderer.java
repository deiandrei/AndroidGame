package com.example.deian.myapplication;

import android.opengl.GLSurfaceView;
import android.opengl.GLES30;

import com.example.deian.myapplication.Engine.Core.Manager;
import com.example.deian.myapplication.Engine.Material.Texture2D;
import com.example.deian.myapplication.Engine.Object.Sprite;
import com.example.deian.myapplication.Engine.Render.Camera.Camera2D;
import com.example.deian.myapplication.Engine.Render.Renderer.SpriteRenderer;
import com.example.deian.myapplication.Engine.Render.Shader;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector2i;

import java.sql.Time;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class OGLRenderer implements GLSurfaceView.Renderer {

    private SpriteRenderer mRenderer;
    private Texture2D mTex;
    private Camera2D mCamera;
    private Sprite mSprite1, mSprite2;

    private float tt;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        int[] vals = new int[4];
        GLES30.glGetIntegerv(GLES30.GL_VIEWPORT, vals, 0);
        Manager.Instance().SetupAddon(vals[2], vals[3]);

        // Set the background frame color
        GLES30.glClearColor(0.0f, 0.0f, 1.0f, 1.0f);

        mTex = Manager.Instance().GetTexture("test.jpg");
        mCamera = new Camera2D(Manager.Instance().mWidth, Manager.Instance().mHeight);
        mRenderer = new SpriteRenderer();

        mSprite1 = new Sprite(new Vector2i(100, 200));
        mSprite2 = new Sprite(new Vector2i(300, 300));
        mSprite2.Position = new Vector2i(150,500);
        mSprite2.Rotation = 45.0f;

        tt = 0.0f;
    }

    public void onDrawFrame(GL10 unused) {
        // Redraw background color
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);

        mSprite2.Position.x = mSprite2.Position.x + (int)(10 * Math.sin(tt));
        tt += 0.1f;

        mRenderer.Begin();
        mCamera.UpdateShader(Manager.Instance().GetShader());

        Manager.Instance().GetShader().UpdateInt(0, "mTexture");
        mTex.Bind(0);

        mRenderer.Render(mSprite1);
        mRenderer.Render(mSprite2);
        mRenderer.End();


    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES30.glViewport(0, 0, width, height);
    }


}
