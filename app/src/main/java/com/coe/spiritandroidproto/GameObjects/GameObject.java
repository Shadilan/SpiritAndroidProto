package com.coe.spiritandroidproto.GameObjects;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 09.10.15.
 */
public interface GameObject {
    public int GetX();
    public int GetY();
    public void SetX(int X);
    public void SetY(int Y);
    public int GetWidth();
    public int GetHeight();
    public Bitmap getImage();
    public void Tick(GameWorld world);
}
