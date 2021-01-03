package com.gamecodeschool.simplegameloop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;

public class ArrowSpec {

    private boolean misActive = false;
    private Bitmap mBitmap;
    private Matrix mMatrix;
    private Context mContext;
    private static final String tag = "Arrow";
    private static final String bitmapName = "arrow";
    private Point mLocation;
    private float mSpeed;
    private int mObjectHeight;
    private int mObjectWidth;
    private static Point mScreenSize;


    ArrowSpec(Context c, Point screenSize){
        mScreenSize = screenSize;
        mContext = c;
        mObjectHeight = mScreenSize.y / 5;
        mObjectWidth = mScreenSize.x / 5;
        mSpeed = mScreenSize.x / 5;
        mLocation = new Point();

        int resID = c.getResources().getIdentifier(bitmapName, "drawable", c.getPackageName());
        mBitmap = BitmapFactory.decodeResource(c.getResources(), resID);
        mBitmap = Bitmap.createScaledBitmap(mBitmap, mObjectWidth, mObjectHeight, false);
        mMatrix = new Matrix();

    }

    public void spawn(Point startPoint){

        mLocation.x = startPoint.x;
        mLocation.y = startPoint.y;
        misActive = true;

    }


    public void draw(Canvas canvas, Paint paint){

        canvas.drawColor(Color.argb(255, 222,16,16));
        //int angle = (int)((GameEngine.mCurrentTime - GameEngine.mStartTime) / 1000) * 6;
        //mMatrix.setTranslate( mScreenSize.x /2, mScreenSize.y/2-600);
        //mMatrix.postRotate(angle, mScreenSize.x /2, mScreenSize.y/2);


        if(misActive)
            canvas.drawBitmap(mBitmap, mLocation.x, mLocation.y, paint);
    }


    public boolean move(){


        if( misActive)
            mLocation.x += mSpeed / GameEngine.mFPS;

        return true;
    }

}
