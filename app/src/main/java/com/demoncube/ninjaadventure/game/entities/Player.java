package com.demoncube.ninjaadventure.game.entities;

import static com.demoncube.ninjaadventure.GameActivity.*;
import static com.demoncube.ninjaadventure.game.GameSettings.debug.*;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import com.demoncube.ninjaadventure.game.controlers.ControllerInterface;
import com.demoncube.ninjaadventure.game.helpers.GameConst;

public class Player extends Character{

    Paint playerHitboxDebugPaint;

    public Player(PointF pos,GameCharacters gameCharType, ControllerInterface controller) {
        super(pos, gameCharType, controller);
        playerHitboxDebugPaint = new Paint();
        playerHitboxDebugPaint.setStrokeWidth(HITBOX_STROKE_WIDTH);
        playerHitboxDebugPaint.setStyle(HITBOX_PAINT_STYLE);
        playerHitboxDebugPaint.setColor(PLAYER_HITBOX_COLOR);
    }

    public Player(GameCharacters gameCharType, ControllerInterface controller) {
        super(new PointF(0,0), gameCharType, controller);
        playerHitboxDebugPaint = new Paint();
        playerHitboxDebugPaint.setStrokeWidth(HITBOX_STROKE_WIDTH);
        playerHitboxDebugPaint.setStyle(HITBOX_PAINT_STYLE);
        playerHitboxDebugPaint.setColor(PLAYER_HITBOX_COLOR);
    }

    public void update(double delta, boolean movePLayer) {
        if (movePLayer) {
            updateAnimation();
            updatePlayerMove(delta);
        }
    }

    public void updatePlayerMove(double delta) {
        float baseSpeed = (float) (delta * 300);
        double x = controller.getMoveVectors().x;
        double y = controller.getMoveVectors().y;
        if (x == 0 && y == 0) {
            isMoving = false;
            resetAnimation();
        } else isMoving = true;
        hitbox.left += x * baseSpeed;
        hitbox.top += y * baseSpeed;
        hitbox.right += x * baseSpeed;
        hitbox.bottom += y * baseSpeed;
        if (Math.abs(x) > Math.abs(y)) {
            if (x > 0) faceDir = GameConst.FaceDir.RIGHT;
            if (x < 0) faceDir = GameConst.FaceDir.LEFT;
        } else {
            if (y > 0) faceDir = GameConst.FaceDir.DOWN;
            if (y < 0) faceDir = GameConst.FaceDir.UP;
        }

    }

    public void render(Canvas c){
        c.drawBitmap(
                getGameCharType().getSprite(aniIndex, faceDir),
                hitbox.left,
                hitbox.top,
                null
        );
        if (DRAW_ENTITY_HITBOX) c.drawRect(
                hitbox.left,
                hitbox.top,
                hitbox.right,
                hitbox.bottom,
                playerHitboxDebugPaint
        );
    }
}
