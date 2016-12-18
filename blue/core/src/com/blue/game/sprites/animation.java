package com.blue.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

/**
 * Created by user on 12/18/2016.
 */

public class animation {
    private float MaxFrameTime = 0.15f;
    private float CurrentFrameTime;
    private int frame;
    private Texture frame1,frame2,frame3,frame4;

    public animation (){
        frame1 = new Texture("frame-1.png");
        frame2 = new Texture("frame-2.png");
        frame3 = new Texture("frame-3.png");
        frame4 = new Texture("frame-4.png");
        frame = 0;
        CurrentFrameTime = 0;
    }

    public void update(float dt) {
        CurrentFrameTime += dt;
        if(CurrentFrameTime >= MaxFrameTime){
            frame++;
            CurrentFrameTime = 0;
        }

        if(frame >= 4){
            frame= 0;
        }
    }

    public Texture getFrame(){
        if(frame == 0)
            return frame1;
        else if(frame == 1)
            return frame2;
        else if(frame == 2)
            return frame3;
        else
            return frame4;
    }

    public void dispose(){
        frame1.dispose();
        frame2.dispose();
        frame3.dispose();
        frame4.dispose();
    }
}
