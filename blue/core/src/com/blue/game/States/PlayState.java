package com.blue.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.blue.game.States.GameStateManager;
import com.blue.game.States.States;
import com.blue.game.blue;
import com.blue.game.sprites.bird;
import com.blue.game.sprites.pipe;


/**
 * Created by user on 12/15/2016.
 */


public class PlayState extends States {
    private static final int PIPE_SPACING = 140;
    private static final int PIPE_COUNT = 4;
    private static final int Ground_Height = -50;
    private static final int IncreaseInSpeed = 20;
    private bird Bird;
    private Texture bg;
    private Texture ground;
    private Vector2 groundpos1,groundpos2;
    private BitmapFont score;
    public static int x;
    private int Speed_change;
    private static Preferences temp = Gdx.app.getPreferences("com.blue.game");


    private Array<pipe> pipes;
    public PlayState(GameStateManager gsm) {
        super(gsm);
        Bird = new bird(30,200);
        cam.setToOrtho(false, blue.WIDTH/2,blue.HEIGHT/2);
        bg = new Texture("background.png");
        ground = new Texture("Ground.png");


        groundpos1 = new Vector2(cam.position.x-cam.viewportWidth/2,Ground_Height);
        groundpos2 = new Vector2((cam.position.x-cam.viewportWidth/2)+ground.getWidth(),Ground_Height);

        pipes = new Array<pipe>(PIPE_COUNT);
        for(int i=1;i<=PIPE_COUNT;i++)
        {
            pipes.add(new pipe(i*(PIPE_SPACING + pipe.PIPE_WIDTH)));
        }
        score = new BitmapFont();
        score.setColor(Color.FOREST);
        x = 0;
        Speed_change=0;



    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            Bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        Bird.update(dt);
        cam.position.x = Bird.getPosition().x + 80;

        for(int i =0;i < pipes.size ; i++)
        {
            pipe Pipe = pipes.get(i);
            if((cam.position.x-(cam.viewportWidth/2))>Pipe.getPosTop().x+Pipe.getTopPipe().getWidth())
            {
                Pipe.reposition(Pipe.getPosTop().x+(PIPE_COUNT * (PIPE_SPACING + Pipe.PIPE_WIDTH)));
            }


        x = (int)(Bird.getPosition().x/(PIPE_SPACING + 65));
        Speed_change = x/5;
        Bird.MOVEMENT = Bird.InitialMovement + Speed_change * IncreaseInSpeed;

            if(Pipe.collision(Bird.Bird_Collider))
            {
                savedata(x);
                gsm.set(new game_over(gsm));
            }
        }



        cam.update();
        update_gnd();
        if(Bird.getPosition().y - (Bird.getBird().getHeight()) <= Ground_Height)
        {
            savedata(x);
            gsm.set(new game_over(gsm));
        }

        if(Bird.getPosition().y > cam.position.y + cam.viewportHeight/2)
        {
            savedata(x);
            gsm.set(new game_over(gsm));
        }


    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg,cam.position.x-(cam.viewportWidth/2),0);
        sb.draw(Bird.getBird(),Bird.getPosition().x,Bird.getPosition().y);


        score.draw(sb,"Score :  " + this.x,cam.position.x + cam.viewportWidth/2 - score.getScaleX() -80 , cam.position.y + cam.viewportHeight/2 - score.getScaleY()-20);
        for(pipe Pipe: pipes) {
            sb.draw(Pipe.getTopPipe(), Pipe.getPosTop().x, Pipe.getPosTop().y);
            sb.draw(Pipe.getBottomPipe(), Pipe.getPosBot().x, Pipe.getPosBot().y);
        }
        sb.draw(ground,groundpos1.x,groundpos1.y);
        sb.draw(ground,groundpos2.x,groundpos2.y);



        sb.end();

    }

    public void update_gnd(){
        if(cam.position.x-(cam.viewportWidth/2) > groundpos1.x + ground.getWidth() ){
            groundpos1.add(ground.getWidth()*2,0);
        }
        if(cam.position.x-(cam.viewportWidth/2) > groundpos2.x + ground.getWidth() ){
            groundpos2.add(ground.getWidth()*2,0);
        }
    }

    public void savedata(int x){
        if(x > temp.getInteger("HighScore,0") )
        temp.putInteger("HighScore", x);
    }

    public static int getData(){
        return temp.getInteger("HighScore") ;
    }


    @Override
    public void dispose() {
        bg.dispose();
        Bird.dispose();
        ground.dispose();
        score.dispose();
        for(pipe Pipe : pipes)
            Pipe.dispose();

        System.out.println("Play State Disposed");

    }
}
