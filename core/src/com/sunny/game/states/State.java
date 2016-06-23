package com.sunny.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * Created by sunny on 15-11-15.
 */
public abstract class State {

    protected OrthographicCamera cam;
    protected Vector3 mouse; //vector3 is a xyz coordinate system
    protected GameStateManager gsm; //manage states, pasue states and so on

    protected State(GameStateManager gsm)
    {
        this.gsm = gsm;
        cam = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();


}
