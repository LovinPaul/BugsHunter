package com.bugshunter.Game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.bugshunter.Game.Actors.Actor;
import com.bugshunter.Game.Actors.Snake.SnakeActor;
import com.bugshunter.Game.Map.HTML1;
import com.bugshunter.Game.Map.Map;
import com.bugshunter.Screen;

import java.util.ArrayList;

public abstract class Game implements Screen {

    private ArrayList<Actor> actors;
    protected Actor me;
    protected Map map;

    protected boolean pause;

    private float touchDownX=-1;
    private float touchDownY=-1;
    private float touchMoveX=-1;
    private float touchMoveY=-1;
    private float touchUpX=-1;
    private float touchUpY=-1;


    Paint mPaint;

    public Game(Context c) {
        actors = new ArrayList<>();
        me = new SnakeActor(50,50);
        actors.add(me);

        map = new HTML1(c);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(4f);

    }

    @Override
    public void pause() {
        pause=true;
    }

    @Override
    public void updateMechanics() {
        if(!pause){
            for(Actor actor : actors){
                actor.moveForward();
            }
        }
    }

    @Override
    public void touch(MotionEvent event) {

        if(pause){pause=false;}
//        me.setAngle((int) calculateNewAngle(me.getX(), me.getY(),event.getX(),event.getY()));
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchUpX=-1;
                touchUpY=-1;
                touchUpX=event.getX();
                touchUpY=event.getY();
                touchDownX=event.getX();
                touchDownY=event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                touchMoveX=event.getX();
                touchMoveY=event.getY();
                break;
            case MotionEvent.ACTION_UP:
                touchUpX=event.getX();
                touchUpY=event.getY();
                me.setAngle((int) calculateNewAngle(touchDownX,touchDownY,touchUpX,touchUpY));

                touchDownX=-1;
                touchDownY=-1;
                touchMoveX=-1;
                touchMoveY=-1;
                touchUpX=-1;
                touchUpY=-1;

                break;
        }

    }

    protected float calculateNewAngle(float x1, float y1, float x2, float y2){
        float angle=-1;
        if(Math.abs(x1-x2)>10 || Math.abs(y1-y2)>10) {
            angle = (float) Math.toDegrees(Math.atan2(y1 - y2, x1 - x2)) - 90;

            if (angle < 0) {
                angle += 360;
            }
        }
        return angle;
    }

    @Override
    public void draw(Canvas canvas) {
        map.draw(canvas);

        for(Actor actor : actors){
            actor.draw(canvas);
        }


//        if(touchDownX>0 || touchDownY>0){
////            if(touchUpX>0){
////                //canvas.drawLine(touchDownX,touchDownY,touchUpX,touchUpY,mPaint);
////            }else
//            if(touchMoveX>0 || touchMoveY>0){
////                if(Math.abs(touchDownX-touchMoveX)>10 || Math.abs(touchDownY-touchMoveY)>10) {
//                    canvas.drawLine(touchDownX,touchDownY,touchMoveX,touchMoveY,mPaint);
////                }
//            }


//        }




    }

}