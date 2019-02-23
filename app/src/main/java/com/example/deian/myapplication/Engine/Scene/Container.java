package com.example.deian.myapplication.Engine.Scene;

import com.example.deian.myapplication.Engine.Object.TexturedSprite;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Container {

    public ArrayList<TexturedSprite> mSprites;

    public Container() {
        mSprites = new ArrayList<TexturedSprite>();
    }

    public void Add(TexturedSprite sprite) {
        mSprites.add(sprite);
    }

    public TexturedSprite Get(int id) {
        return mSprites.get(id);
    }

}
