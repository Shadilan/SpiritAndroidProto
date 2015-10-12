package com.coe.spiritandroidproto;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.coe.spiritandroidproto.GameObjects.Player;
import com.coe.spiritandroidproto.GameObjects.World;
import com.coe.spiritandroidproto.utility.GPSInfo;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 11.10.15.
 */
public class myView extends View {
    private Timer timerRedraw;
    private Timer timerTick;
    private Handler handler;
    private World world;
    private Player player;
    private Paint myPaint;
    private GPSInfo GPSData;
    private myView self;
    private void init(){

        world=new World(1000,1000);
        int X=GPSData.GetLat();
        int Y=GPSData.GetLng();
        handler=new Handler();
        player=new Player(X,Y);
        world.setCenterX(X);
        world.setCenterY(Y);
        world.addObject(player);
        world.Tick();
        myPaint = new Paint();
        self=this;
        timerRedraw=new Timer("Redraw");
        redrawTimerR();
        timerTick=new Timer("Tick");
        tickTimerR();
    };
    private void redrawTimerR(){
        timerRedraw.schedule(new TimerTask(){
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        self.invalidate();
                        redrawTimerR();
                        //Log.d("TestPRJ","TimerDraw");
                    }
                });
            }
        }
                ,100);
    }
    private void tickTimerR(){
        timerTick.schedule(new TimerTask(){
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        GPSData.RequestUpdate();
                        world.setCenterX(GPSData.GetLat());
                        world.setCenterY(GPSData.GetLng());
                        player.SetX(GPSData.GetLat());
                        player.SetY(GPSData.GetLng());
                        world.Tick();
                        Log.d("TestPRJ","TimerTick");
                        tickTimerR();
                    }
                });
            }
        }
                ,1000);
    }
    public myView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        init();
    }
    public myView(Context context,GPSInfo gpsInfo) {
        super(context);
        GPSData=gpsInfo;
        // TODO Auto-generated constructor stub
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        int ratio=this.getHeight()/this.getWidth();
        int width=this.getWidth();
        int height=this.getHeight();
        canvas.drawBitmap(world.getImage(), new Rect(500-width/2,500-height/2,500+width/2,500+height/2),new Rect(0,0,this.getWidth(),this.getHeight()), myPaint);
        myPaint.setColor(Color.BLUE);
        canvas.drawText("Lat:"+GPSData.GetLat(),0,10,myPaint);
        canvas.drawText("Lng:"+GPSData.GetLng(),0,20,myPaint);
    }

}
