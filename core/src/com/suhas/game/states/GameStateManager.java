package com.suhas.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Suhas on 1/25/2016.
 */
public class GameStateManager {

    private java.util.Stack<State> states;


    public GameStateManager(){
        states = new java.util.Stack<State>();
    }


    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop().dispose();
    }

    public void set(State state){
        states.pop().dispose();
        states.push(state);
    }

    public void update(float dt){
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }


}
