package com.demoncube.ninjaadventure.game;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{

    public final Game game;
    public GameView(Context context) {
        super(context);
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        game = new Game(holder);

    }

    public void destroy() {
        game.stop();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return game.touchEvents(event);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        game.start();
        game.isAvailable(true);
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        game.isAvailable(false);
    }
}
