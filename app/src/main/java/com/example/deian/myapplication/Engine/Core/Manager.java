package com.example.deian.myapplication.Engine.Core;

import com.example.deian.myapplication.Engine.Material.Texture2D;
import com.example.deian.myapplication.Utils;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES30;

import com.example.deian.myapplication.Engine.Render.Shader;

import java.io.IOException;
import java.io.InputStream;
import java.util.NavigableMap;
import java.util.TreeMap;

public class Manager {

    private static Manager mInstance = null;
    private Context mContext = null;
    public int mWidth;
    public int mHeight;

    NavigableMap<String, Texture2D> mTextureMap;
    NavigableMap<String, Shader> mShaderMap;
    String mCurrentShader;

    private Manager() {
        mShaderMap = new TreeMap<String, Shader>();
        mTextureMap = new TreeMap<String, Texture2D>();
        mCurrentShader = "";
    }

    public void Setup(Context context) {
        mContext = context;
    }

    public void SetupAddon(int width, int height) {
        mWidth = width;
        mHeight = height;
    }

    public static Manager Instance() {
        if(mInstance == null)
            mInstance = new Manager();

        return mInstance;
    }

    //Shaders
    public void LoadShader(String name, String vs, String fs) {
        if(HasShader(name) || mContext == null) return;

        String vss = Utils.GetStringFromFileAssets(mContext.getAssets(), vs);
        String fss = Utils.GetStringFromFileAssets(mContext.getAssets(), fs);

        Shader shader = new Shader();
        shader.LoadShader(vss, GLES30.GL_VERTEX_SHADER);
        shader.LoadShader(fss, GLES30.GL_FRAGMENT_SHADER);
        shader.CompileProgram();

        mShaderMap.put(name, shader);
    }

    public boolean HasShader(String name) {
        if(mShaderMap.containsKey(name)) return true;

        return false;
    }

    public void BindShader(String name) {
        if(name == mCurrentShader || name == "" || !HasShader(name)) return;

        mCurrentShader = name;
        mShaderMap.get(name).BindShader();
    }

    public Shader GetShader() {
        if(mCurrentShader != "") return mShaderMap.get(mCurrentShader);

        return null;
    }

    //Textures
    public boolean HasTexture(String name) {
        if(mTextureMap.containsKey(name)) return true;
        return false;
    }

    public Texture2D GetTexture(String name) {
        if(!HasTexture(name)) {
            try {
                InputStream is = mContext.getAssets().open(name);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inScaled = false;
                Bitmap bitmap = BitmapFactory.decodeStream(is, null, options);

                Texture2D tex = new Texture2D(bitmap);

                mTextureMap.put(name, tex);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return mTextureMap.get(name);
    }

}
