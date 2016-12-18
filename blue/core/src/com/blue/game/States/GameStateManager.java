package com.blue.game.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.blue.game.States.States;

import java.util.Stack;


/**
 * Created by user on 12/14/2016.
 */

public class GameStateManager {
    private Stack<States> state;

    public GameStateManager(){
        state = new Stack<States>();
    }

    public void push(States states){
        state.push(states);
    }

    public void pop(){
        state.pop().dispose();
    }

    public void set(States states){
        state.pop().dispose();
        state.push(states);
    }

    public void update(float dt){
        state.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        state.peek().render(sb);
    }
}
