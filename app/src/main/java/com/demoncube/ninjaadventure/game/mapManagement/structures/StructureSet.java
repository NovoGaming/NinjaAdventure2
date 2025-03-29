package com.demoncube.ninjaadventure.game.mapManagement.structures;

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
                    new Rect( 0,0,4,3),     //0     //House 1
                    new Rect( 4,0,4,3),     //1     //House 2
                    new Rect( 8,0,4,3),     //2     //House 3
                    new Rect(12,0,4,3),     //3     //House 4
                    new Rect(16,0,3,3),     //4     //Bakery
                    new Rect(19,0,4,3),     //5     //Shop 1
                    new Rect(23,0,3,3),     //6     //Shop 2
                    new Rect(26,0,3,3),     //7     //Shop 3
                    new Rect(29,0,4,4),     //8     //Mail office
                    new Rect(29,4,3,4),     //9     //Windmill
                    new Rect(25,7,4,5),     //10    //Barn
                    new Rect(25,14,4,5),    //11    //Roofless Barn
                    new Rect(19,19,3,3),    //12    //Tool Shack
                    new Rect(0,7,3,3),      //13    //Tent 1
                    new Rect(3,7,3,3),      //14    //Tent 2
                    new Rect(0,10,3,4),     //15    //Igloo 1
                    new Rect(3,10,3,4),     //16    //Igloo 2
                    new Rect(6,10,3,4),     //17    //Igloo 3
                    new Rect(16,6,3,5),     //18    //Watch Tower
                    new Rect(22,3,3,3),     //19    //Wall
                    new Rect(28,3,1,3),     //20    //Wall Sideways
                    new Rect(22,6,3,3),     //21    //Gate opened
                    new Rect(22,9,3,3),     //22    //Gate closed
                    new Rect(19,3,3,5),     //23    //Stake big
                    new Rect(19,9,2,4),     //24    //Stake medium
                    new Rect(21,9,1,3),     //25    //Stake small
                    new Rect(22,12,3,2),     //26    //Pole wall 1
                    new Rect(22,14,3,2),     //27    //Pole wall 2
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
                    {new CollisionBox(new Rect(5, 38, 44, 62), 0)}, //9
                    {new CollisionBox(new Rect(0, 32, 64, 80), 0)}, //10
                    {new CollisionBox(new Rect(0, 32, 64, 80), 0)}, //11
                    {new CollisionBox(new Rect(0, 16, 48, 48), 0)}, //12
                    {new CollisionBox(new Rect(2, 16, 46, 47), 0)}, //13
                    {new CollisionBox(new Rect(2, 26, 46, 47), 0)}, //14
                    {new CollisionBox(new Rect(0, 32, 48, 60), 0)}, //15
                    {new CollisionBox(new Rect(8, 36, 40, 60), 0)}, //16
                    {new CollisionBox(new Rect(0, 24, 48, 60), 0)}, //17
                    {new CollisionBox(new Rect(0, 48, 48, 76), 0)}, //18
                    {new CollisionBox(new Rect(0, 36, 48, 48), 0)}, //19
                    {new CollisionBox(new Rect(0,  0, 16, 48), 0)}, //20
                    {new CollisionBox(new Rect(0, 36, 10, 48), 0), new CollisionBox(new Rect(38, 36, 48, 48), 0)}, //21
                    {new CollisionBox(new Rect(0, 36, 48, 48), 0)}, //22
                    {new CollisionBox(new Rect(0, 48, 48, 80), 0)}, //23
                    {new CollisionBox(new Rect(0, 48, 32, 64), 0)}, //24
                    {new CollisionBox(new Rect(0, 36, 16, 48), 0)}, //25
                    {new CollisionBox(new Rect(0, 24, 48, 32), 0)}, //26
                    {new CollisionBox(new Rect(0, 24, 48, 32), 0)}, //27
            },
            new Rect[] {
                    new Rect(0,3,1,1),      //0     //Door 1 (House 1 - 4)
                    new Rect(1,3,1,1),      //1     //Door 2 (House 1 - 4)
                    new Rect(2,3,1,1),      //2     //Door 3 (House 1 - 4)
                    new Rect(3,3,1,1),      //3     //
                    new Rect(4,3,1,1),      //4     //
                    new Rect(5,3,1,1),      //5     //Head
                    new Rect(6,3,1,1),      //6     //Lantern (House 1 - 4)
                    new Rect(7,3,1,1),      //7     //Chimney (House 1 - 4)
                    new Rect(8,3,1,1),      //8     //Door 4 (House 1 - 4)
                    new Rect(9,3,1,1),      //9     //Door 5 (House 1 - 4)
                    new Rect(0,4,2,1),      //10    //Door 6 (House 1 - 4)
                    new Rect(2,4,2,1),      //11    //Door 7 (House 1 - 4)
                    new Rect(4,4,2,1),      //12    //DOJO sign (House 1 - 4)
                    new Rect(6,4,2,1),      //13    //Sword sign (House 1 - 4)
                    new Rect(26,6,1,1),     //14    //Roof patch (Barn)
                    new Rect(27,6,1,1),     //15    //Chimney (Barn)
                    new Rect(25,10,4,2),    //16    //Barn front with door (Barn)
                    new Rect(25,19,1,1),    //17    //Door 8 (Barn, Roofless barn)
                    new Rect(26,19,1,1),    //18    //Door 9 (Barn, Roofless barn)
                    new Rect(0,10,1,1),     //19    //Door 10 (Tent 1,2)
                    new Rect(0,14,1,1),     //20    //Chimney (Igloo 1 - 3)
                    new Rect(1,14,1,1),     //21    //patch (Igloo 1 - 3)
                    new Rect(2,14,1,1),     //22    //Door 11 (Igloo 1 - 3)
                    new Rect(16,3,3,3),     //23    //Watch tower roof (Watch tower)
                    new Rect(25,6,1,1),     //24    //Wall corner (Wall, Wall sideways, Gate closed, Gate opened)
                    new Rect(19,8,3,1),     //25    //Brass ring (Stake big)
                    new Rect(25,3,3,3),     //26    //Wall sideways (Stake big)
                    new Rect(19,13,2,1),    //27    //Brass ring (Stake medium)
            }
            );

    //----------------------------------------------------------------------------------------------//
    //                                        Enum Functions                                        //
    //----------------------------------------------------------------------------------------------//
    private Bitmap spriteSheet;
    private Rect[] structureMap;  // left = x pos, top = y pos, right = width, bottom = height;
    private Rect[] decorMap;  // left = x pos, top = y pos, right = width, bottom = height;
    private CollisionBox[][] collisionMap;

    StructureSet(int resourceId, Rect[] structureMap, CollisionBox[][] collisionMap, Rect[] decorMap) {
        options.inScaled = false;
        spriteSheet = BitmapFactory.decodeResource(GameActivity.getGameContext().getResources(), resourceId, options);
        this.structureMap = structureMap;
        this.collisionMap = collisionMap;
        this.decorMap = decorMap;
    }

    public Drawable getStructure(int id) {
        if (structureMap.length <= id || 0 > id) id = 0;
        Drawable drawable = new Drawable();

        drawable.bitmap = getScaledBitmap(Bitmap.createBitmap(spriteSheet, structureMap[id].left * DEFAULT_SIZE, structureMap[id].top * DEFAULT_SIZE, structureMap[id].right * DEFAULT_SIZE, structureMap[id].bottom * DEFAULT_SIZE));

        drawable.collisions = collisionMap[id];
        return drawable;
    }

    public Bitmap getDecor(int id) {
        if (decorMap.length <= id || 0 > id) id = 0;
        return getScaledBitmap(Bitmap.createBitmap(spriteSheet, decorMap[id].left * DEFAULT_SIZE, decorMap[id].top * DEFAULT_SIZE, decorMap[id].right * DEFAULT_SIZE, decorMap[id].bottom * DEFAULT_SIZE));
    }

    public float getStructureWidth(int id) {
        if (structureMap.length <= id || 0 > id) id = 0;
        return structureMap[id].right * SIZE;
    }

    public float getStructureHeight(int id) {
        if (structureMap.length <= id || 0 > id) id = 0;
        return structureMap[id].bottom * SIZE;
    }


}




