package com.sunny.game.states;

import java.util.Stack;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by sunny on 15-11-15.
 */
public class GameStateManager
{

    private Stack<State> states;

    public GameStateManager()
    {
        states = new Stack<State>();
    }

    public void push(State state)
    {
        states.push(state);
    }

    public void pop() {

        states.pop().dispose();
    }

    public void set(State state) {
        pop();
        states.push(state);
    }

    public void update(float dt)
    {
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb)
    {
        states.peek().render(sb);
    }


}