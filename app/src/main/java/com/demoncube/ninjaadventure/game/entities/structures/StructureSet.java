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
                    new Rect( 0,0,4,3), //0
                    new Rect( 4,0,4,3), //1
                    new Rect( 8,0,4,3), //2
                    new Rect(12,0,4,3), //3
                    new Rect(16,0,3,3), //4
                    new Rect(19,0,4,3), //5
                    new Rect(23,0,3,3), //6
                    new Rect(26,0,3,3), //7
                    new Rect(29,0,4,4), //8
            },
            new CollisionBox[][] {
                    {new CollisionBox(new Rect(0, 16, 64, 48), 0)}, //0
                    {new CollisionBox(new Rect(0, 16, 64, 48), 0)}, //1
                    {new CollisionBox(new Rect(0, 16, 64, 48), 0)}, //2
                    {new CollisionBox(new Rect(0, 16, 64, 48), 0)}, //3
                    {new CollisionBox(new Rect(0, 16, 48, 48), 0)}, //4
                    {new CollisionBox(new Rect(0, 16, 64, 48), 0)}, //5
                    {new CollisionBox(new Rect(0, 16, 48, 48), 0)}, //6
                    {new CollisionBox(new Rect(0, 16, 48, 48), 0)}, //7
                    {new CollisionBox(new Rect(0, 32, 64, 64), 0)}, //8
            },
            new Rect[] {
                    new Rect(0,3,1,1), //0
                    new Rect(1,3,1,1), //1
                    new Rect(2,3,1,1), //2
                    new Rect(3,3,1,1), //3
                    new Rect(4,3,1,1), //4
                    new Rect(5,3,1,1), //5
                    new Rect(6,3,1,1), //6
                    new Rect(7,3,1,1), //7
                    new Rect(8,3,1,1), //8
                    new Rect(9,3,1,1), //9
                    new Rect(0,4,2,1), //10
                    new Rect(2,4,2,1), //11
                    new Rect(4,4,2,1), //12
                    new Rect(6,4,2,1), //13
            }
            );

    //----------------------------------------------------------------------------------------------//
    //                                        Enum Functions                                        //
    //----------------------------------------------------------------------------------------------//
    private final Bitmap spriteSheet;
    private final Rect[] structureMap;  // left = x pos, top = y pos, right = width, bottom = height;
    private final Rect[] decorMap;  // left = x pos, top = y pos, right = width, bottom = height;
    private final  CollisionBox[][] collisionMap;

    StructureSet(int resourceId, Rect[] structureMap, CollisionBox[][] collisionMap, Rect[] decorMap) {
        options.inScaled = false;
        spriteSheet = BitmapFactory.decodeResource(GameActivity.getGameContext().getResources(), resourceId, options);
        this.structureMap = structureMap;
        this.collisionMap = collisionMap;
        this.decorMap = decorMap;
    }

    public Drawable getStructure(int id) {
        if (structureMap.length <= id) id = 0;
        Drawable drawable = new Drawable();

        drawable.bitmap = getScaledBitmap(Bitmap.createBitmap(spriteSheet, structureMap[id].left * DEFAULT_SIZE, structureMap[id].top * DEFAULT_SIZE, structureMap[id].right * DEFAULT_SIZE, structureMap[id].bottom * DEFAULT_SIZE));

        drawable.collisions = collisionMap[id];

        return drawable;
    }

    public Bitmap getDecor(int id) {
        if (decorMap.length <= id) id = 0;
        return getScaledBitmap(Bitmap.createBitmap(spriteSheet, decorMap[id].left * DEFAULT_SIZE, decorMap[id].top * DEFAULT_SIZE, decorMap[id].right * DEFAULT_SIZE, decorMap[id].bottom * DEFAULT_SIZE));
    }

    public float getStructureWidth(int id) {
        if (structureMap.length <= id) id = 0;
        return structureMap[id].right * SIZE;
    }

    public float getStructureHeight(int id) {
        if (structureMap.length <= id) id = 0;
        return structureMap[id].bottom * SIZE;
    }


}




