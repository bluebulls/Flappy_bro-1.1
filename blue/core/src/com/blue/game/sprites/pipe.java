package com.blue.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by user on 12/15/2016.
 */

public class pipe {
    public static final int PIPE_WIDTH= 65;

    private static final int FLUCTUATE = 150;
    private static final int TUBE_GAP = 135;
    private static final int LOWEST_OPENING = 100;

    private Texture TopPipe,BottomPipe;
    private Rectangle TopBound,BotBound;
    private Vector2 PosTop,PosBot;
    private  Random ran;

    public pipe(float x){
        TopPipe = new Texture("TopPipe.png");
        BottomPipe = new Texture("BottomPipe.png");
        ran = new Random();

        PosTop = new Vector2(x,ran.nextInt(FLUCTUATE) + TUBE_GAP + LOWEST_OPENING);
        PosBot = new Vector2(x,PosTop.y - TUBE_GAP - BottomPipe.getHeight());

        TopBound = new Rectangle(PosTop.x,PosTop.y,TopPipe.getWidth(),TopPipe.getHeight());
        BotBound = new Rectangle(PosBot.x,PosBot.y,BottomPipe.getWidth(),BottomPipe.getHeight());

    }

    public void reposition(float x){
        PosTop.set(x,ran.nextInt(FLUCTUATE) + TUBE_GAP + LOWEST_OPENING);
        PosBot.set(x,PosTop.y - TUBE_GAP - BottomPipe.getHeight());

        TopBound.setPosition(PosTop.x,PosTop.y);
        BotBound.setPosition(PosBot.x,PosBot.y);
    }

    public boolean collision(Rectangle Player)
    {
        return(Player.overlaps(TopBound) || Player.overlaps(BotBound));
    }

    public void dispose()
    {
        TopPipe.dispose();
        BottomPipe.dispose();
    }

    public Texture getTopPipe() {
        return TopPipe;
    }

    public Texture getBottomPipe() {
        return BottomPipe;
    }

    public Vector2 getPosTop() {
        return PosTop;
    }

    public Vector2 getPosBot() {
        return PosBot;
    }
}
