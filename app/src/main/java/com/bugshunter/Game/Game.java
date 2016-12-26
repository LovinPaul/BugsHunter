package com.bugshunter.Game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;

import com.bugshunter.Game.Actors.Actor;
import com.bugshunter.Game.Actors.Bugs.BugActor;
import com.bugshunter.Game.Actors.Bugs.SimpleBug;
import com.bugshunter.Game.Actors.Snake.SnakeActor;
import com.bugshunter.Game.Map.HTML1;
import com.bugshunter.Game.Map.Map;
import com.bugshunter.MainView;
import com.bugshunter.Screen;
import com.bugshunter.UI.InGame.Rime;
import com.bugshunter.UI.UIComp.Button;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Game implements Screen {

    private ArrayList<BugActor> bugActors;

    protected Button quit;
    private MainView main;

    protected SnakeActor me;
    protected Map map;
    protected Rime rimeUI;
    protected Context c;

    protected boolean pause;
    protected int nrMaxOfBugs = 1;
    protected int score;

    private float touchDownX;
    private float touchDownY;
    private float touchMoveX=-1;
    private float touchMoveY=-1;
    private float touchUpX;
    private float touchUpY;

    protected int width;
    protected int height;

    Paint mPaint;

    public static float calculateNewAngle(float x1, float y1, float x2, float y2){
        float angle=-1;
        if(Math.abs(x1-x2)>10 || Math.abs(y1-y2)>10) {
            angle = (float) Math.toDegrees(Math.atan2(y1 - y2, x1 - x2)) + 180;

            if (angle < 0) {
                angle += 360;
            }
        }
        return angle;
    }

    public Game(Context c, MainView mainView) {
        this.c = c;
        main = mainView;

        me = new SnakeActor(50,50);
        bugActors = new ArrayList<>();

        rimeUI = new Rime(me);
        map = new HTML1(c);

        quit = new Button(c,(byte)0);
        quit.setX(50);
        quit.setY(50);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(1f);
        mPaint.setTextSize(50);
    }

    public int getScore() {
        return score;
    }

    @Override
    public void pause() {
        pause=true;
    }

    @Override
    public void updateMechanics() {
        if(!pause && me.isAlive()){
            handleColisionWithBugs((SnakeActor) me);
            handleBugs();
            me.moveForward();
            for(Actor actor : bugActors){
                actor.moveForward();
            }
            ((SnakeActor) me).checkForSelfColision();


        }
    }

    @Override
    public void touch(MotionEvent event) {

        if(pause){pause=false;}
        if(quit.contains(event.getX(),event.getY())){
            main.makeNewMainMenu();
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
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

                touchMoveX=-1;
                touchMoveY=-1;
                break;
        }

    }

    protected void handleColisionWithBugs(SnakeActor snakeActor){

        for(BugActor actor : bugActors){
            if(snakeActor.headContains(actor.getX(), actor.getY())){
                actor.setIsAlive(false);
                snakeActor.addBodyParts(5);
                score += actor.getBugPoints();
                System.out.println("New Bug eaten. Body part added");
            }
        }

    }
    protected void handleBugs(){

        Iterator<BugActor> bugActorIterator = bugActors.iterator();
        while (bugActorIterator.hasNext()){
            BugActor bugActor = bugActorIterator.next();
            if (!bugActor.isAlive()){
                bugActorIterator.remove();
            }
        }

        if(bugActors.size()<nrMaxOfBugs){
            if(width>0 && height>0) {
                float x = (float) (Math.random() * width);
                float y = (float) (Math.random() * height);
                bugActors.add(new SimpleBug(c, x, y));
            }
        }

    }



    @Override
    public void draw(Canvas canvas) {
        width = canvas.getWidth();
        height = canvas.getHeight();

        map.draw(canvas);
        for (Actor actor : bugActors) {
            actor.draw(canvas);
        }

        me.draw(canvas);


        if (me.isAlive()) {
            if (touchMoveX > 0) {
                rimeUI.setTouchInput(touchDownX, touchDownY, touchMoveX, touchMoveY);
                rimeUI.draw(canvas);
            }
        }else{
            canvas.drawText("Game Over", width/2,height/2,mPaint);
            canvas.drawText("Game Over", width/2 -2,height/2 -2,mPaint);
        }

        canvas.drawText(score+"", width/2,50,mPaint);

        quit.draw(canvas);

    }

}
