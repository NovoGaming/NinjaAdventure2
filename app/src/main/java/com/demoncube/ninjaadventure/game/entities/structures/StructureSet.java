package com.demoncube.ninjaadventure.game.entities.structures;

import static com.demoncube.ninjaadventure.game.helpers.GameConst.Sprite.DEFAULT_SIZE;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.LruCache;

import com.demoncube.ninjaadventure.GameActivity;
import com.demoncube.ninjaadventure.R;
import com.demoncube.ninjaadventure.game.GameSettings;
import com.demoncube.ninjaadventure.game.helpers.BitmapMethods;
import com.demoncube.ninjaadventure.game.helpers.customVariables.CollisionBox;
import com.demoncube.ninjaadventure.game.helpers.customVariables.Drawable;

import java.util.Map;

public enum StructureSet implements BitmapMethods {

    VILLAGE(R.drawable.structureset_village);

    //----------------------------------------------------------------------------------------------//
    //                                        Enum Functions                                        //
    //----------------------------------------------------------------------------------------------//
    private static final int MAX_CACHE_SIZE = GameSettings.baseSettings.TILE_CACHE_SIZE;  // Adjust based on device memory
    private static final LruCache<Integer, Drawable> tileCache = new LruCache<>(MAX_CACHE_SIZE);


    private final Bitmap spriteSheet;

    StructureSet(int resourceId) {
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




