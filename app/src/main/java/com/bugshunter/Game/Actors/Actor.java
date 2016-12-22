package com.bugshunter.Game.Actors;

import android.graphics.Canvas;

public abstract class Actor {

    protected float x;
    protected float y;
    protected int angle=90;

    protected int canvasWidth;
    protected int canvasHeight;

    protected float newX;
    protected float newY;

    protected float agility;
    protected int maneuverability;

    public void setAngle(int angle){
        if(angle>=0 && angle<360){
            this.angle=angle;
        }
    }

    public boolean isNewAngleValid(int angle){
        if(angle>=0 && angle<360){
            int phi = Math.abs(this.angle-angle);
            int newAngle = phi > 180 ? 360 - phi : phi;

            if(newAngle<maneuverability){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public float getX(){
        return  x;
    }
    public float getY(){
        return  y;
    }
    public int getAngle(){
        return angle;
    }

    public void moveForward(){
//        newX = (float) (x - agility*Math.cos(Math.toRadians(angle+90)));
//        newY = (float) (y - agility*Math.sin(Math.toRadians(angle+90)));
    }


    public void draw(Canvas canvas){
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

    }

}
