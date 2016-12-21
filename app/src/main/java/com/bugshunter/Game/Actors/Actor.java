package com.bugshunter.Game.Actors;

import android.graphics.Canvas;

public abstract class Actor {

    protected float x;
    protected float y;
    protected int angle=90;

    protected float newX;
    protected float newY;

    protected float agility;

    public void setAngle(int angle){
        if(angle>=0 && angle<360){
            this.angle=angle;
        }
    }

    public float getX(){
        return  x;
    }
    public float getY(){
        return  y;
    }
    public void moveForward(){
//        newX = (float) (x - agility*Math.cos(Math.toRadians(angle+90)));
//        newY = (float) (y - agility*Math.sin(Math.toRadians(angle+90)));
    }


    public void draw(Canvas canvas){

    }

}
