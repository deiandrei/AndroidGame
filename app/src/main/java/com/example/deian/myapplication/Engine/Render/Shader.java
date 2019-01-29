package com.example.deian.myapplication.Engine.Render;

import android.opengl.GLES30;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector3f;

import java.nio.FloatBuffer;

public class Shader {

    private int mProgram;

    public Shader() {
        mProgram = GLES30.glCreateProgram();
    }

    public int CompileShader(String source, int type) {
        int shader = GLES30.glCreateShader(type);

        GLES30.glShaderSource(shader, source);
        GLES30.glCompileShader(shader);

        return shader;
    }

    public void LoadShader(String source, int type) {
        int shader = CompileShader(source, type);

        GLES30.glAttachShader(mProgram, shader);
    }

    public void CompileProgram() {
        GLES30.glBindAttribLocation(mProgram, 0, "mPosTex");

        GLES30.glLinkProgram(mProgram);
        GLES30.glValidateProgram(mProgram);
    }

    public void BindShader() {
        GLES30.glUseProgram(mProgram);
    }

    public void UpdateInt(int val, String location) {
        GLES30.glUniform1i(GLES30.glGetUniformLocation(mProgram, location), val);
    }

    public void UpdateFloat(float val, String location) {
        GLES30.glUniform1f(GLES30.glGetUniformLocation(mProgram, location), val);
    }

    public void UpdateVec2(Vector2f val, String location) {
        GLES30.glUniform2f(GLES30.glGetUniformLocation(mProgram, location), val.x, val.y);
    }

    public void UpdateVec2(Vector2i val, String location) {
        GLES30.glUniform2f(GLES30.glGetUniformLocation(mProgram, location), (float)val.x, (float)val.y);
    }

    public void UpdateVec3(Vector3f val, String location) {
        GLES30.glUniform3f(GLES30.glGetUniformLocation(mProgram, location), val.x, val.y, val.z);
    }

    public void UpdateMat4(Matrix4f val, String location) {
        float[] fb = new float[16];
        val.get(fb);
        FloatBuffer fb2 = FloatBuffer.wrap(fb);
        GLES30.glUniformMatrix4fv(GLES30.glGetUniformLocation(mProgram, location), 1, false, fb2);
    }

}
