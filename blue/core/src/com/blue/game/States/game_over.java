package com.blue.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.blue.game.blue;

/**
 * Created by user on 12/17/2016.
 */

public class game_over extends States {
    private Texture bg;
    private Texture go;
    private BitmapFont Score;
    private static int score;

    public game_over(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, blue.WIDTH/2,blue.HEIGHT/2);
        bg = new Texture("background.png");
        go = new Texture("gameover.png");
        Score = new BitmapFont();
        score = PlayState.x;

    }

    @Override
    public void dispose() {
        bg.dispose();
        go.dispose();
        Score.dispose();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, 0, 0);
        sb.draw(go, cam.position.x - go.getWidth() / 2, cam.position.y - go.getHeight() / 2);
        Score.draw(sb, "Score: " + this.score, cam.position.x - go.getWidth() / 2, cam.position.y - go.getHeight() / 2 + go.getHeight());
        sb.end();
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
        }
    }
}
