package com.demoncube.ninjaadventure.game.entities;

import static com.demoncube.ninjaadventure.game.GameSettings.debug.*;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;

import com.demoncube.ninjaadventure.game.controlers.ControllerInterface;
import com.demoncube.ninjaadventure.game.helpers.GameConst;
import com.demoncube.ninjaadventure.game.helpers.customVariables.CollisionBox;

public class Player extends Character{

    //-------------------------------------------------------------------------//
    //                          Constructors + init                            //
    //-------------------------------------------------------------------------//

    private Paint boxDebugPaint, collisionBoxDebugPaint;

    public Player(PointF pos,GameCharacters gameCharType, ControllerInterface controller) {
        super(pos, gameCharType, new CollisionBox[] {new CollisionBox(new Rect(3,12,13, 16),0)},controller);
        init();
    }

    public Player(GameCharacters gameCharType, ControllerInterface controller) {
        super(new PointF(0,0), gameCharType, new CollisionBox[] {new CollisionBox(new Rect(3,10,13, 16),0)}, controller);
        init();
    }

    public Player(PointF pos,GameCharacters gameCharType) {
        super(pos, gameCharType, new CollisionBox[] {new CollisionBox(new Rect(3,12,13, 16),0)});
        init();
    }

    public Player(GameCharacters gameCharType) {
        super(new PointF(0,0), gameCharType, new CollisionBox[] {new CollisionBox(new Rect(3,10,13, 16),0)});
        init();
    }

    private void init() {
        movementSpeed = 300;

        //--------- DEBUG ---------//
        boxDebugPaint = new Paint();
        boxDebugPaint.setStrokeWidth(BOX_STROKE_WIDTH);
        boxDebugPaint.setStyle(BOX_PAINT_STYLE);
        boxDebugPaint.setColor(PLAYER_BOX_COLOR);

        collisionBoxDebugPaint = new Paint();
        collisionBoxDebugPaint.setStrokeWidth(BOX_STROKE_WIDTH);
        collisionBoxDebugPaint.setStyle(BOX_PAINT_STYLE);
        collisionBoxDebugPaint.setColor(COLLISION_BOX_COLOR);
    }

    //-------------------------------------------------------------------------//
    //                             Update functions                            //
    //-------------------------------------------------------------------------//


    public void update(double delta ,boolean movePLayer, float cameraX, float cameraY) {
        if (movePLayer) {
            updateAnimation();
            updatePlayerMove(delta);
        }
        super.update(delta, cameraX, cameraY);
    }


    private void updatePlayerMove(double delta) {
        float baseSpeed = (float) (delta * movementSpeed);
        double x = controller.getMoveVectors().x;
        double y = controller.getMoveVectors().y;
        if (x == 0 && y == 0) {
            isMoving = false;
            resetAnimation();
        } else isMoving = true;
        box.left += x * baseSpeed;
        box.top += y * baseSpeed;
        box.right += x * baseSpeed;
        box.bottom += y * baseSpeed;
        if (Math.abs(x) > Math.abs(y)) {
            if (x > 0) faceDir = GameConst.FaceDir.RIGHT;
            if (x < 0) faceDir = GameConst.FaceDir.LEFT;
        } else {
            if (y > 0) faceDir = GameConst.FaceDir.DOWN;
            if (y < 0) faceDir = GameConst.FaceDir.UP;
        }
    }

    //-------------------------------------------------------------------------//
    //                             Render functions                            //
    //-------------------------------------------------------------------------//

    @Override
    public void render(Canvas c, float cameraX, float cameraY){
        c.drawBitmap(
                getGameCharType().getSprite(aniIndex, faceDir),
                box.left + cameraX,
                box.top + cameraY,
                null
        );
        if (DRAW_ENTITY_BOX) c.drawRect(
                box.left + cameraX,
                box.top + cameraY,
                box.right + cameraX,
                box.bottom + cameraY,
                boxDebugPaint
        );
        if (DRAW_COLLISION_BOX) {
            for (CollisionBox collision : collisions) {
                c.drawRect(
                        collision.rect.left + box.left + cameraX,
                        collision.rect.top + box.top + cameraY,
                        collision.rect.right + box.left + cameraX,
                        collision.rect.bottom + box.top + cameraY,
                        collisionBoxDebugPaint
                );
            }
        }
    }

    //-------------------------------------------------------------------------//
    //                            Support functions                            //
    //-------------------------------------------------------------------------//


}
