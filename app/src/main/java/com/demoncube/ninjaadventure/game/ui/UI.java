package com.demoncube.ninjaadventure.game.ui;

import android.graphics.Canvas;
import android.view.MotionEvent;

public interface UI {

    void render(Canvas c);
    void touchEvent(MotionEvent event);
}
