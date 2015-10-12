package com.coe.spiritandroidproto.GameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by Administrator on 09.10.15.
 */
public class World implements GameWorld {

    int X;
    int Y;
    int Width;
    int Height;
    ArrayList<GameObject> Objects;
    ArrayList<GameObject> ObjectsToAdd;
    ArrayList<GameObject> ObjectsToRemove;
    Paint mPaint;
    public World(int Width,int Height)
    {
        this.Width=Width;
        this.Height=Height;
        X=0;
        Y=0;
        Objects=new ArrayList<GameObject>();
        ObjectsToAdd=new ArrayList<GameObject>();
        ObjectsToRemove=new ArrayList<GameObject>();
        mPaint =new Paint();
    }
    @Override
    public void Tick() {
        Objects.removeAll(ObjectsToRemove);
        Objects.addAll(ObjectsToAdd);
        ObjectsToAdd.clear();
        ObjectsToRemove.clear();
        for (GameObject object:Objects){
            object.Tick(this);
        }
    }

    @Override
    public Bitmap getImage() {
        Bitmap result=Bitmap.createBitmap(Width,Height,Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(result);
        canvas.drawColor(Color.BLACK);
        mPaint.setColor(Color.CYAN);
        int CenterX=Width/2;
        int CenterY=Height/2;
        int X50=Math.round(X/100)*100;
        int Y50=Math.round(Y/100)*100;
        int smeshX=X50-X;
        int smeshY=Y50-Y;
        int W50=Width/200;
        int H50=Height/200;
        for (int i=0;i<W50;i++){
            canvas.drawLine(CenterX+smeshX-i*100,0,CenterX+smeshX-i*100,Height,mPaint);
            canvas.drawLine(CenterX+smeshX+i*100,0,CenterX+smeshX+i*100,Height,mPaint);
        }
        for (int i=0;i<H50;i++){
            canvas.drawLine(0,CenterY+smeshY-i*100,Width,CenterY+smeshY-i*100,mPaint);
            canvas.drawLine(0,CenterY+smeshY+i*100,Width,CenterY+smeshY+i*100,mPaint);
        }

        for (GameObject obj:Objects){
            canvas.drawBitmap(obj.getImage(),obj.GetX()-obj.GetWidth()/2-X+500,obj.GetY()-obj.GetHeight()/2-Y+500,mPaint);
        }


        return result;
    }

    @Override
    public void setWidth(int width) {
        Width=width;
    }

    @Override
    public void setHeight(int height) {
        Height=height;
    }

    @Override
    public void setCenterX(int X) {
        this.X=X;
    }

    @Override
    public void setCenterY(int Y) {
        this.Y=Y;
    }

    @Override
    public int getWidth() {
        return Width;
    }

    @Override
    public int getHeight() {
        return Height;
    }

    @Override
    public int getCenterX() {
        return X;
    }

    @Override
    public int getCenterY() {
        return Y;
    }

    @Override
    public void addObject(GameObject object) {
        ObjectsToAdd.add(object);
    }

    @Override
    public void removeObject(GameObject object) {
        ObjectsToRemove.add(object);
    }

    @Override
    public GameObject GetObject(int X, int Y) {
        int minRange=1000;
        GameObject result=null;
        for (GameObject obj:Objects){
            int range=(int)Math.sqrt((X-obj.GetX())*(X-obj.GetX())+(Y-obj.GetY())*(Y-obj.GetY()));
            if (range<minRange){
                result=obj;
                minRange=range;
            }
        }
        return result;
    }
}
