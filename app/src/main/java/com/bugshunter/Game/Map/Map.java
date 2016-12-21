package com.bugshunter.Game.Map;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public abstract class Map {

    protected Bitmap background;


    public void draw(Canvas canvas){
            canvas.drawBitmap(background,0,0,null);
    }

}
