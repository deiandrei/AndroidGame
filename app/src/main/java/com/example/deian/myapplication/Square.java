package com.example.deian.myapplication;

import android.opengl.GLES30;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class Square {

    private FloatBuffer mVertices;
    private ShortBuffer mIndices;

    static float squareCoords[] = {
            0.0f,  1.0f, 0.0f, 1.0f, // top left
            0.0f, 0.0f, 0.0f, 0.0f,  // bottom left
            1.0f, 0.0f, 1.0f, 0.0f,  // bottom right
            1.0f,  1.0f, 1.0f, 1.0f }; // top right

    private short drawOrder[] = { 0, 1, 2, 0, 2, 3 }; // order to draw vertices

    public Square() {
        // initialize vertex byte buffer for shape coordinates
        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 4 bytes per float)
                squareCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        mVertices = bb.asFloatBuffer();
        mVertices.put(squareCoords);
        mVertices.position(0);

        // initialize byte buffer for the draw list
        ByteBuffer dlb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 2 bytes per short)
                drawOrder.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        mIndices = dlb.asShortBuffer();
        mIndices.put(drawOrder);
        mIndices.position(0);
    }

    public void draw() {
        // Enable a handle to the triangle vertices
        GLES30.glEnableVertexAttribArray(0);

        // Prepare the triangle coordinate data
        GLES30.glVertexAttribPointer(0, 4,
                GLES30.GL_FLOAT, false,
                16, mVertices);

        // Draw the triangle
        GLES30.glDrawElements(GLES30.GL_TRIANGLES, 6, GLES30.GL_UNSIGNED_SHORT, mIndices);

        // Disable vertex array
        //GLES20.glDisableVertexAttribArray(mPositionHandle);
    }
}
