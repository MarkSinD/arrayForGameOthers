package com.gamecodeschool.gameloopwithoutanimation;

import android.content.Context;
import android.content.SharedPreferences;

public class GameState {

    private static volatile boolean mThreadRunning = false;
    private static volatile boolean mPaused = true;
    private static volatile boolean mGameOver = true;
    private static volatile boolean mDrawing = false;
    private EngineController mEngineController;
    private long mStartTimeMilis;

    GameState(EngineController gs, Context context) {
        mEngineController = gs;
    }





    void stopThread() {
        mThreadRunning = false;
    }

    boolean getThreadRunning() {
        return mThreadRunning;
    }

    void startThread() {
        mThreadRunning = true;
    }

    boolean getDrawing() {
        return mDrawing;
    }

    boolean getPaused() {
        return mPaused;
    }

    boolean getGameOver() {
        return mGameOver;
    }


    private void endGame() {
        stopEverything();
    }


    void startNewGame() {
        stopEverything();
        mEngineController.startNewLevel();
        startEverything();
        mStartTimeMilis = System.currentTimeMillis();
    }

    private void startEverything() {
        mPaused = false;
        mGameOver = false;
        mDrawing = true;
    }

    void stopEverything() {
        mPaused = true;
        mGameOver = true;
        mDrawing = false;
    }
}

