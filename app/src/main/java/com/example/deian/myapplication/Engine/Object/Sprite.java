package com.example.deian.myapplication.Engine.Object;

import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector3f;

public class Sprite {
    public Vector2i Size, Position;
    public float Rotation;

    public Sprite() {
        Size = new Vector2i(1, 1);
        Position = new Vector2i(0,0);
        Rotation = 0.0f;
    }

    public Sprite(Vector2i size, Vector2i position, float rotation) {
        Size = size;
        Position = position;
        Rotation = rotation;
    }

}
