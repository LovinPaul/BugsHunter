package com.bugshunter.Game.Actors.Snake;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.bugshunter.Game.Actors.Actor;

import java.util.ArrayList;

public class SnakeActor extends Actor {


    private int lenght=3;
    private int thickness=15;
    private ArrayList<BodyPart> snakeBodyParts;



    public SnakeActor(float x, float y){

        this.x = x;
        this.y = y;

        agility=thickness;
        maneuverability=120;

        snakeBodyParts = new ArrayList<>();

        for (int i=0; i<lenght; i++){
            snakeBodyParts.add(new BodyPart(x, y+(i*thickness)));//
        }

    }

    @Override
    public void setAngle(int angle) {
        if(isNewAngleValid(angle)){
            this.angle=angle;
        }
    }

    public void addBodyPart(){
        snakeBodyParts.add(new BodyPart(x, y));
        snakeBodyParts.add(new BodyPart(x, y));
        snakeBodyParts.add(new BodyPart(x, y));
        snakeBodyParts.add(new BodyPart(x, y));
    }
    public boolean headContains(float x, float y){

        return snakeBodyParts.get(0).contains(x,y);

    }

    public void moveForward(){
        x = (float) (x - agility*Math.cos(Math.toRadians(angle-180)));
        y = (float) (y - agility*Math.sin(Math.toRadians(angle-180)));

        if(x<0){
            x=canvasWidth;
        }else if(x>canvasWidth){
            x=0;
        }
        if(y<0){
            y=canvasHeight;
        }else if(y>canvasHeight){
            y=0;
        }

        for(int i=snakeBodyParts.size()-1; i>0; i--){
            snakeBodyParts.get(i).x = snakeBodyParts.get(i-1).x;
            snakeBodyParts.get(i).y = snakeBodyParts.get(i-1).y;
        }

        snakeBodyParts.get(0).x = x;
        snakeBodyParts.get(0).y = y;
    }

    public void checkForSelfColision(){
        for(int i=2; i<snakeBodyParts.size(); i++){
            if(snakeBodyParts.get(0).contains(snakeBodyParts.get(i).x,snakeBodyParts.get(i).y)){
                isAlive=false;
            }
        }
    }

    public void draw(Canvas canvas){
        super.draw(canvas);
        for (BodyPart part : snakeBodyParts){
            part.draw(canvas);
        }
    }

    class BodyPart{

        float x;
        float y;

        private Paint mPaint;
        private int bodyThickness;

        public BodyPart(float x, float y){
            this.x = x;
            this.y = y;
            bodyThickness = thickness;

            // and we set a new Paint with the desired attributes
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setColor(Color.rgb((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            mPaint.setStrokeJoin(Paint.Join.ROUND);
            mPaint.setStrokeWidth(4f);

        }

        public boolean contains(float x, float y){
            float dist = (float)(Math.abs(this.x - x) + Math.abs(this.y - y));
            if(dist<bodyThickness){
                return true;
            }else{
                return false;
            }
        }

        public void draw(Canvas canvas){
            canvas.drawCircle(x,y,thickness,mPaint);
        }

    }

}
