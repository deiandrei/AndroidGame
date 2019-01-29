package com.example.deian.myapplication.Engine.Material;

import android.graphics.Bitmap;
import android.opengl.GLES30;
import android.opengl.GLUtils;
import android.util.Log;

import org.w3c.dom.Text;

public class Texture2D {

    private int[] mTexture;

    public Texture2D(Bitmap bitmap) {
        mTexture = new int[1];

        GLES30.glGenTextures(1, mTexture, 0);

        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, mTexture[0]);

        GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MIN_FILTER, GLES30.GL_NEAREST);
        GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MAG_FILTER, GLES30.GL_NEAREST);

        GLUtils.texImage2D(GLES30.GL_TEXTURE_2D, 0, bitmap, 0);

        bitmap.recycle();

        if(mTexture[0] == 0) Log.d("Texture", "eroare!");
    }

    public void Bind(int level) {
        GLES30.glActiveTexture(GLES30.GL_TEXTURE0 + level);
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, mTexture[0]);
    }

}
