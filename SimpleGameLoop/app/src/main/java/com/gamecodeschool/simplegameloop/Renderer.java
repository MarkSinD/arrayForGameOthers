package com.gamecodeschool.simplegameloop;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Renderer {
    private Canvas mCanvas;
    private SurfaceHolder mSurfaceHolder;
    private Paint mPaint;


    Renderer(SurfaceView sh, Point screenSize){
        mSurfaceHolder = sh.getHolder();
        mPaint = new Paint();
    }

    void draw( GameState gs){
        if( mSurfaceHolder.getSurface().isValid()){
            mCanvas = mSurfaceHolder.lockCanvas();
            mCanvas.drawColor(Color.argb(255, 40,0,0));




            mPaint.setTextSize(50);
            mPaint.setColor(Color.argb(255, 255, 255, 255));


            mCanvas.drawText("FPS.", 50, 500, mPaint);
            mCanvas.drawText("FPS : " + GameEngine.mFPS, 100, 600, mPaint);
            mCanvas.drawText("Dynamics of the FPS  : " + GameEngine.mFPSAverageValue / GameEngine.i, 100, 700, mPaint);

            if(GameEngine.mLoopTimeAverageValue / GameEngine.i != 0)
                mCanvas.drawText("Average value of the FPS  : " + 1000/(GameEngine.mLoopTimeAverageValue / GameEngine.i), 100, 800, mPaint);


            mCanvas.drawText("Time.", 50, 1000, mPaint);
            mCanvas.drawText("Time : " + GameEngine.mCurrentTime, 100, 1100, mPaint);
            mCanvas.drawText("Time in which one cycle occurs. : " + GameEngine.mLoopTime, 100, 1200, mPaint);
            mCanvas.drawText("Average value of" , 100, 1300, mPaint);
            mCanvas.drawText("time in which one cycle occurs. : " + GameEngine.mLoopTimeAverageValue / GameEngine.i, 100, 1400, mPaint);

            mCanvas.drawText("FPS.", 50, 500, mPaint);
            mCanvas.drawText("FPS : " + GameEngine.mFPS, 100, 600, mPaint);


            mSurfaceHolder.unlockCanvasAndPost(mCanvas);
        }
    }

}