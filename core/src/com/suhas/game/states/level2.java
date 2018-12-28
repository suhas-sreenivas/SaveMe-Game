package com.suhas.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.suhas.game.FlappyDemo;

/**
 * Created by Suhas on 1/25/2016.
 */
public class level2 extends State {
    private Texture background;
    private Texture playBtn;


    public level2(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        background = new Texture("bg.png");
        playBtn = new Texture("playbtnl2.png");
    }

    @Override
    public void HandleInput() {
        if(Gdx.input.justTouched()){
            if(Gdx.input.justTouched())
                if(Gdx.input.justTouched())
                    if(Gdx.input.justTouched())
            gsm.set(new PlayState2(gsm));
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
        sb.draw(background, 0, 0,cam.viewportWidth,cam.viewportHeight);
        sb.draw(playBtn,cam.position.x - playBtn.getWidth() / 2,cam.position.y);
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        System.out.println("Menu State Disposed");
    }

}
