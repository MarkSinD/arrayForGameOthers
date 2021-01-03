package com.gamecodeschool.gameloopwithoutanimation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Log;

public class PlayerSpec {private static final String tag = "Player";
    private static final String bitmapName = "player";
    private static final float speed = 10f;
    private static final PointF size = new PointF(1400f, 1400f);
    private Bitmap mBitmap;
    private Context mContext;
    private Rect mSectionToDraw;
    private PointF mLocation;
    private Point mScreenSize;


    public PlayerSpec(Context c,  PointF startingLocation, Point screenSize ) {
        Log.e("Метка", " был создан обьект PlayerSpec");
        mLocation = startingLocation;
        Log.e("Метка", " mLocation = " + mLocation.x + " " + mLocation.y);

        mScreenSize = screenSize;
        mContext = c;
        initialize();
    }


    private void initialize(){
        int resID = mContext.getResources().getIdentifier(bitmapName, "drawable", mContext.getPackageName());
        mBitmap = BitmapFactory.decodeResource(mContext.getResources(),resID);
        mBitmap = Bitmap.createScaledBitmap(mBitmap, (int)size.x, (int)size.y, false);
        Log.e("Метка", " инициализация прошла, картинка загружена");
    }

    public void draw(Canvas canvas, Paint paint){
        Log.e("Метка", " Метод PlayerSpec.draw() выполняется");
        canvas.drawBitmap(mBitmap, mLocation.x, mLocation.y, paint);

    }
}