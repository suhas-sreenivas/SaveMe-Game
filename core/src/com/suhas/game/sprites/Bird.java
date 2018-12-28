package com.suhas.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Suhas on 1/25/2016.
 */
public class Bird {
    private static final int GRAVITY=-15;
    private static final int MOVEMENT = 100;

    private Vector3 position;
    private Vector3 velocity;
    private Texture bird;
    private Rectangle bounds;
    private Animation birdAnimation;
    private Texture texture;


    public Bird(int x,int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        texture = new Texture("birdanimation.png");
        bounds = new Rectangle(x,y,texture.getWidth() / 3,texture.getHeight());
        birdAnimation = new Animation(new TextureRegion(texture),3,0.5f);

    }

    public void update(float dt){
        birdAnimation.update(dt);
        if(position.y>0)
        velocity.add(0,GRAVITY,0);
        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y, 0);
        if(position.y<0)
            position.y=0;
        velocity.scl(1/dt);
        bounds.setPosition(position.x,position.y);

    }

    public TextureRegion getTexture() {
        return birdAnimation.getframe();
    }

    public Vector3 getPosition() {
        return position;
    }

    public void jump(){
        velocity.y = 250;
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose(){ texture.dispose(); }

}
