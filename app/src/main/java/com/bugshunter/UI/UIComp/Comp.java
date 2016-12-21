package com.bugshunter.UI.UIComp;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

public abstract class Comp {

    protected int x;
    protected int y;

    protected int width;
    protected int height;

    protected boolean isVisible=true;

    public Comp(Context c){

    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public void setX(int x){
        this.x=x;
    }
    public void setY(int y){
        this.y=y;
    }
    public void setIsVisible(boolean isVisible){
        this.isVisible = isVisible;
    }


    public boolean isVisible(){
        return isVisible;
    }

    public static Bitmap rotateBitmap(Bitmap source, float angle){
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    public boolean contains(float x, float y){
        return isVisible?(x>this.x && x<this.x+width)&&(y>this.y && y<this.y+height):false;
    }

    public void draw(Canvas canvas){
    }

}
