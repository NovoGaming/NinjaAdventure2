package com.demoncube.ninjaadventure.game.controlers;

import android.graphics.PointF;

import com.demoncube.ninjaadventure.game.helpers.customVariables.Victor;

public class EnemyAIController implements ControllerInterface{

    private PointF moveToPos = null;


    private Victor moveVector;
    private double[] lastFeedBack;

    public EnemyAIController() {
        moveVector = new Victor(0,0);
        lastFeedBack = new double[]{0, 0};
    }

    @Override
    public Victor getMoveVectors(float ownerPosX, float ownerPosY) {
        if (moveToPos != null) {

            float dx = moveToPos.x - ownerPosX;
            float dy = moveToPos.y - ownerPosY;

            float length = (float) Math.sqrt(dx * dx + dy * dy);

            if (length <= 1f && length != 0f) { // already within bounds
                moveVector.x = dx;
                moveVector.y = dy;
            } else if (length <= 10) { // no movement
                moveVector.x = 0;
                moveVector.y = 0;
                moveToPos = null;
            } else { // scale down to length 1
                moveVector.x = dx / length;
                moveVector.y = dy / length;
            }
            System.out.println(moveVector.x + ":" + moveVector.y + "|" + length);
        }


        return moveVector;
    }

    @Override
    public boolean isAttacking() {
        return false;
    }

    @Override
    public boolean isInteracting() {
        return false;
    }

    @Override
    public void feedBack(double[] feedBack) {
        this.lastFeedBack = feedBack;
    }

    public void setMoveToPos(PointF moveToPos) {
        this.moveToPos = moveToPos;
    }

    public PointF getMoveToPos() {
        return moveToPos;
    }
}
