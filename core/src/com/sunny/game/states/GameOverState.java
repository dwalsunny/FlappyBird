package com.sunny.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sunny.game.FlappyBird;

/**
 * Created by sunny on 15-11-29.
 */
public class GameOverState extends State {
    private Texture gameOver;

    public GameOverState(GameStateManager gsm)
    {
        super(gsm);
        gameOver = new Texture("gameover.png");

    }


    protected void handleInput(){
        if(Gdx.input.justTouched())
        {
            gsm.set(new PlayState(gsm));
        }

    }

    public void update(float dt) {
        handleInput();
    }
    public void render(SpriteBatch sb)
    {
        sb.begin();
        sb.draw(gameOver, FlappyBird.WIDTH/2 - gameOver.getWidth(), FlappyBird.HEIGHT/2);
        sb.end();
    }

    public void dispose()
    {
        gameOver.dispose();
    }

}
