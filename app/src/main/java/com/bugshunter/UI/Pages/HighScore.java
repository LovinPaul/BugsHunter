package com.bugshunter.UI.Pages;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.bugshunter.HighScore.HighScoreManager;

public class HighScore implements Page{

    Paint mPaint;
    private PageListener pageListener;
    private int Ypos=50;
    private int Xpos;
    private String[] values;

    public HighScore(Context c, PageListener pageListener, HighScoreManager highScoreManager) {

        this.pageListener = pageListener;
        values = highScoreManager.getData();


        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(1f);
        mPaint.setTextSize(50);
    }

    @Override
    public void touch(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP){
            pageListener.loadPage(1);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        for (int i=0; i<values.length; i++){
            canvas.drawText(values[i], Xpos, Ypos+(i*50), mPaint);
        }

        if(Xpos<canvas.getWidth()/4){
            Xpos+=50;
        }

    }
}
