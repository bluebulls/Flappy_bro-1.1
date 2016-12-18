package com.blue.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.blue.game.blue;

/**
 * Created by user on 12/14/2016.
 */

public class MenuState extends States {
    private Texture bg;
    private Texture pb;
    private BitmapFont high;
    private Preferences pref = Gdx.app.getPreferences("com.blue.com");
    private int HighScore;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, blue.WIDTH/2,blue.HEIGHT/2);
        bg= new Texture("background.png");
        pb= new Texture("playbutton.png");
        high = new BitmapFont();
        HighScore = PlayState.getData();
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }


    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg,cam.position.x-(cam.viewportWidth/2),0);
        sb.draw(pb,cam.position.x-(pb.getWidth()/2),cam.position.y );
        high.draw(sb,"High Score: "+ this.HighScore  ,10,10);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        pb.dispose();
        high.dispose();
        System.out.println("Menu State disposed");
    }
}