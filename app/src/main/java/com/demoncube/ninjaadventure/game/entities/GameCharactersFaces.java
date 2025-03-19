package com.demoncube.ninjaadventure.game.entities;

import static com.demoncube.ninjaadventure.game.helpers.GameConst.Sprite.DEFAULT_SIZE;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.demoncube.ninjaadventure.GameActivity;
import com.demoncube.ninjaadventure.R;
import com.demoncube.ninjaadventure.game.helpers.BitmapMethods;
import com.demoncube.ninjaadventure.game.helpers.GameConst;

public enum GameCharactersFaces implements BitmapMethods {

    NINJA_RED_FACE(R.drawable.charactersheet_ninja_red_faceset),
    NINJA_YELLOW_FACE(R.drawable.charactersheet_ninja_yellow_faceset),
    NINJA_GREEN_FACE(R.drawable.charactersheet_ninja_green_faceset),
    NINJA_BLUE_FACE(R.drawable.charactersheet_ninja_blue_faceset),
    NINJA_BLACK_FACE(R.drawable.charactersheet_ninja_black_faceset),
    SKELETON_FACE(R.drawable.charactersheet_skeleton_faceset);

    private final Bitmap faceSheet;
    GameCharactersFaces(int resourceId) {
        options.inScaled = false;                                                                                                           //disables texture upscaling
        faceSheet = getScaledBitmap(BitmapFactory.decodeResource(GameActivity.getGameContext().getResources(), resourceId, options));       //Loads texture into variable
    }

    public Bitmap getFaceSheet() {
        return faceSheet;
    }
}
