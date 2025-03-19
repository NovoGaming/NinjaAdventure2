package com.demoncube.ninjaadventure.game.enviroment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.RectF;

import com.demoncube.ninjaadventure.GameActivity;
import com.demoncube.ninjaadventure.R;
import com.demoncube.ninjaadventure.game.helpers.BitmapMethods;
import com.demoncube.ninjaadventure.game.helpers.GameConst;

import java.util.Map;

public enum TileSets implements BitmapMethods {
    FLOOR(R.drawable.tileset_floor, 22, 26, null, null),
    CLIFF(R.drawable.tileset_cliff, 22, 26, new int[]{5, 6, 7}, new RectF[][]{
            {new RectF(0, 0, 64, 64)},   // Collision for tile ID 5
            {new RectF(0, 32, 64, 64)},  // Collision for tile ID 6
            {new RectF(16, 0, 48, 64)}   // Collision for tile ID 7
    });

    private Bitmap[] sprites;
    Map<Integer, RectF[]> collisionMap;

    TileSets(int resourceId, int tilesInWidth, int tilesInHeight, int[] collisionTileIds, RectF[][] collisionBox){
        options.inScaled = false;
        sprites = new Bitmap[tilesInHeight*tilesInWidth];
        Bitmap spriteSheet = BitmapFactory.decodeResource(GameActivity.getGameContext().getResources(), resourceId, options);           //Loads texture into variable
        for (int j = 0; j < tilesInHeight; j++)
            for (int i = 0; i < tilesInWidth; i++) {
                int index = j * tilesInWidth +i;
                sprites[index] = getScaledBitmap(Bitmap.createBitmap(spriteSheet, GameConst.Sprite.DEFAULT_SIZE*i,GameConst.Sprite.DEFAULT_SIZE*j,GameConst.Sprite.DEFAULT_SIZE,GameConst.Sprite.DEFAULT_SIZE));     //Splits sprites sheet into separate sprites
            }
        if (collisionTileIds == null) return;
        for (int i = 0; i < collisionTileIds.length; i++) {
            collisionMap.put(collisionTileIds[i], collisionBox[i]);
        }
    }


    public Bitmap getSprite(int id) {
        return sprites[id];
    }

    public RectF[] getCollision(int id) {
        return collisionMap.getOrDefault(id, new RectF[]{});
    }
}

