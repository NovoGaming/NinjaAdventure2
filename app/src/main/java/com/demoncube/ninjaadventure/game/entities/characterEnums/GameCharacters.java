package com.demoncube.ninjaadventure.game.entities.characterEnums;

import static com.demoncube.ninjaadventure.game.helpers.GameConst.Sprite.DEFAULT_SIZE;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.demoncube.ninjaadventure.GameActivity;
import com.demoncube.ninjaadventure.R;
import com.demoncube.ninjaadventure.game.helpers.BitmapMethods;

public enum GameCharacters implements BitmapMethods {

    NINJA_RED(R.drawable.charactersheet_ninja_red_spritesheet),
    NINJA_YELLOW(R.drawable.charactersheet_ninja_yellow_spritesheet),
    NINJA_GREEN(R.drawable.charactersheet_ninja_green_spritesheet),
    NINJA_BLUE(R.drawable.charactersheet_ninja_blue_spritesheet),
    NINJA_BLACK(R.drawable.charactersheet_ninja_black_spritesheet),
    SKELETON(R.drawable.charactersheet_skeleton_spritesheet),
    VILLAGER1(R.drawable.charactersheet_villager_1_spritesheet),
    VILLAGER2(R.drawable.charactersheet_villager_2_spritesheet),
    VILLAGER3(R.drawable.charactersheet_villager_3_spritesheet),
    VILLAGER4(R.drawable.charactersheet_villager_4_spritesheet),
    VILLAGER5(R.drawable.charactersheet_villager_5_spritesheet);

    private final Bitmap spriteSheet;
    private Bitmap[][] sprites = new Bitmap[7][4];
    private Bitmap face;
    GameCharacters(int resourceId) {
        Log.d("GameCharacters", "GameCharacters has been called");
        options.inScaled = false;                                                                                         //disables texture upscaling
        spriteSheet = BitmapFactory.decodeResource(GameActivity.getGameContext().getResources(), resourceId, options);    //Loads texture into variable
        for (int j = 0; j < sprites.length; j++)
            for (int i = 0; i < sprites[j].length; i++) {
                sprites[j][i] = getScaledBitmap(Bitmap.createBitmap(spriteSheet, DEFAULT_SIZE * i, DEFAULT_SIZE * j, DEFAULT_SIZE, DEFAULT_SIZE));  //Splits sprites sheet into separate sprites}
            }
    }

    public Bitmap getSpriteSheet() {
        return spriteSheet;
    }

    public Bitmap getFace() {
        return face;
    }

    public Bitmap getSprite(int yPos,int xPos) {
        return sprites[yPos][xPos];
    }

}
