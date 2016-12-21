package com.bugshunter;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.bugshunter.Game.TestGame;
import com.bugshunter.UI.MainMenu;

public class Main extends View {

    private int mData;

    private Context c;
    private Screen screen;

    private Paint mPaint;

    public Main(Context c, AttributeSet attrs) {
        super(c, attrs);
        this.c = c;

        makeNewMainMenu();

        // and we set a new Paint with the desired attributes
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(4f);

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                screen.updateMechanics();
                //Repaint
                invalidate();
                handler.postDelayed(this, 100);

            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        screen.touch(event);
        return true;
    }

    public void pause(){
        screen.pause();
    }
    public void makeNewGame(){
        screen = new TestGame(c);
    }
    public void makeNewMainMenu(){
        screen = new MainMenu(c, this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        screen.draw(canvas);
        canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(),mPaint);
    }

}
