package com.gamecodeschool.simplegameloop;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class GameEngine extends SurfaceView
        implements Runnable, EngineController{


    private ArrowSpec mArrowSpec;
    private Thread mThread = null;
    public static long mFPS = 0;

    private GameState mGameState;
    Renderer mRenderer;
    PhysicsEngine mPhysicsEngine;




    int x;
    int y;
    int StartX;
    int StartY;
    int FinishX;
    int FinishY;
    int DelX;
    int DelY;
    double Angle;




    public GameEngine(Context context, Point size){
        super(context);
        mGameState = new GameState(this, context);
        mArrowSpec = new ArrowSpec(context, size);
        mRenderer = new Renderer(this, size, context, mArrowSpec);
        mPhysicsEngine = new PhysicsEngine(mArrowSpec);
    }



    @Override
    public void run() {

        while (mGameState.getThreadRunning()){
            long frameStartTime = System.currentTimeMillis();







            PhysicsEngine.update();

            mRenderer.draw(mGameState);






            long timeThisFrame = System.currentTimeMillis() - frameStartTime;



            if( timeThisFrame >= 1){
                final int MILLIS_IN_SECOND = 1000;
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        int i = event.getActionIndex();
        x = (int) event.getX(i);
        y = (int) event.getY(i);


        switch (event.getAction() & MotionEvent.ACTION_MASK){

            case MotionEvent.ACTION_UP:
                FinishX = x;
                FinishY = y;
                DelX = Math.abs(FinishX - StartX);
                DelY = Math.abs(FinishY - StartY);
                if(DelY == 0 || DelX == 0)
                    Angle = 90;
                else
                    Angle = Math.atan(DelX/DelY) * 180/ Math.PI;

                Log.e("Поднял. Angle = ", " " + Angle);
                Log.e("StartX = ", " " + StartX);
                Log.e("StartY = ", " " + StartY);
                Log.e("FinishX = ", " " + FinishX);
                Log.e("FinishY = ", " " + FinishY);
                Log.e("DelX = ", " " + DelX);
                Log.e("DelY = ", " " + DelY);
                spawnArrow(new Point(FinishX, FinishY));
                break;


            case MotionEvent.ACTION_DOWN:
                Log.e("Опустил", " ");
                StartX = x;
                StartY = y;
                break;


        }
        return true;
    }

    public void stopThread(){

        mGameState.stopEverything();
        mGameState.stopThread();

        try {
            mThread.join();
        } catch (InterruptedException e){
            Log.e("Exception ", "stopThread()" + e.getMessage());
        }

    }

    public void startThread(){
        mGameState.startThread();
        mThread = new Thread(this);
        mThread.start();

    }


    @Override
    public void startNewLevel() {
        //
    }

    public void spawnArrow(Point startPoint){
        mArrowSpec.spawn(startPoint);
    }
}
