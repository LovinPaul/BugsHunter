package com.bugshunter.UI.Pages;


import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import com.bugshunter.UI.UIComp.Button;

import java.util.ArrayList;

public class MainPage implements Page{

    private ArrayList<Button> buttons;
    private Button newButton;

    private PageListener pageListener;

    public MainPage(Context c, PageListener pageListener) {

        this.pageListener = pageListener;

        buttons = new ArrayList<>();

        newButton = new Button(c,(byte)0);
        buttons.add(newButton);

        newButton = new Button(c,(byte)1);
        buttons.add(newButton);

        newButton = new Button(c,(byte)2);
        buttons.add(newButton);
    }


    @Override
    public void touch(MotionEvent event){

        if(event.getAction() == MotionEvent.ACTION_UP){

            for(Button button : buttons){

                if(button.contains(event.getX(), event.getY())){
                    switch (button.getID()){
                        case 0:
                            pageListener.loadPage(0);
                            break;
                        case 1:
                            pageListener.loadPage(2);
                            break;
                        case 2:
                            pageListener.loadPage(3);
                            break;
                    }
                }

            }
        }

    }


    @Override
    public void draw(Canvas canvas) {

        int i=0;
        int x=(canvas.getWidth()/2)-(buttons.get(0).getWidth()/2);
        int y;
        for(Button button : buttons){

            y=(canvas.getHeight()/2)+(100*(i-buttons.size()/2));
            button.setX(x);
            button.setY(y);
            button.draw(canvas);
            i++;
        }

    }

}
