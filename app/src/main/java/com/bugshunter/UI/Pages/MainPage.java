package com.bugshunter.UI.Pages;


import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import com.bugshunter.UI.UIComp.Button;

import java.util.ArrayList;

public class MainPage implements Page{

    private ArrayList<Button> buttons;

    private Button newGameButton;
    private Button highScore;
    private Button helpButton;
    private Button quitButton;

    private PageListener pageListener;

    public MainPage(Context c, PageListener pageListener) {

        this.pageListener = pageListener;

        buttons = new ArrayList<>();

        newGameButton = new Button(c,0);
        newGameButton.setX(50);
        newGameButton.setY(50);
        buttons.add(newGameButton);

        highScore = new Button(c,1);
        highScore.setX(50);
        highScore.setY(114);
        buttons.add(highScore);

        helpButton = new Button(c,2);
        helpButton.setX(50);
        helpButton.setY(178);
        buttons.add(helpButton);

        quitButton = new Button(c,3);
        quitButton.setX(50);
        quitButton.setY(242);
        buttons.add(quitButton);
    }


    @Override
    public void touch(MotionEvent event){

        if(event.getAction() == MotionEvent.ACTION_UP){
            if(newGameButton.contains(event.getX(), event.getY())){
                //newGameButton.setIsVisible(false);
                pageListener.loadPage(0);
            }else if(highScore.contains(event.getX(), event.getY())) {
                //highScore.setIsVisible(false);
                pageListener.loadPage(2);
            }else if(helpButton.contains(event.getX(), event.getY())) {
                //helpButton.setIsVisible(false);
                pageListener.loadPage(3);
            }else if(quitButton.contains(event.getX(), event.getY())) {
                //quitButton.setIsVisible(false);
                pageListener.loadPage(-1);
            }
        }

    }


    @Override
    public void draw(Canvas canvas) {
//        newGameButton.draw(canvas);
//        highScore.draw(canvas);
//        helpButton.draw(canvas);
//        quitButton.draw(canvas);


        int i=0;
        int x=(canvas.getWidth()/2)-(buttons.get(0).getWidth()/2);
        int y;
        for(Button singleplayerButton : buttons){

            y=(canvas.getHeight()/2)+(100*(i-buttons.size()/2));
            singleplayerButton.setX(x);
            singleplayerButton.setY(y);
            singleplayerButton.draw(canvas);
            i++;
        }


    }

}
