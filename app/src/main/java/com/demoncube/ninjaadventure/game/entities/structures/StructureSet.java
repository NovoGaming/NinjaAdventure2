package com.demoncube.ninjaadventure.game.entities.structures;

import static com.demoncube.ninjaadventure.game.helpers.GameConst.Sprite.*;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.demoncube.ninjaadventure.GameActivity;
import com.demoncube.ninjaadventure.R;
import com.demoncube.ninjaadventure.game.helpers.BitmapMethods;
import com.demoncube.ninjaadventure.game.helpers.customVariables.CollisionBox;
import com.demoncube.ninjaadventure.game.helpers.customVariables.Drawable;

public enum StructureSet implements BitmapMethods {

    VILLAGE(R.drawable.structureset_village,
            new Rect[] {
                    new Rect(0,0,4,3),
                    new Rect(0,3,1,1)
            },
            new CollisionBox[][] {
                    {new CollisionBox(new Rect(0, 16, 64, 48), 0)},
                    null,
            }
            );

    //----------------------------------------------------------------------------------------------//
    //                                        Enum Functions                                        //
    //----------------------------------------------------------------------------------------------//
    private final Bitmap spriteSheet;
    private final Rect[] structureMap;  // left = x pos, top = y pos, right = width, bottom = height;
    private final  CollisionBox[][] collisionMap;

    StructureSet(int resourceId, Rect[] structureMap, CollisionBox[][] collisionMap) {
        options.inScaled = false;
        spriteSheet = BitmapFactory.decodeResource(GameActivity.getGameContext().getResources(), resourceId, options);
        this.structureMap = structureMap;
        this.collisionMap = collisionMap;
    }

    public Drawable getStructure(int id) {
        Drawable drawable = new Drawable();

        drawable.bitmap = getScaledBitmap(Bitmap.createBitmap(spriteSheet, structureMap[id].left * DEFAULT_SIZE, structureMap[id].top * DEFAULT_SIZE, structureMap[id].right * DEFAULT_SIZE, structureMap[id].bottom * DEFAULT_SIZE));

        drawable.collisions = collisionMap[id];

        return drawable;
    }

    public float getStructureWidth(int id) {
        return structureMap[id].right * SIZE;
    }

    public float getStructureHeight(int id) {
        return structureMap[id].bottom * SIZE;
    }


}




