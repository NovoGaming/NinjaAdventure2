package com.demoncube.ninjaadventure.game.entities.characterEnums;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.demoncube.ninjaadventure.GameActivity;
import com.demoncube.ninjaadventure.R;
import com.demoncube.ninjaadventure.game.helpers.BitmapMethods;

public enum GameCharactersFaces implements BitmapMethods {

    // Players:
    NINJA_RED_FACE(R.drawable.charactersheet_ninja_red_faceset),
    NINJA_YELLOW_FACE(R.drawable.charactersheet_ninja_yellow_faceset),
    NINJA_GREEN_FACE(R.drawable.charactersheet_ninja_green_faceset),
    NINJA_BLUE_FACE(R.drawable.charactersheet_ninja_blue_faceset),
    NINJA_BLACK_FACE(R.drawable.charactersheet_ninja_black_faceset),

    // Enemies:
    SKELETON_FACE(R.drawable.charactersheet_skeleton_faceset),

    // Neutral NPCs:
    VILLAGER1_FACE(R.drawable.charactersheet_villager_1_faceset),
    VILLAGER2_FACE(R.drawable.charactersheet_villager_2_faceset),
    VILLAGER3_FACE(R.drawable.charactersheet_villager_3_faceset),
    VILLAGER4_FACE(R.drawable.charactersheet_villager_4_faceset),
    VILLAGER5_FACE(R.drawable.charactersheet_villager_5_faceset);

    private final Bitmap faceSheet;
    GameCharactersFaces(int resourceId) {
        options.inScaled = false;                                                                                                           //disables texture upscaling
        faceSheet = getScaledBitmap(BitmapFactory.decodeResource(GameActivity.getGameContext().getResources(), resourceId, options));       //Loads texture into variable
    }

    public Bitmap getFaceSheet() {
        return faceSheet;
    }
}
