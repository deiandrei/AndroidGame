package com.example.deian.myapplication.Engine.Scene;

import com.example.deian.myapplication.Engine.Object.TexturedSprite;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.joml.Vector2i;
import org.w3c.dom.Text;

import java.util.ArrayList;

class PhysicsBody {
    public Body mBody;
    public TexturedSprite mSpriteRef;
}

public class PhysicsCore {

    private World mWorld;
    public ArrayList<PhysicsBody> mBodies;

    public PhysicsCore() {
        mWorld = new World(new Vec2(0, 98f));
        mBodies = new ArrayList<PhysicsBody>();
    }

    public void Add(TexturedSprite sprite, boolean fixed) {
        BodyDef bodyDef = new BodyDef();
        if(!fixed) bodyDef.type = BodyType.DYNAMIC;
        bodyDef.position.set(sprite.Position.x, sprite.Position.y);

        Body body = mWorld.createBody(bodyDef);

        PolygonShape dynamicBox = new PolygonShape();
        dynamicBox.setAsBox(sprite.Size.x, sprite.Size.y);

        if(!fixed) {
            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = dynamicBox;
            fixtureDef.friction = 0.3f;
            fixtureDef.density = 1.0f;

            body.createFixture(fixtureDef);
        }
        else {
            body.createFixture(dynamicBox, 0);
        }

        PhysicsBody bod = new PhysicsBody();
        bod.mBody = body;
        bod.mSpriteRef = sprite;

        mBodies.add(bod);
    }

    public void Update(float dT) {
        mWorld.step(dT, 6, 2);

        for(int i = 0; i<mBodies.size(); i++) {
            Vec2 pos = mBodies.get(i).mBody.getPosition();
            float rotation = (float)Math.toDegrees((float)mBodies.get(i).mBody.getAngle());

            mBodies.get(i).mSpriteRef.Position = new Vector2i((int)pos.x, (int)pos.y);
            mBodies.get(i).mSpriteRef.Rotation = rotation;
        }
    }

    public void Import(Container container) {
        for(int i = 0; i<container.mSprites.size(); i++) {
            Add(container.Get(i), false);
        }
    }
}
