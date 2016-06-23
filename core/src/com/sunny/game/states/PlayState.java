package com.sunny.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sunny.game.FlappyBird;
import com.sunny.game.sprites.Bird;
import com.sunny.game.sprites.Tube;
import com.badlogic.gdx.utils.Array;



/**
 * Created by sunny on 15-11-15.
 */
public class PlayState extends State {
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_OFFSET = -50;

    private Array<Tube> tubes;
    private Bird bird;
    private Texture backGround;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;


    protected PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(25,100);
        cam.setToOrtho(false, FlappyBird.WIDTH/2, FlappyBird.HEIGHT/2);//I am not using the camera properly... learn more about game cameras
        backGround = new Texture("bg.png");
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(cam.position.x - (cam.viewportWidth / 2),GROUND_OFFSET);
        groundPos2 = new Vector2((cam.position.x - (cam.viewportWidth / 2)) + ground.getWidth(),GROUND_OFFSET);
        tubes = new Array<Tube>();

        for(int i = 1; i <= TUBE_COUNT; i++)
        {
            tubes.add(new Tube(i*(TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched())
        {
            bird.Jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80;
        for(Tube tube: tubes) {
            if (cam.position.x - (cam.viewportWidth / 2) > tube.getPositionBottomTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPositionTopTube().x + (Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT);
            }

            if (tube.collides(bird.getBirdsBounds())) {
                //gsm.set(new GameOverState(gsm));
                gsm.set(new PlayState(gsm));
            }
        }
        if(bird.getPosition().y <= ground.getHeight() + GROUND_OFFSET)
        {
            gsm.set(new PlayState(gsm));
        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(backGround, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        for(Tube tube : tubes)
        {
            sb.draw(tube.getTopTube(), tube.getPositionTopTube().x, tube.getPositionTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPositionBottomTube().x, tube.getPositionBottomTube().y);
        }
        sb.draw(ground, groundPos1.x-20, groundPos1.y,ground.getWidth()+20,ground.getHeight());
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.end();

    }

    @Override
    public void dispose() {
        //other textures may not be getting disposed properly
        backGround.dispose();
        ground.dispose();
        bird.dispose();
        for(int i = 0; i < TUBE_COUNT; i++)
        {
            tubes.get(i).dispose();
        }
        System.out.println("Disposed of PlayState");
    }

    private void updateGround()
    {
        if(groundPos1.x + ground.getWidth() < (cam.position.x - (cam.viewportWidth/2)))
        {
            groundPos1.add(ground.getWidth() * 2, 0);
        }

        if(groundPos2.x + ground.getWidth() < (cam.position.x - (cam.viewportWidth/2)))
        {
            groundPos2.add(ground.getWidth() * 2,0);
        }

    }


}
