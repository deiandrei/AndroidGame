package com.example.deian.myapplication;

import android.opengl.GLSurfaceView;
import android.opengl.GLES30;

import com.example.deian.myapplication.Engine.Core.Manager;
import com.example.deian.myapplication.Engine.Material.Texture2D;
import com.example.deian.myapplication.Engine.Object.Sprite;
import com.example.deian.myapplication.Engine.Object.TexturedSprite;
import com.example.deian.myapplication.Engine.Render.Camera.Camera2D;
import com.example.deian.myapplication.Engine.Render.Renderer.SpriteRenderer;
import com.example.deian.myapplication.Engine.Render.Shader;
import com.example.deian.myapplication.Engine.Scene.Container;
import com.example.deian.myapplication.Engine.Scene.PhysicsCore;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector2i;

import java.sql.Time;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class OGLRenderer implements GLSurfaceView.Renderer {

    private SpriteRenderer mRenderer;
    private Camera2D mCamera;
    private Container mScene;
    private PhysicsCore mPhysics;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        int[] vals = new int[4];
        GLES30.glGetIntegerv(GLES30.GL_VIEWPORT, vals, 0);
        Manager.Instance().SetupAddon(vals[2], vals[3]);

        // Set the background frame color
        GLES30.glClearColor(0.0f, 0.0f, 1.0f, 1.0f);

        mCamera = new Camera2D(Manager.Instance().mWidth, Manager.Instance().mHeight);
        mRenderer = new SpriteRenderer();
        mScene = new Container();

        mScene.Add(new TexturedSprite(Manager.Instance().GetTexture("particle.png"), new Vector2i(100, 200), new Vector2i(0, 0), 0.0f));
        mScene.Add(new TexturedSprite(Manager.Instance().GetTexture("particle.png"), new Vector2i(300, 300), new Vector2i(150, 500), 0.0f));
        mScene.Add(new TexturedSprite(Manager.Instance().GetTexture("test.jpg"), new Vector2i(50,50), new Vector2i(400, 400), 0.0f));

        mPhysics = new PhysicsCore();

        mPhysics.Import(mScene);

        mScene.Add(new TexturedSprite(Manager.Instance().GetTexture("test.jpg"), new Vector2i(1000,50), new Vector2i(0, 1000), 0.0f));
        mPhysics.Add(mScene.mSprites.get(3), true);
    }

    public void onDrawFrame(GL10 unused) {
        // Redraw background color
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);

        mPhysics.Update(1.0f/60.0f);

        mRenderer.Begin();
        mCamera.UpdateShader(Manager.Instance().GetShader());

        mRenderer.Render(mScene);
        mRenderer.End();
    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES30.glViewport(0, 0, width, height);
    }


}
