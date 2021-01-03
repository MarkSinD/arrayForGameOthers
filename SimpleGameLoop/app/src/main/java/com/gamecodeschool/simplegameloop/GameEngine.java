package com.gamecodeschool.simplegameloop;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class GameEngine extends SurfaceView
        implements Runnable, EngineController{


    private Thread mThread = null;
    // Изменить на private
    public static long mFPS = 0;
    public static long mFPSAverageValue = 0;
    public static long mCurrentTime = 0;
    public static long mLoopTime = 0;
    public static long mLoopTimeAverageValue = 0;
    public static long i = 1;

    private GameState mGameState;
    Renderer mRenderer;




    public GameEngine(Context context, Point size){
        super(context);
        mGameState = new GameState(this, context);
        mRenderer = new Renderer(this, size);
    }



    @Override
    public void run() {
        while (mGameState.getThreadRunning()){
            long frameStartTime = System.currentTimeMillis();

            if(!mGameState.getPaused()){
                // Перемещаем
            }

            mRenderer.draw(mGameState);

            long timeThisFrame = System.currentTimeMillis() - frameStartTime;

            if( timeThisFrame >= 1){
                final int MILLIS_IN_SECOND = 1000;


                mCurrentTime = frameStartTime;
                mLoopTime = System.currentTimeMillis() - frameStartTime;
                mFPS = MILLIS_IN_SECOND / timeThisFrame;
                mFPSAverageValue += mFPS;
                mLoopTimeAverageValue += mLoopTime;
                i++;
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
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

    }
}
