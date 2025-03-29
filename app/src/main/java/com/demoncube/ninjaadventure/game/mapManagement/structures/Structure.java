package com.demoncube.ninjaadventure.game.mapManagement.structures;

import static com.demoncube.ninjaadventure.game.GameSettings.debug.BOX_PAINT_STYLE;
import static com.demoncube.ninjaadventure.game.GameSettings.debug.BOX_STROKE_WIDTH;
import static com.demoncube.ninjaadventure.game.GameSettings.debug.COLLISION_BOX_COLOR;
import static com.demoncube.ninjaadventure.game.GameSettings.debug.DRAW_COLLISION_BOX;
import static com.demoncube.ninjaadventure.game.GameSettings.debug.DRAW_ENTITY_BOX;
import static com.demoncube.ninjaadventure.game.GameSettings.debug.PLAYER_BOX_COLOR;
import static com.demoncube.ninjaadventure.game.helpers.GameConst.Sprite.SIZE;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import com.demoncube.ninjaadventure.game.entities.Entity;
import com.demoncube.ninjaadventure.game.helpers.customVariables.CollisionBox;
import com.demoncube.ninjaadventure.game.helpers.customVariables.DecorBitmap;

import java.util.ArrayList;


public class Structure extends Entity {

    private final Bitmap mainBitmap;
    private ArrayList<DecorBitmap> decorBitmaps;

    private Paint boxDebugPaint, collisionBoxDebugPaint;
    public Structure(PointF pos, StructureSet structureSet, int structureId) {
        super(
                pos,
                structureSet.getStructureWidth(structureId),
                structureSet.getStructureHeight(structureId),
                structureSet.getStructure(structureId).collisions
        );
        this.mainBitmap = structureSet.getStructure(structureId).bitmap;
        decorBitmaps = null;
        init();
    }

    private void init() {

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

    @Override
    public void update(double delta, float cameraX, float cameraY) {
        super.update(delta, cameraX, cameraY);
    }

    @Override
    public void render(Canvas c, float cameraX, float cameraY) {
        c.drawBitmap(
                mainBitmap,
                box.left + cameraX,
                box.top + cameraY,
                null
        );
        if (decorBitmaps != null) {
            for (DecorBitmap decor : decorBitmaps) {
                c.drawBitmap(
                        decor.bitmap,
                        decor.posX * SIZE + box.left + cameraX,
                        decor.posY * SIZE + box.top + cameraY,
                        null
                );
            }
        }
        // --------- DEBUG ---------//
        if (DRAW_ENTITY_BOX) c.drawRect(
                box.left + cameraX,
                box.top + cameraY,
                box.right + cameraX,
                box.bottom + cameraY,
                boxDebugPaint
        );
        if (DRAW_COLLISION_BOX && collisions != null) {
            for (CollisionBox collision : collisions) {
                System.out.println(collision.rect.left + ":" + collision.rect.top);
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

    public void addDecor(StructureSet structureSet, int decorId, int decorPosX, int decorPosY) {
        if (decorBitmaps == null) decorBitmaps = new ArrayList<>();
        decorBitmaps.add(new DecorBitmap(structureSet.getDecor(decorId), decorPosX, decorPosY));
    }

    public void changeDecor(int decorId, StructureSet structureSet, int newDecorId, int decorPosX, int decorPosY) {
        if (decorBitmaps == null) return;
        if (decorBitmaps.size() <= decorId) return;
        decorBitmaps.get(decorId).bitmap = structureSet.getDecor(newDecorId);
        decorBitmaps.get(decorId).posX = decorPosX;
        decorBitmaps.get(decorId).posY = decorPosY;
    }

    public void removeDecor(int decorId) {
        if (decorBitmaps.size() <= decorId) return;
        decorBitmaps.remove(decorId);
        if (decorBitmaps.size() == 0) decorBitmaps = null;
    }
}
