package com.bugshunter;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.bugshunter.Game.Game;
import com.bugshunter.Game.TestGame;
import com.bugshunter.HighScore.HighScoreManager;
import com.bugshunter.UI.MainMenu;

public class MainView extends View {

    private int mData;

    private Context c;
    private Screen screen;
    private HighScoreManager highScoreManager;

    private Paint mPaint;

    public MainView(Context c, AttributeSet attrs) {
        super(c, attrs);
        this.c = c;

        // and we set a new Paint with the desired attributes
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(4f);

        highScoreManager = new HighScoreManager(c);
        //highScoreManager.readData();

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                screen.updateMechanics();
                invalidate();//Repaint
                handler.postDelayed(this, 100);

            }
        });

        screen = new MainMenu(c, this, highScoreManager);//makeNewMainMenu();
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
        screen = new TestGame(c, this);
    }
    public void makeNewMainMenu(){
        if(screen instanceof Game){
            highScoreManager.parseNewScore("name_string", ((Game)screen).getScore());
        }

        screen = new MainMenu(c, this, highScoreManager);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        screen.draw(canvas);
        canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(),mPaint);
    }

}
