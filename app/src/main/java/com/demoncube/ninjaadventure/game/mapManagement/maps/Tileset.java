package com.demoncube.ninjaadventure.game.mapManagement.maps;

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

import java.util.HashMap;
import java.util.Map;

public enum Tileset implements BitmapMethods {

    FLOOR(R.drawable.tileset_floor, 22, 26, null),
    CLIFF(R.drawable.tileset_cliff, 12, 10, cliffCollisions());

    private static Map<Integer, CollisionBox[]> cliffCollisions() {
        Map<Integer, CollisionBox[]> collisions = new HashMap<>();
        collisions.put(48, new CollisionBox[]{new CollisionBox(new Rect(0,0,16,16), 1)});
        collisions.put(49, new CollisionBox[]{new CollisionBox(new Rect(0,0,16,16), 1)});
        collisions.put(50, new CollisionBox[]{new CollisionBox(new Rect(0,0,16,16), 1)});
        collisions.put(51, new CollisionBox[]{new CollisionBox(new Rect(0,0,16,16), 1)});
        collisions.put(52, new CollisionBox[]{new CollisionBox(new Rect(0,0,16,16), 1)});
        collisions.put(53, new CollisionBox[]{new CollisionBox(new Rect(0,0,16,16), 1)});
        collisions.put(54, new CollisionBox[]{new CollisionBox(new Rect(0,0,16,16), 1)});
        return collisions;
    }

    //----------------------------------------------------------------------------------------------//
    //                                        Enum Functions                                        //
    //----------------------------------------------------------------------------------------------//
    private static final int MAX_CACHE_SIZE = GameSettings.baseSettings.TILE_CACHE_SIZE;  // Adjust based on device memory
    private static final LruCache<Integer, Drawable> tileCache = new LruCache<>(MAX_CACHE_SIZE);


    private final Bitmap spriteSheet;
    private final Map<Integer, CollisionBox[]> collisionsList;
    private final int tilesInWidth, tilesInHeight;

    Tileset(int resourceId, int tilesInWidth, int tilesInHeight, Map<Integer, CollisionBox[]> collisionsList) {
        this.tilesInWidth = tilesInWidth;
        this.tilesInHeight = tilesInHeight;
        this.collisionsList = collisionsList;
        options.inScaled = false;
        spriteSheet = BitmapFactory.decodeResource(GameActivity.getGameContext().getResources(), resourceId, options);
    }

    private Drawable getTile(int id) {
        Drawable drawable = tileCache.get(id);
        if (drawable != null) return drawable;
        if (spriteSheet != null) {
            drawable = new Drawable();
            int x = (id % tilesInWidth) * DEFAULT_SIZE;
            int y = (id / tilesInWidth) * DEFAULT_SIZE;
            if ((tilesInHeight - 1) * DEFAULT_SIZE < y) y = (tilesInHeight - 1) * DEFAULT_SIZE;
            drawable.bitmap = getScaledBitmap(Bitmap.createBitmap(spriteSheet, x, y, DEFAULT_SIZE, DEFAULT_SIZE));
            if (collisionsList != null) drawable.collision = collisionsList.getOrDefault(id, null);
            tileCache.put(id, drawable);
            return drawable;
        }
        return null;
    }

    public Bitmap getSprite(int id) {
        return getTile(id).bitmap;
    }

    public CollisionBox[] getCollisions (int id) {
        return getTile(id).collision;
    }

    public void clearCache() {
        tileCache.evictAll();  // Clears all stored bitmaps when not needed
    }
}