package com.example.deian.myapplication.Engine.Render.Camera;


import com.example.deian.myapplication.Engine.Render.Shader;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Camera2D {

    public Matrix4f mMatrix;

    public Camera2D(int width, int height) {
        mMatrix = new Matrix4f().identity().ortho2D(0, width, height, 0).lookAt(new Vector3f(0.0f, 0.0f, 1.0f), new Vector3f(0.0f, 0.0f, -1.0f), new Vector3f(0.0f, 1.0f, 0.0f));
    }

    public void UpdateShader(Shader shader) {
        shader.UpdateMat4(mMatrix, "mCameraVP");
    }

}
