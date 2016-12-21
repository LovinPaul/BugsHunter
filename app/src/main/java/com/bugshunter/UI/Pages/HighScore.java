package com.bugshunter.UI.Pages;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;

public class HighScore implements Page{

    private PageListener pageListener;

    public HighScore(Context c, PageListener pageListener) {

        this.pageListener = pageListener;
    }

    @Override
    public void touch(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP){
            pageListener.loadPage(1);
        }
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
