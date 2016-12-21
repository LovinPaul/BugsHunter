package com.bugshunter.UI.Pages;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by plovin on 12/20/2016.
 */

public interface Page {


    public abstract void touch(MotionEvent event);
    public abstract void draw(Canvas canvas);
}
