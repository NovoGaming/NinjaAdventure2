package com.demoncube.ninjaadventure.game.entities.structures;

import static com.demoncube.ninjaadventure.game.helpers.GameConst.Sprite.DEFAULT_SIZE;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.LruCache;

import com.demoncube.ninjaadventure.GameActivity;
import com.demoncube.ninjaadventure.game.GameSettings;
import com.demoncube.ninjaadventure.game.helpers.BitmapMethods;
import com.demoncube.ninjaadventure.game.helpers.customVariables.CollisionBox;
import com.demoncube.ninjaadventure.game.helpers.customVariables.Drawable;

import java.util.Map;

public class StructureSet implements BitmapMethods {



    //----------------------------------------------------------------------------------------------//
    //                                        Enum Functions                                        //
    //----------------------------------------------------------------------------------------------//
    private static final int MAX_CACHE_SIZE = GameSettings.baseSettings.TILE_CACHE_SIZE;  // Adjust based on device memory
    private static final LruCache<Integer, Drawable> tileCache = new LruCache<>(MAX_CACHE_SIZE);


    private final Bitmap spriteSheet;
    private final Map<Integer, Rect[]> structureList;
    private final int tilesInWidth, tilesInHeight;

    public StructureSet(int resourceId, int tilesInWidth, int tilesInHeight, Map<Integer, Rect[]> collisionsList) {
        this.tilesInWidth = tilesInWidth;
        this.tilesInHeight = tilesInHeight;
        this.structureList = collisionsList;
        options.inScaled = false;
        spriteSheet = BitmapFactory.decodeResource(GameActivity.getGameContext().getResources(), resourceId, options);
    }

    private Drawable getStructure(int id) {
        Drawable drawable = tileCache.get(id);
        if (drawable != null) return drawable;
        if (spriteSheet != null) {
            drawable = new Drawable();

            drawable.bitmap = getScaledBitmap(Bitmap.createBitmap(spriteSheet, 0, 0, DEFAULT_SIZE, DEFAULT_SIZE));
            tileCache.put(id, drawable);
            return drawable;
        }
        return null;
    }

    public Bitmap getStructureBitmap(int id) {
        return getStructure(id).bitmap;
    }

    public void clearCache() {
        tileCache.evictAll();  // Clears all stored bitmaps when not needed
    }
}




