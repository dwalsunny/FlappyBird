package com.sunny.game.sprites;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import java.util.Random;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

//rectangle from java not from badlogic
/**
 * Created by sunny on 15-11-22.
 */
public class Tube {
    public static final int TUBE_WIDTH = 52;

    private static final int FLUCTURATION = 130;
    private static final int TUBE_GAP = 100;
    private static final int LOWEST_OPENING = 120;
    private Texture topTube;
    private Texture bottomTube;

    private Vector2 positionTopTube;
    private Vector2 positionBottomTube;
    private Random rand;

    private Rectangle boundsTop;
    private Rectangle boundsBottom;

    public Rectangle getBoundsTop() {
        return boundsTop;
    }

    public Rectangle getBoundsBottom() {
        return boundsBottom;
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPositionTopTube() {
        return positionTopTube;
    }

    public Vector2 getPositionBottomTube() {
        return positionBottomTube;
    }

    public Tube(float x) {
        topTube = new Texture("TopTube.png");
        bottomTube = new Texture("BottomTube.png");
        rand = new Random();

        positionTopTube = new Vector2(x, rand.nextInt(FLUCTURATION) + TUBE_GAP + LOWEST_OPENING);
        positionBottomTube = new Vector2(x, positionTopTube.y - TUBE_GAP - bottomTube.getHeight());

        boundsTop = new Rectangle(positionTopTube.x, positionTopTube.y, 52, topTube.getHeight());
        boundsBottom = new Rectangle(positionBottomTube.x, positionBottomTube.y, bottomTube.getWidth(),bottomTube.getHeight());
    }

    public void reposition(float x){
        positionTopTube.set(x, rand.nextInt(FLUCTURATION) + TUBE_GAP + LOWEST_OPENING);
        positionBottomTube.set(x, positionTopTube.y - TUBE_GAP - bottomTube.getHeight());
        boundsTop.setPosition(positionTopTube.x, positionTopTube.y);
        boundsBottom.setPosition(positionBottomTube.x, positionBottomTube.y);
    }

    public boolean collides(Rectangle player)
    {
        boolean collision = player.overlaps(boundsTop) ||  player.overlaps(boundsBottom);
        return collision;

    }

    public void dispose()
    {
        topTube.dispose();
        bottomTube.dispose();
    }


}
