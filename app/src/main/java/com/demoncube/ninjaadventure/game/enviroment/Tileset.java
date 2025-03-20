package com.demoncube.ninjaadventure.game.enviroment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;

import com.demoncube.ninjaadventure.GameActivity;
import com.demoncube.ninjaadventure.R;
import com.demoncube.ninjaadventure.game.helpers.BitmapMethods;
import com.demoncube.ninjaadventure.game.helpers.GameConst;

public enum Tileset implements BitmapMethods {
    FLOOR(R.drawable.tileset_floor, 22, 26),
    CLIFF(R.drawable.tileset_cliff, 12, 10);

    private static final int MAX_CACHE_SIZE = 100;  // Adjust based on device memory
    private static final LruCache<Integer, Bitmap> tileCache = new LruCache<>(MAX_CACHE_SIZE);

    private final Bitmap spriteSheet;
    private final int tilesInWidth, tilesInHeight;

    Tileset(int resourceId, int tilesInWidth, int tilesInHeight) {
        this.tilesInWidth = tilesInWidth;
        this.tilesInHeight = tilesInHeight;
        options.inScaled = false;
        spriteSheet = BitmapFactory.decodeResource(GameActivity.getGameContext().getResources(), resourceId, options);
    }

    public Bitmap getSprite(int id) {
        Bitmap tile = tileCache.get(id);
        if (tile == null) {
            if (spriteSheet != null) {
                int x = (id % tilesInWidth) * GameConst.Sprite.DEFAULT_SIZE;
                int y = (id / tilesInWidth) * GameConst.Sprite.DEFAULT_SIZE;
                tile = getScaledBitmap(Bitmap.createBitmap(spriteSheet, x, y, GameConst.Sprite.DEFAULT_SIZE, GameConst.Sprite.DEFAULT_SIZE));
                tileCache.put(id, tile);
            }
        }
        return tile;
    }

    public void clearCache() {
        tileCache.evictAll();  // Clears all stored bitmaps when not needed
    }
}