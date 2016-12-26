package com.bugshunter.Game.Actors.Bugs;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.bugshunter.R;

public class SimpleBug extends BugActor{

    //private Paint mPaint;
    private Bitmap img;
    //private int thickness;

    public SimpleBug(Context c, float x, float y){
        this.x=x;
        this.y=y;
        //thickness=10;
        bugPoints=13;
        img = BitmapFactory.decodeResource(c.getResources(), R.drawable.simple_bug);

//        mPaint = new Paint();
//        mPaint.setAntiAlias(true);
//        mPaint.setColor(Color.rgb((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeJoin(Paint.Join.ROUND);
//        mPaint.setStrokeWidth(4f);

    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(img,x-img.getWidth()/2,y-img.getHeight()/2,null);
//        canvas.drawCircle(x,y,thickness, mPaint);
//        canvas.drawPoint(x,y, mPaint);
    }

}
