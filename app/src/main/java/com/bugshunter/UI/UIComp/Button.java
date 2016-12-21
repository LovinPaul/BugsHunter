package com.bugshunter.UI.UIComp;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.bugshunter.R;

public class Button extends Comp{

    private Bitmap normalButton;
    //private Bitmap hoverButton;

    private Paint mPaint;

    private byte id;

    public Button(Context c, byte id){
        super(c);
        this.id=id;

        // and we set a new Paint with the desired attributes
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(4f);

        switch (id){
            case 0:
                normalButton = BitmapFactory.decodeResource(c.getResources(), R.drawable.new_game);
                break;
            case 1:
                normalButton = BitmapFactory.decodeResource(c.getResources(), R.drawable.high_score);
                break;
            case 2:
                normalButton = BitmapFactory.decodeResource(c.getResources(), R.drawable.help);
                break;
        }


        width = normalButton.getWidth();
        height = normalButton.getHeight();
    }


    public byte getID(){
        return id;
    }

    public void draw(Canvas canvas){
        if(isVisible){
            canvas.drawBitmap(normalButton,x,y,null);
            canvas.drawRect(x,y,x+width,y+height,mPaint);
        }
    }

}
