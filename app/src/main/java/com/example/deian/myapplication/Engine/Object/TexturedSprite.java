package com.example.deian.myapplication.Engine.Object;

import com.example.deian.myapplication.Engine.Material.Texture2D;

import org.joml.Vector2i;

public class TexturedSprite extends Sprite {

    public Texture2D mTexture;

    public TexturedSprite() {
        super();
    }

    public TexturedSprite(Texture2D texture, Vector2i size, Vector2i position, float rotation) {
        super(size, position, rotation);
        mTexture = texture;
    }

    public TexturedSprite(Texture2D texture) {
        super();
        mTexture = texture;
    }

}
