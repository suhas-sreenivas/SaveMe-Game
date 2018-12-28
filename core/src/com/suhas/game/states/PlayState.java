package com.suhas.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.suhas.game.FlappyDemo;
import com.suhas.game.sprites.Bird;
import com.suhas.game.sprites.Tube;

/**
 * Created by Suhas on 1/25/2016.
 */
public class PlayState extends State {
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET=-30;


   public static int score;
    private String yourScoreName = "SCORE :" +score;
    BitmapFont yourBitmapFontName = new BitmapFont(Gdx.files.internal("data/o.fnt"),
            Gdx.files.internal("data/o.png"), false);

   private Bird bird;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPos1,groundPos2;

    private Array<Tube> tubes;
    public PlayState(GameStateManager gsm) {
        super(gsm);
        score = 0;
        bird = new Bird(50,200);
        cam.setToOrtho(false, FlappyDemo.WIDTH/2,FlappyDemo.HEIGHT/2);
        bg = new Texture("bg.png");
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth/2,GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth/2) + ground.getWidth(),GROUND_Y_OFFSET);

        tubes = new Array<Tube>();

        for(int i = 1;i <=TUBE_COUNT;i++){
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    protected void HandleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
        }

    }

    @Override
    public void update(float dt) {
        HandleInput();
        updateGround();
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80;
        for(int i = 0; i < tubes.size; i++){
            Tube tube = tubes.get(i);
            if(cam.position.x - cam.viewportWidth/2 > tube.getPosTopTube().x + tube.getTopTube().getWidth())
            { tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH +TUBE_SPACING) * TUBE_COUNT));
            score++;
                System.out.println(score);
                yourScoreName = "SCORE :" + score;
                if(score == 2) gsm.set(new level2(gsm));
            }



            if(tube.collides(bird.getBounds()))
                gsm.set(new EndState(gsm));
        }
        if(bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET)
            gsm.set(new EndState(gsm));
        cam.update();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - cam.viewportWidth / 2, 0, cam.viewportWidth, cam.viewportHeight);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        for(Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground,groundPos2.x,groundPos2.y);
        yourBitmapFontName.setColor(0.0f, 0.0f, 0.0f, 1.0f);
        yourBitmapFontName.draw(sb, yourScoreName, cam.position.x - cam.viewportWidth/2, ground.getHeight() - 50);
        sb.end();

    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        for(Tube tube : tubes)
            tube.dispose();
        ground.dispose();
        System.out.println("Play State Disposed");
    }
    private void updateGround(){
        if(cam.position.x - cam.viewportWidth/2 > groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth()*2,0);

        if(cam.position.x - cam.viewportWidth/2 > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth()*2,0);
    }
}
