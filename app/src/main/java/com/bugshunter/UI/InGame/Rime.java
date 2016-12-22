package com.bugshunter.UI.InGame;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.bugshunter.Game.Actors.Actor;
import com.bugshunter.Game.Game;

public class Rime {

    Actor me;
    Paint mPaint;
    RectF maneuverability;


    private float touchDownX;
    private float touchDownY;
    private float touchMoveX;
    private float touchMoveY;

    public Rime(Actor me){
        this.me = me;

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(4f);

        maneuverability = new RectF();

    }

    public void setTouchInput(float touchDownX, float touchDownY, float touchMoveX, float touchMoveY){
        this.touchDownX=touchDownX;
        this.touchDownY=touchDownY;
        this.touchMoveX=touchMoveX;
        this.touchMoveY=touchMoveY;

        if(me.isNewAngleValid((int) Game.calculateNewAngle(touchDownX,touchDownY,touchMoveX,touchMoveY))){
            mPaint.setColor(Color.GREEN);
        }else{
            mPaint.setColor(Color.RED);
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawLine(touchDownX,touchDownY,touchMoveX,touchMoveY,mPaint);
        maneuverability.set(touchDownX-50,touchDownY-50, touchDownX+50, touchDownY+50);
        canvas.drawArc(maneuverability, me.getAngle()-120, 240, true, mPaint);
    }


}
