package com.demoncube.ninjaadventure.game.ui;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.MotionEvent;

import com.demoncube.ninjaadventure.game.GameSettings;
import com.demoncube.ninjaadventure.game.helpers.customVariables.vector;

public class UIJoystick implements UI{

    PointF pos;
    float radius;
    Paint basePaint, decorPaint, debugPaint;
    private boolean touchDown = false;
    private float xTouch, yTouch;
    private int pointerId = -1;
    vector outputVector = new vector(0,0);


    public UIJoystick(PointF centerPos, float radius, Paint basePaint,Paint decorPaint) {
        this.pos = centerPos;
        this.radius = radius;
        this.basePaint = basePaint;
        this.decorPaint = decorPaint;

        debugPaint = new Paint();
        debugPaint.setColor(GameSettings.debug.UI_COLOR);
        debugPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        debugPaint.setTextSize(40);
    }


    @Override
    public void render(Canvas c) {
        c.drawCircle(pos.x,pos.y, radius, basePaint);
        c.drawCircle((float) (pos.x + (radius*outputVector.x)),(float) (pos.y + (radius*outputVector.y)), radius/3, decorPaint);
        if (GameSettings.debug.DRAW_UI_DEBUG) {
            c.drawText("x: " + outputVector.x, pos.x + radius + 10, pos.y - radius+25, debugPaint);
            c.drawText("y: " + outputVector.y, pos.x + radius + 10, pos.y - radius+75, debugPaint);
            c.drawLine(pos.x, pos.y, xTouch,yTouch,debugPaint);
            c.drawLine(pos.x, pos.y, xTouch,pos.y,debugPaint);
            c.drawLine(xTouch, yTouch, xTouch,pos.y,debugPaint);
        }
    }

    @Override
    public void touchEvent(MotionEvent event) {
        final int action = event.getActionMasked();
        final int actionIndex = event.getActionIndex();
        final int pointerId = event.getPointerId(actionIndex);

        final PointF eventPos = new PointF(event.getX(actionIndex), event.getY(actionIndex));

        switch (action){
            case MotionEvent.ACTION_POINTER_DOWN:
            case MotionEvent.ACTION_DOWN: {
                if (isInsideJayStick(eventPos, pointerId)) {
                    touchDown = true;
                    xTouch = eventPos.x;
                    yTouch = eventPos.y;

                    calcOutputVector();
                }
            }break;
            //-----------------------------------------------------//
            case MotionEvent.ACTION_MOVE: {
                for (int i = 0; i < event.getPointerCount(); i++) {
                    if (touchDown && event.getPointerId(i) == this.pointerId) {
                        xTouch = event.getX(i);
                        yTouch = event.getY(i);

                        calcOutputVector();
                    }
                }
            }break;
            //-----------------------------------------------------//
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_UP: {
                if(pointerId == this.pointerId) {
                    resetJoystick();
                }
            }break;
        }
    }

    private boolean isInsideJayStick (PointF eventPos, int pointerId) {
        if (isInsideRadius(eventPos, pos, radius)) {
            this.pointerId = pointerId;
            return true;
        }
        return false;
    }

    public boolean isInsideRadius(PointF eventPos, PointF radiusCenterPos, float r) {
        float a = Math.abs(eventPos.x - radiusCenterPos.x);
        float b = Math.abs(eventPos.y - radiusCenterPos.y);
        float c = (float) Math.hypot(a, b);
        return c <= r;
    }

    private void resetJoystick () {
        touchDown = false;
        pointerId = -1;
        outputVector.x = 0;
        outputVector.y = 0;
    }

    private void calcOutputVector() {
        double dx = xTouch - pos.x;
        double dy = yTouch - pos.y;
        double distance = Math.sqrt(
                Math.pow(dx, 2) + Math.pow(dy, 2)
        );
        if (distance < radius) {
            dx = dx/radius;
            dy = dy/radius;
        } else {
            dx = dx/distance;
            dy = dy/distance;
        }
        outputVector.x = dx;
        outputVector.y = dy;
    }

    public vector getOutputVector() {
        return outputVector;
    }
}

