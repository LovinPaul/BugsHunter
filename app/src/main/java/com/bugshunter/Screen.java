package com.bugshunter;


import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;

public interface Screen {


    public void pause();

    public void touch(MotionEvent event);

    public void updateMechanics();

    public void draw(Canvas canvas);


}
