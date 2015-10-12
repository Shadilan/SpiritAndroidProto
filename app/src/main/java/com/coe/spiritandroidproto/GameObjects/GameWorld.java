package com.coe.spiritandroidproto.GameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Administrator on 09.10.15.
 */
public interface GameWorld {
    public void Tick();
    public Bitmap getImage();
    public void setWidth(int width);
    public void setHeight(int height);
    public void setCenterX(int X);
    public void setCenterY(int Y);
    public int getWidth();
    public int getHeight();
    public int getCenterX();
    public int getCenterY();
    public void addObject(GameObject object);
    public void removeObject(GameObject object);
    public GameObject GetObject(int X,int Y);

}
