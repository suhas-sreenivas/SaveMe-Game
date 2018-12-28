package com.suhas.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.suhas.game.FlappyDemo;

/**
 * Created by Suhas on 2/8/2016.
 */
public class EndState extends State{

    Texture endBack,gameOver;

    public EndState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        this.endBack = endBack;
        endBack = new Texture("bg.png");
        gameOver = new Texture("playbtn1.png");
    }

    @Override
    public void dispose() {

    }

    @Override
    public void HandleInput() {
        if(Gdx.input.justTouched()){
            PlayState.score = 0;
            gsm.set(new PlayState(gsm));
        }

    }

    @Override
    public void update(float dt) {
        HandleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(endBack, 0, 0,cam.viewportWidth,cam.viewportHeight);
        sb.draw(gameOver,cam.position.x - gameOver.getWidth() / 2,cam.position.y);
        sb.end();
    }
}
