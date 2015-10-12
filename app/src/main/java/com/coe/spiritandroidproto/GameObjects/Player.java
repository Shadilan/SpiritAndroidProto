package com.coe.spiritandroidproto.GameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

/**
 * Created by Administrator on 09.10.15.
 */
public class Player implements GameObject{

    int X;
    int Y;
    int Width=40;
    int Height=40;
    Bitmap image;
    int Life=0;
    int Energy=0;
    int Power=0;
    Paint mPaint;
    public Player(int X,int Y){
        this.X=X;
        this.Y=Y;
        image=Bitmap.createBitmap(40,40,Bitmap.Config.ARGB_8888);
        mPaint= new Paint();
        Canvas canvas=new Canvas(image);
        canvas.drawColor(Color.TRANSPARENT);
        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(20,20,20,mPaint);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(3);
        int k=40/6+1;
        canvas.drawLine(3*k,0*k,1*k,5*k,mPaint);
        canvas.drawLine(1*k,5*k,6*k,3*k,mPaint);
        canvas.drawLine(6*k,3*k,1*k,1*k,mPaint);
        canvas.drawLine(1*k,1*k,3*k,6*k,mPaint);
        canvas.drawLine(3*k,6*k,5*k,1*k,mPaint);
        canvas.drawLine(5*k,1*k,0*k,3*k,mPaint);
        canvas.drawLine(0*k,3*k,5*k,5*k,mPaint);
        canvas.drawLine(5*k,5*k,3*k,0*k,mPaint);
    }

    @Override
    public int GetX() {
        return X;
    }

    @Override
    public int GetY() {
        return Y;
    }

    @Override
    public void SetX(int X) {
        this.X=X;
    }

    @Override
    public void SetY(int Y) {
        this.Y=Y;
    }

    @Override
    public int GetWidth() {
        return 40;
    }

    @Override
    public int GetHeight() {
        return 40;
    }

    @Override
    public Bitmap getImage() {
        return image;
    }

    @Override
    public void Tick(GameWorld world) {

    }


}
