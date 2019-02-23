package com.example.deian.myapplication.Engine.Render.Renderer;

import android.opengl.GLES30;

import com.example.deian.myapplication.Engine.Core.Manager;
import com.example.deian.myapplication.Engine.Object.Sprite;
import com.example.deian.myapplication.Engine.Object.TexturedSprite;
import com.example.deian.myapplication.Engine.Scene.Container;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class SpriteRenderer {

    private FloatBuffer mVertices;
    private ShortBuffer mIndices;

    static float squareCoords[] = {
            0.0f,  1.0f, 0.0f, 1.0f, // top left
            0.0f, 0.0f, 0.0f, 0.0f,  // bottom left
            1.0f, 0.0f, 1.0f, 0.0f,  // bottom right
            1.0f,  1.0f, 1.0f, 1.0f }; // top right

    private short drawOrder[] = { 0, 1, 2, 0, 2, 3 }; // order to draw vertices

    public SpriteRenderer() {
        Manager.Instance().LoadShader("sprite-renderer", "sprite_renderer.rvert", "sprite_renderer.rfrag");

        // Setup
        ByteBuffer bb = ByteBuffer.allocateDirect(squareCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        mVertices = bb.asFloatBuffer();
        mVertices.put(squareCoords);
        mVertices.position(0);
        ByteBuffer dlb = ByteBuffer.allocateDirect(drawOrder.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        mIndices = dlb.asShortBuffer();
        mIndices.put(drawOrder);
        mIndices.position(0);
    }

    public void Begin() {
        //Prepare mesh rendering
        GLES30.glEnableVertexAttribArray(0);
        GLES30.glVertexAttribPointer(0, 4, GLES30.GL_FLOAT, false,16, mVertices);

        GLES30.glEnable(GLES30.GL_BLEND);
        GLES30.glBlendFunc(GLES30.GL_SRC_ALPHA, GLES30.GL_ONE_MINUS_SRC_ALPHA);

        //Bind shader
        Manager.Instance().BindShader("sprite-renderer");
    }

    public void End() {
        GLES30.glDisable(GLES30.GL_BLEND);

        GLES30.glDisableVertexAttribArray(0);
    }

    public void Render(Sprite sprite) {
        Manager.Instance().GetShader().UpdateMat4(new Matrix4f().translate(new Vector3f(sprite.Position.x, sprite.Position.y, 0.0f)).rotate((float)Math.toRadians(sprite.Rotation), new Vector3f(0.0f, 0.0f, 1.0f)), "mModel");
        Manager.Instance().GetShader().UpdateVec2(sprite.Size, "mSize");

        GLES30.glDrawElements(GLES30.GL_TRIANGLES, 6, GLES30.GL_UNSIGNED_SHORT, mIndices);
    }

    public void Render(TexturedSprite sprite) {
        Manager.Instance().GetShader().UpdateInt(0, "mTexture");
        sprite.mTexture.Bind(0);

        Render((Sprite)sprite);
    }

    public void Render(Container container) {
        for(int i=0; i<container.mSprites.size(); i++)
            Render(container.Get(i));
    }

}
