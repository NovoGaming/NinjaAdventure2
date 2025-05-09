package com.demoncube.ninjaadventure.game.entities.enemies;

import static com.demoncube.ninjaadventure.game.GameSettings.debug.*;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;

import com.demoncube.ninjaadventure.game.controlers.ControllerInterface;
import com.demoncube.ninjaadventure.game.entities.Character;
import com.demoncube.ninjaadventure.game.entities.characterEnums.GameCharacters;
import com.demoncube.ninjaadventure.game.helpers.GameConst;
import com.demoncube.ninjaadventure.game.helpers.customVariables.CollisionBox;

public class Skeleton extends Character {
    //-------------------------------------------------------------------------//
    //                          Constructors + init                            //
    //-------------------------------------------------------------------------//

    private Paint boxDebugPaint, collisionBoxDebugPaint;

    public Skeleton(PointF pos,GameCharacters gameCharType, ControllerInterface controller) {
        super(pos, gameCharType, new CollisionBox[] {new CollisionBox(new Rect(3,12,13, 16),0)},controller);
        init();
    }

    public Skeleton(GameCharacters gameCharType, ControllerInterface controller) {
        super(new PointF(0,0), gameCharType, new CollisionBox[] {new CollisionBox(new Rect(3,10,13, 16),0)}, controller);
        init();
    }

    public Skeleton(PointF pos,GameCharacters gameCharType) {
        super(pos, gameCharType, new CollisionBox[] {new CollisionBox(new Rect(3,12,13, 16),0)});
        init();
    }

    public Skeleton(GameCharacters gameCharType) {
        super(new PointF(0,0), gameCharType, new CollisionBox[] {new CollisionBox(new Rect(3,10,13, 16),0)});
        init();
    }

    private void init() {
        movementSpeed = 250;

        //--------- DEBUG ---------//
        boxDebugPaint = new Paint();
        boxDebugPaint.setStrokeWidth(BOX_STROKE_WIDTH);
        boxDebugPaint.setStyle(BOX_PAINT_STYLE);
        boxDebugPaint.setColor(ENEMY_BOX_COLOR);

        collisionBoxDebugPaint = new Paint();
        collisionBoxDebugPaint.setStrokeWidth(BOX_STROKE_WIDTH);
        collisionBoxDebugPaint.setStyle(BOX_PAINT_STYLE);
        collisionBoxDebugPaint.setColor(COLLISION_BOX_COLOR);
    }

    //-------------------------------------------------------------------------//
    //                             Update functions                            //
    //-------------------------------------------------------------------------//

    @Override
    public void update(double delta, float cameraX, float cameraY) {
        if (true) { // future switch between actions
            updateAnimation();
            updatePlayerMove(delta);
        }
    }

    private void updatePlayerMove(double delta) {
        float baseSpeed = (float) (delta * movementSpeed);
        double x = controller.getMoveVectors(getBoundBox().left, getBoundBox().top).x;
        double y = controller.getMoveVectors(getBoundBox().left, getBoundBox().top).y;
        if (x == 0 && y == 0) {
            isMoving = false;
            resetAnimation();
        } else isMoving = true;
        BoundBox.left += x * baseSpeed;
        BoundBox.top += y * baseSpeed;
        BoundBox.right += x * baseSpeed;
        BoundBox.bottom += y * baseSpeed;
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
                BoundBox.left + cameraX,
                BoundBox.top + cameraY,
                null
        );
        if (DRAW_ENTITY_BOX) c.drawRect(
                BoundBox.left + cameraX,
                BoundBox.top + cameraY,
                BoundBox.right + cameraX,
                BoundBox.bottom + cameraY,
                boxDebugPaint
        );
        if (DRAW_COLLISION_BOX && collisions != null) {
            for (CollisionBox collision : collisions) {
                if (!collision.isActive) continue;
                switch (collision.collisionGroup) {
                    case 0: { // Draw standard collision
                        c.drawRect(
                                collision.rect.left + BoundBox.left + cameraX,
                                collision.rect.top + BoundBox.top + cameraY,
                                collision.rect.right + BoundBox.left + cameraX,
                                collision.rect.bottom + BoundBox.top + cameraY,
                                collisionBoxDebugPaint
                        );
                    }
                    break;
                    case 1: { // Draw trigger box
                        c.drawRect(
                                collision.rect.left + BoundBox.left + cameraX,
                                collision.rect.top + BoundBox.top + cameraY,
                                collision.rect.right + BoundBox.left + cameraX,
                                collision.rect.bottom + BoundBox.top + cameraY,
                                null
                        );
                    }
                    break;
                }
            }
        }
    }

    //-------------------------------------------------------------------------//
    //                            Support functions                            //
    //-------------------------------------------------------------------------//

}
