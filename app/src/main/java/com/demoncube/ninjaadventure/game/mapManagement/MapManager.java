package com.demoncube.ninjaadventure.game.mapManagement;

import static com.demoncube.ninjaadventure.game.GameSettings.debug.DRAW_MAP_COLLISIONS;
import static com.demoncube.ninjaadventure.game.helpers.GameConst.Sprite.*;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.demoncube.ninjaadventure.GameActivity;
import com.demoncube.ninjaadventure.game.helpers.GameMapStorage;
import com.demoncube.ninjaadventure.game.helpers.customVariables.CollisionBox;
import com.demoncube.ninjaadventure.game.mapManagement.maps.GameMap;
import com.demoncube.ninjaadventure.game.mapManagement.maps.Tileset;

public class MapManager {
    private GameMap currentMap;

    private final Tileset floorset = Tileset.FLOOR;
    private final Tileset cliffset = Tileset.CLIFF;

    private final Paint collisionPaint;

    public MapManager() {
        initMap();
        collisionPaint = new Paint();
        collisionPaint.setStyle(Paint.Style.STROKE);
        collisionPaint.setStrokeWidth(5);
        collisionPaint.setColor(Color.BLACK);
    }

    public boolean canMoveHere(float x, float y) {
        if (x < 0 || y < 0) return false;
        if (x >= getMaxWidthCurrentMap() || y >= getMaxHeightCurrentMap()) return false;
        return true;
    }

    public int getMaxWidthCurrentMap() {
        return currentMap.getArrayWidth() * SIZE;
    }

    public int getMaxHeightCurrentMap() {
        return currentMap.getArrayHeight() * SIZE;
    }

    public void drawTiles(Canvas c, float cameraX, float cameraY){

        // Calculate number of tiles that fit horizontally and vertically
        int tilesAcross = (int) Math.ceil(GameActivity.SCREEN_WIDTH / (float) SIZE);
        int tilesDown = (int) Math.ceil(GameActivity.SCREEN_HEIGHT / (float) SIZE);

        // Find the top-left corner tile on the map based on camera position
        int startX = Math.max(0, (int) Math.floor(-cameraX / SIZE) - 1);
        int startY = Math.max(0, (int) Math.floor(-cameraY / SIZE) - 1);

        // Find the bottom-down corner tile on the map based on camera position
        int tilesAcrossToDraw = Math.min(startX + tilesAcross + 2, currentMap.getArrayWidth());
        int tilesDownToDraw = Math.min(startY + tilesDown + 2, currentMap.getArrayHeight());
        Tileset tileset;

        for (int i = startY; i < tilesDownToDraw;i++) {
            for (int j = startX; j < tilesAcrossToDraw; j++) {
                int spriteId = currentMap.getSpriteID(j, i);
                switch (currentMap.getTileset(j, i)) {
                    case 0: tileset = floorset; break;
                    case 1: tileset = cliffset; break;
                    default: {
                        tileset = floorset;
                        spriteId = 10;
                    }
                }
                c.drawBitmap(
                        tileset.getSprite(spriteId),
                        j * SIZE + cameraX,
                        i * SIZE + cameraY,
                        null
                );
                if (DRAW_MAP_COLLISIONS) {
                    CollisionBox[] collisions = tileset.getCollisions(spriteId);
                    if (collisions == null) continue;
                    for (CollisionBox collision : collisions) {
                        c.drawRect(
                                collision.rect.left + j * SIZE + cameraX,
                                collision.rect.top + i * SIZE + cameraY,
                                collision.rect.right + j * SIZE + cameraX,
                                collision.rect.bottom + i * SIZE + cameraY,
                                collisionPaint
                        );
                    }
                }
            }
        }
    }


    //-----------------------------------------------------------
    //                       Map Controls
    //-----------------------------------------------------------
    public GameMap getCurrentMap() {
        return currentMap;
    }

    private void initMap() {

        GameMap mainMap = new GameMap(GameMapStorage.MainMap.TILE_IDS, GameMapStorage.MainMap.TILESET_IDS);

        currentMap = mainMap;
    }
}