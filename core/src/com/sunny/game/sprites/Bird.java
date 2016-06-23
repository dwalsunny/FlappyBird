package com.sunny.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by sunny on 15-11-15.
 */
public class Bird {
    public static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;
    private Vector3 position; //hold x,y,z axis
    private Vector3 velocity;
    private Rectangle birdsBounds;
    private Animation birdAnimation;
    private Texture texture;
    private Sound flap;

    public Bird(int x, int y)
    {
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        texture = new Texture("birdanimation.png");
        flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
        birdsBounds = new Rectangle(x, y, texture.getWidth() / 3, texture.getHeight() / 3);
        birdAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);
    }

    public void update(float dt)
    {
        birdAnimation.update(dt);
        if(position.y <= 0)
        {

            position.y = 0;
            return;
        }
        velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y, 0);
        velocity.scl(1/dt);
        birdsBounds.setPosition(position.x,position.y);

    }

    public Vector3 getPosition()
    {
        return position;
    }

    public TextureRegion getTexture()
    {

        return birdAnimation.getFrame();
    }

    public Rectangle getBirdsBounds() {return birdsBounds;}

    public void Jump()
    {
        velocity.y = 250;
        flap.play(0.5f);
    }

    public void dispose()
    {
        flap.dispose();
        texture.dispose();
    }



}
