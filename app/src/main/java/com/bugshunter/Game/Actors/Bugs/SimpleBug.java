package com.bugshunter.Game.Actors.Bugs;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.bugshunter.R;

public class SimpleBug extends BugActor{

    private Bitmap img;

    public SimpleBug(Context c, float x, float y){
        this.x=x;
        this.y=y;
        img = BitmapFactory.decodeResource(c.getResources(), R.drawable.simple_bug);
    }

    public void draw(Canvas canvas){
            canvas.drawBitmap(img,x,y,null);
    }

}
