package com.sunny.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sunny.game.FlappyBird;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
/**
 * Created by sunny on 15-11-15.
 */
public class MenuState extends State {

    private Texture background;
    private Texture playButton;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);//I am not using the camera properly... learn more about game cameras
        background = new Texture("bg.png");
        playButton = new Texture("playbtn.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched())
        {
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.setProjectionMatrix(cam.combined);
        sb.draw(background, 0, 0, FlappyBird.WIDTH, FlappyBird.HEIGHT);
        sb.draw(playButton, (FlappyBird.WIDTH/2) - (playButton.getWidth()/2), cam.position.y);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();
        System.out.println("Disposed of MenuState");

    }
}
