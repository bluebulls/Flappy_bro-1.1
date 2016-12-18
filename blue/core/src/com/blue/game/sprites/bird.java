package com.blue.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by user on 12/15/2016.
 */

public class bird {
    private static final int Gravity = -10;
    public int MOVEMENT = 120;
    public int InitialMovement = 120;
    private static final int JUMP_VEL = 400;
    private Vector3 position;
    private Vector3 velocity;
    public Rectangle Bird_Collider;
    public animation bird;



    public bird(int x, int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        bird = new animation();
        Bird_Collider = new Rectangle(x,y,bird.getFrame().getWidth(),bird.getFrame().getHeight());
    }

    public Rectangle getBird_Collider() {
        return Bird_Collider;
    }

    public void update(float dt){
        bird.update(dt);
        if(position.y>0){
        velocity.add(0,Gravity,0);}
        velocity.scl(dt);
        position.add(MOVEMENT * dt,velocity.y,0);
        if(position.y<0){
            position.y=0;
        }
        velocity.scl(1/dt);
        Bird_Collider.setPosition(position.x,position.y);

    }

    public void dispose(){
        bird.dispose();
    }

    public void jump(){
        velocity.add(JUMP_VEL);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getBird() {
        return bird.getFrame();
    }
}
