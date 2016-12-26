package com.bugshunter.UI;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

import com.bugshunter.HighScore.HighScoreManager;
import com.bugshunter.MainView;
import com.bugshunter.R;
import com.bugshunter.Screen;
import com.bugshunter.UI.Pages.Help;
import com.bugshunter.UI.Pages.HighScore;
import com.bugshunter.UI.Pages.MainPage;
import com.bugshunter.UI.Pages.Page;
import com.bugshunter.UI.Pages.PageListener;

public class MainMenu implements Screen,PageListener{



    private int cWidth;
    private int cHeight;

    protected Bitmap background;
    private Page page;
    private MainView main;
    private HighScoreManager highScoreManager;
    private Context c;
    private int curentPage;

    public MainMenu(Context c, MainView main, HighScoreManager highScoreManager) {
        this.c = c;
        this.main = main;
        this.highScoreManager = highScoreManager;

        page = new MainPage(c, this);
        background = BitmapFactory.decodeResource(c.getResources(), R.drawable.code_2);
    }


    @Override
    public void pause() {}

    @Override
    public void touch(MotionEvent event) {
        page.touch(event);
    }

    @Override
    public void updateMechanics() {

    }

    @Override
    public void loadPage(int loadPage) {
        curentPage=loadPage;
        switch (loadPage){
            case 0:
                main.makeNewGame();
                System.out.println("New Game Starts Here.");
                break;
            case 1:
                page = new MainPage(c, this);
                break;
            case 2:
                page = new HighScore(c, this, highScoreManager);
                break;
            case 3:
                page = new Help(c, this);
                break;
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(background,0,0,null);
        page.draw(canvas);

    }


}
